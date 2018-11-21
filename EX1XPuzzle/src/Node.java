/**
 * Represents a possible state of the game.
 * Node contains a possible Board (game state),
 * the parent node and the move that led to this node:
 * can be: U-up, D-down, L-left, R-right.
 * */
public class Node {
    /* Properties */
    private Board stateBoard;
    private Node parent;
    private String oneMove;
    private int depth = -1;

    /* Constructor */
    /**
     * Constructs a Node.
     * @param b Node's board
     * @param n parent node
     * @param path the chosen direction leading to a node's current state.
     *          Can be: U = up, D = down, L = left, R = right.
     */
    public Node(Board b, Node n, String path){
        stateBoard = b;
        parent = n;
        oneMove = path;
    }

    /* Getters & Setters */
    /**
     * @return this node's board
     */
    public Board getStateBoard(){
        return stateBoard;
    }
    /**
     * @return this node's parent node
     */
    public Node getParent(){
        return parent;
    }
    /**
     * @return the move that led to this node's state
     */
    public String getPath(){
        return oneMove;
    }
    /**
     * @return this node's depth
     */
    public int getDepth(){
        return depth;
    }
    /**
     * sets the node's depth when necessary.
     * @param d the depth to set to the node
     */
    public void setDepth(int d){
        depth = d;
    }
}
