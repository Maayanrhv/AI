package SearchAlgorithms;
import Game.Board;
import Tools.Node;
import Tools.Output;

public class BFS implements SearchAlgorithm {
    public Output execute(int size, Board initial, Board goal){
        if(initial.isEqual(goal)){
            return new Output("", 0, 0);
        }
        else {
            Node initialNode = new Node(initial, null, null);
            //TODO change the following return value
            return new Output(null, 0, 0);
        }
    }
    private void algorithm(){
        /*
        L -> make_queue(initialNode)
        while L not empty
            Node <- L.remove_front()
            if goal(Node) return true
            S <- successors(Node)
            L.insert(S)
        return false
         */
    }
}
