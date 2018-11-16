package Tools;

/**
 * The Output properties at the end of the game.
 */
public class Output {
    /* Properties */
    private String path;
    private int nodesAmount;
    private int cost;

    /*Constructor*/
    /**
     * Constructs a new Output data.
     * @param p the path to the goal node from the initial node
     * @param nodesNum the amount of nodes passed until the goal was reached
     * @param algCost the algorithm cost or depth until the goal was reached
     */
    public Output(String p, int nodesNum, int algCost){
        path = p;
        nodesAmount = nodesNum;
        cost = algCost;
    }

    /* Getters % Setters*/
    public String getPath(){return path;}
    public int getNodesAmount(){return nodesAmount;}
    public int getCost(){return cost;}
}
