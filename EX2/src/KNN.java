import java.util.HashMap;
import java.util.Map;

/**
 * This class runs KNN algorithm.
 * With a given training data, it predicts the
 * classification of given attributes' values set.
 */
public class KNN {
    /* Properties */
    private int k;
    private Data data;
    private TestData testData;

    /* Constructors */
    /**
     * Constructs a new KNN instance.
     * @param dataObj the training data
     * @param td the test data
     */
    public KNN(Data dataObj, TestData td){
        data = dataObj;
        testData = td;
    }

    /* Methods */

    /**
     * Runs KNN algorithm and creates a map of the results.
     * That is, it match a predicted classification to the
     * row index number in the test file.
     */
    public Map<Integer,String> algorithm(){
        // booting k
        k = 5;
        // for each row in the test file, find its classification
        Map<Integer,String> predictedClassifications = new HashMap<>();
        // calculate hamming distances between this test row to each row in trainRows
        // take the k closest rows to the test row and check which classification
        // appear the most.
        // that's the predicted classification for this test row.
        return predictedClassifications;
    }
}
