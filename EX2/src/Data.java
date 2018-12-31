import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class holds the training data according to which,
 * each algorithm will calculate the classification for each test
 * in the test file.
 */
public class Data {
    /* Properties */
    private ArrayList<Row> trainRows = new ArrayList<>();
    private ArrayList<String>[] possibleAttributes;
    private Map<Integer, String> attributesPositions = new HashMap<>();
    private ArrayList<String> possibleClassifications = new ArrayList<>();
    private String classificationName;
    private int classificationPosition;
    private int amountOfAttributes;

    /* Getters & Setters */
    /**
     * @return trainRows
     */
    public ArrayList<Row> getRows() {
        return trainRows;
    }
    /**
     * @return possibleAttributes
     */
    public ArrayList<String>[] getPossibleAttributes() {
        return possibleAttributes;
    }
    /**
     * @return attributesPositions
     */
    public Map<Integer, String> getAttributesPositions() {
        return attributesPositions;
    }
    /**
     * @return classificationName
     */
    public String getClassificationName(){
        return classificationName;
    }
    /**
     * @return possibleClassifications
     */
    public ArrayList<String> getPossibleClassifications() {
        return possibleClassifications;
    }
    /**
     * @return classificationPosition
     */
    public int getClassificationPosition() {
        return classificationPosition;
    }
    /**
     * @return amountOfAttributes
     */
    public int getAmountOfAttributes() {
        return amountOfAttributes;
    }

    /**
     * @param rowsVal to set as trainRows
     */
    public void setRows(ArrayList<Row> rowsVal) {
        this.trainRows = rowsVal;
    }
    /**
     * @param possibleAttributesVal to set as possibleAttributes
     */
    public void setPossibleAttributes(ArrayList<String>[] possibleAttributesVal) {
        this.possibleAttributes = possibleAttributesVal;
    }
    /**
     * @param attributesPositionsVal to set as attributesPositions
     */
    public void setAttributesPositions(Map<Integer, String> attributesPositionsVal) {
        this.attributesPositions = attributesPositionsVal;
    }
    /**
     * @param classificationNameVal to set as classificationName
     */
    public void setClassificationName(String classificationNameVal) {
        this.classificationName = classificationNameVal;
    }
    /**
     * @param possibleClassificationsVal to set as possibleClassifications
     */
    public void setPossibleClassifications(ArrayList<String> possibleClassificationsVal) {
        this.possibleClassifications = possibleClassificationsVal;
    }
    /**
     * @param classificationPositionVal to set as classificationPosition
     */
    public void setClassificationPosition(int classificationPositionVal) {
        this.classificationPosition = classificationPositionVal;
    }
    /**
     * @param amountOfAttributesVal to set as amountOfAttributes
     */
    public void setAmountOfAttributes(int amountOfAttributesVal) {
        amountOfAttributes = amountOfAttributesVal;
    }


    /* Methods */
}
