import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.Color;

/**
This is a class that acts as an interface b/t the user and the chess program
It creates the JFrame and JLayeredPane necessary and sets their size
*/
public class ChessBoardInitializer{

    public ChessBoardInitializer(){
        // create JFrame, JLayeredPane instances
        JFrame myFrame = new JFrame();
        JLayeredPane myJLP = new JLayeredPane();

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color darkGreen = new Color(25, 45, 25);
        // myFrame.getContentPane().setBackground(darkGreen);
        myFrame.setResizable(true);

        // set applet's tab icon
        myFrame.setIconImage(new Logo().getImage());
        myFrame.setTitle("ChessGame Application");

        // set their sizes
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myFrame.add(myJLP);

        // call the class to create the ChessGame around these piece
        ChessBoard myBoard = new ChessBoard(myFrame, myJLP);
    }
}
