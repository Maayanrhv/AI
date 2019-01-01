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

        // first, calculate the probability of each classification value
        // according to the training data
        // P(Class) = #rowsWithAClassification / #rowsTotal

        // calculate how many times each attribute value and each
        // classification value appear together.
        // P(Att & Class)

        // calculate how many times each attribute value appears,
        // given the classification value (for each classification value)
        // P(Att|Class)

        // for each row in the test file, find its classification:
        // for each attribute value in this test row, take its probability
        // given each possible classification value (we calculated in the previous step),
        // and multiply all probabilities given the same classification value.
        // the classification value that has the biggest multiplication value,
        // is the predicted classification
        // P(Att1|Class1) * P(Att2|Class1) * .... = multiplicationValue1
        // P(Att1|Class2) * P(Att2|Class2) * .... = multiplicationValue2
        // and so on ...

        return predictedClassifications;
    }
}

/**
 * This class keeps the probabilities of each classification value.
 * P(Class)
 */
class ClassProb{
    /* Properties */
    String classification;
    double probability;
    /* Constructor */
    ClassProb(String classification, double probability){
        this.classification = classification;
        this.probability = probability;
    }

    /* Getters & Setters */
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
 * each attribute value and classification value together.
 * P(Att & Class)
 */
 class AttAndClassProb{
    /* Properties */
    String attribute;
    String classification;
    double probability;
    /* Constructor */
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