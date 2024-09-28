package dev.tonimatas.utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class FontUtils {
    public static Rectangle2D getBounds(Graphics2D graphics2D, String text) {
        return graphics2D.getFont().getStringBounds(text, graphics2D.getFontMetrics().getFontRenderContext());
    }
}
