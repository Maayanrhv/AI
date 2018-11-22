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

    // relevant for A* algorithm
    public int f(Board goalState){
        return g()+h(goalState);
    }
    public int g(){
        return depth;
    }
    public int h(Board goalState){
        int size = stateBoard.getSize();
        int[][] goalBoard = goalState.getBoard();
        int[][] currentBoard = stateBoard.getBoard();
        int heuristicCalc = 0;
        int k=0,xTarget,yTarget;
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                int currentValue = currentBoard[i][j];
                if(currentValue!=0 && currentValue!=goalBoard[i][j]){
                    //calculating where currentValue is targeted to be
                    // in the goal state.
                    xTarget=(currentValue-1)/size;
                    yTarget=(currentValue-1)%size;
                    //calculating the distances between
                    //current x & y and the target x & y.
                    //and the result is added to the heuristic value calculated.
                    heuristicCalc+=Math.abs(xTarget-i)+Math.abs(yTarget-j);
                }
            }
        }
        return heuristicCalc;
    }
}
