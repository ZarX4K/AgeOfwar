package AgeOfWar.Game;


import AgeOfWar.Game.Graphics.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {
    public  void main(String[] args) {
        Graphics window = new Graphics();
        KeyReader keyReader = new KeyReader();    }



    public class KeyReader implements KeyListener {
        public boolean knightSpawn,archerSpawn,tankSpawn,skyAttackSpawn;
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                knightSpawn = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                archerSpawn = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                tankSpawn = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_F) {
                skyAttackSpawn = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                knightSpawn = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                archerSpawn = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                tankSpawn = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_F) {
                skyAttackSpawn = false;
            }
        }
    }
}




