import java.util.HashMap;
import java.util.Map;

public class Row {
    /* Properties */
    Map<String,String> values = new HashMap<>();
    int rowNumber;
    String classification;

    /* Constructors */

    /* Getters & Setters */
    /**
     * @return values
     */
    public Map<String, String> getValues() {
        return values;
    }
    /**
     * @return rowNumber
     */
    public int getRowNumber() {
        return rowNumber;
    }
    /**
     * @return classification
     */
    public String getClassification() {
        return classification;
    }

    /**
     * @param valuesVal to set as values
     */
    public void setValues(Map<String, String> valuesVal) {
        this.values = valuesVal;
    }
    /**
     * @param rowNumberVal to set as rowNumber
     */
    public void setRowNumber(int rowNumberVal) {
        this.rowNumber = rowNumberVal;
    }
    /**
     * @param classificationVal to set as classification
     */
    public void setClassification(String classificationVal) {
        this.classification = classificationVal;
    }

    /* Methods */

}
