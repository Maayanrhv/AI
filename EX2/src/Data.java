import java.util.ArrayList;

/**
 * This class holds the training data according to which,
 * each algorithm will calculate the classification for each test
 * in the test file.
 */
public class Data {
    /* Properties */
    private ArrayList<Row> trainRows = new ArrayList<>();
    private AttributeRelation attributeRelation = new AttributeRelation();
    private ArrayList<String> possibleClassifications = new ArrayList<>();
    private String classificationName;
    private int classificationPosition;
    private int amountOfAttributes;

    /* Getters & Setters */
    /**
     * @return trainRows
     */
    public ArrayList<Row> getTrainRows() {
        return trainRows;
    }
    /**
     * @return attributeRelation
     */
    public AttributeRelation getAttributeRelation() {
        return attributeRelation;
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
     * @param attributeRelationVal to set as trainRows
     */
    public void setAttributeRelation(AttributeRelation attributeRelationVal) {
        this.attributeRelation = attributeRelationVal;
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
}
