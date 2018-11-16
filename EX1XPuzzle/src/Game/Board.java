package Game;

public class Board {
    /* Properties */
    private int size;
    private int [][] board;

    /* Getters & Setters */
    public void SetBoardMatrix(int[][] b){
        board = b;
    }
    public int[][] GetBoardMatrix(){
        return board;
    }
    /* Methods */
    /**
     * Generates a board matrix out of an array of integers.
     * @param initialInput represents the initial board as an array.
     */
    public void initialBoardGenerator(int[] initialInput){
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
