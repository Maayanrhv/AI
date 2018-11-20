/**
 * The main class.
 * Extract the input data from input file.
 * Calls the game manager to run the game.
 */
public class java_ex1 {
    /**
     * The main function that creates the game and runs it.
     * @param args the input file
     */
    public static void main(String[] args) {
        String[] input = FileExtractor.extract(args[0]);
        int i = args[0].lastIndexOf('\\');
        String filePath = args[0].substring(0, i);
        GameManager gameManager = new GameManager(input,filePath);
        gameManager.run();
    }
}
