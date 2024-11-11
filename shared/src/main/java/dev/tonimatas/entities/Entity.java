package dev.tonimatas.entities;

import dev.tonimatas.world.World;

import java.awt.*;
import java.util.UUID;

public abstract class Entity {
    public World world;
    public Rectangle shape;
    public Color color;
    public boolean filled;
    public int speed;
    public boolean death;

    public Entity(World world, Point position, Rectangle shape, Color color, boolean filled, int speed) {
        this.world = world;
        shape.setLocation(position);
        this.shape = shape;
        this.color = color;
        this.filled = filled;
        this.speed = speed;
    }
    
    public Point getPosition() {
        return shape.getLocation();
    }

    public abstract void update();

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(shape.x, shape.y, shape.width, shape.height);
    }

    public void kill() {
        death = true;
    }
}
