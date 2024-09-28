package dev.tonimatas.entities;

import dev.tonimatas.game.GamePanel;

import java.awt.*;
import java.util.Random;

public class Enemy extends Entity {
    protected Random random = new Random();
    protected int movementSameDirection = 0;
    protected boolean directionX = false;

    public Enemy() {
        super(getSpawnPosition(), new Rectangle(20, 20), Color.RED, true, 1);
    }

    public static Point getSpawnPosition() {
        while (true) {
            Random random = new Random();

            Point point = new Point(random.nextInt(450), random.nextInt(450));

            if (GamePanel.player.shape.getLocation().distance(point) >= 100) {
                return point;
            }
        }
    }

    @Override
    public void update() {
        Point playerPos = GamePanel.player.shape.getLocation();

        if (movementSameDirection <= 0) {
            directionX = random.nextBoolean();
            movementSameDirection = random.nextInt(40);
        }

        if (playerPos.x != shape.x && (directionX || playerPos.y == shape.y)) {
            movementSameDirection--;

            if (playerPos.x > shape.x) {
                shape.x += speed;
            } else {
                shape.x -= speed;
            }
        }

        if (playerPos.y != shape.y && (!directionX || playerPos.x == shape.x)) {
            movementSameDirection--;

            if (playerPos.y > shape.y) {
                shape.y += speed;
            } else {
                shape.y -= speed;
            }
        }
    }
}
