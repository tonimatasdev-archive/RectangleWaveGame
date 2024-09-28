package dev.tonimatas;

import dev.tonimatas.game.GamePanel;
import dev.tonimatas.game.WaveThread;
import dev.tonimatas.listeners.GameKeyListener;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int panelHeight = 985;
    public static int panelWidth = 1008;
    public static boolean exit = false;

    public static void main(String[] args) {
        JFrame window = new JFrame("Rectangle Wave Game");

        window.setSize(1024, 1024);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setFocusable(true);
        window.setAutoRequestFocus(true);
        window.setResizable(false);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.addKeyListener(new GameKeyListener());

        GamePanel panel = new GamePanel(window);

        window.add(panel);

        long frameTime = 1000 / 60;

        new WaveThread().start();

        while (!exit) {
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