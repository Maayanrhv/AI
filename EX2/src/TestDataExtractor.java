import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class takes the data from test input file and creates a TestData object from it.
 */
public class TestDataExtractor {
    /* Properties */
    private Data data;

    /* Constructors */
    /**
     * Constructs a new TestDataExtractor object that depends
     * on a given training data (Data object).
     * @param dataObj a training Data object.
     */
    public TestDataExtractor(Data dataObj) {
        data = dataObj;
    }

    /* Methods */
    /**
     * Extracts given input from file to a TestData object.
     */
    public TestData extractAndProcess() {
        String fileName = "test.txt";
        TestData testData = new TestData();

        ArrayList<String> input = new ArrayList<>();
        String line; // this will reference one line at a time

        ArrayList<Row> testRows = new ArrayList<>();

        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int rowsCount = 0;
            while((line = bufferedReader.readLine()) != null) {
                // if this is NOT the first line -
                //      build a TestRow object to hold all values for each line.
                if(rowsCount != 0){
                    String[] attributesValues = line.split("\t");
                    // build a Row object to hold all values for each line.
                    Row testRow = new Row();
                    // adding the content of this line to testRow
                    Map<String, String> values = new HashMap<>();
                    for(int i = 0; i<data.getAmountOfAttributes(); i++){
                        String attName =  data.getAttributesPositions().get(i);
                        String attVal = attributesValues[i];
                        values.put(attName, attVal);
                    }
                    testRow.setValues(values);
                    // adding to testRow the row number (this row index in test.txt)
                    testRow.setRowNumber(rowsCount);
                    // adding this testRow to testRows ArrayList
                    testRows.add(testRow);
                }
                input.add(line);
                rowsCount++;
            }
            // adding testRows to data.
            testData.setTestRows(testRows);

            bufferedReader.close();
            return testData;
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return null;
    }
}
