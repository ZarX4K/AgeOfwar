package AgeOfWar.Characters;

public class Knight extends BaseCharacterStats {
    private int critical;

    public Knight(int x, int y, int width, int height, String imagePath, int health, int damage, int critical, int priceBuy) {
        super(x, y, width, height, imagePath, health, damage, priceBuy);
        this.critical = critical;
    }
}
