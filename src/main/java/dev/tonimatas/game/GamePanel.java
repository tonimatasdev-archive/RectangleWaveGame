package dev.tonimatas.game;

import dev.tonimatas.Main;
import dev.tonimatas.entities.Entity;
import dev.tonimatas.entities.Player;
import dev.tonimatas.listeners.GameMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    public static Player player;
    public static List<Entity> entities = new ArrayList<>();

    public GamePanel(JFrame frame) {
        player = new Player(new Point(Main.panelWidth / 2 - 10, Main.panelHeight / 2 - 10));
        setSize(frame.getSize());
        setVisible(true);
        addMouseListener(new GameMouseListener());
    }

    public void update() {
        player.update();

        for (Entity entity : entities) {
            entity.update();
        }

        if (WaveSystem.spawningWave && WaveSystem.timeToWave == 0) {
            WaveSystem.spawnWave();
        }

        if (entities.isEmpty() && WaveSystem.inWave) {
            WaveSystem.nextWave();
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.panelWidth, Main.panelHeight);

        player.paint(g2d);

        for (Entity entity : entities) {
            entity.paint(g2d);
        }
        
        WaveSystem.drawWaveStrings(g2d);
    }
}
