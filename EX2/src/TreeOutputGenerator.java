import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

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
        // order attributes alphabetically
        String[] attsVals = tree.getAttValues();
        Arrays.sort(attsVals);

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