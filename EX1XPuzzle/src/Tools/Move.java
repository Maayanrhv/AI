package Tools;

/** Represents the Node's operation
 * That is, what operation led to this node (=current state of the game).
 */
public class Move {
   /* Properties */
    /* The chosen direction les to a node's current state.
    * Can be: U = up, D = down, L = left, R = right.
     */
    private char direction;

    /* Set & Get*/
    public void setDirection(char dir){
        this.direction = dir;
    }
    public char getDirection(){
        return this.direction;
    }
}
