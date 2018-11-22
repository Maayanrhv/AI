import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A* algorithm class.
 * Solves the game using the A* algorithm
 */
public class AStar extends SearchAlgorithm {
    /* Methods */
    /**
     * Executes the algorithm run as a part of the game.
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
            Node resNode = algorithm(initialNode, size);
            if(resNode != null){
                String path = pathToGoalCalculator(resNode);
                return new Output(path, developedNodes, resNode.g());
            }
            else{
                return new Output(null, 0, 0);
            }
        }
    }

    /**
     * The BFS algorithm.
     * @param initialNode the root node from which the BFS starts running
     * @param size the board size
     * @return the goal node
     */
    @Override
    protected Node algorithm(Node initialNode, int size){
        Comparator<Node> compare = (Node n1, Node n2)->(n1.f(goal)-n2.f(goal));
        PriorityQueue<Node> openList = new PriorityQueue<>(compare);
        openList.add(initialNode);
        while (!openList.isEmpty()){
            Node node = openList.poll();
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
}
