import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * Generates an output tree file in a given directory path.
 * This path was originally chosen to be the input file directory.
 */
public class TreeOutputGenerator {
    /**
     * Generates an output tree file.
     * @param tree the tree to print
     */
    public static void generateOutputFile(Node tree){
        String filePath = "output_tree.txt";
        try {
            FileWriter fw = new FileWriter(filePath);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(printTree(tree, "", 0));
            pw.close();
        }catch (IOException e){
            System.out.println("Error writing file '" + filePath + "'");
        }
    }

    /**
     * sort the attributes values before printing the tree.
     * @param attsVals attributes values to sort
     * @return sorted attributes values string array
     */
    static String[] sort(String[] attsVals){
        // find out whether there are numbers in the data
        boolean isNumeric = false;
        ArrayList<String> partialNumericList = new ArrayList<>();
        ArrayList<String> partialStringList = new ArrayList<>();
        for(String value: attsVals){
            if(isNumeric(value)){
                isNumeric = true;
                partialNumericList.add(value);
            }
            else{
                partialStringList.add(value);
            }
        }

        if(isNumeric){
            // in case some or all of the values are numeric
            return sortNumericAndReturnStringList(partialStringList, partialNumericList);
        }
        else {
            // in case none of the values are numeric, all are strings that represent characters or words
            Arrays.sort(attsVals);
            return attsVals;
        }
    }

    /**
     * sorts a list of partially or fully numeric string values.
     * @param partialStringList the list of all the none numeric values in attsVals
     * @param partialNumericList the list of all the numeric values in attsVals
     * @return the sorted attributes values string array.
     */
    static String[] sortNumericAndReturnStringList(ArrayList<String> partialStringList, ArrayList<String> partialNumericList){
        // first, put the numeric values
        int[] intAttsVals = new int[partialNumericList.size()];
        int i = 0;
        for (String val: partialNumericList) {
            intAttsVals[i] = Integer.parseInt(val);
            i++;
        }
        Arrays.sort(intAttsVals);
        //reverse to string
        String[] sortedStringArray = new String[partialNumericList.size()];
        for (int j = 0; j < intAttsVals.length; j++) {
            sortedStringArray[j] = "" + intAttsVals[j];
        }

        // add the rest of the attsVals array if necessary
        if(!partialStringList.isEmpty()) {
            // in case some of the values are numeric, not all of them
            // then put the strings values that are left
            for(String stringVal : partialStringList) {
                sortedStringArray[i] = stringVal;
            }
        }
        return sortedStringArray;
    }

    /**
     * checks whether the attributes values are numeric or not.
     * @param str an attribute's value
     * @return true if str numeric, false otherwise.
     */
    static boolean isNumeric(String str)
    {
        try {
            Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    /**
     * creates a string with the entire tree format to print
     * @param tree to print
     * @param string at the end of the recursion, it will contain the tree in a printing format
     * @param depth current depth of the tree
     * @return the string to print
     */
    static String printTree(Node tree, String string, int depth){
        if(tree.classification != null){
            // we reached a leaf
            string += ":" + tree.classification;
            return string;
        }
        // firstly, print tabs at the start of each line
        int i;
        if(depth != 0){
            string += "\n";
        }
        for(i=0; i<depth; i++){
            string += "\t";
        }
        // secondly, if this is a subtree, add | after the tab(s)
        if(i != 0){
            string += "|";
        }
        String[] attsVals = tree.getAttValues();
        attsVals = sort(attsVals);

        for(int j=0; j<attsVals.length; j++){
            string += tree.attributeName + "=" + attsVals[j];
            string = printTree(tree.attributeValuesNodes.get(attsVals[j]), string, depth +1);
            if(j < attsVals.length -1) {
                string += "\n";
                for(i=0; i<depth; i++){
                    string += "\t";
                }
                if(depth != 0){
                    string += "|";
                }
            }
        }

        return string;
    }
}