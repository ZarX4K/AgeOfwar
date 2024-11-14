package AgeOfWar.Logic;

import AgeOfWar.Characters.Knight;
import java.util.List;
import java.util.Random;

public class Attack {
    private static final long ATTACK_INTERVAL = 500;
    private static final Random RANDOM = new Random();
    private Moving moving;
    private boolean healthBoostApplied = false;

    public Attack(Moving moving) {
        this.moving = moving;
    }

    public void performAttack(Knight knight, Knight enemyKnight, List<Knight> knights, List<Knight> enemyKnights) {
        long currentTime = System.currentTimeMillis();

        if (knight.isAlive() && enemyKnight.isAlive() && currentTime - knight.getLastAttackTime() >= ATTACK_INTERVAL) {
            knight.takeDamage(enemyKnight.getDamage());
            enemyKnight.takeDamage(knight.getDamage());
            knight.setLastAttackTime(currentTime);
            enemyKnight.setLastAttackTime(currentTime);

            if (!healthBoostApplied && knight.getHealth() <= 60 && enemyKnight.getHealth() <= 60) {
                if (RANDOM.nextBoolean()) {
                    knight.setHealth(knight.getHealth() + 25);
                    System.out.println("Player knight narrowly avoids defeat!");
                } else {
                    enemyKnight.setHealth(enemyKnight.getHealth() + 25);
                    System.out.println("Enemy knight narrowly avoids defeat!");
                }
                healthBoostApplied = true;
            }

            // Handle defeated knights
            if (knight.getHealth() <= 0) {
                knights.remove(knight);
                System.out.println("Player knight defeated!");
            } else {
                // If knight is still alive, set it to move again
                knight.setMoving(true);
            }

            if (enemyKnight.getHealth() <= 0) {
                enemyKnights.remove(enemyKnight);
                System.out.println("Enemy knight defeated!");
            } else {
                // If enemy knight is still alive, set it to move again
                enemyKnight.setMoving(true);
            }
        }
    }

}