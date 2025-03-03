package ui;

import javax.swing.*;
import java.awt.*;


public class NavBar extends JPanel {
    public NavBar(Claude app) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(Color.BLACK);  // Ajouter cette ligne pour le fond noir
        setOpaque(true);            // Ajouter cette ligne pour s'assurer que le fond est visible


        String[] icons = {"📄 CV", "🤖 IA", "📊 Stats", "🔍 Accurancy"};
        String[] commands = {"resume", "ml", "stats", "filter"};

        // Définir les couleurs pour chaque bouton
        Color[] colors = {
                new Color(65, 105, 225),  // Bleu royal pour CV
                new Color(46, 139, 87),   // Vert mer pour IA
                new Color(218, 165, 32),  // Jaune doré pour Stats
                new Color(178, 34, 34)    // Rouge foncé pour Filtres
        };

        for (int i = 0; i < icons.length; i++) {
            JButton btn = new JButton(icons[i]);
            String cmd = commands[i];

            // Définir la taille
            btn.setPreferredSize(new Dimension(120, 40));

            // Configuration de la police
            btn.setFont(new Font("Dialog", Font.PLAIN, 16));

            // Colorer le bouton
            btn.setBackground(colors[i]);
            btn.setForeground(Color.WHITE);  // Texte en blanc
            btn.setOpaque(true);
            btn.setBorderPainted(false);

            // Effet hover (survol)
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(btn.getBackground().brighter());
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(btn.getBackground().darker());
                }
            });

            btn.addActionListener(e -> app.showPanel(cmd));
            add(btn);
        }
    }
}