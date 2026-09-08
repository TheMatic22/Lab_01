import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Widget", "A small widget", "PR001", 9.99);
    }

    @Test
    void testFullConstructor() {
        assertEquals("Widget", product.getName());
        assertEquals("A small widget", product.getDescription());
        assertEquals("PR001", product.getID());
        assertEquals(9.99, product.getCost());
    }

    @Test
    void testOverloadedConstructorNoDescription() {
        Product p = new Product("Gadget", "PR002", 19.99);
        assertEquals("Gadget", p.getName());
        assertEquals("", p.getDescription());
        assertEquals("PR002", p.getID());
        assertEquals(19.99, p.getCost());
    }

    @Test
    void testCopyConstructor() {
        Product copy = new Product(product);
        assertEquals(product, copy);
    }

    @Test
    void testSetName() {
        product.setName("Gizmo");
        assertEquals("Gizmo", product.getName());
    }

    @Test
    void testSetDescription() {
        product.setDescription("An updated description");
        assertEquals("An updated description", product.getDescription());
    }

    @Test
    void testSetCostValid() {
        product.setCost(14.99);
        assertEquals(14.99, product.getCost());
    }

    @Test
    void testSetCostNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> product.setCost(-5.00));
    }

    @Test
    void testToCSV() {
        assertEquals("PR001,Widget,A small widget,9.99", product.toCSV());
    }

    @Test
    void testToJSON() {
        String expected = "{\"ID\":\"PR001\",\"name\":\"Widget\",\"description\":\"A small widget\",\"cost\":9.99}";
        assertEquals(expected, product.toJSON());
    }

    @Test
    void testToXML() {
        String expected = "<product><ID>PR001</ID><name>Widget</name>"
                + "<description>A small widget</description><cost>9.99</cost></product>";
        assertEquals(expected, product.toXML());
    }

    @Test
    void testEqualsSameValues() {
        Product other = new Product("Widget", "A small widget", "PR001", 9.99);
        assertEquals(product, other);
    }

    @Test
    void testEqualsDifferentValues() {
        Product other = new Product("Widget", "A small widget", "PR999", 9.99);
        assertNotEquals(product, other);
    }
}