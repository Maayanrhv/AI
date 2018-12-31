import java.util.HashMap;
import java.util.Map;

/**
 * The Output properties at the end of the prediction of all algorithms.
 * This class receives the data required for the output file each
 * algorithm predicted the classification of each row in test file.
 */
public class Output {
    /* Properties */
    private Map<Integer,String> KNNPredictions;
    private Map<Integer,String> NaiveBayesPredictions;
    private Map<Integer,String> ID3Predictions;
    private int rowCount;

    /* Constructor */
    public Output(Map<Integer,String> KNN, Map<Integer,String> NaiveBayes, Map<Integer,String> ID3){
        KNNPredictions = KNN;
        NaiveBayesPredictions = NaiveBayes;
        ID3Predictions = ID3;
        rowCount = KNN.size();
    }

    /* Getters % Setters*/
    /**
     * @return KNN classification predictions
     */
    public Map<Integer, String> getKNNPredictions() {
        return KNNPredictions;
    }
    /**
     * @return NaiveBayes classification predictions
     */
    public Map<Integer, String> getNaiveBayesPredictions() {
        return NaiveBayesPredictions;
    }
    /**
     * @return ID3 classification predictions
     */
    public Map<Integer, String> getID3Predictions() {
        return ID3Predictions;
    }
    /**
     * @return rowCount
     */
    public int getRowCount() {
        return rowCount;
    }
}