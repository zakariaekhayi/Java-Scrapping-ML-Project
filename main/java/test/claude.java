package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class claude extends JFrame {
    private JPanel contentPanel;// declaration des classes simples
    private NavBar navBar;
    private ResumePanel resumePanel;
    private MLPanel mlPanel;
    private StatsPanel statsPanel;
    private FilterPanel filterPanel;

    public claude() {
        setTitle("Application de Recherche d'Emploi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initComponents();
        showPanel("resume");
    }

    private void initComponents() {
        contentPanel = new JPanel(new CardLayout());
        navBar = new NavBar(this);// on a deja delare le navbar
        resumePanel = new ResumePanel();
        mlPanel = new MLPanel();
        statsPanel = new StatsPanel();
        filterPanel = new FilterPanel();

        contentPanel.add(resumePanel, "resume");
        contentPanel.add(mlPanel, "ml");
        contentPanel.add(statsPanel, "stats");
        contentPanel.add(filterPanel, "filter");

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(navBar, BorderLayout.SOUTH);// ici on ajoute le navbar
    }

    public void showPanel(String panelName) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {//declarer que c'est une  interface graphique de swing
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new claude().setVisible(true);// c'est ca qui est important , ici on a initialise claude par new claude()
        });// faire appelle au constructer de claude()
    }
}

class NavBar extends JPanel {// declaration de class navbar
    public NavBar(claude app) {// il prend en argumenet  un objet de classe la classe claude
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createEtchedBorder());

        String[] icons = {"📄 CV", "🤖 IA", "📊 Stats", "🔍 Filtres"};
        String[] commands = {"resume", "ml", "stats", "filter"};

        for (int i = 0; i < icons.length; i++) {// on a cree les 4 navbar par une boucle
            JButton btn = new JButton(icons[i]);
            String cmd = commands[i];
            btn.addActionListener(e -> app.showPanel(cmd));
            add(btn);
        }
    }
}

class ResumePanel extends JPanel {
    public ResumePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Importer votre CV");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel dropZone = new JPanel();
        dropZone.setPreferredSize(new Dimension(200, 200));
        dropZone.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        dropZone.setBackground(Color.WHITE);
        dropZone.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(ResumePanel.this) == JFileChooser.APPROVE_OPTION) {
                    // Logique d'importation à implémenter
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(titleLabel, gbc);

        gbc.gridy = 1;
        add(dropZone, gbc);
    }
}

class MLPanel extends JPanel {
    public MLPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JTextArea questionArea = new JTextArea(5, 30);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(questionArea);

        JButton sendButton = new JButton("Envoyer");
        sendButton.addActionListener(e -> {
            // Logique du chatbot à implémenter
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(scrollPane, gbc);

        gbc.gridy = 1;
        add(sendButton, gbc);
    }
}

class StatsPanel extends JPanel {
    public StatsPanel() {
        setLayout(new BorderLayout());
        JLabel placeholder = new JLabel("Graphiques de statistiques à implémenter", SwingConstants.CENTER);
        add(placeholder, BorderLayout.CENTER);
    }
}

class FilterPanel extends JPanel {
    public FilterPanel() {
        setLayout(new GridLayout(4, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] cities = {"Ville", "Paris", "Lyon", "Marseille"};
        String[] sectors = {"Secteur", "IT", "Génie Civil", "Mécatronique"};
        String[] contracts = {"Type de contrat", "CDI", "CDD", "Stage"};
        String[] education = {"Niveau d'études", "Bac+3", "Bac+5", "Doctorat"};

        add(new JComboBox<>(cities));
        add(new JComboBox<>(sectors));
        add(new JComboBox<>(contracts));
        add(new JComboBox<>(education));
    }
}