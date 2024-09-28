package dev.tonimatas.entities;

import dev.tonimatas.listeners.GameKeyListener;

import java.awt.*;

public class Player extends Entity {
    public Player(Point position) {
        super(position, new Rectangle(20, 20), Color.GREEN, true, 1);
    }

    @Override
    public void update() {
        if (GameKeyListener.w) {
            shape.y -= speed;
        }

        if (GameKeyListener.a) {
            shape.x -= speed;
        }

        if (GameKeyListener.s) {
            shape.y += speed;
        }

        if (GameKeyListener.d) {
            shape.x += speed;
        }
    }
    
    @Override
    public void kill() {
        Runtime.getRuntime().halt(0);
    }
}
