import java.io.*;

/**
 * This class takes data from input file and turns each line into a string.
 * Each string is added to a String array to be sent to the game manager.
 */
public class FileExtractor {
    /**
     * Extracts given input from file to strings
     */
    public static String[] extract(){
        String fileName = "input.txt";
        String[] input = new String[3];
        // this will reference one line at a time
        String line;
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
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
        }
        return null;
    }
}
