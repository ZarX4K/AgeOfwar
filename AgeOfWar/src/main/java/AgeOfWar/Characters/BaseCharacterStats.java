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
    private long lastAttackTime;
    protected int priceBuy;
    private int moveSpeed;
    private boolean isMoving;

    protected Image standImage;
    protected Image walkImage;
    protected Image attackImage;

    public BaseCharacterStats(int x, int y, int width, int height, String standImagePath, String walkImagePath, String attackImagePath, int health, int damage, int priceBuy, int moveSpeed, boolean isMoving) {
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
        this.moveSpeed = moveSpeed;
        this.isMoving = isMoving;
    }

    public void draw(Graphics g) {
            g.drawImage(standImage, getX(), getY(), width, height, null);
    }
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int getDamage() {
        return this.damage;
    }
    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean isMoving() {
        return isMoving;
    }
    public void move(int deltaX, int deltaY) {
        if (isMoving) {
            this.x += deltaX;
            this.y += deltaY;
        }
    }
    public long getLastAttackTime() {
        return lastAttackTime;
    }

    public void setLastAttackTime(long lastAttackTime) {
        this.lastAttackTime = lastAttackTime;
    }
    public void revive(int health) {
        this.health = health;
        this.isMoving = true;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
