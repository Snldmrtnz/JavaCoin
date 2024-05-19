package CommonlyUsedClasses;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class GJLabel extends JLabel {

    public GJLabel(String text, Font font, Color background, Color foreground) {
        super(text);
        setFont(font);
        setBorder(new EmptyBorder(5, 15, 5, 15));
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setBackground(background);
        setForeground(foreground);
    }
}