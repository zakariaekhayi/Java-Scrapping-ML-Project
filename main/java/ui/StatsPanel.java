package ui;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StatsPanel extends JPanel {
    public StatsPanel(String imagePath) {
        setLayout(new BorderLayout());

        // Charger les données CSV
        String csvFile = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ui/table.csv";  // Remplacer par le chemin réel du fichier CSV
        Map<String, Integer> contractTypeCounts = new HashMap<>();
        Map<String, Integer> educationLevelCounts = new HashMap<>();
        Map<String, Integer> sectorCounts = new HashMap<>();
        Map<String, Integer> regionCounts = new HashMap<>();

        // Lire le fichier CSV et remplir les maps
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Ignorer la première ligne (en-têtes)
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Compter les types de contrats
                contractTypeCounts.put(fields[1], contractTypeCounts.getOrDefault(fields[1], 0) + 1);

                // Compter les niveaux d'études
                educationLevelCounts.put(fields[3], educationLevelCounts.getOrDefault(fields[3], 0) + 1);

                // Compter les secteurs d'activités
                sectorCounts.put(fields[4], sectorCounts.getOrDefault(fields[4], 0) + 1);

                // Compter les régions
                regionCounts.put(fields[2], regionCounts.getOrDefault(fields[2], 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Créer les graphiques
        JFreeChart contractTypeChart = createPieChart("Répartition des Types de Contrat", contractTypeCounts);
        JFreeChart educationLevelChart = createPieChart("Répartition des Niveaux d'Étude", educationLevelCounts);
        JFreeChart sectorChart = createPieChart("Répartition des Secteurs d'Activité", sectorCounts);
        JFreeChart regionChart = createPieChart("Répartition des Régions", regionCounts);

        // Ajouter les graphiques au panneau
        setLayout(new GridLayout(2, 2)); // Affichage en 2x2
        add(new ChartPanel(contractTypeChart));
        add(new ChartPanel(educationLevelChart));
        add(new ChartPanel(sectorChart));
        add(new ChartPanel(regionChart));
    }

    // Méthode pour créer un graphique circulaire à partir des données
    private JFreeChart createPieChart(String title, Map<String, Integer> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        return ChartFactory.createPieChart(title, dataset, true, true, false);
    }

    // Méthode pour créer un graphique en barres (facultatif, en fonction des préférences)
    private JFreeChart createBarChart(String title, Map<String, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), entry.getKey(), entry.getKey());
        }
        return ChartFactory.createBarChart(title, "Catégorie", "Nombre", dataset, PlotOrientation.VERTICAL, false, true, false);
    }


}
