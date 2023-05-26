import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

public class RoundedPanel extends JPanel{

    /** Stroke size, it is recommended to set it to 1 for better view */
    private int strokeSize = 1;
    /** Sets if it has an High Quality view */
    private boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    private Dimension arcs = new Dimension(20, 20);

	//FOLLOWING CODES GOES HERE

    public RoundedPanel(){
        super();
        setOpaque(false);
    }

    public RoundedPanel(LayoutManager lm){
        super();
        setOpaque(false);
        setLayout(lm);
    }

    @Override
    public void paintComponent(Graphics g){
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
        // graphics.setColor(getForeground());
        // graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width, height, arcs.width, arcs.height);

        // sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }
}
