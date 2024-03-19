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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // draw the rounded button
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, _cornerRadius, _cornerRadius);

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

    @Override
    /*
     * Method that chages where a mouse click is registered
     */
    public boolean contains(int x, int y) {
        // Get the dimensions of the button
        Dimension size = getSize();
        // Check if the point is within the rectangle bounds
        if (super.contains(x, y)) {
            // Calculate the dimensions of the rectangle excluding the rounded corners
            int width = size.width - _cornerRadius * 2;
            int height = size.height - _cornerRadius * 2;

            // Calculate the distance from the click point to the nearest edge of the
            // rectangle
            int dx = Math.min(Math.abs(x - _cornerRadius), Math.abs(x - width - _cornerRadius));
            int dy = Math.min(Math.abs(y - _cornerRadius), Math.abs(y - height - _cornerRadius));

            // Calculate the distance from the click point to the nearest corner of the
            // rectangle
            double distanceToCorner = Math.sqrt(dx * dx + dy * dy);

            // If the distance to the corner is less than the corner radius, the point is
            // within the rounded corner
            if (distanceToCorner <= _cornerRadius) {
                return true;
            }
        }
        return false;
    }
}
