package AgeOfWar.Logic;

import AgeOfWar.Characters.Knight;
import java.util.ArrayList;
import java.util.List;

public class Collisions {
    private Attack attack;
    private Hitboxes hitboxes;
    private Moving moving;

    public Collisions(Moving moving, Hitboxes hitboxes, Attack attack) {
        this.moving = moving;
        this.hitboxes = hitboxes;
        this.attack = attack;
    }

    public void checkCollisions(List<Knight> knights, List<Knight> enemyKnights) {
        List<Knight> defeatedKnights = new ArrayList<>();
        List<Knight> defeatedEnemyKnights = new ArrayList<>();

        for (Knight knight : new ArrayList<>(knights)) {
            Knight nearestEnemy = findNearestEnemy(knight, enemyKnights);
            if (nearestEnemy != null && hitboxes.knightsCollide(knight, nearestEnemy)) {
                moving.stopKnight(knight);
                moving.stopKnight(nearestEnemy);
                attack.performAttack(knight, nearestEnemy, knights, enemyKnights);

                if (!knight.isAlive()) {
                    defeatedKnights.add(knight);
                }
                if (!nearestEnemy.isAlive()) {
                    defeatedEnemyKnights.add(nearestEnemy);
                }
            }
        }

        // Remove all defeated knights after the iteration
        knights.removeAll(defeatedKnights);
        enemyKnights.removeAll(defeatedEnemyKnights);
    }

    private Knight findNearestEnemy(Knight knight, List<Knight> enemyKnights) {
        Knight nearestEnemy = null;
        int nearestDistance = Integer.MAX_VALUE;

        for (Knight enemyKnight : enemyKnights) {
            int distance = Math.abs(knight.getX() - enemyKnight.getX());
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestEnemy = enemyKnight;
            }
        }
        return nearestEnemy;
    }
}

