import protagonist.MC;

import java.io.*;

public class Main {
    public static void main(String[] args) {
//        //test MC single instance
//        MC hektor = MC.getMC();
//        System.out.println(hektor.hp);
//        System.out.println(hektor.mp);
//        System.out.println(hektor.energy);
//        System.out.println(hektor.exp);

        // The name of the file to open.
        String fileName = "src//story.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}

