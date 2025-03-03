package ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class FilterPanel extends JPanel {

    public FilterPanel() {
        // Configuration du panneau
        setBackground(Color.white);
        setLayout(new BorderLayout());

        // Création de la fenêtre principale
        JPanel frame = new JPanel();

        frame.setLayout(new GridLayout(2, 2));

        // Création des panneaux et ajout à la fenêtre
        createAndAddChartPanel(frame, "Prédiction de Salaire", 86.13, 13.87);
        createAndAddChartPanel(frame, "Prédiction de Profil", 92.0, 8.0);
        createAndAddAlgorithmsPanel(frame);
        createAndAddResultsPanel(frame);

        // Ajout du panel principal
        add(frame, BorderLayout.SOUTH);

        // Définir la taille préférée pour le panel principal
        frame.setPreferredSize(new Dimension(900, 690)); // Exemple de taille (800x600 pixels)


    }

    public void createAndAddChartPanel(JPanel frame, String title, double accuracy, double error) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Précision", accuracy);
        dataset.setValue("Erreur", error);

        JFreeChart chart = ChartFactory.createPieChart(
                title,   // Titre du graphique
                dataset, // Données
                false,   // Inclure la légende
                true,    // Inclure les info-bulles
                false    // Inclure les URLs
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Précision", new Color(0, 204, 102)); // Vert pour la précision
        plot.setSectionPaint("Erreur", new Color(255, 51, 51)); // Rouge pour l'erreur
        plot.setCircular(true);
        plot.setStartAngle(90);

        // Activer l'affichage des pourcentages
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})")); // "{0}" pour le nom, "{1}" pour la valeur brute, "{2}" pour le pourcentage
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 12)); // Police des étiquettes
        plot.setLabelBackgroundPaint(Color.WHITE); // Fond des étiquettes
        plot.setLabelOutlinePaint(null); // Pas de contour pour les étiquettes
        plot.setLabelShadowPaint(null); // Pas d'ombre pour les étiquettes

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(350, 250)); // Taille personnalisée du panneau
        frame.add(chartPanel);
    }


    public void createAndAddAlgorithmsPanel(JPanel frame) {
        JPanel algorithmsPanel = new JPanel();
        algorithmsPanel.setBackground(Color.CYAN);
        algorithmsPanel.setLayout(new GridLayout(4, 1));
        algorithmsPanel.add(new JLabel("Modèle 1 : Prédiction de Salaire"));
        algorithmsPanel.add(new JLabel("Algorithme : Régression Linéaire"));

        frame.add(algorithmsPanel);
    }

    public void createAndAddResultsPanel(JPanel frame) {
        JPanel resultsPanel = new JPanel();
        resultsPanel.setBackground(Color.CYAN);
        resultsPanel.setLayout(new GridLayout(4, 1));
        resultsPanel.add(new JLabel("Modèle 2 : Prédiction de Profil"));
        resultsPanel.add(new JLabel("Algorithme : Clustering"));
        frame.add(resultsPanel);
    }


}
