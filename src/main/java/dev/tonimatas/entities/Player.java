package dev.tonimatas.entities;

import dev.tonimatas.Main;
import dev.tonimatas.game.WaveSystem;
import dev.tonimatas.listeners.GameKeyListener;

import javax.swing.*;
import java.awt.*;

public class Player extends Entity {
    public boolean needFullLoad = false;
    public double energy = 1;
    public double maxEnergy = 50;
    
    public Player(Point position) {
        super(position, new Rectangle(20, 20), Color.GREEN, true, 1);
    }

    @Override
    public void update() {

        if (GameKeyListener.space && !needFullLoad) {
            if (energy > 1) {
                energy -= 1;
                speed = 3;
            } else {
                needFullLoad = true;
            }
        }
        
        if (GameKeyListener.w && shape.y > 0) {
            shape.y -= speed;
        }

        if (GameKeyListener.a && shape.x > 0) {
            shape.x -= speed;
        }

        if (GameKeyListener.s && shape.y < Main.panelHeight - 20) {
            shape.y += speed;
        }

        if (GameKeyListener.d && shape.x < Main.panelWidth - 20) {
            shape.x += speed;
        }
        
        speed = 1;
        
        if (energy < maxEnergy) {
            energy += 0.25F;
        } else if (energy == maxEnergy) {
            needFullLoad = false;
        }
    }

    @Override
    public void kill() {
        Main.exit = true;
        JOptionPane.showMessageDialog(null, "You dead in the wave " + WaveSystem.wave + ".");
        Runtime.getRuntime().halt(0);
    }
}
