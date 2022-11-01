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

        // instantiate the necessary chesspieces 
        BlackPawn bPawn = new BlackPawn();
        WhitePawn wPawn = new WhitePawn();
        BlackRook bRook = new BlackRook();
        WhiteRook wRook = new WhiteRook();
        BlackKnight bKnight = new BlackKnight();
        WhiteKnight wKnight = new WhiteKnight();
        BlackBishop bBishop = new BlackBishop();
        WhiteBishop wBishop = new WhiteBishop();
        BlackQueen bQueen = new BlackQueen();
        WhiteQueen wQueen = new WhiteQueen();
        BlackKing bKing = new BlackKing();
        WhiteKing wKing = new WhiteKing();

        // pull panels from the list 
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

        // instantiate the labels
        JLabel label1 = new JLabel(bRook.getImage(), JLabel.CENTER);
        JLabel label2 = new JLabel(bKnight.getImage(), JLabel.CENTER);
        JLabel label3 = new JLabel(bBishop.getImage(), JLabel.CENTER);
        JLabel label4 = new JLabel(bQueen.getImage(), JLabel.CENTER);
        JLabel label5 = new JLabel(bKing.getImage(), JLabel.CENTER);
        JLabel label6 = new JLabel(bBishop.getImage(), JLabel.CENTER);
        JLabel label7 = new JLabel(bKnight.getImage(), JLabel.CENTER);
        JLabel label8 = new JLabel(bRook.getImage(), JLabel.CENTER);
        JLabel label9 = new JLabel(bPawn.getImage(), JLabel.CENTER);
        JLabel label10 = new JLabel(bPawn.getImage(), JLabel.CENTER);
        JLabel label11 = new JLabel(bPawn.getImage(), JLabel.CENTER);
        JLabel label12 = new JLabel(bPawn.getImage(), JLabel.CENTER);
        JLabel label13 = new JLabel(bPawn.getImage(), JLabel.CENTER);
        JLabel label14 = new JLabel(bPawn.getImage(), JLabel.CENTER);
        JLabel label15 = new JLabel(bPawn.getImage(), JLabel.CENTER);
        JLabel label16 = new JLabel(bPawn.getImage(), JLabel.CENTER);
        JLabel label17 = new JLabel(wPawn.getImage(), JLabel.CENTER);
        JLabel label18 = new JLabel(wPawn.getImage(), JLabel.CENTER);
        JLabel label19 = new JLabel(wPawn.getImage(), JLabel.CENTER);
        JLabel label20 = new JLabel(wPawn.getImage(), JLabel.CENTER);
        JLabel label21 = new JLabel(wPawn.getImage(), JLabel.CENTER);
        JLabel label22 = new JLabel(wPawn.getImage(), JLabel.CENTER);
        JLabel label23 = new JLabel(wPawn.getImage(), JLabel.CENTER);
        JLabel label24 = new JLabel(wPawn.getImage(), JLabel.CENTER);
        JLabel label25 = new JLabel(wRook.getImage(), JLabel.CENTER);
        JLabel label26 = new JLabel(wKnight.getImage(), JLabel.CENTER);
        JLabel label27 = new JLabel(wBishop.getImage(), JLabel.CENTER);
        JLabel label28 = new JLabel(wQueen.getImage(), JLabel.CENTER);
        JLabel label29 = new JLabel(wKing.getImage(), JLabel.CENTER);
        JLabel label30 = new JLabel(wBishop.getImage(), JLabel.CENTER);
        JLabel label31 = new JLabel(wKnight.getImage(), JLabel.CENTER);
        JLabel label32 = new JLabel(wRook.getImage(), JLabel.CENTER);

        // set size of each label equal to the panel 
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

        // insert pieces at their starting locations 
        jpanel1.add(label1, BorderLayout.CENTER);
        jpanel2.add(label2, BorderLayout.CENTER);
        jpanel3.add(label3, BorderLayout.CENTER);
        jpanel4.add(label4, BorderLayout.CENTER);
        jpanel5.add(label5, BorderLayout.CENTER);
        jpanel6.add(label6, BorderLayout.CENTER);
        jpanel7.add(label7, BorderLayout.CENTER);
        jpanel8.add(label8, BorderLayout.CENTER);
        jpanel9.add(label9, BorderLayout.CENTER);
        jpanel10.add(label10, BorderLayout.CENTER);
        jpanel11.add(label11, BorderLayout.CENTER);
        jpanel12.add(label12, BorderLayout.CENTER);
        jpanel13.add(label13, BorderLayout.CENTER);
        jpanel14.add(label14, BorderLayout.CENTER);
        jpanel15.add(label15, BorderLayout.CENTER);
        jpanel16.add(label16, BorderLayout.CENTER);
        jpanel17.add(label17, BorderLayout.CENTER);
        jpanel18.add(label18, BorderLayout.CENTER);
        jpanel19.add(label19, BorderLayout.CENTER);
        jpanel20.add(label20, BorderLayout.CENTER);
        jpanel21.add(label21, BorderLayout.CENTER);
        jpanel22.add(label22, BorderLayout.CENTER);
        jpanel23.add(label23, BorderLayout.CENTER);
        jpanel24.add(label24, BorderLayout.CENTER);
        jpanel25.add(label25, BorderLayout.CENTER);
        jpanel26.add(label26, BorderLayout.CENTER);
        jpanel27.add(label27, BorderLayout.CENTER);
        jpanel28.add(label28, BorderLayout.CENTER);
        jpanel29.add(label29, BorderLayout.CENTER);
        jpanel30.add(label30, BorderLayout.CENTER);
        jpanel31.add(label31, BorderLayout.CENTER);
        jpanel32.add(label32, BorderLayout.CENTER);

        // delete
        System.out.println(label1.getSize());

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
