package AgeOfWar.Logic;

import AgeOfWar.Characters.Knight;
import java.util.List;
import java.util.Random;

public class Attack {

    private static final long ATTACK_INTERVAL = 500; // 0.5 seconds
    private static final Random RANDOM = new Random();
    private Moving moving;
    private boolean healthBoostApplied = false; // Flag to track if the health boost has been applied

    public Attack(Moving moving) {
        this.moving = moving;
    }

    public void performAttack(Knight knight, Knight enemyKnight, List<Knight> knights, List<Knight> enemyKnights) {
        long currentTime = System.currentTimeMillis();

        if (knight.isAlive() && enemyKnight.isAlive() && currentTime - knight.getLastAttackTime() >= ATTACK_INTERVAL) {
            // Knights deal damage to each other
            knight.takeDamage(enemyKnight.getDamage());
            enemyKnight.takeDamage(knight.getDamage());
            System.out.println("Knights are attacking each other.");
            System.out.println(enemyKnight.getHealth());
            System.out.println(knight.getHealth());
            knight.setLastAttackTime(currentTime);
            enemyKnight.setLastAttackTime(currentTime);

            // Only apply the health boost when both knights have 60 or less health, but only once
            if (!healthBoostApplied && knight.getHealth() <= 60 && enemyKnight.getHealth() <= 60) {
                // Use random selection to decide which knight will be boosted
                switch (RANDOM.nextInt(2)) {  // random 0 or 1
                    case 0:
                        knight.setHealth(knight.getHealth() + 25);  // Boost player knight
                        System.out.println("Player knight narrowly avoids defeat!");
                        break;
                    case 1:
                        enemyKnight.setHealth(enemyKnight.getHealth() + 25);  // Boost enemy knight
                        System.out.println("Enemy knight narrowly avoids defeat!");
                        break;
                }
                healthBoostApplied = true; // Set the flag to true after the boost is applied
            }

            // If health still <= 0, knight is defeated
            if (knight.getHealth() <= 0) {
                System.out.println("Player knight defeated!");
                knights.remove(knight);
            }
            if (enemyKnight.getHealth() <= 0) {
                System.out.println("Enemy knight defeated!");
                enemyKnights.remove(enemyKnight);
            }
        }

        // Ensure knights that are still alive are set to moving
        if (knight.isAlive()) knight.setMoving(true);
        if (enemyKnight.isAlive()) enemyKnight.setMoving(true);
    }

}
