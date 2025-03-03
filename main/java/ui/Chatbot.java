package ui;

import Form.ModernFlatLafForm;
import NLP1.AnalyzeTextWithNLP;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class Chatbot extends BackgroundPanel {
    private final JTextArea messageArea;
    private final JButton sendButton;
    private final JScrollPane scrollPane;
    private boolean isPlaceholderVisible = true;
    private static final String PLACEHOLDER = "Envoyez un message...";
    private static final Color PLACEHOLDER_COLOR = new Color(156, 163, 175);
    private static final Color TEXT_COLOR = new Color(52, 53, 65);
    private static final Color BUTTON_COLOR = new Color(16, 163, 127);
    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    private static final String PROMPT_FILE_PATH = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/NLP1/prompt.txt";

    public Chatbot(String imagePath) {
        super(imagePath);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Création du panel principal pour la zone de message
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(10, 0));
        inputPanel.setBackground(BACKGROUND_COLOR);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 0.1f), 1),
                new EmptyBorder(12, 16, 12, 16)
        ));

        // Configuration de la zone de texte
        messageArea = new JTextArea(3, 40);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setText(PLACEHOLDER);
        messageArea.setForeground(PLACEHOLDER_COLOR);
        messageArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageArea.setBorder(null);

        // Configuration du scroll
        scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(null);
        scrollPane.setBackground(BACKGROUND_COLOR);

        // Configuration du bouton d'envoi
        sendButton = createStyledButton();

        // Ajout des listeners
        setupListeners();

        // Assemblage des composants
        inputPanel.add(scrollPane, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Ajout du panel principal
        add(inputPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(32, 32));
        button.setBackground(BUTTON_COLOR);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        // Icône de flèche personnalisée
        button.setIcon(new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                int[] xPoints = {10, 18, 10};
                int[] yPoints = {8, 13, 18};
                g2.fillPolygon(xPoints, yPoints, 3);
                g2.dispose();
            }

            @Override
            public int getIconWidth() {
                return 32;
            }

            @Override
            public int getIconHeight() {
                return 32;
            }
        });

        return button;
    }

    private void setupListeners() {
        messageArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (isPlaceholderVisible) {
                    messageArea.setText("");
                    messageArea.setForeground(TEXT_COLOR);
                    isPlaceholderVisible = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (messageArea.getText().trim().isEmpty()) {
                    messageArea.setText(PLACEHOLDER);
                    messageArea.setForeground(PLACEHOLDER_COLOR);
                    isPlaceholderVisible = true;
                }
            }
        });

        messageArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isShiftDown()) {
                    messageArea.append("\n");
                    e.consume();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    sendMessage();
                }
            }
        });

        sendButton.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        // Vérifier qu'il ne s'agit pas du placeholder et que le message n'est pas vide
        if (!isPlaceholderVisible && !messageArea.getText().trim().isEmpty()) {
            try {
                // 1. Vider le fichier prompt.txt
                Files.write(Paths.get(PROMPT_FILE_PATH), new byte[0]);

                // 2. Écrire le contenu de la textarea dans prompt.txt
                Files.write(Paths.get(PROMPT_FILE_PATH),
                        messageArea.getText().getBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE);

                // 3. Lancer l'analyse
                runAnalyzeTextWithNLP();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erreur lors de l'écriture du fichier : " + ex.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void runAnalyzeTextWithNLP() {
        SwingUtilities.invokeLater(() -> {
            String dbUrl = "jdbc:mysql://localhost:3306/jobsoffers";
            String dbUser = "root";
            String dbPassword = "";
            String dictionaryFile = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/NLP1/dictionary.json";
            String textFile = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/NLP1/prompt.txt";
            AnalyzeTextWithNLP analyzer = null;
            try {
                analyzer = new AnalyzeTextWithNLP(dictionaryFile, dbUrl, dbUser, dbPassword);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                analyzer.analyzeText(textFile);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
