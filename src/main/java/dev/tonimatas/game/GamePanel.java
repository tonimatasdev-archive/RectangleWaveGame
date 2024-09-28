package dev.tonimatas.game;

import dev.tonimatas.entities.Enemy;
import dev.tonimatas.entities.Entity;
import dev.tonimatas.entities.Player;
import dev.tonimatas.listeners.GameMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {
    public static Player player;
    public static List<Entity> entities = new ArrayList<>();

    public GamePanel(JFrame frame) {
        player = new Player(new Point(frame.getWidth() / 2 - 10, frame.getHeight() / 2 - 10));
        setSize(frame.getSize());
        setVisible(true);
        addMouseListener(new GameMouseListener());
    }

    public void update() {
        player.update();

        for (Entity entity : entities) {
            entity.update();
        }

        if (WaveThread.spawningWave && WaveThread.timeToWave == 0) {
            int enemyCount = new Random().nextInt(1, WaveThread.maxSpawnCount);

            for (int x = enemyCount; x > 0; x--) {
                GamePanel.entities.add(new Enemy());
            }

            WaveThread.spawningWave = false;
            WaveThread.inWave = true;
        }

        if (entities.isEmpty() && WaveThread.inWave) {
            WaveThread.inWave = false;
            WaveThread.spawningWave = true;
            WaveThread.wave++;
            WaveThread.timeToWave = 3;
            WaveThread.maxSpawnCount++;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);

        String waveText = "Wave " + WaveThread.wave;
        Rectangle2D waveTextBounds = g.getFont().getStringBounds(waveText, g.getFontMetrics().getFontRenderContext());
        double waveTextX = (double) getWidth() / 2 - waveTextBounds.getCenterX();

        g.drawString(waveText, (int) waveTextX, 20);

        if (WaveThread.timeToWave != 0) {
            String waveTimeText = String.valueOf(WaveThread.timeToWave);
            Rectangle2D waveTimeTextBounds = g.getFont().getStringBounds(waveTimeText, g.getFontMetrics().getFontRenderContext());
            double waveTimeTextX = (double) getWidth() / 2 - waveTimeTextBounds.getCenterX();

            g.drawString(waveTimeText, (int) waveTimeTextX, 35);
        }


        player.paint(g);

        for (Entity entity : entities) {
            entity.paint(g);
        }
    }
}
