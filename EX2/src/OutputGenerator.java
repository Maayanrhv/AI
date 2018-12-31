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

            // create first line
            pw.print("Num\tDT\tKNN\tNaiveBayes");
            pw.print("\n");
            // create the rest of the file
            for(int i = 1; i<output.getRowCount(); i++){
                pw.print(i + "\t" + output.getID3Predictions().get(i)
                + "\t" + output.getKNNPredictions().get(i) + "\t"
                        + output.getNaiveBayesPredictions().get(i));
                pw.print("\n");
            }
            pw.print("\t"+ output.getID3PredictionPercentage() + "\t" +
                    output.getKNNPredictionPercentage() + "\t" + output.getNaiveBayesPredictionPercentage());
            pw.close();
        }catch (IOException e){
            System.out.println("Error writing file '" + filePath + "'");
        }
    }
}