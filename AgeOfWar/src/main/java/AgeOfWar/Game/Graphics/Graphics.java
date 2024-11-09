package AgeOfWar.Game.Graphics;

import AgeOfWar.Game.Game;

import javax.swing.*;

public class Graphics extends JFrame {
    private Game.KeyReader keyReader;


    public Graphics() {
        this.keyReader = keyReader;
        setTitle("AGE OF WAR");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("gameLogo.jpg")).getImage());
    }
}
