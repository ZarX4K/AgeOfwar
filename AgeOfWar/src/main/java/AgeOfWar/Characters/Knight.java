package AgeOfWar.Characters;

import java.awt.*;

public class Knight extends BaseCharacterStats {
    private int critical;

    public Knight(int x, int y, int width, int height, String standImage, String walkImage, String attackImage, int health, int damage, int priceBuy, int critical) {
        super(x, y, width, height, standImage, walkImage, attackImage, health, damage, priceBuy);
        this.critical = critical;
    }

}
