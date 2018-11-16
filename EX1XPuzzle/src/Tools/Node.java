package Tools;
import Game.Board;

/** Represents a possible state of the game. */
public class Node {
    /* Properties */
    private Board stateBoard;
    private Node parent;
    private String oneMove;

    /* Constructor */
    /**
     * Constructs a Node.
     * @param b Node's board
     * @param n parent node
     * @param p the chosen direction leading to a node's current state.
     *          Can be: U = up, D = down, L = left, R = right.
     */
    public Node(Board b, Node n, String p){
        stateBoard = b;
        parent = n;
        oneMove = p;
    }

    /* Getters & Setters */
    public Board getStateBoard(){
        return stateBoard;
    }
    public Node getParent(){
        return parent;
    }
    public String getPath(){
        return oneMove;
    }
}
