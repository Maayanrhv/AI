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
        // take the classification of the first row in train data as the default
        // classification of DTL's first call.
        String defClass = data.getTrainRows().get(0).getClassification();
        Node tree = DTL(data.getTrainRows(), data.getAttributeRelation(), new Node(defClass));

        // Print tree
        printTree(tree);

        // Predict classifications
        // for each row in the test file, find its classification
        Map<Integer,String> predictedClassifications = new HashMap<>();
        for(Row testRow: testData.getTestRows()){
            String predictedClass = predictClass(testRow, tree);
            predictedClassifications.put(testRow.getRowNumber(), predictedClass);
        }

        // calculate ID3's accuracy percentage.
        percentageCalc(predictedClassifications);
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
    Node DTL(ArrayList<Row> examples, AttributeRelation attributes, Node defaultClass){
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
        if(attributes.getAttributesPositions().isEmpty()){ return Mode(examples); }


        // Step 1: choose the ideal attribute name -> best
        //         - Gain(examples, attName) using Entropy(examples)
        //         - take the attribute with the biggest Gain value
        String best = null;
        if(attributes.getAttributesPositions().size() == 1){
            best = attributes.getAttributesPositions().get(0);
        }
        else {
            double gain;
            double maxGain = 0;
            for (Map.Entry<Integer, String> attName : attributes.getAttributesPositions().entrySet()) {
                gain = Gain(examples, attName.getValue());
                if (maxGain < gain) {
                    maxGain = gain;
                    best = attName.getValue();
                }
            }
        }

        Node tree = null;
        // Step 2: for each attribute value vi in best do:
        for(String vi: data.getAttributeRelation().getPossibleAttributes()
                [data.getAttributeRelation().findPossibleAttsPositionByAttName(best)]) {
            //         - take all the train rows where their attribute name (best type)
            //           is valued by vi -> iExamples
            ArrayList<Row> iExamples = new ArrayList<>();
            for(Row row: examples) {
                if(row.getValues().get(best).equals(vi)){
                    iExamples.add(row);
                }
            }

            // calculate attributes without best
            AttributeRelation attributesWithoutBest = CalcAttsWithoutBest(attributes, best);
            //         - recursive call to DTL(iExamples, attributes\{best}, Mode(examples)) -> subtree
            Node subtree = DTL(iExamples, attributesWithoutBest, Mode(examples));
            //         - create a new node with the attribute name, best and subtree -> tree
            //           subtree is a branch of tree with attribute value - vi
            if(tree == null) {
                Map<String, Node> branches = new HashMap<>();
                branches.put(vi, subtree);
                tree = new Node(best, branches);
            }
            else{
                tree.attributeValuesNodes.put(vi,subtree);
            }
        }

        // Step 3: return tree
        return tree;
    }

    /**
     * creates a new AttributeRelation object that is equal to the given
     * AttributeRelation object, except for best attribute name.
     * @param attributes
     * @param best
     * @return
     */
    AttributeRelation CalcAttsWithoutBest(AttributeRelation attributes, String best){
        // create a new AttributeRelation instance to return
        AttributeRelation newAtts = new AttributeRelation();
        ArrayList<String>[] possibleAtts = new ArrayList[attributes.getPossibleAttributes().length - 1];
        Map<Integer,String> newAttsPoses = new HashMap<>();
        // find the position of best in attributes
        int posToExclude = attributes.findPossibleAttsPositionByAttName(best);
        // add attributes' names and values to newAttsPoses and possibleAtts, excluding best and its values
        int countOfNewAtts = 0;
        for(int i=0; i<attributes.getAttributesPositions().size(); i++){
            // if i in not the position of best, then:
            //      - add the att name's values to possibleAtts
            //      - add the att name to newAttsPoses, where the position is the
            //        position of its values in possibleAtts
            if(i != posToExclude){
                possibleAtts[countOfNewAtts] = attributes.getPossibleAttributes()[i];
                newAttsPoses.put(countOfNewAtts,attributes.getAttributesPositions().get(i));
                countOfNewAtts++;
            }
            // else, do nothing (don't add best and its values to newAtts
        }
        // add newAttsPoses and possibleAtts to newAtts and return it
        newAtts.setPossibleAttributes(possibleAtts);
        newAtts.setAttributesPositions(newAttsPoses);
        return newAtts;
    }

    /**
     * calculates the most common classification in a group of training rows.
     * @param examples the training rows
     * @return a leaf node with the calculated classification
     */
    Node Mode(ArrayList<Row> examples){
        // create a new map that holds how many rows there are with a specific classification value
        Map<String, Integer> amountOfClass = new HashMap<>();
        for(Row row: examples){
            // if this row's classification does not exist in amountOfClass's keys,
            // then add it as a new key with value of 1
            if(!amountOfClass.containsKey(row.getClassification())){
                amountOfClass.put(row.getClassification(), 1);
            }
            // otherwise, add +1 to the key's value where key = this row's classification
            else{
                amountOfClass.put(row.getClassification(), amountOfClass.get(row.getClassification()) + 1);
            }
        }
        // go through amountOfClass map, and check which classification has the most repeats
        String mostCommonClass = "";
        int max = 0;
        for(Map.Entry<String, Integer> classVal : amountOfClass.entrySet()){
            if(max < classVal.getValue()){
                max = classVal.getValue();
                mostCommonClass = classVal.getKey();
            }
        }
        return new Node(mostCommonClass);
    }

    /**
     * calculates the gain of examples with a given attribute name
     * @param examples list of train data rows
     * @param attName attribute name (type)
     * @return the calculated gain
     */
    double Gain(ArrayList<Row> examples, String attName){
        // A - attribute name
        //      Ai - each attribute value of type A
        // S - examples, list of train rows
        //      Sv - a sub-list of train rows in examples,
        //           where all attributes values of type A are equal to Ai

        //calculate sigma: sum of((|Sv|/|S|)* Entropy(S))
        double sigma = 0;
        //find A's position
        int posA = data.getAttributeRelation().findPossibleAttsPositionByAttName(attName);
        double entropyOfS = Entropy(examples);
        for(String attVal: data.getAttributeRelation().getPossibleAttributes()[posA]){
            // create Sv list
            ArrayList<Row> Sv = new ArrayList<>();
            for (Row row: examples){
                if(row.getValues().get(attName).equals(attVal)){
                    Sv.add(row);
                }
            }

            sigma +=  ((double)(Sv.size()) * Entropy(Sv) / (double)(examples.size()));
        }

        //calculate gain: Entropy(S) - sigma
        return (entropyOfS - sigma);
    }

    /**
     * calculates the entropy of each classification value in a
     * list of train data rows and sums them up.
     * @param examples list of train data rows
     * @return the summed-up entropy of each classification value.
     */
    double Entropy(ArrayList<Row> examples){
        double entropy = 0;
        // keep all possible classification values in a list.
        int classesAmount = data.getPossibleClassifications().size();
        // an array that keeps the amount of rows in examples that have a classification value
        double[] eachClassValAmount = new double[classesAmount];
        // classVal will contain each possible classification value
        String classVal;
        // count how many appearances of each classification value there are in examples
        for(Row row: examples){
            for(int i=0; i<classesAmount; i++){
                classVal = data.getPossibleClassifications().get(i);
                if(row.getClassification().equals(classVal)){
                    eachClassValAmount[i]++;
                }
            }
        }
        // for each classification p, calculate: -p * log(p)
        // add this calculation to the final entropy value
        for(int i=0; i<classesAmount; i++){
            eachClassValAmount[i] = eachClassValAmount[i]/examples.size();
            if(eachClassValAmount[i] > 0) {
                entropy -= eachClassValAmount[i] * (Math.log(eachClassValAmount[i]) / (Math.log(2)));
            }
        }

        return entropy;
    }

    /**
     * Predicts the classification of a given test row.
     * This is a recursive function that runs through the data tree
     * and searches for the branch that contains the test row's attribute values.
     * @param testRow the row to predict its classification
     * @param tree the DTL tree to run through
     * @return the predicted classification of the given test row
     */
    String predictClass(Row testRow, Node tree){
        // Stop condition: if the node's classification isn't null - return it
        if(tree.classification != null){
            return tree.classification;
        }

        // otherwise, call recursively to this func with the attributes values
        // of the given row until reaching a classification
        String attName = tree.attributeName;
        String attVal = testRow.getValues().get(attName);
        return predictClass(testRow, tree.attributeValuesNodes.get(attVal));
    }

    void printTree(Node tree){
        TreeOutputGenerator.generateOutputFile(tree);
    }

    /**
     * Calculates this algorithm's amount of correct predictions
     * in relation to all predictions (the amount of test rows).
     * The calculated value is put in algPredictionPercentage property.
     * @param predictedClassifications the predicted classifications
     */
    public void percentageCalc(Map<Integer,String> predictedClassifications){
        int testRowsAmount = testData.getTestRows().size();
        int corrPredictAmount = 0;
        for (Map.Entry<Integer, String> aRow : predictedClassifications.entrySet()) {
            String prediction = aRow.getValue();
            String corrClass = testData.getTestRows().get(aRow.getKey()-1).getClassification();
            if(prediction.equals(corrClass)){
                corrPredictAmount++;
            }
        }
        algPredictionPercentage = (double)(corrPredictAmount)/(double) (testRowsAmount);
    }
}

/**
 * This class represents a node in the DTL tree.
 * A node can be a subtree or a leaf.
 * If it is a subtree, classification property is null, since we haven't determined what it is yet.
 * If it is a leaf, only classification property is NOT null, since the leaf represents only a classification.
 */
class Node{
    /* Properties */
    String attributeName;
    // String - each value of this attribute name
    // Node - the root of a branch (subtree)
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

    /* Method */
    /**
     * @return the values of the attribute name from the map.
     */
    public String[] getAttValues(){
        if(this.attributeValuesNodes != null) {
            String[] attVals = new String[this.attributeValuesNodes.size()];
            int i = 0;
            for (Map.Entry<String, Node> entry : this.attributeValuesNodes.entrySet()) {
                attVals[i] = entry.getKey();
                i++;
            }
            return attVals;
        }
        else{
            // it's a leaf
            return null;
        }
    }
}