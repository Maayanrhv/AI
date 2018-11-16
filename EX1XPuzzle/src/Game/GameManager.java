package Game;
import SearchAlgorithms.SearchAlgorithm;
import Tools.InputTranslator;

public class GameManager {
    /* Properties */
    private InputTranslator it = new InputTranslator();
    private SearchAlgorithm algorithm;
    private int boardSize;
    private Board initialBoard;

    /* Constructor */
    public GameManager(String[] input){
        inputTranslate(input);
    }

    /* Methods*/
    /**
     * Translating the chosen algorithm that was given in the input.
     * @param key indicates which algorithm to use.
     */
    private void inputAlgorithm(String key){
        int k = it.StringToIntParser(key);
        algorithm = it.getAlgorithm(k);
    }
    private void inputSize(String size){
        boardSize = it.StringToIntParser(size);
    }
    private void inputInitialBoard(String board){
        initialBoard = it.boardInputTranslator(board,boardSize);
    }
    private void inputTranslate(String[] input){
        // translating the chosen algorithm
        inputAlgorithm(input[0]);
        // translating the chosen board size
        inputSize(input[1]);
        // translating the initial board
        inputInitialBoard(input[2]);
    }
    public void run(){
        algorithm.execute(initialBoard,boardSize);
    }
}
