import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductReader {

    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select a Product data file");
        int result = chooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting.");
            return;
        }

        Path filePath = chooser.getSelectedFile().toPath();

        ArrayList<Product> productList = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0].trim();
                String name = fields[1].trim();
                String description = fields[2].trim();
                double cost = Double.parseDouble(fields[3].trim());

                Product p = new Product(name, description, id, cost);
                productList.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Display the table from the ArrayList
        String header = String.format("%-10s%-16s%-24s%-8s", "ID#", "Name", "Description", "Cost");
        System.out.println(header);
        System.out.println("=".repeat(58));

        for (Product p : productList) {
            String row = String.format("%-10s%-16s%-24s%-8.2f",
                    p.getID(), p.getName(), p.getDescription(), p.getCost());
            System.out.println(row);
        }

        pipe.close();
    }
}