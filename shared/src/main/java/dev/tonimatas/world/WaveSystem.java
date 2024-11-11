package dev.tonimatas.world;

import dev.tonimatas.entities.Enemy;
import dev.tonimatas.utils.FontUtils;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WaveSystem extends Thread {
    public World world;
    public int wave = 1;
    public int timeToWave = 10;
    public boolean spawningWave = true;
    public boolean inWave = false;

    public void spawnWave() {
        int minSpawnCount = (int) (0.7 * wave);
        
        int enemyCount = new Random().nextInt(minSpawnCount == 0 ? 1 : minSpawnCount, wave + 2);

        for (int x = enemyCount; x > 0; x--) {
            world.entities.add(new Enemy(world));
        }

        spawningWave = false;
        inWave = true;
    }

    public void nextWave() {
        inWave = false;
        spawningWave = true;
        wave++;
        timeToWave = 3;

        new WaveSystem().start();
    }

    public void drawWaveStrings(Graphics2D g) {
        g.setColor(Color.WHITE);

        String waveText = "Wave " + wave;
        double waveTextX = (double) world.width / 2 - FontUtils.getBounds(g, waveText).getCenterX();

        g.drawString(waveText, (int) waveTextX, 20);

        if (timeToWave != 0) {
            String waveTimeText = String.valueOf(timeToWave);
            double waveTimeTextX = (double) world.width / 2 - FontUtils.getBounds(g, waveTimeText).getCenterX();

            g.drawString(waveTimeText, (int) waveTimeTextX, 38);
        }

        if (!world.entities.isEmpty()) {
            String enemyCountText = "Remaining: " + world.entities.size();
            double enemyCountTextX = (double) world.width - 10 - FontUtils.getBounds(g, enemyCountText).getWidth();

            g.drawString(enemyCountText, (int) enemyCountTextX, 20);
        }
    }

    public void reset() {
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
