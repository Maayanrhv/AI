/**
 * An interface that represents all possible
 * algorithms that can be used in this game.
 */
public interface SearchAlgorithm {
    /**
     * Executes the algorithm that is run as a part of the game.
     * @param size the board size in the game
     * @param initialBoard board to begin the game with
     * @param goalBoard board to end the game with
     * @return the Output data
     */
    Output execute(int size, Board initialBoard, Board goalBoard);
}
