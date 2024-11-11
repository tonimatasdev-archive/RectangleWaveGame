package dev.tonimatas;

import dev.tonimatas.server.Server;

public class API {
    private static Server server;
    
    public static void setServer(Server server) {
        if (API.server == null) {
            API.server = server;
        }
    }
}
