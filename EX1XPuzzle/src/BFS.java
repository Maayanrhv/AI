import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS algorithm class.
 * Solves the game using the BFS algorithm
 */
public class BFS extends SearchAlgorithm {
    /* Methods */
    /**
     * The BFS algorithm.
     * @param initialNode the root node from which the BFS starts running
     * @param size the board size
     * @return the goal node
     */
    @Override
    protected Node algorithm(Node initialNode, int size){
        /* PSEUDO CODE
        L -> make_queue(initialNode)
        while L not empty
            Node <- L.remove_front()
            if goal(Node) return true
            S <- successors(Node)
            L.insert(S)
        return false
         */
        Queue<Node> openList = new LinkedList<>();
        openList.add(initialNode);
        while (!openList.isEmpty()){
            Node node = openList.poll();
            developedNodes ++;
            if (isGoal(node))
                return node;
            Queue<Node> adjacentNodes = successors(node, size);
            openList.addAll(adjacentNodes);
            adjacentNodes.clear();
        }
        return null;
    }
}