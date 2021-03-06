import java.util.LinkedList;
import java.util.Queue;

/**
 * A parent class to all algorithms.
 * Contains common methods.
 */
public class SearchAlgorithm {
    /* Properties */
    protected Board goal;
    protected int developedNodes = 0;
    protected int limit = -1;

    /* Methods */
    /**
     * Executes the algorithm run as a part of the game.
     * @param size the board size in the game
     * @param initialBoard board to begin the game with
     * @param goalBoard board to end the game with
     * @return the Output data
     */
    public Output execute(int size, Board initialBoard, Board goalBoard){
        // if initial board is also the goal
        if(initialBoard.isEqual(goalBoard)){
            return new Output("", 0, 0);
        }
        else {
            goal = goalBoard;
            Node initialNode = new Node(initialBoard, null, null);
            Node resNode = algorithm(initialNode, size);
            if(resNode != null){
                String path = pathToGoalCalculator(resNode);
                return new Output(path, developedNodes, 0);
            }
            else{
                return new Output(null, 0, 0);
            }
        }
    }

    /**
     * The Algorithm itself that finds the goal state.
     * To be override by each algorithm class.
     * @param initialNode the root node from which the BFS starts running
     * @param size the board size
     * @return the goal node
     */
    protected Node algorithm(Node initialNode, int size){
        return new Node(initialNode.getStateBoard(), null, null);
    }

    /**
     * Checks whether a node's board is the goal board.
     * @param node the node to check
     * @return true if the given node's board is the goal one. false otherwise.
     */
    protected boolean isGoal(Node node){
        return goal.isEqual(node.getStateBoard());
    }

    /**
     * Calculates the board after making a move.
     * @param board the board to copy with the required change
     * @param size the board size
     * @param emptyX the empty cell row
     * @param emptyY the empty cell column
     * @param x the row of the cell that is switched with the empty one
     * @param y the column of the cell that is switched with the empty one
     * @return the string values of the new board that's created.
     */
    protected String generateBoardString(Board board, int size, int emptyX, int emptyY, int x, int y){
        int[] valuesOrder = new int[size*size];
        int toSwitchWithZero = board.getBoard()[x][y];
        int k=0;
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if(i==x && j==y){
                    valuesOrder[k] = 0;
                }
                else if(i==emptyX && j==emptyY){
                    valuesOrder[k] = toSwitchWithZero;
                }
                else {
                    valuesOrder[k] = board.getBoard()[i][j];
                }
                k++;
            }
        }

        String s = "";
        int m;
        for (m=0; m<(size*size)-1; m++){
            s+= Integer.toString(valuesOrder[m]);
            s+= "-";
        }
        s += Integer.toString(valuesOrder[m]);
        return s;
    }

    /**
     * Calculates the moves that led to the goal node
     * @param goalNode the final node of the path
     * @return a String of the path that led to the goal node.
     * format example of the returned String: "UDUR" as up-down-up-right.
     */
    protected String pathToGoalCalculator(Node goalNode){
        String path="";
        Node currentNode = goalNode;
        while (currentNode.getParent()!= null){
            path+=currentNode.getPath();
            currentNode = currentNode.getParent();
        }
        return new StringBuilder(path).reverse().toString();
    }

    /**
     * Calculates the adjacent nodes to check (the possible next moves in the game)
     * @param parentNode the current state in the game
     * @param size the board size
     * @return a priority queue of the given node's adjacent nodes.
     */
    protected LinkedList<Node> successors(Node parentNode, int size){
        LinkedList<Node> adjacent = new LinkedList<>();
        Board board = parentNode.getStateBoard();
        Board tempBoardU = new Board(size);
        Board tempBoardD = new Board(size);
        Board tempBoardL = new Board(size);
        Board tempBoardR = new Board(size);
        Node upNode, downNode, leftNode, rightNode;
        for(int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if(board.getBoard()[i][j] == 0){
                    //if Up move is valid - create the relevant new node
                    if(i<size-1){
                        String boardValues = generateBoardString(board, size, i, j, i+1, j);
                        tempBoardU.boardGenerator(boardValues);
                        upNode = new Node(tempBoardU, parentNode, "U");
                        adjacent.add(upNode);
                    }
                    //if Down move is valid - create the relevant new node
                    if(i>0){
                        String boardValues = generateBoardString(board, size, i, j, i-1, j);
                        tempBoardD.boardGenerator(boardValues);
                        downNode = new Node(tempBoardD, parentNode, "D");
                        adjacent.add(downNode);
                    }
                    //if Left move is valid - create the relevant new node
                    if(j<size-1){
                        String boardValues = generateBoardString(board, size, i, j, i, j+1);
                        tempBoardL.boardGenerator(boardValues);
                        leftNode = new Node(tempBoardL, parentNode, "L");
                        adjacent.add(leftNode);
                    }
                    //if Right move is valid - create the relevant new node
                    if(j>0){
                        String boardValues = generateBoardString(board, size, i, j, i, j-1);
                        tempBoardR.boardGenerator(boardValues);
                        rightNode = new Node(tempBoardR, parentNode, "R");
                        adjacent.add(rightNode);
                    }
                    break;
                }
            }
        }

        return adjacent;
    }

    /**
     * calculates the depth of each adjacent node
     * @param adjacent the set of adjacent nodes
     */
    protected void setDepths(Queue<Node> adjacent){
        for (Node node:adjacent) {
            node.setDepth(node.getParent().getDepth()+1);
        }
    }
}
