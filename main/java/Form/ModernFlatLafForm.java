// Importation des packages nécessaires
package Form;

import ML_Chance.WekaAnalysisExtended_training;
import Ml_Salaire.TestSalaryPredictionGUI;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import ui.Claude;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Classe pour gérer l'arrière-plan
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de l'image de fond.");
        }
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.setColor(new Color(0, 0, 0, 180)); // Couche semi-transparente noire
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
    }
}

// Classe pour gérer les panneaux transparents
class TransparentPanel extends JPanel {
    public TransparentPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(255, 255, 255, 60)); // Blanc semi-transparent
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2d.dispose();
    }
}

// Classe principale pour l'application
public class ModernFlatLafForm extends JFrame {
    private JComboBox<String> experience;
    private JComboBox<String> langue;
    private JComboBox<String> teletravail;
    private JComboBox<String> contrat;
    private JComboBox<String> secteur;
    private JComboBox<String> niveauEtudes;
    private JComboBox<String> ville;


    private void generateArffFile1() {
        // Mapping des valeurs en fonction des sélections
        String experienceValue = switch (experience.getSelectedItem().toString()) {
            case "Débutant" -> "1";
            case "Intermédiaire" -> "0";
            case "Senior" -> "2";
            case "Expert" -> "3";
            default -> "?";
        };

        String langueValue = switch (langue.getSelectedItem().toString()) {
            case "Français" -> "1";
            case "Anglais" -> "2";
            case "Anglais Français" -> "0";
            default -> "?";
        };

        String teletravailValue = switch (teletravail.getSelectedItem().toString()) {
            case "Non" -> "0";
            case "Hybride" -> "1";
            case "Oui 100%" -> "2";
            default -> "?";
        };

        String contratValue = switch (contrat.getSelectedItem().toString()) {
            case "CDD" -> "2";
            case "Freelance" -> "5";
            case "CDI" -> "0";
            default -> "?";
        };

        String secteurValue = switch (secteur.getSelectedItem().toString()) {
            case "Distribution" -> "47";
            case "Assurance" -> "96";
            case "Multimédia" -> "19";
            case "Commerce" -> "94";
            case "Santé" -> "71";
            case "Environnement" -> "30";
            case "Call Centers" -> "27";
            case "Hôtellerie" -> "21";
            case "Banque" -> "95";
            case "Télécommunications" -> "97";
            default -> "?";
        };

        String villeValue = switch (ville.getSelectedItem().toString()) {
            case "Fès" -> "6";
            case "Meknès" -> "9";
            case "Oujda" -> "8";
            case "Tanger" -> "1";
            case "Settat" -> "2";
            case "Marrakech" -> "3";
            case "Laâyoune" -> "10";
            case "Casablanca" -> "0";
            case "Agadir" -> "5";
            case "Safi" -> "11";
            case "Rabat" -> "4";
            case "Al Hoceima" -> "7";
            default -> "?";
        };

        String niveauEtudesValue = switch (niveauEtudes.getSelectedItem().toString()) {
            case "Bac +3" -> "3";
            case "Bac +5" -> "5";
            case "Bac +2" -> "2";
            case "Autre" -> "0";
            case "Avant Bac" -> "1";
            default -> "?";
        };

        String SalaireValue ="NaN";

        String arffContent = "@relation jobs\n\n" +
                "@attribute Ville NUMERIC\n" +
                "@attribute Secteur_activite NUMERIC\n" +
                "@attribute Type_contrat NUMERIC\n" +
                "@attribute Niveau_etudes NUMERIC\n" +
                "@attribute Experience NUMERIC\n" +
                "@attribute Langue NUMERIC\n" +
                "@attribute Teletravail NUMERIC\n\n" +
                "@data\n" +
                villeValue + "," + secteurValue + "," + contratValue + "," +
                niveauEtudesValue + "," + experienceValue + "," + langueValue + "," + teletravailValue;

        String filePath = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Chance/output_form1.arff";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(arffContent);
            System.out.println("creation avec succes ");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la génération du fichier ARFF : " + ex.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void generateArffFile2() {
        // Mapping des valeurs en fonction des sélections
        String experienceValue = switch (experience.getSelectedItem().toString()) {
            case "Débutant" -> "1";
            case "Intermédiaire" -> "0";
            case "Senior" -> "2";
            case "Expert" -> "3";
            default -> "?";
        };

        String langueValue = switch (langue.getSelectedItem().toString()) {
            case "Français" -> "1";
            case "Anglais" -> "2";
            case "Anglais Français" -> "0";
            default -> "?";
        };

        String teletravailValue = switch (teletravail.getSelectedItem().toString()) {
            case "Non" -> "0";
            case "Hybride" -> "1";
            case "Oui 100%" -> "2";
            default -> "?";
        };

        String contratValue = switch (contrat.getSelectedItem().toString()) {
            case "CDD" -> "2";
            case "Freelance" -> "5";
            case "CDI" -> "0";
            default -> "?";
        };

        String secteurValue = switch (secteur.getSelectedItem().toString()) {
            case "Distribution" -> "47";
            case "Assurance" -> "96";
            case "Multimédia" -> "19";
            case "Commerce" -> "94";
            case "Santé" -> "71";
            case "Environnement" -> "30";
            case "Call Centers" -> "27";
            case "Hôtellerie" -> "21";
            case "Banque" -> "95";
            case "Télécommunications" -> "97";
            default -> "?";
        };

        String villeValue = switch (ville.getSelectedItem().toString()) {
            case "Fès" -> "6";
            case "Meknès" -> "9";
            case "Oujda" -> "8";
            case "Tanger" -> "1";
            case "Settat" -> "2";
            case "Marrakech" -> "3";
            case "Laâyoune" -> "10";
            case "Casablanca" -> "0";
            case "Agadir" -> "5";
            case "Safi" -> "11";
            case "Rabat" -> "4";
            case "Al Hoceima" -> "7";
            default -> "?";
        };

        String niveauEtudesValue = switch (niveauEtudes.getSelectedItem().toString()) {
            case "Bac +3" -> "3";
            case "Bac +5" -> "5";
            case "Bac +2" -> "2";
            case "Autre" -> "0";
            case "Avant Bac" -> "1";
            default -> "?";
        };

        String SalaireValue ="NaN";

        String arffContent = "@relation jobs\n\n" +
                "@attribute Ville NUMERIC\n" +
                "@attribute Secteur_activite NUMERIC\n" +
                "@attribute Type_contrat NUMERIC\n" +
                "@attribute Niveau_etudes NUMERIC\n" +
                "@attribute Experience NUMERIC\n" +
                "@attribute Langue NUMERIC\n" +
                "@attribute Salaire NUMERIC\n" +
                "@attribute Teletravail NUMERIC\n\n" +
                "@data\n" +
                villeValue + "," + secteurValue + "," + contratValue + "," +
                niveauEtudesValue + "," + experienceValue + "," + langueValue + ","  + SalaireValue  + "," +teletravailValue;

        String filePath = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Chance/output_form2.arff";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(arffContent);
            System.out.println("creation avec succes ");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la génération du fichier ARFF : " + ex.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }




    public ModernFlatLafForm() {
        initializeFrame();
        initializeComponents();
    }

    private void initializeFrame() {
        setTitle("AI JOBS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo(null);

        // Chargez l'image de fond depuis un chemin
        String imagePath = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/form/image.jpg"; // Changez le chemin en fonction de l'emplacement de l'image
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        backgroundPanel.setLayout(new BorderLayout(20, 20));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addHeader(backgroundPanel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        setContentPane(backgroundPanel);
    }

    private void addHeader(JPanel mainPanel) {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 150, 243));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));

