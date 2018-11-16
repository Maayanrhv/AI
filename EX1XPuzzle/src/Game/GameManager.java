package Game;
import SearchAlgorithms.SearchAlgorithm;
import Tools.InputTranslator;
import Tools.Output;
import Tools.OutputGenerator;

import static Tools.InputTranslator.StringToIntParser;

/**
 * Runs the entire game.
 */
public class GameManager {
    /* Properties */
    private InputTranslator it;
    private SearchAlgorithm algorithm;
    private int boardSize;
    private Board initialBoard;
    private Board goalBoard;

    /* Constructor */
    /**
     * Constructs a new game
     * @param input the input strings
     */
    public GameManager(String[] input, String inputPath){
        it = new InputTranslator(inputPath);
        inputTranslate(input);
        goalBoardGenerator();
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
        goalBoard = new Board(boardSize);
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
     */
    private void goalBoardGenerator(){
        // creating board cells values;
        String s = "1";
        int i;
        for(i =2; i<boardSize*boardSize;i++){
            s+= "-" + i;
        }
        s+= "-" + 0;

        goalBoard.boardGenerator(s);
    }
    /**
     * runs the game after it was initialized.
     */
    public void run(){
        Output output = algorithm.execute(boardSize, initialBoard, goalBoard);
        OutputGenerator.generateOutputFile(output, it.getFilePath());
    }
}
