import java.io.*;

/**
 * This class takes the data from train input file and creates a Data object from it.
 */
public class FileExtractor {
    /**
     * Extracts given input from file a Data object.
     */
    public static Data extractAndProcess(){
        String fileName = "train.txt";
        Data data = new Data();

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
            return data;
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