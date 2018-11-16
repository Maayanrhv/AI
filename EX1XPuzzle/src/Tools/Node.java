package Tools;
import Game.Board;

/** Represents a possible state of the game. */
public class Node {
    /* Properties */
    private Board stateBoard;
    private Node parent;
    private Move operation;

    /* Constructor */
    public Node(Board b, Node n, Move m){
        this.stateBoard = b;
        this.parent = n;
        this.operation = m;
    }
}
