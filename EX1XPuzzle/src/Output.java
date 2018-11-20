/**
 * The Output properties at the end of the game.
 * This class receives the data required for the output file
 * from the execute function of each algorithm.
 */
public class Output {
    /* Properties */
    private String path;
    private int nodesAmount;
    private int cost;

    /*Constructor*/
    /**
     * Constructs a new Output data.
     * @param pathToGoal the path to the goal node from the initial node
     * @param nodesNum the amount of nodes passed until the goal was reached
     * @param algCost the algorithm cost or depth until the goal was reached
     */
    public Output(String pathToGoal, int nodesNum, int algCost){
        path = pathToGoal;
        nodesAmount = nodesNum;
        cost = algCost;
    }

    /* Getters % Setters*/
    /**
     * @return the output file directory
     */
    public String getPath(){return path;}
    /**
     * @return the number of opened nodes during the used algorithm
     */
    public int getNodesAmount(){return nodesAmount;}
    /**
     * @return the algorithm's cost (A*)\solution depth (of IDS)\0 (of BFS)
     */
    public int getCost(){return cost;}
}
