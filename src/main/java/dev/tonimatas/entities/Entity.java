package dev.tonimatas.entities;

import dev.tonimatas.game.GamePanel;

import java.awt.*;

public abstract class Entity {
    public Rectangle shape;
    public Color color;
    public boolean filled;
    public int speed;

    public Entity(Point position, Rectangle shape, Color color, boolean filled, int speed) {
        shape.setLocation(position);
        this.shape = shape;
        this.color = color;
        this.filled = filled;
        this.speed = speed;
    }

    public abstract void update();

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(shape.x, shape.y, shape.width, shape.height);
    }

    public void kill() {
        GamePanel.entities.remove(this);
    }
}
