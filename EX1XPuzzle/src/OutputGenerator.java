import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Generates an output file in a given directory path.
 * This path was originally chosen to be the input file directory.
 */
public class OutputGenerator {
    /**
     * Generates an output file.
     * @param output the output data to add to the file.
     */
    public static void generateOutputFile(Output output){
        String filePath = "output.txt";
        try {
            FileWriter fw = new FileWriter(filePath);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(output.getPath());
            pw.print(" ");
            pw.print(output.getNodesAmount());
            pw.print(" ");
            pw.print(output.getCost());
            pw.close();
        }catch (IOException e){
            System.out.println("Error writing file '" + filePath + "'");
        }
    }
}
