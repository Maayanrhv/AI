package SearchAlgorithms;
import Game.Board;
import Tools.Node;

public interface SearchAlgorithm {
    void execute(Board board, int size, Node goal);
}
