package SearchAlgorithms;
import Game.Board;
import Tools.Output;

public interface SearchAlgorithm {
    Output execute(int size, Board initial, Board goal);
}
