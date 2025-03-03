package ML_Chance;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ML2Panel extends JFrame {


WekaAnalysisExtended_training predictor;


    private void ML2Panel(String arffFilePath) {

        try {
            WekaAnalysisExtended_training analyzer = new WekaAnalysisExtended_training();

            predictor = new WekaAnalysisExtended_training();
            int x1, x2, x3, x4, x5, x6, x7;
            BufferedReader reader = new BufferedReader(new FileReader(arffFilePath));
            String line;

            // Ignorer les lignes d'en-tête jusqu'à @data
            while ((line = reader.readLine()) != null && !line.equals("@data")) {
                continue;
            }


            // Lire la première ligne de données
            if ((line = reader.readLine()) != null) {
                // Diviser la ligne en valeurs en utilisant la virgule comme séparateur
                String[] values = line.split(",");

                // Convertir et stocker chaque valeur
                x1 = Integer.parseInt(values[0]); // Ville
                x2 = Integer.parseInt(values[1]); // Secteur_activite
                x3 = Integer.parseInt(values[2]); // Type_contrat
                x4 = Integer.parseInt(values[3]); // Niveau_etudes
                x5 = Integer.parseInt(values[4]); // Experience
                x6 = Integer.parseInt(values[5]); // Langue
                x7 = Integer.parseInt(values[6]); // Teletravail

                // À ce point, vous pouvez utiliser les variables x1 à x7
                System.out.println("Valeurs extraites :");
                System.out.println("Ville (x1) : " + x1);
                System.out.println("Secteur_activite (x2) : " + x2);
                System.out.println("Type_contrat (x3) : " + x3);
                System.out.println("Niveau_etudes (x4) : " + x4);
                System.out.println("Experience (x5) : " + x5);
                System.out.println("Langue (x6) : " + x6);
                System.out.println("Teletravail (x7) : " + x7);


                // Exemple de prédiction
                double chance = analyzer.predict_chance(0,0,0,0,0,0,0);
                System.out.printf("\ntest de model Chance de la combinaison : %.2f%%\n", chance);
            }

            reader.close();

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion des données : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Exécution de l'interface graphique


            ML2Panel frame = new ML2Panel();
            frame.ML2Panel("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Chance/output_form.arff");


    }
}