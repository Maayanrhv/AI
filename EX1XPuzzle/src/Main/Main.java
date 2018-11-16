package Main;

import Game.Board;
import Game.GameManager;
import Tools.FileExtractor;
import Tools.InputTranslator;

public class Main {
    //TODO create a new game
    //TODO receive the input and validate it
    //TODO translate the input
    public static void main(String[] args) {
        String[] input = FileExtractor.extract(args[0]);
        GameManager gameManager = new GameManager(input);
        gameManager.run();
    }
}
