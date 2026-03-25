import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
public class Pole extends JButton {

    public Pole(Color kolor) {
        setPreferredSize(new Dimension(50, 50));
        setBackground(kolor);
        setBorder(new LineBorder(Color.GRAY, 1));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
    }
}