import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> folks = new ArrayList<String>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\persondata.txt");

        boolean done = false;

        String personrec = "";
        String ID = "";
        String FirstName = "";
        String LastName = "";
        String title = "";
        int yearOfBirth = 1940 - 2010;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter Your ID [6-Digits]");
            FirstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            LastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            title = SafeInput.getNonZeroLenString(in, "Enter Professional Title");
            yearOfBirth = SafeInput.getRangedInt(in, "Enter Year of Birth", 100, 9999);

            personrec = ID + " , " + FirstName + " , " + LastName + " , " + title + " , " + yearOfBirth;
            folks.add(personrec);

            done = SafeInput.getYNConfirm(in, "Are You Finished");

        } while (!done);

        for (String p : folks)
            System.out.println(p);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : folks)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

