package Ml_Salaire;

import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Classifier;

public class SalaryPredictionUtils {

    // Charger le modèle depuis un chemin donné
    public static Classifier loadModel(String modelPath) throws Exception {
        return (Classifier) weka.core.SerializationHelper.read(modelPath);
    }

    // Charger la structure des données depuis un fichier ARFF
    public static Instances loadData(String dataPath) throws Exception {
        DataSource source = new DataSource(dataPath);
        Instances dataStructure = source.getDataSet();
        dataStructure.setClassIndex(dataStructure.numAttributes() - 1);
        return dataStructure;
    }

    // Effectuer une prédiction pour une instance donnée
    public static double predictSalary(Classifier model, Instance instance) throws Exception {
        return model.classifyInstance(instance);
    }
}
