package Ml_Salaire;

import ui.BackgroundPanel;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.Attribute;
import weka.core.DenseInstance;

import javax.swing.*;
import java.awt.*;

public class TestSalaryPredictionGUI {

    public void showPanel() throws Exception {
        Classifier model;
        Instance newInstance;
        StringBuilder inputValues;

        try {
            // Charger le modèle et les données en utilisant la classe utilitaire
            String modelPath = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Salaire/salaryModel.model";
            String dataPath = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Chance/output_form2.arff";

            model = SalaryPredictionUtils.loadModel(modelPath);
            Instances dataStructure = SalaryPredictionUtils.loadData(dataPath);

            // Créer une nouvelle instance pour la prédiction à partir des données du fichier ARFF
            newInstance = new DenseInstance(dataStructure.numAttributes());
            newInstance.setDataset(dataStructure);

            inputValues = new StringBuilder();

            // Traiter chaque attribut sauf "Salaire"
            for (int i = 0; i < dataStructure.numAttributes(); i++) {
                Attribute att = dataStructure.attribute(i);

                // Exclure l'attribut "Salaire" du traitement
                if (!att.name().equalsIgnoreCase("Salaire")) {
                    // Récupérer la valeur de chaque attribut de la première instance
                    double value = dataStructure.instance(0).value(i);

                    // Définir la valeur de l'attribut dans la nouvelle instance pour la prédiction
                    newInstance.setValue(i, value);
                }
            }

            // Prédire le salaire
            double prediction = SalaryPredictionUtils.predictSalary(model, newInstance);
            inputValues.append("Salaire prédit : ").append(String.format("%.2f DH", prediction));

            // Affichage avec fond et texte agrandi
            JFrame resultFrame = new JFrame("Résultat");
            resultFrame.setSize(650, 600);
            resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            resultFrame.setLocationRelativeTo(null);  // Centrer la fenêtre

            // Créer un JPanel personnalisé avec un fond d'écran
            JPanel resultPanel = new BackgroundPanel("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Salaire/hello.jpg");  // Remplacez par le chemin de votre image
            resultPanel.setLayout(new BorderLayout());

            // Créer un JLabel pour afficher le résultat avec une grande taille de police
            JLabel resultLabel = new JLabel(inputValues.toString(), SwingConstants.CENTER);
            resultLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Taille de police plus grande
            resultLabel.setForeground(Color.BLACK); // Texte en noir pour le résultat
            resultPanel.add(resultLabel, BorderLayout.CENTER);


            // Ajouter le JPanel à la fenêtre
            resultFrame.add(resultPanel);
            resultFrame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
