package AgeOfWar.Game.Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackGroundScreens extends JPanel {
    private BufferedImage introImage;

    public BackGroundScreens() {
        loadIntroScreenImage();
    }

    private void loadIntroScreenImage() {
        try {
            introImage = ImageIO.read(getClass().getResourceAsStream("/AgeOfWar_Intro.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the image scaled to the current panel size
        if (introImage != null) {
            g.drawImage(introImage, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
