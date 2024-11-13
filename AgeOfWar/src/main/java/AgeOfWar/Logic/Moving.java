package AgeOfWar.Logic;

import AgeOfWar.Characters.Knight;

import java.util.List;

public class Moving {

    // Method to move a knight towards its target
    public void moveCharacterTowards(Knight knight, Knight target) {
        if (knight == null || target == null || !knight.isMoving()) {
            return;  // Ensure knight moves only if isMoving is true
        }

        // Get the current position of the knight and the target
        int knightX = knight.getX();
        int knightY = knight.getY();
        int targetX = target.getX();
        int targetY = target.getY();

        // Calculate the direction vector
        double directionX = targetX - knightX;
        double directionY = targetY - knightY;

        // Normalize the direction vector to avoid diagonal speed being faster
        double length = Math.sqrt(directionX * directionX + directionY * directionY);
        if (length != 0) {
            directionX /= length;
            directionY /= length;
        }

        // Update the knight's position by a small step in the direction of the target
        int moveSpeed = knight.getMoveSpeed(); // Make sure you have a method to get movement speed
        knight.setX(knightX + (int) (directionX * moveSpeed));
        knight.setY(knightY + (int) (directionY * moveSpeed));
    }

    // Find the closest enemy knight (you already have this logic)
    public Knight findClosestEnemy(Knight knight, List<Knight> enemyKnights) {
        Knight closestKnight = null;
        double minDistance = Double.MAX_VALUE;

        for (Knight enemy : enemyKnights) {
            double distance = Math.hypot(knight.getX() - enemy.getX(), knight.getY() - enemy.getY());
            if (distance < minDistance) {
                minDistance = distance;
                closestKnight = enemy;
            }
        }

        return closestKnight;
    }
}
