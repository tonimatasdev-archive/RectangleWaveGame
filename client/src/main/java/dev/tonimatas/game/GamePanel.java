package dev.tonimatas.game;

import dev.tonimatas.entities.Entity;
import dev.tonimatas.entities.Player;
import dev.tonimatas.listeners.GameKeyListener;
import dev.tonimatas.listeners.GameMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    public static boolean playerIsDeath = false;
    public static Player player;
    public static List<Entity> entities = new ArrayList<>();

    public GamePanel(JFrame frame) {
        player = new Player();
        setSize(frame.getSize());
        setVisible(true);
        addMouseListener(new GameMouseListener());
    }

    public static void resetGame() {
        playerIsDeath = false;
        player = new Player();
        entities.clear();

        JOptionPane.showMessageDialog(null, "You dead in the wave " + WaveSystem.wave + ".");

        GameKeyListener.reset();
        WaveSystem.reset();

        new WaveSystem().start();
    }

    public void update() {
        if (playerIsDeath) {
            resetGame();
            return;
        }

        player.update();

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
        g2d.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 18));

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameFrame.panelWidth, GameFrame.panelHeight);

        player.paint(g2d);

        for (Entity entity : entities) {
            entity.paint(g2d);
        }

        WaveSystem.drawWaveStrings(g2d);

        String energyCount = "Energy: " + (int) GamePanel.player.energy;

        g.drawString(energyCount, 8, GameFrame.panelHeight - 10);
    }
}
