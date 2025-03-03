package Ml_Salaire;



import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import java.util.Random;
import weka.core.SelectedTag;


public class SalaryPrediction {
    public static void main(String[] args) {
        try {
            // Charger le fichier ARFF
            System.out.println("Chargement des données...");
            String filePath = "C:/Users/KFSATARNYON/Videos/scraping_data/scraping_data/src/main/java/tools/fillo_num.arff";
            DataSource source = new DataSource(filePath);
            Instances data = source.getDataSet();

            // Vérifier si le fichier est correctement chargé
            if (data == null) {
                System.err.println("Erreur : Les données n'ont pas pu être chargées.");
                return;
            }
            System.out.println("Données chargées avec succès.");

            // Trouver l'index de l'attribut salaire
            int salaireIndex = -1;
            for (int i = 0; i < data.numAttributes(); i++) {
                if (data.attribute(i).name().toLowerCase().contains("salaire")) {
                    salaireIndex = i;
                    break;
                }
            }

            if (salaireIndex == -1) {
                System.err.println("Erreur : Attribut salaire non trouvé dans le fichier.");
                return;
            }

            // Définir l'attribut salaire comme cible
            System.out.println("Définition de l'attribut salaire comme cible...");
            data.setClassIndex(salaireIndex);
            System.out.println("Attribut cible défini : " + data.classAttribute().name());

            // Créer et configurer le modèle de régression linéaire
            System.out.println("Création du modèle de régression linéaire...");
            LinearRegression model = new LinearRegression();

            // Configuration du modèle
            model.setEliminateColinearAttributes(true);
            model.setAttributeSelectionMethod(new SelectedTag(1, LinearRegression.TAGS_SELECTION));
            model.buildClassifier(data);

            System.out.println("Modèle construit avec succès.");

            // Évaluation du modèle
            System.out.println("Évaluation du modèle...");
            Evaluation eval = new Evaluation(data);
            eval.crossValidateModel(model, data, 10, new Random(1));

            // Afficher les résultats détaillés
            System.out.println("\nRésultats d'évaluation :");
            System.out.println("Coefficient de corrélation : " + eval.correlationCoefficient());
            System.out.println("Erreur moyenne absolue : " + eval.meanAbsoluteError());
            System.out.println("Erreur quadratique moyenne : " + eval.rootMeanSquaredError());
            System.out.println("Nombre total d'instances : " + eval.numInstances());

            // Afficher les coefficients du modèle
            System.out.println("\nÉquation de régression :");
            System.out.println(model);

            // Sauvegarder le modèle
            System.out.println("\nSauvegarde du modèle...");
            weka.core.SerializationHelper.write(
                    "C:/Users/KFSATARNYON/Videos/scraping_data/scraping_data/src/main/java/tools/salaryModel.model",
                    model
            );
            System.out.println("Modèle sauvegardé avec succès.");

        } catch (Exception e) {
            System.err.println("Erreur lors du processus : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
