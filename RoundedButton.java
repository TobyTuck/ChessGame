import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private int _cornerRadius;
    private Icon _icon;

    public RoundedButton(int cornerRadius) {
        super();
        setOpaque(false);
        setContentAreaFilled(false);

        // set field value
        _cornerRadius = cornerRadius;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw the rounded button
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, _cornerRadius, _cornerRadius);

        // draw the icon (if applicable)
        Icon icon = getIcon();
        if (icon != null) {
            int x = (getWidth() - icon.getIconWidth()) / 2;
            int y = (getHeight() - icon.getIconHeight()) / 2;
            icon.paintIcon(this, g2d, x, y);
        }

        g2d.dispose();
    }

    @Override
    public void paintBorder(Graphics g) {
        // Don't paint the border
    }

    @Override
    public void setIcon(Icon icon) {
        super.setIcon(icon);
    }
}
