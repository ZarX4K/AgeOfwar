package AgeOfWar.Logic;

import AgeOfWar.Characters.Knight;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Collisions {

    private Attack attack;

    public Collisions(Moving moving) {
        this.attack = new Attack(moving);  // Pass the moving instance here
    }
    public void checkCollisions(List<Knight> knights, List<Knight> enemyKnights) {
        List<Knight> knightsToRemove = new ArrayList<>();
        List<Knight> enemyKnightsToRemove = new ArrayList<>();

        for (int i = 0; i < knights.size(); i++) {
            Knight knight = knights.get(i);
            if (!knight.isAlive()) continue;

            for (int j = 0; j < enemyKnights.size(); j++) {
                Knight enemyKnight = enemyKnights.get(j);
                if (!enemyKnight.isAlive()) continue;

                // Kontrola kolize hitboxů
                if (new Rectangle(knight.getX(), knight.getY(), knight.getWidth(), knight.getHeight())
                        .intersects(new Rectangle(enemyKnight.getX(), enemyKnight.getY(), enemyKnight.getWidth(), enemyKnight.getHeight()))) {

                    // Proveď útok
                    attack.performAttack(knight, enemyKnight, knights, enemyKnights);
                    knight.setMoving(false);
                    enemyKnight.setMoving(false);

                    // Přidej do seznamu pro odstranění, pokud je mrtvý
                    if (!knight.isAlive() && !knightsToRemove.contains(knight)) {
                        knightsToRemove.add(knight);
                    }
                    if (!enemyKnight.isAlive() && !enemyKnightsToRemove.contains(enemyKnight)) {
                        enemyKnightsToRemove.add(enemyKnight);
                    }
                }
            }
        }

        // Odstraň mrtvé rytíře mimo hlavní smyčku
        knights.removeAll(knightsToRemove);
        enemyKnights.removeAll(enemyKnightsToRemove);
    }
}
