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
     * The A* algorithm.
     * @param initialNode the root node from which the A* starts running
     * @param size the board size
     * @return the goal node
     */
    @Override
    protected Node algorithm(Node initialNode, int size){
        //this comparator gives priority to the second node
        // when the returned value = 1;
        Comparator<Node> compare = (Node n1, Node n2)-> {
            //compare by heuristic function
            int fRes1 = n1.f(goal);
            int fRes2 = n2.f(goal);
            if(fRes2 < fRes1)
                return 1;
            else if(fRes2 == fRes1)
                //compare by developing time of each node
                if(n2.getTime() < n1.getTime())
                    return 1;
                else if(n2.getTime() > n1.getTime())
                    return -1;
                else
                    //compare by rank (U-D-L-R)
                    if(n2.getRank() > n1.getRank())
                        return 1;
                    else
                        return -1;
            else return -1;
        };

        PriorityQueue<Node> openList = new PriorityQueue<>(compare);
        openList.add(initialNode);
        while (!openList.isEmpty()){
            Node node = openList.poll();
            developedNodes ++;
            if (isGoal(node))
                return node;
            Queue<Node> adjacentNodes = successors(node, size);
            setDepths(adjacentNodes);
            setNodesDevelopingTime(adjacentNodes);
            openList.addAll(adjacentNodes);
            adjacentNodes.clear();
        }
        return null;
    }

    /**
     * sets each adjacent node its developing moment.
     * System.nanoTime() is monotonic, therefore the node that was created first
     * in the openList will be the one to be chosen before a later created node.
     * @param nodesQueue the adjacent nodes. They are all created together, therefore,
     *                   their developing time is the same for each one.
     */
    private void setNodesDevelopingTime(Queue<Node> nodesQueue){
        long time = System.nanoTime();
        for (Node node:nodesQueue) {
            node.setTime(time);
        }
    }
}
