package dev.tonimatas.game;

import dev.tonimatas.entities.Enemy;
import dev.tonimatas.utils.FontUtils;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WaveSystem extends Thread {
    public static int wave = 1;
    public static int timeToWave = 10;
    public static boolean spawningWave = true;
    public static boolean inWave = false;

    public static void spawnWave() {
        int minSpawnCount = (int) (0.7 * wave);
        
        int enemyCount = new Random().nextInt(minSpawnCount == 0 ? 1 : minSpawnCount, wave + 2);

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

        new WaveSystem().start();
    }

    public static void drawWaveStrings(Graphics2D g) {
        g.setColor(Color.WHITE);

        String waveText = "Wave " + WaveSystem.wave;
        double waveTextX = (double) GameFrame.panelWidth / 2 - FontUtils.getBounds(g, waveText).getCenterX();

        g.drawString(waveText, (int) waveTextX, 20);

        if (WaveSystem.timeToWave != 0) {
            String waveTimeText = String.valueOf(WaveSystem.timeToWave);
            double waveTimeTextX = (double) GameFrame.panelWidth / 2 - FontUtils.getBounds(g, waveTimeText).getCenterX();

            g.drawString(waveTimeText, (int) waveTimeTextX, 38);
        }

        if (!GamePanel.entities.isEmpty()) {
            String enemyCountText = "Remaining: " + GamePanel.entities.size();
            double enemyCountTextX = (double) GameFrame.panelWidth - 10 - FontUtils.getBounds(g, enemyCountText).getWidth();

            g.drawString(enemyCountText, (int) enemyCountTextX, 20);
        }
    }

    public static void reset() {
        wave = 1;
        timeToWave = 10;
        spawningWave = true;
        inWave = false;
    }

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
}
