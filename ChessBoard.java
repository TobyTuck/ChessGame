import javax.swing.*;
import java.awt.*;  // used for Dimension, GraphicsEnvironment, GraphicsDevice
import java.awt.event.*;
import java.util.Random;

public class ChessBoard extends JFrame /* implements ActionListener */{

    // JPanel that consists of the entire 8 * 8 chessboard
    private JPanel board;
    private JPanel south;
    private JPanel north;
    private JPanel east;
    private JPanel west;

    //holds all the individual squares
    List list;

    // individual squares of the chessboard
    private JPanel square0;
    private JPanel square1;
    private JPanel square2;
    private JPanel square3;
    private JPanel square4;
    private JPanel square5;
    private JPanel square6;
    private JPanel square7;
    private JPanel square8;
    private JPanel square9;

    private JPanel square10;
    private JPanel square11;
    private JPanel square12;
    private JPanel square13;
    private JPanel square14;
    private JPanel square15;
    private JPanel square16;
    private JPanel square17;
    private JPanel square18;
    private JPanel square19;

    private JPanel square20;
    private JPanel square21;
    private JPanel square22;
    private JPanel square23;
    private JPanel square24;
    private JPanel square25;
    private JPanel square26;
    private JPanel square27;
    private JPanel square28;
    private JPanel square29;

    private JPanel square30;
    private JPanel square31;
    private JPanel square32;
    private JPanel square33;
    private JPanel square34;
    private JPanel square35;
    private JPanel square36;
    private JPanel square37;
    private JPanel square38;
    private JPanel square39;

    private JPanel square40;
    private JPanel square41;
    private JPanel square42;
    private JPanel square43;
    private JPanel square44;
    private JPanel square45;
    private JPanel square46;
    private JPanel square47;
    private JPanel square48;
    private JPanel square49;

    private JPanel square50;
    private JPanel square51;
    private JPanel square52;
    private JPanel square53;
    private JPanel square54;
    private JPanel square55;
    private JPanel square56;
    private JPanel square57;
    private JPanel square58;
    private JPanel square59;

    private JPanel square60;
    private JPanel square61;
    private JPanel square62;
    private JPanel square63;

