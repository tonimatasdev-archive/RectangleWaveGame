package dev.tonimatas.server;

import dev.tonimatas.entities.Player;
import dev.tonimatas.world.World;

import java.util.Map;

public interface Server {
    Map<String, Player> getPlayers();
    
    World getWorld();
    
    void tick();
}
