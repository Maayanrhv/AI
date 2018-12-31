/**
 * The main class.
 * Extract the input data from input file - train.txt.
 * Calls for a manager class to execute the algorithms.
 */
public class java_ex2 {
    /**
     * The main function that sends the data to be processed,
     * and then sends it to the manager class that will use it to
     * calculate the classification in the test file.
     */
    public static void main(String[] args) {
        Data data = TrainDataExtractor.extractAndProcess();
        PredictionManager manager = new PredictionManager(data);
        manager.run();
    }
}
