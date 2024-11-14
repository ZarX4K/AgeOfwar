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

public class MainLogic implements Runnable {
    private GameGraphics gameGraphics;
    private List<Knight> knights;
    private List<Knight> enemyKnights;
    private Tank tank;
    private Archer archer;
    private Collisions collisions;
    private Moving moving;
    private Hitboxes hitboxes;
    private Attack attack;
    private BackGroundScreens backGroundScreens;
    private int gameState = 1;
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
        moving = new Moving();
        attack = new Attack(moving);
        collisions = new Collisions(moving, hitboxes, attack);
        keyReader = new KeyReader();
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(keyReader);
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameStarted) {
                    gameStarted = true;
                    gameState = 2;
                    System.out.println("Game state changed to In-Game!");
                }
            }
        });
    }


        private void spawnKnight() {
            Knight knight = new Knight(150, 730, 222, 222, "knight.png", "knight.png", "knight.png", 100, 20, 10, 3, true, false, 10);
            knights.add(knight);
            System.out.println("Knight spawned");
            moving.moveKnight(knight, knights); // Pass the list of knights for collision handling
        }

        private void spawnEnemyKnight() {
            Knight enemyKnight = new Knight(1400, 730, 222, 222, "enemyKnight.png", "enemyKnight.png", "enemyKnight.png", 100, 20, 10, 3, true, true, 10);
            enemyKnights.add(enemyKnight);
            System.out.println("Enemy Knight spawned");
            moving.moveKnight(enemyKnight, enemyKnights); // Pass the list of enemy knights
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

            for (Knight knight : knights) {
                if (knight.isAlive() && knight.isMoving()) {
                    moving.moveKnight(knight, knights); // Pass all knights for queue and collision checks
                }
            }

            for (Knight enemyKnight : enemyKnights) {
                if (enemyKnight.isAlive() && enemyKnight.isMoving()) {
                    moving.moveKnight(enemyKnight, enemyKnights); // Pass all enemy knights
                }
            }
            collisions.checkCollisions(knights, enemyKnights);

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
            if (e.getKeyCode() == KeyEvent.VK_L) ENEMYknightSpawn = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) knightSpawn = false;
            if (e.getKeyCode() == KeyEvent.VK_L) ENEMYknightSpawn = false;
        }
    }

    public void startGameThread() {
        if (gameThread == null) {
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
        gameState = 1;
        gameStarted = false;
        gamePanel.repaint();
    }

    public List<Knight> getKnights() {
        return knights;
    }

    public List<Knight> getEnemyKnights() {
        return enemyKnights;
    }

    public int getGameState() {
        return gameState;
    }
}
