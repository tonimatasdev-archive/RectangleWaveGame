package dev.tonimatas.listeners;

import dev.tonimatas.entities.Enemy;
import dev.tonimatas.entities.Entity;
import dev.tonimatas.game.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (GamePanel.entities.isEmpty()) return;

        for (Entity entity : GamePanel.entities) {
            if (entity instanceof Enemy enemy) {
                if (entity.shape.contains(e.getX(), e.getY())) {
                    enemy.hurt();
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
