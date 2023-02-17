import javax.swing.*;
import java.awt.*;

public class Tester extends JFrame {

    public Tester() {
        super("My Frame");

        // Create a JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.red);
        layeredPane.setLayout(new BorderLayout());
        
        // Create and add components to the JLayeredPane
        JLabel label1 = new JLabel("Label 1");
        layeredPane.add(label1, BorderLayout.CENTER, JLayeredPane.DEFAULT_LAYER);
        JLabel label2 = new JLabel("Label 2");
        label2.setBounds(0, 0, 100, 100);
        layeredPane.add(label2, BorderLayout.CENTER, JLayeredPane.PALETTE_LAYER);
        
        // Add the JLayeredPane to the JFrame
        add(layeredPane);
        
        // Set the size and make the frame visible
        setSize(700, 700);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Tester();
    }
}
