package Main;
import Game.GameManager;
import Tools.FileExtractor;

/**
 * The main class.
 */
public class Main {
    /**
     * The main function that creates the game and runs it.
     * @param args the input file
     */
    public static void main(String[] args) {
        String[] input = FileExtractor.extract(args[0]);
        GameManager gameManager = new GameManager(input);
        gameManager.run();
    }
}
