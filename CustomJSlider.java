
/*
This class makes a custom slider
*/
import javax.swing.JSlider;
import java.awt.Color;

public class CustomJSlider extends JSlider {

    public CustomJSlider(int min, int max) {
        super(min, max);
        setOpaque(false);
        setForeground(new Color(25, 25, 112));
        setUI(new JSliderUI(this));
    }

    @Override
    public int getValue() {
        return super.getValue();
    }
}