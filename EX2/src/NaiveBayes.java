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
     * That is, it matches a predicted classification to the
     * row index number in the test file.
     */
    public Map<Integer,String> algorithm(){
        // Bayes: P(Att|Class) = (N(Att & Class) + 1)/ (N(Class) + #AttPossibleValues)
        Map<Integer,String> predictedClassifications = new HashMap<>();
        // We hold a list of each class type to ease the Naive Bayes calculations later.
        Map<String, Double> classesProbs = new HashMap<>(); // keeps the probabilities of each classification value. P(Class)
        Map<String, Integer> classesAmounts = new HashMap<>(); // keeps the amount of rows for each classification value. N(Class)
        ArrayList<AttAndClassProb> attsAndClassesProbs = new ArrayList<>();
        ArrayList<AttGivenClassProb> attsGivenClassesProbs = new ArrayList<>();
        // first, calculate the probability of each classification value
        // according to the training data
        // N(Class) = #rowsWithClassClassification
        // P(Class) = #rowsWithClassClassification / #totalTrainRows
        // count #rows-With-Class-Classification
        int classCount = 0;
        for(String classVal: data.getPossibleClassifications()){
            for(Row trainRow:data.getTrainRows()){
                if(trainRow.getClassification().equals(classVal)){
                    classCount++;
                }
            }
            double prob = (double)classCount / data.getTrainRows().size();
            classesProbs.put(classVal, prob);
            classesAmounts.put(classVal, classCount);
            classCount = 0;
        }

        // calculate how many times each attribute value and each
        // classification value appear together.
        // N(Att & Class) = #rowsWithAttAndClass in train data
        int attAndClassCount = 0;
        for(String classVal: data.getPossibleClassifications()){
            for(int i=0; i<data.getAmountOfAttributes(); i++) {
                for (String attVal : data.getAttributeRelation().getPossibleAttributes()[i]) {
                    String attributeName = "";
                    for (Row trainRow : data.getTrainRows()) {
                        String attName = data.getAttributeRelation().getAttributesPositions().get(i);
                        attributeName = attName;
                        if (trainRow.getClassification().equals(classVal)
                                && trainRow.getValues().get(attName).equals(attVal)) {
                            attAndClassCount++;
                        }
                    }
                    double prob = (double) attAndClassCount;
                    int attName = i;
                    AttAndClassProb aacp = new AttAndClassProb(attributeName,attVal, classVal, prob, attName);
                    attsAndClassesProbs.add(aacp);
                    attAndClassCount = 0;
                }
            }
        }

        // calculate how many times each attribute value appears,
        // given the classification value (for each classification value)
        // P(Att|Class) = (N(Att & Class) + 1)/ (N(Class) + #AttPossibleValues)
        for(AttAndClassProb attAndClass: attsAndClassesProbs){
            int amountOfValuesInAttName = data.getAttributeRelation()
                    .getPossibleAttributes()[attAndClass.attributeTypePos].size();
            double prob = (attAndClass.probability + 1)/
                            (classesAmounts.get(attAndClass.classification) + amountOfValuesInAttName);
            AttGivenClassProb agcp = new AttGivenClassProb(attAndClass.attributeName, attAndClass.attributeValue,
                                                                attAndClass.classification, prob);
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
        // the final prob-1 will be - multiplicationValue1 * N(Class1)
        // the final prob-2 will be - multiplicationValue1 * N(Class2)
        // and so on ...
        // choose the bigger prob-i as the predicted classification
        for(Row testRow: testData.getTestRows()){
            double multVal;
            double max = 0;
            String predictedClassification = "";
            // check the probability with every possible classification
            for(String possibleClass: data.getPossibleClassifications()){
                multVal = calcProbsMult(testRow, possibleClass, attsGivenClassesProbs, classesProbs.get(possibleClass));
                if(max < multVal) {
                    max = multVal;
                    predictedClassification = possibleClass;
                }
                else if(max == multVal){
                    // in case probabilities of all classifications are equal
                    int newMax = 0;
                    for(Map.Entry<String, Integer> amount:classesAmounts.entrySet()){
                        int val = amount.getValue();
                        if(newMax < val){
                            newMax = val;
                            predictedClassification = amount.getKey();
                        }
                    }
                }
            }
            predictedClassifications.put(testRow.getRowNumber(), predictedClassification);
        }

        // calculate NB's accuracy percentage.
        percentageCalc(predictedClassifications);
        return predictedClassifications;
    }

    /**
     * Calculates P(Att1|ClassX) * P(Att2|ClassX) * .... = multiplicationValueX
     * finalMult = multiplicationValueX * classProbability
     * @param testRow with the relevant Attributes (Att1, Att2,...)
     * @param classification ClassX
     * @param attsGivenClassesProbs list of probabilities of attribute given classification
     * @param classProb the probability of ClassX
     * @return finalMult
     */
    double calcProbsMult (Row testRow, String classification,
                                 ArrayList<AttGivenClassProb> attsGivenClassesProbs, double classProb){
        double mult = 1;
        for (Map.Entry<String, String> aAtt : testRow.getValues().entrySet()) {
            AttGivenClassProb attGivenClassProb = findAttandClass(attsGivenClassesProbs, aAtt.getKey(),aAtt.getValue(),
                                                                    classification);
            if(attGivenClassProb != null) {
                mult *= attGivenClassProb.probability;
            }
            else return 0;
        }
        mult *= classProb;
        return mult;
    }

    /**
     * finds the AttGivenClassProb instance with the given attribute and classification.
     * @param attributeName of AttGivenClassProb to find
     * @param attributeVal of AttGivenClassProb to find
     * @param classification of AttGivenClassProb to find
     * @return AttGivenClassProb with these attribute and classification.
     */
    AttGivenClassProb findAttandClass(ArrayList<AttGivenClassProb> attsGivenClassesProbs,
                                      String attributeName, String attributeVal, String classification){
        for(int i=0; i<attsGivenClassesProbs.size(); i++){
            if(attsGivenClassesProbs.get(i).attributeName.equals(attributeName) &&
                    attsGivenClassesProbs.get(i).attributeValue.equals(attributeVal) &&
                        attsGivenClassesProbs.get(i).classification.equals(classification))
                return attsGivenClassesProbs.get(i);
        }
        return null;
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
 * This class keeps the probabilities of the appearance of
 * each attribute value and classification value together.
 * P(Att & Class)
 */
 class AttAndClassProb{
    /* Properties */
    String attributeName;
    String attributeValue;
    String classification;
    double probability;
    int attributeTypePos;

    /* Constructor */
    /**
     * Constructs a new AttAndClassProb instance.
     * @param attributeVal the attribute Value
     * @param classification the classification
     * @param probability of attribute and classification
     * @param attNamePos the attribute name (type) position in train data
     */
    AttAndClassProb(String attributeName, String attributeVal, String classification, double probability, int attNamePos){
        this.attributeName = attributeName;
        this.attributeValue = attributeVal;
        this.classification = classification;
        this.probability = probability;
        this.attributeTypePos = attNamePos;
    }
 }

/**
 * This class keeps the probabilities of the appearance of
 * each attribute value given the classification value appeared.
 * P(Att|Class)
 */
 class AttGivenClassProb{
    /* Properties */
    String attributeName;
    String attributeValue;
    String classification;
    double probability;

    /* Constructor */
    /**
     * Constructs a new AttGivenClassProb instance.
     * @param attributeName the attribute name (type)
     * @param attributeVal the attribute value
     * @param classification the classification
     * @param probability of attribute given the classification
     */
    AttGivenClassProb(String attributeName, String attributeVal, String classification, double probability){
        this.attributeName = attributeName;
        this.attributeValue = attributeVal;
        this.classification = classification;
        this.probability = probability;
    }
 }
