import java.util.Map;

public class PredictionManager {
    /* Properties */
    private Data data;

    /* Constructors */
    /**
     * Constructs a new program manager.
     * @param dt the training data.
     */
    public PredictionManager(Data dt){
        data = dt;
    }

    /* Methods */
    /**
     * Runs the programs to predict classifications in test file,
     * and creates an output file with the results.
     */
    public void run(){
        TestDataExtractor tde = new TestDataExtractor(data);
        TestData testData = tde.extractAndProcess();

        // call all algorithms
        // KNN
        KNN knn = new KNN(data, testData);
        Map<Integer,String> KNNPredictions = knn.algorithm();
        // NaiveBayes
        NaiveBayes naiveBayes = new NaiveBayes(data, testData);
        Map<Integer,String> NaiveBayesPredictions = naiveBayes.algorithm();
        // ID3
        ID3 id3 = new ID3(data, testData);
        Map<Integer,String> ID3Predictions = id3.algorithm();

        // Create the Output file
        Output output = new Output(KNNPredictions, NaiveBayesPredictions, ID3Predictions,
                knn.algPredictionPercentage, naiveBayes.algPredictionPercentage, id3.algPredictionPercentage);
        OutputGenerator.generateOutputFile(output);
    }
}
