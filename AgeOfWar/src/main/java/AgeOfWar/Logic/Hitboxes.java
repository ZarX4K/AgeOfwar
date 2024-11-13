package AgeOfWar.Logic;

import AgeOfWar.Characters.Knight;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Hitboxes {

    public List<Rectangle> getAllKnightHitboxes(List<Knight> knights) {
        List<Rectangle> hitboxes = new ArrayList<>();
        for (Knight knight : knights) {
            hitboxes.add(new Rectangle(knight.getX(), knight.getY(), knight.getWidth(), knight.getHeight()));
        }
        return hitboxes;
    }

    // Metoda pro získání hitboxů pro další typy postav, pokud je to potřeba
    // Například: getAllArcherHitboxes, getAllTankHitboxes, atd.
}
