package Game;
import SearchAlgorithms.SearchAlgorithm;
import Tools.InputTranslator;
import Tools.Node;
import static Tools.InputTranslator.StringToIntParser;

/**
 * Runs the entire game.
 */
public class GameManager {
    /* Properties */
    private InputTranslator it = new InputTranslator();
    private SearchAlgorithm algorithm;
    private int boardSize;
    private Board initialBoard;
    private Board finalBoard;
    private Node goal;

    /* Constructor */
    /**
     * Constructs a new game
     * @param input the input strings
     */
    public GameManager(String[] input){
        inputTranslate(input);
        goal = goalNodeGenerator();
    }

    /* Methods*/
    /**
     * Translating the chosen algorithm that was given in the input.
     * @param key indicates which algorithm to use.
     */
    private void inputAlgorithm(String key){
        int k = StringToIntParser(key);
        algorithm = it.getAlgorithm(k);
    }
    /**
     * Turns the given board size from String to int
     * @param size the board size
     */
    private void inputSize(String size){
        boardSize = StringToIntParser(size);
        initialBoard = new Board(boardSize);
        finalBoard = new Board(boardSize);
    }
    /**
     * Turns the given board from String to Board
     * @param board the board in String format
     */
    private void inputInitialBoard(String board){
        initialBoard.boardGenerator(board);
    }
    /**
     * Turns the given input from String to the relevant format
     * @param input String array of all input details
     */
    private void inputTranslate(String[] input){
        // translating the chosen algorithm
        inputAlgorithm(input[0]);
        // translating the chosen board size
        inputSize(input[1]);
        // translating the initial board
        inputInitialBoard(input[2]);
    }
    /**
     * Creates the goal node
     * @return a goal Node
     */
    private Node goalNodeGenerator(){
        // creating board cells values;
        String s = "1";
        int i;
        for(i =2; i<boardSize*boardSize;i++){
            s+= "-" + i;
        }
        s+= "-" + 0;

        finalBoard.boardGenerator(s);
        return new Node(finalBoard,null,null);
    }
    /**
     * runs the game after it was initialized.
     */
    public void run(){
        algorithm.execute(initialBoard, boardSize, goal);
    }
}