    /**
    No-arg constructor that creates the board- and adds all pieces to their default positions
    */
    public ChessBoard(){
        // get screen dimensions
        Dimension screenSize = 
            Toolkit.getDefaultToolkit().getScreenSize();

        // build the JFrame
        this.setDefaultCloseOperation
            (JFrame.EXIT_ON_CLOSE);
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        device.setFullScreenWindow(this);
        this.setLayout(new BorderLayout());

        // build the chessboard
        board = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        // ensure the board is square- and divides evenly into 8 
        int eightDivisible = 0;
        int boardHeight;
        do{
            boardHeight = (int)(screenSize.height * 0.9) + eightDivisible;
            ++eightDivisible;
        }while(boardHeight % 8 != 0);
        board.setPreferredSize(new Dimension(boardHeight, boardHeight));

        // build a dark green color for side panels
        Color darkGreen = new Color(0, 100, 0);

        // build supporting panels
        int length = boardHeight;
        north = new JPanel();
        north.setPreferredSize(new Dimension(screenSize.width, 
                                             (int)(0.5 * (screenSize.height - length))));
        north.setBackground(darkGreen);
        south = new JPanel();
        south.setPreferredSize(new Dimension(screenSize.width, 
                                             (int)(0.5 * (screenSize.height - length))));
        south.setBackground(darkGreen);

        west = new JPanel();
        west.setPreferredSize(new Dimension((int)(0.5 * (screenSize.width - length)), length));
        west.setBackground(darkGreen);
        east = new JPanel();
        east.setPreferredSize(new Dimension((int)(0.5 * (screenSize.width - length)), length));
        east.setBackground(darkGreen);

        // add components of each square to the list
        list = new List();

        square0 = new JPanel(); list.push(square0);
        square1 = new JPanel(); list.push(square1);
        square2 = new JPanel(); list.push(square2);
        square3 = new JPanel(); list.push(square3);
        square4 = new JPanel(); list.push(square4);
        square5 = new JPanel(); list.push(square5);
        square6 = new JPanel(); list.push(square6);
        square7 = new JPanel(); list.push(square7);
        square8 = new JPanel(); list.push(square8);
        square9 = new JPanel(); list.push(square9);
        
        square10 = new JPanel(); list.push(square10);
        square11 = new JPanel(); list.push(square11);
        square12 = new JPanel(); list.push(square12);
        square13 = new JPanel(); list.push(square13);
        square14 = new JPanel(); list.push(square14);
        square15 = new JPanel(); list.push(square15);
        square16 = new JPanel(); list.push(square16);
        square17 = new JPanel(); list.push(square17);
        square18 = new JPanel(); list.push(square18);
        square19 = new JPanel(); list.push(square19);

        square20 = new JPanel(); list.push(square20);
        square21 = new JPanel(); list.push(square21);
        square22 = new JPanel(); list.push(square22);
        square23 = new JPanel(); list.push(square23);
        square24 = new JPanel(); list.push(square24);
        square25 = new JPanel(); list.push(square25);
        square26 = new JPanel(); list.push(square26);
        square27 = new JPanel(); list.push(square27);
        square28 = new JPanel(); list.push(square28);
        square29 = new JPanel(); list.push(square29);

        square30 = new JPanel(); list.push(square30);
        square31 = new JPanel(); list.push(square31);
        square32 = new JPanel(); list.push(square32);
        square33 = new JPanel(); list.push(square33);
        square34 = new JPanel(); list.push(square34);
        square35 = new JPanel(); list.push(square35);
        square36 = new JPanel(); list.push(square36);
        square37 = new JPanel(); list.push(square37);
        square38 = new JPanel(); list.push(square38);
        square39 = new JPanel(); list.push(square39);

        square40 = new JPanel(); list.push(square40);
        square41 = new JPanel(); list.push(square41);
        square42 = new JPanel(); list.push(square42);
        square43 = new JPanel(); list.push(square43);
        square44 = new JPanel(); list.push(square44);
        square45 = new JPanel(); list.push(square45);
        square46 = new JPanel(); list.push(square46);
        square47 = new JPanel(); list.push(square47);
        square48 = new JPanel(); list.push(square48);
        square49 = new JPanel(); list.push(square49);

        square50 = new JPanel(); list.push(square50);
        square51 = new JPanel(); list.push(square51);
        square52 = new JPanel(); list.push(square52);
        square53 = new JPanel(); list.push(square53);
        square54 = new JPanel(); list.push(square54);
        square55 = new JPanel(); list.push(square55);
        square56 = new JPanel(); list.push(square56);
        square57 = new JPanel(); list.push(square57);
        square58 = new JPanel(); list.push(square58);
        square59 = new JPanel(); list.push(square59);

        square60 = new JPanel(); list.push(square60);
        square61 = new JPanel(); list.push(square61);
        square62 = new JPanel(); list.push(square62);
        square63 = new JPanel(); list.push(square63);

        //Set properties of all the items
        // in the List
        int cellRemainder,
            count = 0;
        Object generic;
        JPanel jpanel;

        for(int index = 0; index < list.getSize(); ++index){
            // type conversions
            generic = list.pop(index);
            jpanel = (JPanel)generic;
              
            // set size of the JPanel- (8.57, 8.56)
            jpanel.setPreferredSize(new Dimension((int)(length / 8.0), 
                                                  (int)(length / 8.0)));

            // make the two colors of the chessboard
            Color tan = new Color(210, 180, 140);
            Color brown = new Color(139, 69, 19);

            cellRemainder = index % 2;
            if((int)(count / 8) % 2 == 0){
                // test if index is even or odd
                // even
                if(cellRemainder == 0)
                    jpanel.setBackground(tan);

                // odd
                else
                    jpanel.setBackground(brown);}

            else{
                // test if index is even or odd
                // even
                if(cellRemainder == 0)
                    jpanel.setBackground(brown);

                // odd
                else
                    jpanel.setBackground(tan);}

            // add components to board
            board.add(jpanel);
            ++count;}

        // test ability to add images to the JFrame
        JLabel label = new JLabel();

        Object x = list.pop(20);
        JPanel y = (JPanel) x;

        label.setPreferredSize(y.getPreferredSize());

        BlackPawn pawn = new BlackPawn();
        label.setIcon(new ImageIcon(pawn.getImage()));

        y.add(label);

        this.add(board, BorderLayout.CENTER);
        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        this.setVisible(true);
    }

    /**
    Methods that return dimensions of different portions of the JFrame and its panels
    Purpose: testing the values are as expected
    Note: Currently the screen dimension given by the Dimension class appears to be too large,
          however, the JPanel porportions are correct; both laptop and desktop have shown the
          chessboard to have the correct proportions; the following bash command will give the user
          the true dimensions of the screen: xrandr | grep '*' | awk '{ print $1 }'
    */
    // Method to return size of a random chess square
    public Dimension getCellDimension(){
        Random random = new Random();
        int index = random.nextInt(64);

        Object obj = list.pop(index);
        JPanel jpanel = (JPanel)obj;
        return jpanel.getSize();
    }

    // Method to return size of the chessboard
    public Dimension getBoardDimension(){
        return board.getSize();
    }

    // Method to return size of 'north' portion of the JFrame
    public Dimension getNorthDimension(){
        return north.getSize();
    }

    // Method to return size of 'south' portion of the JFrame
    public Dimension getSouthDimension(){
        return south.getSize();
    }

    // Method to return to size of 'west' portion of the JFrame
    public Dimension getWestDimension(){
        return west.getSize();
    }

    // Method to return the size of the 'east' portion of the JFrame
    public Dimension getEastDimension(){
        return east.getSize();
    }

    // Method to return the dimensions of the entire JFrame
    public Dimension getWindowSize(){
        return this.getSize();
    }
}
