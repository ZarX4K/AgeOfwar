package AgeOfWar.Characters;

import java.awt.*;

public class Knight extends BaseCharacterStats {
    private int critical;


    public Knight(int x, int y, int width, int height, String standImagePath, String walkImagePath, String attackImagePath, int health, int damage, int priceBuy, int moveSpeed, boolean isMoving, int critical) {
        super(x, y, width, height, standImagePath, walkImagePath, attackImagePath, health, damage, priceBuy, moveSpeed, isMoving);
        this.critical = critical;
    }
}
