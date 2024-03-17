import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

public class RoundedPanel extends JPanel {

    /** Sets if it has an High Quality view */
    private boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    private Dimension arcs;

    public RoundedPanel() {
        super();

        arcs = new Dimension(0, 0);
        setOpaque(false);
    }

    public RoundedPanel(int arcSize) {
        super();

        arcs = new Dimension(arcSize, arcSize);
        setOpaque(false);
    }

    public RoundedPanel(LayoutManager lm) {
        super();
        setOpaque(false);
        setLayout(lm);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;

        // sets antialiasing if HQ
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        // draws the rounded opaque panel with borders
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
        graphics.drawRoundRect(0, 0, width, height, arcs.width, arcs.height);

        // sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }
}
