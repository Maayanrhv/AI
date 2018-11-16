package Game;
import static Tools.InputTranslator.StringToIntParser;

/**
 * A game board representing a possible state during the game.
 */
public class Board {
    /* Properties */
    private int size;
    private int [][] board;

    /* Constructor */
    /**
     * constructs the Board
     * @param boardSize the board will be boardSize*boardSize sized
     */
    public Board(int boardSize){
        size = boardSize;
        board = new int[size][size];
    }

    /* Methods */
    /**
     * Generates the board
     * @param boardValues the String input
     */
    public void boardGenerator(String boardValues){
        String[] cells = boardValues.split("-");
        int[] numbersInBoard = new int[size*size];
        // parse input strings to ints and add it to numbersInBoard array
        for (int i=0; i<size*size; i++){
            numbersInBoard[i] = StringToIntParser(cells[i]);
        }
        int k = 0;
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                board[i][j] = numbersInBoard[k];
                k++;
            }
        }
    }

    public void print(){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
