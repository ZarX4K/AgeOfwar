package AgeOfWar.Game.Graphics;

import AgeOfWar.Game.Game;
import AgeOfWar.Game.Logic.MainLogic;

import javax.swing.*;
import java.awt.*;

public class GameGraphics extends JFrame {

    public GameGraphics(MainLogic mainLogic, BackGroundScreens backGroundScreens) {
        setTitle("AGE OF WAR");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("gameLogo.jpg")).getImage());
        GamePanel gamePanel = new GamePanel(mainLogic, backGroundScreens);
        setContentPane(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private class GamePanel extends JPanel {
        private MainLogic mainLogic;
        private BackGroundScreens backGroundScreens;

        public GamePanel(MainLogic mainLogic, BackGroundScreens backGroundScreens) {
            this.mainLogic = mainLogic;
            this.backGroundScreens = backGroundScreens;
            setPreferredSize(new Dimension(1280, 720));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Your custom drawing code here
        }
    }
}
