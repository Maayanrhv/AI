import java.util.ArrayList;
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
    public double algPredictionPercentage;

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
        // Bayes: P(Att|Class) = P(Att & Class) / P(Class)
        Map<Integer,String> predictedClassifications = new HashMap<>();
        // We hold a list of each class type to ease the Naive Bayes calculations later.
        Map<String, Double> classesProbs = new HashMap<>(); // keeps the probabilities of each classification value.
        ArrayList<AttAndClassProb> attsAndClassesProbs = new ArrayList<>();
        ArrayList<AttGivenClassProb> attsGivenClassesProbs = new ArrayList<>();
        // first, calculate the probability of each classification value
        // according to the training data
        // P(Class) = #rowsWithClassClassification / #rowsTotal
        // count #rows-With-Class-Classification
        int classCount = 0;
        for(String classVal: data.getPossibleClassifications()){
            for(Row trainRow:data.getTrainRows()){
                if(trainRow.getClassification().equals(classVal)){
                    classCount++;
                }
            }
            double prob = (double)classCount/(double)data.getTrainRows().size();
            classesProbs.put(classVal, prob);
            classCount = 0;
        }

        // calculate how many times each attribute value and each
        // classification value appear together.
        // P(Att & Class) = #rowsWithAttAndClass / #rowsTotal
        int attAndClassCount = 0;
        for(String classVal: data.getPossibleClassifications()){
            for(int i=0; i<data.getAmountOfAttributes(); i++) {
                for (String attVal : data.getPossibleAttributes()[i]) {
                    for (Row trainRow : data.getTrainRows()) {
                        String attName = data.getAttributesPositions().get(i);
                        if (trainRow.getClassification().equals(classVal)
                                && trainRow.getValues().get(attName).equals(attVal)) {
                            attAndClassCount++;
                        }
                    }
                    double prob = (double) attAndClassCount / (double) data.getTrainRows().size();
                    AttAndClassProb aacp = new AttAndClassProb(attVal, classVal, prob);
                    attsAndClassesProbs.add(aacp);
                    attAndClassCount = 0;
                }
            }
        }

        // calculate how many times each attribute value appears,
        // given the classification value (for each classification value)
        // P(Att|Class)
        for(AttAndClassProb attAndClass: attsAndClassesProbs){
            double prob = attAndClass.probability / classesProbs.get(attAndClass.classification);
            AttGivenClassProb agcp = new AttGivenClassProb(attAndClass.attribute, attAndClass.classification, prob);
            attsGivenClassesProbs.add(agcp);
        }

        // for each row in the test file, find its classification:
        // for each attribute value in this test row, take its probability
        // given each possible classification value (we calculated in the previous step),
        // and multiply all probabilities given the same classification value.
        // the classification value that has the biggest multiplication value,
        // is the predicted classification
        // P(Att1|Class1) * P(Att2|Class1) * .... = multiplicationValue1
        // P(Att1|Class2) * P(Att2|Class2) * .... = multiplicationValue2
        // and so on ...
        for(Row testRow: testData.getTestRows()){
            double multVal;
            double max = 0;
            String predictedClassification = "";
            // check the probability with every possible classification
            for(String possibleClass: data.getPossibleClassifications()){
                multVal = calcProbsMult(testRow, possibleClass);
                if(max < multVal) {
                    max = multVal;
                    predictedClassification = possibleClass;
                }
            }
            predictedClassifications.put(testRow.getRowNumber(), predictedClassification);
        }

        return predictedClassifications;
    }

    /**
     * Calculates P(Att1|ClassX) * P(Att2|ClassX) * .... = multiplicationValueX
     * @param testRow with the relevant Attributes (Att1, Att2,...)
     * @param classification ClassX
     * @return multiplicationValueX
     */
    public double calcProbsMult (Row testRow, String classification){
        double mult = 1;
        for (Map.Entry<String, String> aAtt : testRow.getValues().entrySet()) {
            mult *= findAttandClass(aAtt.getValue(), classification).probability;
        }
        return mult;
    }

    /**
     * finds the AttGivenClassProb instance with the given attribute and classification.
     * @param attribute of AttGivenClassProb to find
     * @param classification of AttGivenClassProb to find
     * @return AttGivenClassProb with these attribute and classification.
     */
    public AttGivenClassProb findAttandClass(String attribute, String classification){
        return new AttGivenClassProb("","");
    }
}

/**
 * This class keeps the probabilities of the appearance of
 * each attribute value and classification value together.
 * P(Att & Class)
 */
 class AttAndClassProb{
    /* Properties */
    String attribute;
    String classification;
    double probability;

    /* Constructor */
    /**
     * Constructs a new AttAndClassProb instance.
     * @param attribute the attribute
     * @param classification the classification
     * @param probability of attribute and classification
     */
    AttAndClassProb(String attribute, String classification, double probability){
        this.attribute = attribute;
        this.classification = classification;
        this.probability = probability;
    }

    /* Getters & Setters */
    /**
     * @return attribute
     */
    public String getAttribute() {
        return attribute;
    }
    /**
     * @return classification
     */
    String getClassification() {
        return classification;
    }
    /**
     * @return probability
     */
    double getProbability() {
        return probability;
    }
 }

/**
 * This class keeps the probabilities of the appearance of
 * each attribute value given the classification value appeared.
 * P(Att|Class)
 */
 class AttGivenClassProb{
    /* Properties */
    String attribute;
    String classification;
    double probability;

    /* Constructor */
    /**
     * Constructs a new AttGivenClassProb instance.
     * @param attribute the attribute
     * @param classification the classification
     * @param probability of attribute given the classification
     */
    AttGivenClassProb(String attribute, String classification, double probability){
        this.attribute = attribute;
        this.classification = classification;
        this.probability = probability;
    }

    /* Getters & Setters */
    /**
     * @return attribute
     */
    public String getAttribute() {
        return attribute;
    }
    /**
     * @return classification
     */
    String getClassification() {
        return classification;
    }
    /**
     * @return probability
     */
    double getProbability() {
        return probability;
    }
 }
