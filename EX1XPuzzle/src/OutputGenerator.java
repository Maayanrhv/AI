import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Generates an output file in a given directory path.
 * This path was originally chosen to be the input file directory.
 */
public class OutputGenerator {
    /**
     * Generates an output file.
     * @param output the output data to add to the file.
     * @param filePath the output directory
     */
    public static void generateOutputFile(Output output, String filePath){
        try {
            FileWriter fw = new FileWriter(filePath+"\\output.txt");
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
