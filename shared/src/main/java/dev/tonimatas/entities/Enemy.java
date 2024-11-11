package dev.tonimatas.entities;

import dev.tonimatas.world.World;

import java.awt.*;
import java.util.Random;

public class Enemy extends Entity {
    protected Random random = new Random();
    protected int movementSameDirection = 0;
    protected boolean directionX = false;
    protected int hearts;
    protected Player target;

    public Enemy(World world) {
        super(world, getSpawnPosition(world), new Rectangle(20, 20), Color.RED, true, 1);

        this.hearts = random.nextInt(1, 4);

        switch (hearts) {
            case 1 -> color = Color.RED;
            case 2 -> color = new Color(255, 100, 0);
            case 3 -> color = Color.YELLOW;
        }

        nearestPlayer();
    }

    public static Point getSpawnPosition(World world) {
        while (true) {
            Random random = new Random();

            Point point = new Point(random.nextInt(world.width), random.nextInt(world.height));

            if (world.getCenter().distance(point) >= 400 &&
                    world.width - 20 > point.x && world.height - 20 > point.y) {
                return point;
            }
        }
    }

    @Override
    public void update() {
        if (target == null) {
            return;
        }
        
        Point playerPos = target.getPosition();

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

        if (shape.intersects(target.shape)) {
            target.kill();
        }
    }
    
    public void nearestPlayer() {
        Player nearestPlayer = null;
        
        for (Player player : world.server.getPlayers().values()) {
            if (nearestPlayer == null) {
                nearestPlayer = player;
            } else {
                if (nearestPlayer.getPosition().distance(this.getPosition()) > player.getPosition().distance(this.getPosition())) {
                    nearestPlayer = player;
                }
            }
        }
        
        target = nearestPlayer;
    }

    public void hurt() {
        hearts--;

        switch (hearts) {
            case 0 -> kill();
            case 1 -> color = Color.RED;
            case 2 -> color = new Color(255, 100, 0);
            case 3 -> color = Color.YELLOW;
        }

        if (hearts == 0) {
            kill();
        }
    }
}
