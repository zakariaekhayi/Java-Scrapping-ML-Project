package tools;



import java.io.File;
import java.io.IOException;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

public class CSV2Arff {
    public static void main(String[] args) {
        try {
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML3/fillo.csv"));
            Instances data = loader.getDataSet();

            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(new File("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML3/fillo.arff"));
            saver.writeBatch();

            System.out.println("Conversion successful!");

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}