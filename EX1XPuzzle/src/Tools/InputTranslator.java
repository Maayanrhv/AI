package Tools;
import Game.Board;
import SearchAlgorithms.AStar;
import SearchAlgorithms.BFS;
import SearchAlgorithms.IDS;
import SearchAlgorithms.SearchAlgorithm;
import java.util.HashMap;
import java.util.Map;

/**
 * Translates the given input to readable data.
 */
public class InputTranslator {
    /* Properties */
    private BFS bfs;
    private AStar aStar;
    private IDS ids;
    private Map<Integer, SearchAlgorithm> algorithmTypes = new HashMap<>();

    /* Constructor */
    public InputTranslator(){
        bfs = new BFS();
        aStar = new AStar();
        ids = new IDS();
        setDictionary();
    }

    /* Getters & Setters */
    /**
     * getting a requested algorithm by a given key.
     * @param key of the requested algorithm
     * @return the requested algorithm
     */
    public SearchAlgorithm getAlgorithm(int key){

        return algorithmTypes.get(key);
    }
    /**
     * Setting the algorithms dictionary
     */
    private void setDictionary (){
        algorithmTypes.put(1,ids);
        algorithmTypes.put(2,bfs);
        algorithmTypes.put(3,aStar);
    }

    /* Methods */
    /**
     * Turns the given board size from String to int.
     * @param s the board size in String format
     * @return the board size in int format
     */
    public int StringToIntParser (String s){
        // checking if s is a valid number
        if (s.matches("[0-9]+") && s.length() > 0) {
            return Integer.parseInt(s);
        }
        //s is not a valid number
        return -1;
    }
    /**
     * Generates the board from a String input
     * @param b the String input
     * @return board in a Board format
     */
    public Board boardInputTranslator(String b, int size){
        Board board = new Board();
        int[][] newBoard = new int[size][size];
        String[] cells = b.split("-");
        int[] numbersInBoard = new int[size*size];
        // parse input strings to ints and add it to numbersInBoard array
        for (int i=0; i<size*size; i++){
            numbersInBoard[i] = StringToIntParser(cells[i]);
        }
        int k = 0;
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                newBoard[i][j] = numbersInBoard[k];
                k++;
            }
        }

        board.SetBoardMatrix(newBoard);
        return board;
    }
}
