import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Calendar;

public class PersonReader {

    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select a Person data file");
        int result = chooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting.");
            return;
        }

        Path filePath = chooser.getSelectedFile().toPath();

        String header = String.format("%-10s%-12s%-12s%-8s%-6s", "ID#", "Firstname", "Lastname", "Title", "YOB");
        System.out.println(header);
        System.out.println("=".repeat(48));

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0].trim();
                String firstName = fields[1].trim();
                String lastName = fields[2].trim();
                String title = fields[3].trim();
                String yob = fields[4].trim();

                String row = String.format("%-10s%-12s%-12s%-8s%-6s", id, firstName, lastName, title, yob);
                System.out.println(row);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        pipe.close();
    }
}
