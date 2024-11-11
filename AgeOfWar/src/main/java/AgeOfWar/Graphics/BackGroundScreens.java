package AgeOfWar.Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackGroundScreens extends JPanel {
    private BufferedImage introImage;
    private BufferedImage backgroundImage;


    public BackGroundScreens() {
        loadIntroScreenImage();
        loadBackgroundScreenImage();
    }

    private void loadIntroScreenImage() {
        try {
            introImage = ImageIO.read(getClass().getResourceAsStream("/AgeOfWarIntro.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadBackgroundScreenImage() {
        try {
                backgroundImage = ImageIO.read(getClass().getResourceAsStream("/GameBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public BufferedImage getIntroImage() {
        return introImage;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

}
