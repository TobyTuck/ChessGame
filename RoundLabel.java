import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundLabel extends JLabel {

    private int cornerRadius;

    public RoundLabel(int arcSize) {
        super();
        this.cornerRadius = arcSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Paint background
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

        // Paint text
        super.paintComponent(g2d);

        g2d.dispose();
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}
