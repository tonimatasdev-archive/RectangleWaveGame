package dev.tonimatas.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    public static boolean w, a, s, d;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> w = true;
            case KeyEvent.VK_A -> a = true;
            case KeyEvent.VK_S -> s = true;
            case KeyEvent.VK_D -> d = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> w = false;
            case KeyEvent.VK_A -> a = false;
            case KeyEvent.VK_S -> s = false;
            case KeyEvent.VK_D -> d = false;
        }
    }
}
