package AgeOfWar.Characters;

import javax.swing.*;
import java.awt.*;

public class BaseCharacterStats {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int health;
    protected int damage;
    protected int priceBuy;
    protected String imagePath;

    public BaseCharacterStats(int x, int y, int width, int height, String imagePath, int health, int damage, int priceBuy) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imagePath = imagePath;
        this.health = health;
        this.damage = damage;
        this.priceBuy = priceBuy;

    }

    private Image loadImage(String imagePath) {
        ImageIcon ii = new ImageIcon(getClass().getResource(imagePath));
        return ii.getImage();
    }
}
