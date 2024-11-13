package AgeOfWar.Logic;

import AgeOfWar.Characters.Archer;
import AgeOfWar.Characters.Knight;
import AgeOfWar.Characters.Tank;
import AgeOfWar.Graphics.BackGroundScreens;
import AgeOfWar.Graphics.GameGraphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainLogic implements Runnable {
    private GameGraphics gameGraphics;
    private List<Knight> knights;  // List to store knights
    private List<Knight> enemyKnights;  // List to store knights

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
        knights = new ArrayList<>();
        enemyKnights = new ArrayList<>();
        hitboxes = new Hitboxes();
        moving = new Moving();  // Initialize `moving` first
        collisions = new Collisions(moving);  // Now pass `moving` to `Collisions`
        keyReader = new KeyReader();
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(keyReader);
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameStarted) {
                    gameStarted = true;
                    gameState = 2; // Game state changes to In-Game
                    System.out.println("Game state changed to In-Game!");
                }
            }
        });
    }






    private void spawnKnight() {
        Knight newKnight = new Knight(150, 730, 222, 222, "knight.png", "knight.png", "knight.png", 100, 20, 10, 3, true, 10);  // Added boolean value
        knights.add(newKnight);
        System.out.println("Knight spawned");
        // Make sure to update the targeting logic for the new knight
        for (Knight knight : knights) {
            Knight target = moving.findClosestEnemy(knight, enemyKnights);
            moving.moveCharacterTowards(knight, target);  // Move the knight towards the target
        }
    }

    private void spawnEnemyKnight() {
        Knight newEnemyKnight = new Knight(1400, 730, 222, 222, "enemyKnight.png", "enemyKnight.png", "enemyKnight.png", 100, 20, 10, 3,true,10);
        enemyKnights.add(newEnemyKnight);
        System.out.println("Enemy Knight spawned");
        // Update the targeting logic for the new enemy knight
        for (Knight enemyKnights : enemyKnights) {
            Knight target = moving.findClosestEnemy(enemyKnights, knights);
            moving.moveCharacterTowards(enemyKnights, target);  // Update the enemy's target
        }
    }






    public void update() {
        if (keyReader.knightSpawn) {
            spawnKnight();
            keyReader.knightSpawn = false;
        }
        if (keyReader.ENEMYknightSpawn) {
            spawnEnemyKnight();
            keyReader.ENEMYknightSpawn = false;
        }

        // Temporary lists to store new knights that need to be added
        List<Knight> knightsToAdd = new ArrayList<>();
        List<Knight> enemyKnightsToAdd = new ArrayList<>();

        // Move and target logic
        for (Knight knight : knights) {
            if (knight.isAlive()) { // Check if the knight is alive
                Knight target = moving.findClosestEnemy(knight, enemyKnights);
                if (target != null) {
                    moving.moveCharacterTowards(knight, target);
                }
            }
        }

        for (Knight enemyKnight : enemyKnights) {
            if (enemyKnight.isAlive()) { // Check if the enemy knight is alive
                Knight target = moving.findClosestEnemy(enemyKnight, knights);
                if (target != null) {
                    moving.moveCharacterTowards(enemyKnight, target);
                }
            }
        }

        // Collision detection
        List<Rectangle> knightHitboxes = hitboxes.getAllKnightHitboxes(knights);
        List<Rectangle> enemyKnightHitboxes = hitboxes.getAllKnightHitboxes(enemyKnights);
        collisions.checkCollisions(knights, enemyKnights);

        // After all the movements, add the new knights to the main list
        knights.addAll(knightsToAdd);
        enemyKnights.addAll(enemyKnightsToAdd);

        gamePanel.repaint();
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



    public void startGameThread() {
        if (gameThread == null) {  // Prevent creating a new thread if one already exists
            gameThread = new Thread(this);
            gameThread.start();
        }
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



    public void resetGame() {
        gameState = 1; // Reset to Intro state
        gameStarted = false;
        gamePanel.repaint();
    }



    public int getGameState() {
        return gameState;
    }

    public Collisions getCollisions() {
        return collisions;
    }

    public Hitboxes getHitboxes() {
        return hitboxes;
    }

    public KeyReader getKeyReader() {
        return keyReader;
    }

    public List<Knight> getKnights() {
        return knights;
    }

    public List<Knight> getEnemyKnights() {
        return enemyKnights;
    }

    public Tank getTank() {
        return tank;
    }


}
