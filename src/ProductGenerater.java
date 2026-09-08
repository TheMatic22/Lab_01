import javax.swing.JFileChooser;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;

public class ProductGenerater {

    public static void main(String[] args) {
        SafeInputObj input = new SafeInputObj();
        ArrayList<Product> productList = new ArrayList<>();

        boolean addingMore = true;
        while (addingMore) {
            String id = input.getNonZeroLenString("Enter product ID");
            String name = input.getNonZeroLenString("Enter product name");
            String description = input.getNonZeroLenString("Enter product description");
            double cost = input.getRangedDouble("Enter product cost", 0, 100000);

            Product p = new Product(name, description, id, cost);
            productList.add(p);

            addingMore = input.getYNConfirm("Add another product?");
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Product data file");
        int result = chooser.showSaveDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting without saving.");
            return;
        }

        Path filePath = chooser.getSelectedFile().toPath();

        try (PrintWriter writer = new PrintWriter(filePath.toFile())) {
            for (Product p : productList) {
                writer.println(p.toCSV());
            }
            System.out.println("Saved " + productList.size() + " products to " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
