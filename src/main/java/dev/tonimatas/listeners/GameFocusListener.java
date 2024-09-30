package dev.tonimatas.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GameFocusListener implements FocusListener {
    @Override
    public void focusGained(FocusEvent e) {
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        GameKeyListener.reset();
    }
}
