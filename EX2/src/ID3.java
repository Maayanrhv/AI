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
        Node tree = DTL(data.getTrainRows(), data.getPossibleAttributes(), new Node(defClass));

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
     * @param defaultClass the most common classification in the tree. Calculated with mode function.
     *                     At the first call, defaultClass = the first row's classification from
     *                     the training data.
     * @return a node that represents a subtree or a leaf.
     */
    Node DTL(ArrayList<Row> examples, ArrayList<String>[] attributes, Node defaultClass){
        // Stop conditions:
        //      - if there are no more train rows - return defaultClass.
        if (examples.isEmpty()){ return defaultClass;}
        //      - if all the train rows that we got as a parameter to the function,
        //        have the same classification - return this classification.
        boolean allEqual = true;
        String aClass = examples.get(0).getClassification();
        for(Row row: examples){
            if(!row.getClassification().equals(aClass)){
                allEqual = false;
                break;
            }
        }
        if(allEqual){ return new Node(aClass); }
        //      - if the attributes list is empty, but there are still some different
        //        classifications - return Mode(examples)
        if(attributes == null){ return Mode(examples); }


        // Step 1: choose the ideal attribute -> best
        //         - Entropy
        //         - Gain
        //         - take the attribute with the biggest Gain value
        double entropy = Entropy();
        String best = Gain(Entropy());


        Node tree = null;
        // Step 2: for each attribute value i in best do:
        for(String vi: data.getPossibleAttributes()[data.findPossibleAttsPositionByAttName(best)]) {
            //         - take all the train rows where their attribute name (best type)
            //           is valued by vi -> iExamples
            ArrayList<Row> iExamples = new ArrayList<>();
            for(Row row: examples) { //TODO: is it all row in examples or all row in train data?
                if(row.getValues().get(best).equals(vi)){
                    iExamples.add(row);
                }
            }
            // calculate attributes without best
            ArrayList<String>[] attributesWithoutBest = CalcAttsWithoutBest(attributes, best);
            //         - recursive call to DTL(iExamples, attributes\{best}, Mode(examples)) -> subtree
            Node subtree = DTL(iExamples, attributesWithoutBest, Mode(examples));
            //         - create a new node with the attribute name, best and subtree -> tree
            //           subtree is a branch of tree with attribute value - vi
            Map<String, Node> branches = new HashMap<>();
            branches.put(vi, subtree);
            tree = new Node(best, branches);
        }


        // Step 3: return tree
        return tree;
    }

    //TODO: complete this func
    ArrayList<String>[] CalcAttsWithoutBest(ArrayList<String>[] attributes, String best){
        ArrayList<String>[] newAtts = new ArrayList[attributes.length - 1];
        for(int i=0; i<attributes.length; i++){

        }
        return newAtts;
    }

    /**
     * calculates the most common classification in a group of training rows.
     * @param examples the training rows
     * @return a leaf node with the calculated classification
     */
    Node Mode(ArrayList<Row> examples){
        return null;
    }

    String Gain(){
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
    /* Properties */
    String attributeName;
    Map<String, Node> attributeValuesNodes;
    String classification;

    /* Constructors */
    /**
     * constructs a subtree node (not a leaf node).
     * @param attributeName the attribute name (type) of the node
     * @param nodes sons of this node (subtrees to this tree)
     */
    Node(String attributeName, Map<String, Node> nodes){
        this.attributeName = attributeName;
        this.attributeValuesNodes = nodes;
        this.classification = null;
    }

    /**
     * constructs a leaf node.
     * @param classification of the leaf
     */
    Node(String classification){
        this.classification = classification;
        this.attributeName = null;
        this.attributeValuesNodes = null;
    }
}