import java.util.ArrayList;
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
        for(Row testRow: testData.getTestRows()){
            // keep the hamming distances of the k chosen train rows,
            // so that we can compare distances and change these 2 lists
            // whenever necessary.
            ArrayList<rowInfo> kClosestRowsInfo = new ArrayList<>();
            // calculate hamming distances between this test row to each row in trainRows
            for(Row trainRow: data.getTrainRows()){
                int hammingDistance = findHammingDistance(testRow,trainRow);
                // if kClosestRowsInfo is not yet full
                if(kClosestRowsInfo.size() < k){
                    rowInfo rowInformation = new rowInfo(hammingDistance, trainRow.getClassification());
                    kClosestRowsInfo.add(rowInformation);
                }
                // if kClosestRowsInfo is full, we prioritize by choosing
                // the k smallest hammingDistances
                else{
                    for(rowInfo ri: kClosestRowsInfo){
                        if(ri.hammingDistance > hammingDistance){
                            kClosestRowsInfo.remove(ri);
                            rowInfo rowInformation = new rowInfo(hammingDistance, trainRow.getClassification());
                            kClosestRowsInfo.add(rowInformation);
                            break;
                        }
                    }
                }
            }
            // take the k closest rows to the test row and check which classification
            // appear the most.
            // PredictedClassification is the predicted classification for this test row.
            String predictedClass = predictClassification(kClosestRowsInfo);
            predictedClassifications.put(testRow.getRowNumber(), predictedClass);
        }

        return predictedClassifications;
    }

    /**
     * Calculates the Hamming distance between 2 given rows.
     * @param testRow first row
     * @param trainRow second row
     * @return the Hamming distance between these 2 rows.
     */
    public int findHammingDistance(Row testRow, Row trainRow){
        int hammingDistance = 0;
        return hammingDistance;
    }

    /**
     * Predicts the classification of a test row by a given
     * k-closest-rows array.
     * @param kClosestRowsInfo the array to find the most
     *                        frequent classification value in.
     * @return the predicted classification value.
     */
    public String predictClassification(ArrayList<rowInfo> kClosestRowsInfo){
        // a map that holds the amount of each classification value in kClosestRowsInfo array
        //      String - key - the classification value
        //      Integer - value - the amount of time this classification value appears kClosestRowsInfo array
        Map<String, Integer> classValAmount = new HashMap<>();
        for(rowInfo ri: kClosestRowsInfo){
            Integer val = classValAmount.get(ri.classification);
            if (val == null) classValAmount.put(ri.classification, 1);
            else classValAmount.put(ri.classification, val + 1);
        }

        // check which classification value appears mostly
        int maxVal = 0;
        String predictedClass = null;
        for (Map.Entry<String, Integer> entry : classValAmount.entrySet()) {
            if(maxVal < entry.getValue()){
                maxVal = entry.getValue();
                predictedClass = entry.getKey();
            }
        }

        return predictedClass;
    }
}


/**
 * This class holds the classification of a train row,
 * and the Hamming distance between a test row and this train row.
 * The goal of this class is to ease the matching between a
 * train-row's classification and its Hamming-distance from the test-row,
 * so that if a smaller Hamming distance was found, we'd be able to change
 * it to the smaller one along with its matching classification.
 */
class rowInfo{
    int hammingDistance;
    String classification;

    /* Constructor */
    /**
     * Constructs a new rowInfo instance.
     * @param hammingDis hamming distance from a specific train row
     * @param classificationVal classification of this train row
     */
    public rowInfo(int hammingDis, String classificationVal){
        hammingDistance = hammingDis;
        classification = classificationVal;
    }
}