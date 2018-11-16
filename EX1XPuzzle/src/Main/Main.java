package Main;

import Game.Board;
import Tools.InputTranslator;

public class Main {
    //TODO create a new game
    //TODO receive the input and validate it
    //TODO translate the input
    public static void main(String[] args) {
        String board = "1-0-3-4-2-5-7-8-6";
        InputTranslator it = new InputTranslator();
        Board b = it.boardInputTranslator(board, 3);
        b.print();
    }
}
