import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class takes the data from train input file and creates a Data object from it.
 */
public class TrainDataExtractor {
    /**
     * Extracts given input from file to a Data object.
     */
    public static Data extractAndProcess(){
        String fileName = "train.txt";
        Data data = new Data();

        ArrayList<String> input = new ArrayList<>();
        String line; // this will reference one line at a time

        ArrayList<String>[] possibleAttributes = null;
        ArrayList<String> possibleClassifications = new ArrayList<>();
        ArrayList<Row> trainRows = new ArrayList<>();

        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int rowsCount = 0;
            while((line = bufferedReader.readLine()) != null) {
                // if this is the first line -
                //      collect the attributes names and map each attribute to its column position
                //      count how many attributes types/names there are
                //      collect classification name
                //      collect the classification column position
                if(rowsCount == 0){
                    String[] attributesNames = line.split("\t");
                    Map<Integer,String> attributesPositions = new HashMap<>();
                    int i;
                    for(i=0; i<attributesNames.length - 1; i++){
                        attributesPositions.put(i, attributesNames[i]);
                    }
                    data.getAttributeRelation().setAttributesPositions(attributesPositions);
                    // adding the amount of attributes to data.
                    data.setAmountOfAttributes(i);
                    // adding the classification name to data.
                    data.setClassificationName(attributesNames[i]);
                    // adding the classification position to data.
                    data.setClassificationPosition(i);
                    if(data.getAmountOfAttributes()!= 0) {
                        possibleAttributes = new ArrayList[data.getAmountOfAttributes()];
                    }
                }
                // if this is NOT the first line -
                //      collect each attribute's values
                //      collect the classification's values
                //      build a Row object to hold all values for each line.
                else
                {
                    String[] attributesValues = line.split("\t");
                    // collect each attribute's values
                    int i;
                    for (i=0; i<data.getAmountOfAttributes(); i++){
                        boolean doesAttExist = false;
                        if(possibleAttributes[i] != null) {
                            for (String val: possibleAttributes[i]) {
                                if(val.equals(attributesValues[i])){
                                    doesAttExist = true;
                                    break;
                                }
                            }
                            if(!doesAttExist){
                                // add the current attribute value of the i attribute type
                                // to the possibleAttributes array.
                                possibleAttributes[i].add(attributesValues[i]);
                            }
                        }
                        else{
                            // add the current attribute value of the i attribute type
                            // to the possibleAttributes array.
                            possibleAttributes[i] = new ArrayList<>();
                            possibleAttributes[i].add(attributesValues[i]);
                        }
                    }

                    // collect the classification's values
                    boolean doesClassExist = false;
                    for (String val: possibleClassifications) {
                        if(val.equals(attributesValues[i])){
                            doesClassExist = true;
                            break;
                        }
                    }
                    if(!doesClassExist){
                        // add the current attribute value of the i attribute type
                        // to the possibleAttributes array.
                        possibleClassifications.add(attributesValues[i]);
                    }

                    // build a Row object to hold all values for each line.
                    Row trainRow  = new Row();
                    // adding the content of this row to trainRow
                    Map<String, String> values = new HashMap<>();
                    for(int j = 0; j<data.getAmountOfAttributes(); j++){
                        String attName =  data.getAttributeRelation().getAttributesPositions().get(j);
                        String attVal = attributesValues[j];
                        values.put(attName, attVal);
                    }
                    trainRow.setValues(values);
                    // adding to trainRow the row number (this row index in train.txt)
                    trainRow.setRowNumber(rowsCount);
                    // adding to trainRow its classification
                    trainRow.setClassification(attributesValues[i]);
                    // adding this trainRow to trainRows ArrayList
                    trainRows.add(trainRow);
                }
                input.add(line);
                rowsCount++;
            }
            // adding all the possible attributes to data.
            data.getAttributeRelation().setPossibleAttributes(possibleAttributes);
            // adding all the possible classifications to data.
            data.setPossibleClassifications(possibleClassifications);
            // adding rows to data.
            data.setRows(trainRows);

            bufferedReader.close();
            return data;
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