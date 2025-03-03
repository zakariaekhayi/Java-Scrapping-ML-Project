package ui;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        // Charger l'image
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner l'image en l'étirant pour remplir tout le panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}