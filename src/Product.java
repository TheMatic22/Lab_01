
public class Product {

    private String name;
    private String description;
    private String ID;      // should never change once assigned
    private double cost;


    public Product(String name, String description, String ID, double cost) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.cost = cost;
    }

    public Product(String name, String ID, double cost) {
        this(name, "", ID, cost);
    }

    public Product(Product other) {
        this(other.name, other.description, other.ID, other.cost);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        this.cost = cost;
    }

    public String toCSV() {
        return ID + "," + name + "," + description + "," + cost;
    }

    public String toJSON() {
        return "{"
                + "\"ID\":\"" + ID + "\","
                + "\"name\":\"" + name + "\","
                + "\"description\":\"" + description + "\","
                + "\"cost\":" + cost
                + "}";
    }

    public String toXML() {
        return "<product>"
                + "<ID>" + ID + "</ID>"
                + "<name>" + name + "</name>"
                + "<description>" + description + "</description>"
                + "<cost>" + cost + "</cost>"
                + "</product>";
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.cost, cost) != 0) return false;
        if (!name.equals(product.name)) return false;
        if (!description.equals(product.description)) return false;
        return ID.equals(product.ID);
    }
}