        JLabel headerLabel = new JLabel("AI JOBS", SwingConstants.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));

        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }

    private void initializeComponents() {
        JPanel formPanel = new TransparentPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = createBaseConstraints();

        addFormComponents(formPanel, gbc);
        addSubmitLabels(formPanel, gbc);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setOpaque(false);
        containerPanel.add(formPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = (JScrollPane) ((BorderLayout) getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER);
        if (scrollPane != null) {
            scrollPane.setViewportView(containerPanel);
        }
    }

    private GridBagConstraints createBaseConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.weightx = 1;
        return gbc;
    }

    private void addFormComponents(JPanel panel, GridBagConstraints gbc) {
        experience = new JComboBox<>(new String[]{
                "Sélectionnez votre expérience", "Débutant", "Intermédiaire", "Senior", "Expert"
        });
        addFormField(panel, "Expérience", experience, gbc);

        langue = new JComboBox<>(new String[]{
                "Sélectionnez vos langues", "Français", "Anglais", "Anglais Français"
        });
        addFormField(panel, "Langue", langue, gbc);

        teletravail = new JComboBox<>(new String[]{
                "Sélectionnez le télétravail", "Non", "Hybride", "Oui 100%"
        });
        addFormField(panel, "Télétravail", teletravail, gbc);

        contrat = new JComboBox<>(new String[]{
                "Sélectionnez un type de contrat", "CDI", "CDD", "Freelance"
        });
        addFormField(panel, "Type de contrat", contrat, gbc);

        secteur = new JComboBox<>(new String[]{
                "Sélectionnez un secteur d'activité", "Distribution", "Assurance", "Multimédia",
                "Commerce", "Santé", "Environnement", "Call Centers", "Hôtellerie", "Banque",
                "Télécommunications"
        });
        addFormField(panel, "Secteur d'activité", secteur, gbc);

        niveauEtudes = new JComboBox<>(new String[]{
                "Sélectionnez votre niveau d'études", "Bac +3", "Bac +5", "Bac +2", "Autre", "Avant Bac"
        });
        addFormField(panel, "Niveau d'études", niveauEtudes, gbc);

        ville = new JComboBox<>(new String[]{
                "Selectionner votre Ville", "Fès", "Meknès", "Oujda", "Tanger", "Settat",
                "Marrakech", "Laâyoune", "Casablanca", "Agadir", "Safi", "Rabat", "Al Hoceima"
        });
        addFormField(panel, "Ville", ville, gbc);
    }

    private void addFormField(JPanel panel, String labelText, JComponent field, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(label, gbc);

        if (field instanceof JTextField || field instanceof JComboBox) {
            field.setPreferredSize(new Dimension(field.getPreferredSize().width, 35));
        }

        panel.add(field, gbc);
    }

    private void addSubmitLabels(JPanel panel, GridBagConstraints gbc) {
        JPanel labelPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        labelPanel.setOpaque(false);

        JLabel predictSalaryLabel = createStyledLabel("Prédire Salaire", new Color(33, 150, 243));
        predictSalaryLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Logic for predicting salary
                generateArffFile2();
                navigateformtosalrypanel();
                System.out.println("Prédire Salaire clicked");
            }
        });
        labelPanel.add(predictSalaryLabel);

        JLabel predictJobChangeLabel = createStyledLabel("Prédire Chance de Travail", new Color(76, 175, 80));
        predictJobChangeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Logic for predicting job change
                generateArffFile1();
                navigateformatoML2Panel();
            }
        });
        labelPanel.add(predictJobChangeLabel);

        JLabel backLabel = createStyledLabel("Revenir", new Color(244, 67, 54));
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToFormtomain();

            }
        });
        labelPanel.add(backLabel);

        panel.add(labelPanel, gbc);
    }


    private JLabel createStyledLabel(String text, Color backgroundColor) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(backgroundColor);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        return label;
    }



    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.putClientProperty("JButton.buttonType", "roundRect");
        button.putClientProperty("FlatLaf.style",
                "font: $medium.font; " +
                        "background: " + colorToHex(backgroundColor) + "; " +
                        "foreground: #fff; " +
                        "focusWidth: 1; " +
                        "borderWidth: 0; " +
                        "arc: 10");
        return button;
    }

    private String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }


    private void navigateToFormtomain() {
        // Obtenir la fenêtre parente
        Window window = SwingUtilities.getWindowAncestor(this);

        // Fermer la fenêtre actuelle si c'est un JFrame
        if (window instanceof JFrame) {
            window.dispose();
        }

        // Ouvrir la nouvelle fenêtre
        Claude mainForm = new Claude();
        mainForm.setVisible(true);
    }



    public void navigateformatoML2Panel(){
        // Obtenir la fenêtre parente
        Window window = SwingUtilities.getWindowAncestor(this);

        // Fermer la fenêtre actuelle si c'est un JFrame
        if (window instanceof JFrame) {
            window.dispose();
        }
        // Ouvrir la nouvelle fenêtre

        WekaAnalysisExtended_training analyzer = new WekaAnalysisExtended_training();

        // Chemin du fichier d'entrée
        String filePath = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Chance/output_form1.arff";

        WekaAnalysisExtended_training panel = new WekaAnalysisExtended_training();
        try {
            panel.showPanel(analyzer,filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    public void navigateformtosalrypanel(){
        // Obtenir la fenêtre parente
        Window window = SwingUtilities.getWindowAncestor(this);

        // Fermer la fenêtre actuelle si c'est un JFrame
        if (window instanceof JFrame) {
            window.dispose();
        }



        TestSalaryPredictionGUI panel = new TestSalaryPredictionGUI();
        try {
            panel.showPanel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }






    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FlatMaterialLighterIJTheme.setup();
                new ModernFlatLafForm().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}