package AgeOfWar.Logic;

import AgeOfWar.Characters.Archer;
import AgeOfWar.Characters.Knight;
import AgeOfWar.Characters.Tank;
import AgeOfWar.Graphics.BackGroundScreens;
import AgeOfWar.Graphics.GameGraphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MainLogic implements Runnable {
    private GameGraphics gameGraphics;
    private Knight knight;
    private Tank tank;
    private Archer archer;
    private Collisions collisions;
    private Moving moving;
    private Hitboxes hitboxes;
    private  Attack attack;
    private BackGroundScreens backGroundScreens;
    private int gameState = 1; // 1: Intro Screen, 2: In-Game, 3: Game Over
    private boolean gameStarted = false;
    private Thread gameThread;
    private long currentTime;
    private double delta = 0;
    private int fps = 60;
    private long lastTime = System.nanoTime();
    private double drawInterval = 1000000000 / fps;
    private JPanel gamePanel;
    private KeyReader keyReader;

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void initialize() {
        knight = new Knight(100, 100, 255, 550, "stick.png", "stick.png", "stick.png", 100, 20, 10, 50);

        System.out.println(knight);
        keyReader = new KeyReader();
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(keyReader);
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameStarted) {
                    gameStarted = true;
                    gameState = 2; // Game state changes to In-Game
                    System.out.println("Game state changed to In-Game!"); // Debugging statement
                }
            }
        });
    }



    public void update() {
        gamePanel.repaint();
    }

    // Handle game updates based on key events
        //if (keyReader.knightSpawn) {
            // Logic to spawn knight
       // }

       // if (keyReader.archerSpawn) {
            // Logic to spawn archer
     //   }

        // Additional updates for other spawn types like tank, etc.
   // }

    private void spawnRocket() {
        Random random = new Random();
        int randPick = random.nextInt(1) + 1; // Randomly spawn 1 rocket
        for (int i = 0; i < randPick; i++) {
            int randCorner = random.nextInt(2);
            // Logic to spawn rockets at random positions
        }
    }

    public void startGameThread() {
        if (gameThread == null) {  // Prevent creating a new thread if one already exists
            gameThread = new Thread(this);
            gameThread.start();
        }
    }


    public void resetGame() {
        gameState = 1; // Reset to Intro state
        gameStarted = false;
        gamePanel.repaint();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                delta--;
            }
        }
    }

    public class KeyReader implements KeyListener {
        public boolean knightSpawn, archerSpawn, tankSpawn, skyAttackSpawn;
        public boolean ENEMYknightSpawn, ENEMYarcherSpawn, ENEMYtankSpawn, ENEMYskyAttackSpawn;

        @Override
        public void keyTyped(KeyEvent e) {}

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

    public int getGameState() {
        return gameState;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public KeyReader getKeyReader() {
        return keyReader;
    }

    public Knight getKnight() {
        return knight;
    }

    public Tank getTank() {
        return tank;
    }

    public Archer getArcher() {
        return archer;
    }

    public Hitboxes getHitboxes() {
        return hitboxes;
    }

    public Attack getAttack() {
        return attack;
    }

    public Moving getMoving() {
        return moving;
    }

    public Collisions getCollisions() {
        return collisions;
    }

    public BackGroundScreens getBackGroundScreens() {
        return backGroundScreens;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public double getDelta() {
        return delta;
    }

    public int getFps() {
        return fps;
    }

    public long getLastTime() {
        return lastTime;
    }

    public double getDrawInterval() {
        return drawInterval;
    }

    public GameGraphics getGameGraphics() {
        return gameGraphics;
    }
}
