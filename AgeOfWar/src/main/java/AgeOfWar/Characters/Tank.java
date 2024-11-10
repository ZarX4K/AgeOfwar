package AgeOfWar.Characters;

public class Tank extends Archer {
private int armor;

    public Tank(int x, int y, int width, int height, String imagePath, int health, int damage, int critical, int range, int armor, int priceBuy) {
        super(x, y, width, height, imagePath, health, damage, critical, range, priceBuy);
        this.armor = armor;
    }
}
