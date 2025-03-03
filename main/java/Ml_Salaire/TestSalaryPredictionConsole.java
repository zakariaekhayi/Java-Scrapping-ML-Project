package Ml_Salaire;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.Attribute;

import java.util.Scanner;

public class TestSalaryPredictionConsole {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Charger le modèle et les données
            String modelPath = "C:/Users/KFSATARNYON/Videos/scraping_data/scraping_data/src/main/java/tools/salaryModel.model";
            String dataPath = "C:/Users/KFSATARNYON/Videos/scraping_data/scraping_data/src/main/java/tools/fillo_num.arff";

            Classifier model = SalaryPredictionUtils.loadModel(modelPath);
            Instances dataStructure = SalaryPredictionUtils.loadData(dataPath);

            while (true) {
                // Créer une nouvelle instance
                Instance newInstance = new DenseInstance(dataStructure.numAttributes());
                newInstance.setDataset(dataStructure);

                System.out.println("\n=== Nouvelle prédiction de salaire ===");
                for (int i = 0; i < dataStructure.numAttributes() - 1; i++) {
                    Attribute att = dataStructure.attribute(i);
                    System.out.print("Entrez " + att.name() + ": ");
                    String value = scanner.nextLine().trim();

                    if (att.isNumeric()) {
                        newInstance.setValue(i, Double.parseDouble(value));
                    } else if (att.isNominal()) {
                        int valIndex = att.indexOfValue(value);
                        if (valIndex == -1) {
                            System.out.println("Valeur non reconnue. Essayez à nouveau.");
                            i--;
                        } else {
                            newInstance.setValue(i, valIndex);
                        }
                    }
                }

                // Prédire le salaire
                double prediction = SalaryPredictionUtils.predictSalary(model, newInstance);
                System.out.printf("\nSalaire prédit : %.2f DH\n", prediction);

                System.out.print("\nVoulez-vous faire une autre prédiction ? (oui/non): ");
                if (!scanner.nextLine().equalsIgnoreCase("oui")) {
                    break;
                }
            }
            scanner.close();
            System.out.println("Au revoir !");

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
