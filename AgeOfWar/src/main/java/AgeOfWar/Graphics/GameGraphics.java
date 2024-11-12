package AgeOfWar.Graphics;

import AgeOfWar.Characters.Knight;
import AgeOfWar.Graphics.BackGroundScreens;
import AgeOfWar.Logic.MainLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameGraphics extends JFrame {
    private MainLogic mainLogic;
    private BackGroundScreens backGroundScreens;

    public GameGraphics() {
        // Initialize mainLogic and backGroundScreens first
        mainLogic = new MainLogic();
        backGroundScreens = new BackGroundScreens();

        setTitle("AGE OF WAR");
        setPreferredSize(new Dimension(1660, 990));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("gameLogo.png")).getImage());

        // Create the gamePanel and add it to the window
        GamePanel gamePanel = new GamePanel(mainLogic, backGroundScreens);
        add(gamePanel);

        // Set the game panel in mainLogic and initialize it
        mainLogic.setGamePanel(gamePanel);
        mainLogic.initialize();
        mainLogic.startGameThread();

        // Final setup for the window
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private class GamePanel extends JPanel {


        public GamePanel(MainLogic mainLogic, BackGroundScreens backGroundScreens) {
            setBackground(Color.black);
            setFocusable(true);

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);


            if (mainLogic.getGameState() == 1) {
                g.drawImage(backGroundScreens.getIntroImage(), 0, 0, null);
            } else if (mainLogic.getGameState() == 2) {
                g.drawImage(backGroundScreens.getBackgroundImage(), 0, 0, null);

            }
            if (mainLogic.getGameState() == 2) {
                for (Knight knight : mainLogic.getKnights()) {
                    g.drawImage(knight.getWalkImage(), knight.getX(), knight.getY(), knight.getWidth(), knight.getHeight(), null);

                }
                for (Knight knight : mainLogic.getEnemyKnights()) {
                    g.drawImage(knight.getWalkImage(), knight.getX(), knight.getY(), knight.getWidth(), knight.getHeight(), null);

                }
            }
        }
    }
}