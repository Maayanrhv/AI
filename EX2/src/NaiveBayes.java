import java.util.HashMap;
import java.util.Map;

/**
 * This class runs NaiveBayes algorithm.
 * With a given training data, it predicts the
 * classification of given attributes' values set.
 */
public class NaiveBayes {
    /* Properties */
    private Data data;
    private TestData testData;

    /* Constructors */
    /**
     * Constructs a new NaiveBayes instance.
     * @param dataObj the training data
     * @param td the test data
     */
    public NaiveBayes(Data dataObj, TestData td){
        data = dataObj;
        testData =td;
    }

    /* Methods */
    /**
     * Runs NaiveBayes algorithm and creates a map of the results.
     * That is, it match a predicted classification to the
     * row index number in the test file.
     */
    public Map<Integer,String> algorithm(){
        // for each row in the test file, find its classification
        Map<Integer,String> predictedClassifications = new HashMap<>();

        return predictedClassifications;
    }
}
