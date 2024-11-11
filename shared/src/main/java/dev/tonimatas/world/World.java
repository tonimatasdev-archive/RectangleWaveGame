package dev.tonimatas.world;

import dev.tonimatas.entities.Entity;
import dev.tonimatas.server.Server;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {
    public int height = 985;
    public int width = 1008;
    public final Server server;
    public List<Entity> entities = new ArrayList<>();
    
    public World(Server server) {
        this.server = server;
    }
    
    public void tick() {
        for (Entity entity : entities) {
            entity.update();
        }

        for (Entity entity : entities) {
            if (entity.death) {
                removeEntity(entity);
            }
        }
    }
    
    private void removeEntity(Entity entity) {
        entities.remove(entity.uuid);
    }
    
    public Point getCenter() {
        return new Point(width / 2 - 10, height / 2 - 10);
    }
}
