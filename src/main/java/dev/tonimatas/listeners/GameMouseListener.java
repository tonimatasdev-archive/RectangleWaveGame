package dev.tonimatas.listeners;

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
            if (entity.shape.contains(e.getX(), e.getY())) {
                entity.kill();
                break;
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
