import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Jane", "Doe", "P001", "Ms.", 1990);
    }

    @Test
    void testFullConstructor() {
        assertEquals("Jane", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals("P001", person.getID());
        assertEquals("Ms.", person.getTitle());
        assertEquals(1990, person.getYOB());
    }

    @Test
    void testOverloadedConstructorNoTitle() {
        Person p = new Person("John", "Smith", "P002", 1985);
        assertEquals("John", p.getFirstName());
        assertEquals("Smith", p.getLastName());
        assertEquals("P002", p.getID());
        assertEquals("", p.getTitle());
        assertEquals(1985, p.getYOB());
    }

    @Test
    void testCopyConstructor() {
        Person copy = new Person(person);
        assertEquals(person, copy);
    }

    @Test
    void testSetFirstName() {
        person.setFirstName("Janet");
        assertEquals("Janet", person.getFirstName());
    }

    @Test
    void testSetLastName() {
        person.setLastName("Jones");
        assertEquals("Jones", person.getLastName());
    }

    @Test
    void testSetTitle() {
        person.setTitle("Dr.");
        assertEquals("Dr.", person.getTitle());
    }

    @Test
    void testSetYOBValid() {
        person.setYOB(2000);
        assertEquals(2000, person.getYOB());
    }

    @Test
    void testSetYOBTooLowThrows() {
        assertThrows(IllegalArgumentException.class, () -> person.setYOB(1939));
    }

    @Test
    void testSetYOBTooHighThrows() {
        assertThrows(IllegalArgumentException.class, () -> person.setYOB(2011));
    }

    @Test
    void testFullName() {
        assertEquals("Jane Doe", person.fullName());
    }

    @Test
    void testFormalName() {
        assertEquals("Ms. Jane Doe", person.formalName());
    }

    @Test
    void testGetAgeForSpecifiedYear() {
        assertEquals("30", person.getAge(2020));
    }

    @Test
    void testGetAgeCurrentYear() {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int expectedAge = currentYear - 1990;
        assertEquals(String.valueOf(expectedAge), person.getAge());
    }

    @Test
    void testToCSV() {
        assertEquals("P001,Jane,Doe,Ms.,1990", person.toCSV());
    }

    @Test
    void testToJSON() {
        String expected = "{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"ID\":\"P001\",\"title\":\"Ms.\",\"YOB\":1990}";
        assertEquals(expected, person.toJSON());
    }

    @Test
    void testToXML() {
        String expected = "<person><firstName>Jane</firstName><lastName>Doe</lastName>"
                + "<ID>P001</ID><title>Ms.</title><YOB>1990</YOB></person>";
        assertEquals(expected, person.toXML());
    }

    @Test
    void testEqualsSameValues() {
        Person other = new Person("Jane", "Doe", "P001", "Ms.", 1990);
        assertEquals(person, other);
    }

    @Test
    void testEqualsDifferentValues() {
        Person other = new Person("Jane", "Doe", "P999", "Ms.", 1990);
        assertNotEquals(person, other);
    }
}