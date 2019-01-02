import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class runs ID3 algorithm (Decision Tree).
 * With a given training data, it predicts the
 * classification of given attributes' values set.
 */
public class ID3 {
    /* Properties */
    private Data data;
    private TestData testData;
    public double algPredictionPercentage;

    /* Constructors */
    /**
     * Constructs a new ID3 instance.
     * @param dataObj the training data
     * @param td the test data
     */
    public ID3(Data dataObj, TestData td){
        data = dataObj;
        testData = td;
    }

    /* Methods */
    /**
     * Runs ID3 algorithm and creates a map of the results.
     * That is, it matches a predicted classification to the
     * row index number in the test file.
     */
    public Map<Integer,String> algorithm(){
        // Build DTL tree from the train data
        //take the classification of the first row in train data as the default
        // classification of DTL's first call.
        String defClass = data.getTrainRows().get(0).getClassification();
        Node tree = DTL(data.getTrainRows(), data.getPossibleAttributes(), defClass);

        // Print tree
        printTree(tree);

        // Predict classifications
        // for each row in the test file, find its classification
        Map<Integer,String> predictedClassifications = new HashMap<>();
        for(Row testRow: testData.getTestRows()){
            String predictedClass = predictClass(testRow, tree);
            predictedClassifications.put(testRow.getRowNumber(), predictedClass);
        }

        return predictedClassifications;
    }

    /**
     * A recursive function that builds the tree of training data,
     * from which the algorithm will predict the classification of each test row.
     * @param examples the training data to build a tree or a subtree from.
     *                 At the first call, examples = training data
     * @param attributes the attributes names & their values that were'nt already
     *                   dealt with in the tree.
     *                   At the first call, attributes = all attributes from training data
     * @param defaultClass the most common classification in the tree. Calculated with mod function.
     *                     At the first call, defaultClass = the first row's classification from
     *                     the training data.
     * @return a node that represents a subtree or a leaf.
     */
    Node DTL(ArrayList<Row> examples, ArrayList<String>[] attributes, String defaultClass){
        // Stop conditions:
        //      - if there are no more trin rows - return defaultClass.
        //      - if all the train rows that we got as a parameter to the function,
        //        have the same classification - return this classification.
        //      - if the attributes list is empty, but there are still some different
        //        classifications - return Mod(examples)

        // Step 1: choose the ideal attribute -> best
        //         - Entropy
        //         - Gain
        //         - take the attribute with the biggest Gain value

        // Step 2: create a new node with the aatribute name, best -> tree

        // Step 3:
        return null;
    }

    /**
     * calculates the most common classification in a group of training rows.
     * @param examples the training rows
     * @return a leaf node with the calculated classification
     */
    Node Mod(ArrayList<Row> examples){
        return null;
    }

    double Gain(){
        return 0;
    }

    double Entropy(){
        return 0;
    }

    String predictClass(Row testRow, Node tree){
        return null;
    }

    void printTree(Node tree){

    }
}

class Node{

}