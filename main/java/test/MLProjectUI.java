package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MLProjectUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MLProjectUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Projet Intégré - Machine Learning, Scraping, Filtrage de Données");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Barre de navigation
        JPanel navbar = new JPanel();
        navbar.setLayout(new GridLayout(1, 4, 10, 10));

        JButton btnImportCV = new JButton(new ImageIcon("cv.jpeg"));
        btnImportCV.setToolTipText("Importer un CV");
        JButton btnAI = new JButton(new ImageIcon("ai.png"));
        btnAI.setToolTipText("Intelligence Artificielle");
        JButton btnStats = new JButton(new ImageIcon("stats.jpg"));
        btnStats.setToolTipText("Statistiques");
        JButton btnFilter = new JButton(new ImageIcon("iltre.jpeg"));
        btnFilter.setToolTipText("Filtrage de Données");

        navbar.add(btnImportCV);
        navbar.add(btnAI);
        navbar.add(btnStats);
        navbar.add(btnFilter);
        frame.add(navbar, BorderLayout.SOUTH);

        // Panneau central pour afficher les options
        JPanel mainPanel = new JPanel(new CardLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
 //css
        // Panel pour Importation de CV
        JPanel importCVPanel = new JPanel();
        importCVPanel.setLayout(new BorderLayout());
        JLabel lblImportCV = new JLabel("Importer votre CV", JLabel.CENTER);
        JButton btnUploadCV = new JButton("Cliquez pour importer");
        btnUploadCV.setPreferredSize(new Dimension(200, 200));
        importCVPanel.add(lblImportCV, BorderLayout.NORTH);// zedna label
        importCVPanel.add(btnUploadCV, BorderLayout.CENTER);// zedna

        mainPanel.add(importCVPanel, "ImportCV");

        // Panel pour Machine Learning
        JPanel mlPanel = new JPanel();
        mlPanel.setLayout(new BorderLayout());
        JLabel lblChat = new JLabel("Posez une question", JLabel.CENTER);
        JTextField tfChat = new JTextField();
        JButton btnSend = new JButton("Envoyer");
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.add(tfChat, BorderLayout.CENTER);
        chatPanel.add(btnSend, BorderLayout.EAST);
        mlPanel.add(lblChat, BorderLayout.NORTH);
        mlPanel.add(chatPanel, BorderLayout.CENTER);

        mainPanel.add(mlPanel, "MachineLearning");

        // Panel pour Statistiques
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BorderLayout());
        JLabel lblStats = new JLabel("Statistiques", JLabel.CENTER);
        statsPanel.add(lblStats, BorderLayout.NORTH);
        // Graphiques simulés avec des JLabel pour cet exemple
        JLabel lblGraph = new JLabel(new ImageIcon("icons/graph.png"));
        statsPanel.add(lblGraph, BorderLayout.CENTER);

        mainPanel.add(statsPanel, "Statistiques");

        // Panel pour Filtrage
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(4, 2, 10, 10));
        filterPanel.add(new JLabel("Ville :"));
        JComboBox<String> cbVille = new JComboBox<>(new String[]{"Casablanca", "Rabat", "Fès"});
        filterPanel.add(cbVille);
        filterPanel.add(new JLabel("Secteur :"));
        JComboBox<String> cbSecteur = new JComboBox<>(new String[]{"IT", "Génie Civil", "Mécatronique"});
        filterPanel.add(cbSecteur);
        filterPanel.add(new JLabel("Type de contrat :"));
        JComboBox<String> cbContrat = new JComboBox<>(new String[]{"CDI", "CDD", "Stage"});
        filterPanel.add(cbContrat);
        filterPanel.add(new JLabel("Niveau d'étude :"));
        JComboBox<String> cbNiveau = new JComboBox<>(new String[]{"Bac+3", "Bac+5", "Doctorat"});
        filterPanel.add(cbNiveau);

        mainPanel.add(filterPanel, "Filtrage");

        // Action Listeners pour changer de panneau
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        btnImportCV.addActionListener(e -> cardLayout.show(mainPanel, "ImportCV"));
        btnAI.addActionListener(e -> cardLayout.show(mainPanel, "MachineLearning"));
        btnStats.addActionListener(e -> cardLayout.show(mainPanel, "Statistiques"));
        btnFilter.addActionListener(e -> cardLayout.show(mainPanel, "Filtrage"));

        frame.setVisible(true);
    }
}
