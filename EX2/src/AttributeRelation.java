import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class holds the relations between attribute name and its values.
 * Relations are the attributes name and values' values and positions.
 */
public class AttributeRelation {
    /* Properties */
    private ArrayList<String>[] possibleAttributes;
    private Map<Integer, String> attributesPositions = new HashMap<>();

    /* Getters & Setters */
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

    /* Methods */
    /**
     * maps an attribute name to its position.
     * @param attName attribute name to find its position
     * @return the attribute name's position
     */
    public int findPossibleAttsPositionByAttName(String attName){
        for(int i=0; i<attributesPositions.size(); i++){
            if(attributesPositions.get(i).equals(attName))
                return i;
        }
        return -1;
    }
}
