package AgeOfWar.Characters;

public class Archer extends Knight{
    public int range;

    public Archer(int x, int y, int width, int height, String imagePath, int health, int damage, int critical, int range, int priceBuy) {
        super(x, y, width, height, imagePath, health, damage, critical, priceBuy);
        this.range = range;
    }
}
