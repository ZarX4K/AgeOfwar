package AgeOfWar.Characters;

import javax.swing.*;
import java.awt.*;

public class BaseCharacterStats {
    public int x;
    public int y;
    protected int width;
    protected int height;
    protected int health;
    protected int damage;
    protected int priceBuy;
    protected Image standImage;
    protected Image walkImage;
    protected Image attackImage;

    public BaseCharacterStats(int x, int y, int width, int height, String standImagePath, String walkImagePath, String attackImagePath, int health, int damage, int priceBuy) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.standImage = new ImageIcon(getClass().getResource("/" + standImagePath)).getImage();
        this.walkImage = new ImageIcon(getClass().getResource("/" + walkImagePath)).getImage();
        this.attackImage = new ImageIcon(getClass().getResource("/" + attackImagePath)).getImage();
        this.health = health;
        this.damage = damage;
        this.priceBuy = priceBuy;
    }

    public void draw(Graphics g) {


            g.drawImage(standImage, getX(), getY(), width, height, null);
       // System.out.println("Knight image loaded: " + (standImage != null));

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getStandImage() {
        return standImage;
    }

    public Image getWalkImage() {
        return walkImage;
    }

    public Image getAttackImage() {
        return attackImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
