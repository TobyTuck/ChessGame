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
        list = new List(8);

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
            
            // delete
            System.out.println(index);

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

        // add chesspieces to the board
        JLabel label1 = new JLabel(); JLabel label2 = new JLabel(); JLabel label3 = new JLabel();
        JLabel label4 = new JLabel(); JLabel label5 = new JLabel(); JLabel label6 = new JLabel();
        JLabel label7 = new JLabel(); JLabel label8 = new JLabel(); JLabel label9 = new JLabel();
        JLabel label10 = new JLabel(); JLabel label11 = new JLabel(); JLabel label12 = new JLabel();
        JLabel label13 = new JLabel(); JLabel label14 = new JLabel(); JLabel label15 = new JLabel();
        JLabel label16 = new JLabel(); JLabel label17 = new JLabel(); JLabel label18 = new JLabel();
        JLabel label19 = new JLabel(); JLabel label20 = new JLabel(); JLabel label21 = new JLabel();
        JLabel label22 = new JLabel(); JLabel label23 = new JLabel(); JLabel label24 = new JLabel();
        JLabel label25 = new JLabel(); JLabel label26 = new JLabel(); JLabel label27 = new JLabel();
        JLabel label28 = new JLabel(); JLabel label29 = new JLabel(); JLabel label30 = new JLabel();
        JLabel label31 = new JLabel(); JLabel label32 = new JLabel();

        JPanel jpanel1 = (JPanel) list.pop(0); JPanel jpanel2 = (JPanel) list.pop(1);
        JPanel jpanel3 = (JPanel) list.pop(2); JPanel jpanel4 = (JPanel) list.pop(3);
        JPanel jpanel5 = (JPanel) list.pop(4); JPanel jpanel6 = (JPanel) list.pop(5);
        JPanel jpanel7 = (JPanel) list.pop(6); JPanel jpanel8 = (JPanel) list.pop(7);
        JPanel jpanel9 = (JPanel) list.pop(8); JPanel jpanel10 = (JPanel) list.pop(9);
        JPanel jpanel11 = (JPanel) list.pop(10); JPanel jpanel12 = (JPanel) list.pop(11);
        JPanel jpanel13 = (JPanel) list.pop(12); JPanel jpanel14 = (JPanel) list.pop(13);
        JPanel jpanel15 = (JPanel) list.pop(14); JPanel jpanel16 = (JPanel) list.pop(15);
        JPanel jpanel17 = (JPanel) list.pop(48); JPanel jpanel18 = (JPanel) list.pop(49);
        JPanel jpanel19 = (JPanel) list.pop(50); JPanel jpanel20 = (JPanel) list.pop(51);
        JPanel jpanel21 = (JPanel) list.pop(52); JPanel jpanel22 = (JPanel) list.pop(53);
        JPanel jpanel23 = (JPanel) list.pop(54); JPanel jpanel24 = (JPanel) list.pop(55);
        JPanel jpanel25 = (JPanel) list.pop(56); JPanel jpanel26 = (JPanel) list.pop(57);
        JPanel jpanel27 = (JPanel) list.pop(58); JPanel jpanel28 = (JPanel) list.pop(59);
        JPanel jpanel29 = (JPanel) list.pop(60); JPanel jpanel30 = (JPanel) list.pop(61);
        JPanel jpanel31 = (JPanel) list.pop(62); JPanel jpanel32 = (JPanel) list.pop(63);

        label1.setPreferredSize(jpanel1.getPreferredSize()); 
        label2.setPreferredSize(jpanel2.getPreferredSize());
        label3.setPreferredSize(jpanel3.getPreferredSize());
        label4.setPreferredSize(jpanel4.getPreferredSize());
        label5.setPreferredSize(jpanel5.getPreferredSize());
        label6.setPreferredSize(jpanel6.getPreferredSize());
        label7.setPreferredSize(jpanel7.getPreferredSize());
        label8.setPreferredSize(jpanel8.getPreferredSize());
        label9.setPreferredSize(jpanel9.getPreferredSize());
        label10.setPreferredSize(jpanel10.getPreferredSize());
        label11.setPreferredSize(jpanel11.getPreferredSize());
        label12.setPreferredSize(jpanel12.getPreferredSize());
        label13.setPreferredSize(jpanel13.getPreferredSize());
        label14.setPreferredSize(jpanel14.getPreferredSize());
        label15.setPreferredSize(jpanel15.getPreferredSize());
        label16.setPreferredSize(jpanel16.getPreferredSize());
        label17.setPreferredSize(jpanel17.getPreferredSize());
        label18.setPreferredSize(jpanel18.getPreferredSize());
        label19.setPreferredSize(jpanel19.getPreferredSize());
        label20.setPreferredSize(jpanel20.getPreferredSize());
        label21.setPreferredSize(jpanel21.getPreferredSize());
        label22.setPreferredSize(jpanel22.getPreferredSize());
        label23.setPreferredSize(jpanel23.getPreferredSize());
        label24.setPreferredSize(jpanel24.getPreferredSize());
        label25.setPreferredSize(jpanel25.getPreferredSize());
        label26.setPreferredSize(jpanel26.getPreferredSize());
        label27.setPreferredSize(jpanel27.getPreferredSize());
        label28.setPreferredSize(jpanel28.getPreferredSize());
        label29.setPreferredSize(jpanel29.getPreferredSize());
        label30.setPreferredSize(jpanel30.getPreferredSize());
        label31.setPreferredSize(jpanel31.getPreferredSize());
        label32.setPreferredSize(jpanel32.getPreferredSize());

        // set the pawn image to the appropriate jpanels
        BlackPawn bPawn1 = new BlackPawn();
        label9.setIcon(new ImageIcon(bPawn1.getImage()));
        BlackPawn bPawn2 = new BlackPawn();
        label10.setIcon(new ImageIcon(bPawn2.getImage()));
        BlackPawn bPawn3 = new BlackPawn();
        label11.setIcon(new ImageIcon(bPawn3.getImage()));
        BlackPawn bPawn4 = new BlackPawn();
        label12.setIcon(new ImageIcon(bPawn4.getImage()));
        BlackPawn bPawn5 = new BlackPawn();
        label13.setIcon(new ImageIcon(bPawn5.getImage()));
        BlackPawn bPawn6 = new BlackPawn();
        label14.setIcon(new ImageIcon(bPawn6.getImage()));
        BlackPawn bPawn7 = new BlackPawn();
        label15.setIcon(new ImageIcon(bPawn7.getImage()));
        BlackPawn bPawn8 = new BlackPawn();
        label16.setIcon(new ImageIcon(bPawn8.getImage()));

        WhitePawn wPawn1 = new WhitePawn();
        label17.setIcon(new ImageIcon(wPawn1.getImage()));
        WhitePawn wPawn2 = new WhitePawn();
        label18.setIcon(new ImageIcon(wPawn2.getImage()));
        WhitePawn wPawn3 = new WhitePawn();
        label19.setIcon(new ImageIcon(wPawn3.getImage()));
        WhitePawn wPawn4 = new WhitePawn();
        label20.setIcon(new ImageIcon(wPawn4.getImage()));
        WhitePawn wPawn5 = new WhitePawn();
        label21.setIcon(new ImageIcon(wPawn5.getImage()));
        WhitePawn wPawn6 = new WhitePawn();
        label22.setIcon(new ImageIcon(wPawn6.getImage()));
        WhitePawn wPawn7 = new WhitePawn();
        label23.setIcon(new ImageIcon(wPawn7.getImage()));
        WhitePawn wPawn8 = new WhitePawn();
        label24.setIcon(new ImageIcon(wPawn8.getImage()));

        // set the rook image to the appropriate jpanels
        BlackRook bRook1 = new BlackRook();
        label1.setIcon(new ImageIcon(bRook1.getImage()));
        BlackRook bRook2 = new BlackRook();
        label8.setIcon(new ImageIcon(bRook2.getImage()));

        WhiteRook wRook1 = new WhiteRook();
        label25.setIcon(new ImageIcon(wRook1.getImage()));
        WhiteRook wRook2 = new WhiteRook();
        label32.setIcon(new ImageIcon(wRook2.getImage()));

        // set the knight image to the appropriate jpanels
        BlackKnight bKnight1 = new BlackKnight();
        label2.setIcon(new ImageIcon(bKnight1.getImage()));
        BlackKnight bKnight2 = new BlackKnight();
        label7.setIcon(new ImageIcon(bKnight2.getImage()));

        WhiteKnight wKnight1 = new WhiteKnight();
        label26.setIcon(new ImageIcon(wKnight1.getImage()));
        WhiteKnight wKnight2 = new WhiteKnight();
        label31.setIcon(new ImageIcon(wKnight2.getImage()));

        // set the bishop image to the appropriate jpanels
        BlackBishop bBishop1 = new BlackBishop();
        label3.setIcon(new ImageIcon(bBishop1.getImage()));
        BlackBishop bBishop2 = new BlackBishop();
        label6.setIcon(new ImageIcon(bBishop2.getImage()));

        WhiteBishop wBishop1 = new WhiteBishop();
        label27.setIcon(new ImageIcon(wBishop1.getImage()));
        WhiteBishop wBishop2 = new WhiteBishop();
        label30.setIcon(new ImageIcon(wBishop2.getImage()));

        // set the queen image to the appropriate jpanels
        BlackQueen bQueen = new BlackQueen();
        label4.setIcon(new ImageIcon(bQueen.getImage()));

        WhiteQueen wQueen = new WhiteQueen();
        label28.setIcon(new ImageIcon(wQueen.getImage()));

        // set the king image to the appropriate jpanels
        BlackKing bKing = new BlackKing();
        label5.setIcon(new ImageIcon(bKing.getImage()));

        WhiteKing wKing = new WhiteKing();
        label29.setIcon(new ImageIcon(wKing.getImage()));

        jpanel1.add(label1); jpanel2.add(label2); jpanel3.add(label3); jpanel4.add(label4);
        jpanel5.add(label5); jpanel6.add(label6); jpanel7.add(label7); jpanel8.add(label8);
        jpanel9.add(label9); jpanel10.add(label10); jpanel11.add(label11); jpanel12.add(label12);
        jpanel13.add(label13); jpanel14.add(label14); jpanel15.add(label15); jpanel16.add(label16);
        jpanel17.add(label17); jpanel18.add(label18); jpanel19.add(label19); jpanel20.add(label20);
        jpanel21.add(label21); jpanel22.add(label22); jpanel23.add(label23); jpanel24.add(label24);
        jpanel25.add(label25); jpanel26.add(label26); jpanel27.add(label27); jpanel28.add(label28);
        jpanel29.add(label29); jpanel30.add(label30); jpanel31.add(label31); jpanel32.add(label32);

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
