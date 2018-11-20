import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class takes data from input file and turns each line into a string.
 * Each string is added to a String array to be sent to the game manager.
 */
public class FileExtractor {
    /**
     * Extracts given input from file to strings
     * @param fileName the name of the file to open
     */
    public static String[] extract(String fileName){
        String[] input = new String[3];
        // this will reference one line at a time
        String line;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0;
            while((line = bufferedReader.readLine()) != null) {
                input[i] = line;
                i++;
            }
            bufferedReader.close();
            return input;
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            // ex.printStackTrace();
        }
        return null;
    }
}
