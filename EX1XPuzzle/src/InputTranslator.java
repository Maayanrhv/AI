import java.util.HashMap;
import java.util.Map;

/**
 * Translates the given input to readable data.
 * Adapts the algorithm choice to the relevant algorithm class.
 * Turns String to int whenever necessary.
 */
public class InputTranslator {
    /* Properties */
    private BFS bfs;
    private AStar aStar;
    private IDS ids;
    private Map<Integer, SearchAlgorithm> algorithmTypes = new HashMap<>();
    private String filePath;

    /* Constructor */
    /**
     * Constructs a new InputTranslator instance.
     * @param fPath the path to create the output file int
     */
    public InputTranslator(String fPath){
        filePath = fPath;
        bfs = new BFS();
        aStar = new AStar();
        ids = new IDS();
        setDictionary();
    }

    /* Getters & Setters */
    /**
     * Setting the algorithms dictionary
     */
    private void setDictionary (){
        algorithmTypes.put(1,ids);
        algorithmTypes.put(2,bfs);
        algorithmTypes.put(3,aStar);
    }
    /**
     * getting a requested algorithm by a given key.
     * @param key of the requested algorithm
     * @return the requested algorithm
     */
    public SearchAlgorithm getAlgorithm(int key){
        return algorithmTypes.get(key);
    }
    /**
     * @return the input file's path.
     */
    public String getFilePath(){
        return filePath;
    }

    /* Methods */
    /**
     * Turns the given board size from String to int.
     * @param s the board size in String format
     * @return the board size in int format
     */
    public static int StringToIntParser (String s){
        // checking if s is a valid number
        if (s.matches("[0-9]+") && s.length() > 0) {
            return Integer.parseInt(s);
        }
        //s is not a valid number
        return -1;
    }
}
