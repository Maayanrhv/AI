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
    private int depth = 0;
    private int rank;
    private long time;

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
        setRank();
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
     * @return this node's rank
     */
    public int getRank(){
        return rank;
    }
    /**
     * @return this node's developing time
     */
    public long getTime(){
        return time;
    }
    /**
     * sets the node's depth when necessary.
     * @param d the depth to set to the node
     */
    public void setDepth(int d){
        depth = d;
    }
    /**
     * sets the rank of this node.
     * U gets the highest ranking and R the lowest ranking.
     * Therefore, U has higher priority over R.
     */
    private void setRank(){
        if("U".equals(oneMove)) rank = 4;
        else if("D".equals(oneMove)) rank = 3;
        else if("L".equals(oneMove)) rank = 2;
        else if("R".equals(oneMove)) rank = 1;
    }
    /**
     * sets the developing time.
     * @param whenNodeDeveloped the time this node was developed.
     */
    public void setTime(long whenNodeDeveloped){
        time = whenNodeDeveloped;
    }

    /* Methods */
    // relevant for A* algorithm
    /**
     * f function = g + h
     * @param goalState the goal board
     * @return g + h
     */
    public int f(Board goalState){
        return g()+h(goalState);
    }
    /**
     * g function - calculates the cost to reach this node.
     * In this exercise, g is the node's depth.
     * @return this node's depth
     */
    public int g(){
        return depth;
    }
    /**
     * h function - the heuristic function.
     * h - Manhattan distance
     * @param goalState the goal board
     * @return the (goal_X_coordinate - current_X-coordinate)+(goal_Y_coordinate - current_Y-coordinate)
     */
    private int h(Board goalState){
        int size = stateBoard.getSize();
        int[][] goalBoard = goalState.getBoard();
        int[][] currentBoard = stateBoard.getBoard();
        int heuristicCalc = 0;
        int xTarget,yTarget;
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
