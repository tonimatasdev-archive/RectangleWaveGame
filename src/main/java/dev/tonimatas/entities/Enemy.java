package dev.tonimatas.entities;

import dev.tonimatas.Main;
import dev.tonimatas.game.GamePanel;

import java.awt.*;
import java.util.Random;

public class Enemy extends Entity {
    protected Random random = new Random();
    protected int movementSameDirection = 0;
    protected boolean directionX = false;
    protected int hearts;

    public Enemy() {
        super(getSpawnPosition(), new Rectangle(20, 20), Color.RED, true, 1);
        
        this.hearts = random.nextInt(1, 4);

        switch (hearts) {
            case 1 -> color = Color.RED;
            case 2 -> color = new Color(255, 100, 0);
            case 3 -> color = Color.YELLOW;
        }
    }

    public static Point getSpawnPosition() {
        while (true) {
            Random random = new Random();

            Point point = new Point(random.nextInt(Main.panelWidth), random.nextInt(Main.panelHeight));

            if (GamePanel.player.shape.getLocation().distance(point) >= 400 && 
                    Main.panelWidth - 20 > point.x && Main.panelHeight - 20 > point.y) {
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

        if (shape.intersects(GamePanel.player.shape)) {
            GamePanel.player.kill();
        }
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
