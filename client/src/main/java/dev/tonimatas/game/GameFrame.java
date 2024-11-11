package dev.tonimatas.game;

import dev.tonimatas.listeners.GameFocusListener;
import dev.tonimatas.listeners.GameKeyListener;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class GameFrame extends JFrame {
    public GameFrame() {
        setSize(1024, 1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setAutoRequestFocus(true);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        addKeyListener(new GameKeyListener());
        addFocusListener(new GameFocusListener());

        GamePanel panel = new GamePanel(this);

        add(panel);

        long frameTime = 1000 / 60;

        new WaveSystem().start();

        while (true) {
            long time = System.currentTimeMillis();

            panel.update();
            panel.repaint();

            long elapsedTime = System.currentTimeMillis() - time;
            long sleepTime = frameTime - elapsedTime;

            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
