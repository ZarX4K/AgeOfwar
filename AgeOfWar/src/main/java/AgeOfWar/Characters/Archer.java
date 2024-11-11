package AgeOfWar.Characters;

public class Archer extends Knight{
    public int range;

    public Archer(int x, int y, int width, int height, String standImage, String walkImage, String attackImage, int health, int damage, int priceBuy, int critical, int range) {
        super(x, y, width, height, standImage, walkImage, attackImage, health, damage, priceBuy, critical);
        this.range = range;
    }
}
