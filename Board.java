import javax.swing.*;
import java.awt.*;  // used for Dimension
import java.awt.event.*;

public class Board extends JFrame 
                   implements ActionListener{

    private JPanel board;

    // No-arg constructor that  creates the board
    public Board(){
        // build the JFrame
        this.setDefaultCloseOperation
            (JFrame.EXIT_ON_CLOSE);
        // get the screen size
        Dimension screenSize = 
            Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLayout(new BorderLayout());
        getContentPane().setBackground(Color.green);

        board = new JPanel(new FlowLayout(CENTER(), 0, 0);
}
