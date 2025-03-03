package ui;

import javax.swing.*;
import java.awt.*;

public class Claude extends JFrame {
    private JPanel contentPanel;
    private NavBar navBar;
    private ResumePanel resumePanel;
    private Chatbot mlPanel;
    private StatsPanel statsPanel;
    private FilterPanel filterPanel;

    public Claude() {//c'est le main
        setTitle("Application de Recherche d'Emploi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo(null);

        initComponents();
        showPanel("resume");//la premiere chose qui sera appelle de showPanel
    }

    private void initComponents() {
        contentPanel = new JPanel(new CardLayout());
        navBar = new NavBar(this);// c'est tres important c'est l'initialisation de nav bar
        resumePanel = new ResumePanel("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ui/resume2.jpg");
        mlPanel = new Chatbot("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ui/chatbot.jpg");
        statsPanel = new StatsPanel("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ui/sample.jpeg");
        // Dans votre code principal
        filterPanel = new FilterPanel();

        contentPanel.add(resumePanel, "resume");
        contentPanel.add(mlPanel, "ml");
        contentPanel.add(statsPanel, "stats");
        contentPanel.add(filterPanel, "filter");

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(navBar, BorderLayout.SOUTH);
    }

    public void showPanel(String panelName) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);
    }
}
