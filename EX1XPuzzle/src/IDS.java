import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * IDS algorithm class.
 * Solves the game using the IDS algorithm
 */
public class IDS extends SearchAlgorithm {
    /* Methods */
    /**
     * Executes the algorithm that is run as a part of the game.
     * @param size the board size in the game
     * @param initialBoard board to begin the game with
     * @param goalBoard board to end the game with
     * @return the Output data
     */
    @Override
    public Output execute(int size, Board initialBoard, Board goalBoard){
        // if initial board is also the goal
        if(initialBoard.isEqual(goalBoard)){
            return new Output("", 0, 0);
        }
        else {
            goal = goalBoard;
            Node initialNode = new Node(initialBoard, null, null);
            initialNode.setDepth(0);
            Node resNode = algorithm(initialNode, size);
            if(resNode != null){
                String path = pathToGoalCalculator(resNode);
                return new Output(path, developedNodes, limit);
            }
            else{
                return new Output(null, 0, 0);
            }
        }
    }

    /**
     * The IDS algorithm (DFS-L).
     * @param initialNode the root node from which the IDS starts running
     * @param size the board size
     * @return the goal node
     */
    @Override
    protected Node algorithm(Node initialNode, int size){
        // the goal node to be
        Node resNode = null;
        while (resNode == null){
            limit ++;
            developedNodes = 0;
            resNode = DFS(initialNode, size);
        }
        return resNode;
    }

    /**
     * DFS algorithm with a given limit
     * @param initialNode the root node from which the algorithm starts running
     * @param size the board size
     * @return the goal node to the algorithm function
     */
    private Node DFS(Node initialNode, int size){
        Stack<Node> openList = new Stack<>();
        openList.push(initialNode);
        while (!openList.isEmpty()){
            Node node = openList.pop();
            if(limit < node.getDepth())
                continue;
            developedNodes ++;
            if (isGoal(node))
                return node;
            Queue<Node> adjacentNodes = successors(node, size);
            setDepths(adjacentNodes);
            openList.addAll(adjacentNodes);
            adjacentNodes.clear();
        }
        return null;
    }

    /**
     * Calculates the adjacent nodes to check (the possible next moves in the game)
     * @param parentNode the current state in the game
     * @param size the board size
     * @return a priority queue of the given node's adjacent nodes.
     */
    @Override
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
                    //if Right move is valid - create the relevant new node
                    if(j>0){
                        String boardValues = generateBoardString(board, size, i, j, i, j-1);
                        tempBoardR.boardGenerator(boardValues);
                        rightNode = new Node(tempBoardR, parentNode, "R");
                        adjacent.add(rightNode);
                    }
                    //if Left move is valid - create the relevant new node
                    if(j<size-1){
                        String boardValues = generateBoardString(board, size, i, j, i, j+1);
                        tempBoardL.boardGenerator(boardValues);
                        leftNode = new Node(tempBoardL, parentNode, "L");
                        adjacent.add(leftNode);
                    }
                    //if Down move is valid - create the relevant new node
                    if(i>0){
                        String boardValues = generateBoardString(board, size, i, j, i-1, j);
                        tempBoardD.boardGenerator(boardValues);
                        downNode = new Node(tempBoardD, parentNode, "D");
                        adjacent.add(downNode);
                    }
                    //if Up move is valid - create the relevant new node
                    if(i<size-1){
                        String boardValues = generateBoardString(board, size, i, j, i+1, j);
                        tempBoardU.boardGenerator(boardValues);
                        upNode = new Node(tempBoardU, parentNode, "U");
                        adjacent.add(upNode);
                    }
                    break;
                }
            }
        }

        return adjacent;
    }
}
