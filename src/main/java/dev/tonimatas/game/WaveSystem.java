package dev.tonimatas.game;

import dev.tonimatas.Main;
import dev.tonimatas.entities.Enemy;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WaveSystem extends Thread {
    public static int wave = 1;
    public static int timeToWave = 10;
    public static boolean spawningWave = true;
    public static boolean inWave = false;
    public static int maxSpawnCount = 3;

    @Override
    public void run() {
        do {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (timeToWave > 0) {
                timeToWave--;
            }

        } while (timeToWave != 0);
    }
    
    public static void spawnWave() {
        int enemyCount = new Random().nextInt(1, WaveSystem.maxSpawnCount);

        for (int x = enemyCount; x > 0; x--) {
            GamePanel.entities.add(new Enemy());
        }

        WaveSystem.spawningWave = false;
        WaveSystem.inWave = true;
    }
    
    public static void nextWave() {
        WaveSystem.inWave = false;
        WaveSystem.spawningWave = true;
        WaveSystem.wave++;
        WaveSystem.timeToWave = 3;
        WaveSystem.maxSpawnCount++;
        
        new WaveSystem().start();
    }
    
    public static void drawWaveStrings(Graphics2D g) {
        g.setColor(Color.WHITE);
        
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 18));
        String waveText = "Wave " + WaveSystem.wave;
        Rectangle2D waveTextBounds = g.getFont().getStringBounds(waveText, g.getFontMetrics().getFontRenderContext());
        double waveTextX = (double) Main.panelWidth / 2 - waveTextBounds.getCenterX();

        g.drawString(waveText, (int) waveTextX, 20);

        if (WaveSystem.timeToWave != 0) {
            String waveTimeText = String.valueOf(WaveSystem.timeToWave);
            Rectangle2D waveTimeTextBounds = g.getFont().getStringBounds(waveTimeText, g.getFontMetrics().getFontRenderContext());
            double waveTimeTextX = (double) Main.panelWidth / 2 - waveTimeTextBounds.getCenterX();

            g.drawString(waveTimeText, (int) waveTimeTextX, 35);
        }
    }
}
