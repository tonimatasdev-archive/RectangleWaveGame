package dev.tonimatas.game;

import dev.tonimatas.Main;

import java.util.concurrent.TimeUnit;

public class WaveThread extends Thread {
    public static int wave = 1;
    public static int timeToWave = 10;
    public static boolean spawningWave = true;
    public static boolean inWave = false;
    public static int maxSpawnCount = 3;

    @Override
    public void run() {
        while (!Main.exit) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (timeToWave > 0) {
                timeToWave--;
            }
        }
    }
}
