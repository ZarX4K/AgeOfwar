package AgeOfWar.Characters;

public class Tank extends Archer {
private int armor;

    public Tank(int x, int y, int width, int height, String standImage, String walkImage, String attackImage, int health, int damage, int priceBuy, int critical, int range, int armor) {
        super(x, y, width, height, standImage, walkImage, attackImage, health, damage, priceBuy, critical, range);
        this.armor = armor;
    }
}
