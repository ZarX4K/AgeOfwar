package AgeOfWar.Logic;

import AgeOfWar.Graphics.GameGraphics;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainLogic {
    public GameGraphics.GamePanel gamePanel; // Reference to the game panel

    private JPanel gameWindow;
    private int gameState = 1; // Set initial game state to 1
    private boolean gameStarted = false; // Track if the game has started
    private int fps = 60; // Target FPS
    private double drawInterval = 1000000000.0 / fps; // Interval in nanoseconds for each frame
    private long lastTime = System.nanoTime(); // Last time the frame was drawn
    public MainLogic() {
        // Constructor logic (if needed)
    }
    public MainLogic(GameGraphics.GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void initialize() {
        gameWindow.setFocusable(true);
        gameWindow.addKeyListener(new KeyReader());
        gameWindow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if the game has not started yet
                if (!gameStarted) {
                    gameStarted = true;  // Set the game as started
                    gameState = 2;       // Change game state to 2
                    System.out.println("Game state changed to 2");
                }
            }
        });
    }

    public class KeyReader implements KeyListener {
        public boolean knightSpawn, archerSpawn, tankSpawn, skyAttackSpawn;
        public boolean ENEMYknightSpawn, ENEMYarcherSpawn, ENEMYtankSpawn, ENEMYskyAttackSpawn;

        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) knightSpawn = true;
            if (e.getKeyCode() == KeyEvent.VK_S) archerSpawn = true;
            if (e.getKeyCode() == KeyEvent.VK_D) tankSpawn = true;
            if (e.getKeyCode() == KeyEvent.VK_F) skyAttackSpawn = true;

            if (e.getKeyCode() == KeyEvent.VK_L) ENEMYknightSpawn = true;
            if (e.getKeyCode() == KeyEvent.VK_K) ENEMYarcherSpawn = true;
            if (e.getKeyCode() == KeyEvent.VK_J) ENEMYtankSpawn = true;
            if (e.getKeyCode() == KeyEvent.VK_H) ENEMYskyAttackSpawn = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) knightSpawn = false;
            if (e.getKeyCode() == KeyEvent.VK_S) archerSpawn = false;
            if (e.getKeyCode() == KeyEvent.VK_D) tankSpawn = false;
            if (e.getKeyCode() == KeyEvent.VK_F) skyAttackSpawn = false;

            if (e.getKeyCode() == KeyEvent.VK_L) ENEMYknightSpawn = false;
            if (e.getKeyCode() == KeyEvent.VK_K) ENEMYarcherSpawn = false;
            if (e.getKeyCode() == KeyEvent.VK_J) ENEMYtankSpawn = false;
            if (e.getKeyCode() == KeyEvent.VK_H) ENEMYskyAttackSpawn = false;
        }
    }

    public void run() {
        while (gameState != 0) {
            long currentTime = System.nanoTime();
            double delta = (currentTime - lastTime) / drawInterval;

            if (delta >= 1) {

                gameWindow.repaint(); // Repaint the screen to update graphics
                lastTime = currentTime;
            }
        }
    }

    public int getGameState() {
        return gameState;
    }
}
