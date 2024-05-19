package CommonlyUsedClasses;

import java.awt.*;
import javax.swing.*;

public class GJButton extends JButton {

    public GJButton(String text, Color background, Color foreground, Font font, String tooltip, int width, int height) {
        super(text);
        setBackground(background);
        setForeground(foreground);
        setFont(font);
        setFocusable(false);
        setToolTipText(tooltip);
        setSize(width, height);
        setBorder(null);
    }

    public GJButton(String text, Font font, int width, int height) {
        super(text);
        setBackground(null);
        setForeground(Color.WHITE);
        setFont(font);
        setFocusable(false);
        setSize(width, height);
        setBorder(null);
    }
}
