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
        String[] input = FileExtractor.extract();
        for (int i=0;i<input.length;i++){
            input[i] = input[i].replaceAll(" ","");
        }
        //int i = args[0].lastIndexOf('\\');
        //String filePath = args[0].substring(0, i);
        GameManager gameManager = new GameManager(input);
        gameManager.run();
    }
}
