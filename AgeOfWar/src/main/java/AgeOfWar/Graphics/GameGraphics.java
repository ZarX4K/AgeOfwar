package AgeOfWar.Graphics;

import AgeOfWar.Logic.MainLogic;

import javax.swing.*;
import java.awt.*;

public class GameGraphics extends JFrame {
    private MainLogic mainLogic;
    public GameGraphics(MainLogic mainLogic, BackGroundScreens backGroundScreens) {
        setTitle("AGE OF WAR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("gameLogo.png")).getImage());
        GamePanel gamePanel = new GamePanel(mainLogic, backGroundScreens);
        setContentPane(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        mainLogic.setGamePanel(gamePanel);
        mainLogic.initialize();
        mainLogic.startGameThread();

    }

    public class GamePanel extends JPanel {
        private MainLogic mainLogic;
        private BackGroundScreens backGroundScreens;


        public GamePanel(MainLogic mainLogic, BackGroundScreens backGroundScreens) {
            this.mainLogic = mainLogic;
            this.backGroundScreens = backGroundScreens;
            setPreferredSize(new Dimension(1700, 976));
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw based on the current game state
            if (mainLogic.getGameState() == 1) {
                // Draw intro screen
                g.drawImage(backGroundScreens.getIntroImage(), 0, 0, null);
            } else if (mainLogic.getGameState() == 2) {
                // Draw the main game background
                g.drawImage(backGroundScreens.getBackgroundImage(), 0, 0, null);
            }
        }
    }

}
