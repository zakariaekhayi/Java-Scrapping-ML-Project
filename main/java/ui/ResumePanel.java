package ui;

import Form.ModernFlatLafForm;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResumePanel extends BackgroundPanel {
    public ResumePanel(String imagePath) {
        super(imagePath);

        AbstractBorder brdr = new TextBubbleBorder(Color.BLACK, 2, 16, 0);

        setLayout(new BorderLayout());

        // Créer le titre
        JLabel titleLabel = new JLabel("Importer votre CV");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Créer la zone de dépôt cliquable
        JPanel dropZone = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(150, 50);
            }

            @Override
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        dropZone.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        dropZone.setBackground(Color.BLUE);
        dropZone.setBorder(brdr);

        // Ajouter un écouteur pour rendre la zone cliquable
        dropZone.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToMainForm();
            }
        });

        // Ajouter un label au centre de la zone de dépôt
        JLabel dropLabel = new JLabel("Profile");
        dropLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dropLabel.setForeground(Color.WHITE);
        dropLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dropZone.setLayout(new BorderLayout());
        dropZone.add(dropLabel, BorderLayout.CENTER);

        // Panel contenant la zone de dépôt
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setMaximumSize(new Dimension(175, 150));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(dropZone);

        // Wrapper panel pour centrer horizontalement
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        wrapperPanel.setOpaque(false);
        wrapperPanel.add(contentPanel);

        // Ajouter le wrapper panel en bas de la frame
        add(wrapperPanel, BorderLayout.SOUTH);

        // Réduire les marges uniquement en bas
        setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
    }

    private void navigateToMainForm() {
        // Obtenir la fenêtre parente
        Window window = SwingUtilities.getWindowAncestor(this);

        // Fermer la fenêtre actuelle si c'est un JFrame
        if (window instanceof JFrame) {
            window.dispose();
        }

        // Ouvrir la nouvelle fenêtre
        ModernFlatLafForm mainForm = new ModernFlatLafForm();
        mainForm.setVisible(true);
    }
}
