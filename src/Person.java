import java.util.Calendar;
public class Person {

    private String firstName;
    private String lastName;
    private String ID;      // should never change once assigned
    private String title;   // prefix: Mr., Mrs., Ms., Prof., Dr., Hon., etc.
    private int YOB;         // year of birth, expected range 1940 - 2010

    public Person(String firstName, String lastName, String ID, String title, int YOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.title = title;
        this.YOB = YOB;
    }

    public Person(String firstName, String lastName, String ID, int YOB) {
        this(firstName, lastName, ID, "", YOB);
    }

    public Person(Person other) {
        this(other.firstName, other.lastName, other.ID, other.title, other.YOB);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    // No setID(): the ID should never change once a Person is created.
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getYOB() {
        return YOB;
    }

    public void setYOB(int YOB) {
        if (YOB < 1940 || YOB > 2010) {
            throw new IllegalArgumentException("Year of birth must be between 1940 and 2010.");
        }
        this.YOB = YOB;
    }


    public String fullName() {
        return firstName + " " + lastName;
    }

    public String formalName() {
        return title + " " + fullName();
    }


    public String getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return getAge(currentYear);
    }

    public String getAge(int year) {
        int age = year - YOB;
        return String.valueOf(age);
    }

    /**
     * Converts this Person into a comma-separated value (CSV) record,
     * suitable for writing to a text file.
     *
     * @return a CSV String in the order ID,firstName,lastName,title,YOB
     */
    public String toCSV() {
        return ID + "," + firstName + "," + lastName + "," + title + "," + YOB;
    }

    public String toJSON() {
        return "{"
                + "\"firstName\":\"" + firstName + "\","
                + "\"lastName\":\"" + lastName + "\","
                + "\"ID\":\"" + ID + "\","
                + "\"title\":\"" + title + "\","
                + "\"YOB\":" + YOB
                + "}";
    }
    public String toXML() {
        return "<person>"
                + "<firstName>" + firstName + "</firstName>"
                + "<lastName>" + lastName + "</lastName>"
                + "<ID>" + ID + "</ID>"
                + "<title>" + title + "</title>"
                + "<YOB>" + YOB + "</YOB>"
                + "</person>";
    }
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", YOB=" + YOB +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (YOB != person.YOB) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        if (!ID.equals(person.ID)) return false;
        return title.equals(person.title);
    }
}
