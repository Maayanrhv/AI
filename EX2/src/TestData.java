import java.util.ArrayList;

/**
 * This class holds the testing data that each algorithm will
 * calculate the classification for each of its TestRow-s.
 */
public class TestData {
    /* Properties */
    private ArrayList<Row> testRows = new ArrayList<>();

    /* Getters & Setters */
    /**
     * @return testRows
     */
    public ArrayList<Row> getTestRows() {
        return testRows;
    }

    /**
     * @param testRowsVal to set as trainRows
     */
    public void setTestRows(ArrayList<Row> testRowsVal) {
        this.testRows = testRowsVal;
    }
}
