import javax.swing.*;
import java.awt.*;  // used for Dimension, GraphicsEnvironment, GraphicsDevice
import java.awt.event.*;  // used for MouseAdapter and MouseEvent
import java.util.Random;
import java.awt.image.BufferedImage;

public class ChessBoard extends JFrame /* implements MouseListener */{

    // JPanel that consists of the entire 8 * 8 chessboard
    private JPanel board;

    // supporting panels that cover the bottom, left, top, and right of the screen board isn't
    private JPanel south;
    private JPanel north;
    private JPanel east;
    private JPanel west;

    // panels that fit insde the east and west panels, w/ the purpose of holding captured chesspieces
    private JPanel northWest;
    private JPanel southEast;

    //holds all the individual squares
    List list;

    // declare individual squares of the chessboard
    private JPanel square0;         private JPanel square10;
    private JPanel square1;         private JPanel square11;
    private JPanel square2;         private JPanel square12;
    private JPanel square3;         private JPanel square13;
    private JPanel square4;         private JPanel square14;
    private JPanel square5;         private JPanel square15;
    private JPanel square6;         private JPanel square16;
    private JPanel square7;         private JPanel square17;
    private JPanel square8;         private JPanel square18;
    private JPanel square9;         private JPanel square19;

    private JPanel square20;        private JPanel square30;
    private JPanel square21;        private JPanel square31;
    private JPanel square22;        private JPanel square32;
    private JPanel square23;        private JPanel square33;
    private JPanel square24;        private JPanel square34;
    private JPanel square25;        private JPanel square35;
    private JPanel square26;        private JPanel square36;
    private JPanel square27;        private JPanel square37;
    private JPanel square28;        private JPanel square38;
    private JPanel square29;        private JPanel square39;

    private JPanel square40;        private JPanel square50;
    private JPanel square41;        private JPanel square51;
    private JPanel square42;        private JPanel square52;
    private JPanel square43;        private JPanel square53;
    private JPanel square44;        private JPanel square54;
    private JPanel square45;        private JPanel square55;
    private JPanel square46;        private JPanel square56;
    private JPanel square47;        private JPanel square57;
    private JPanel square48;        private JPanel square58;
    private JPanel square49;        private JPanel square59;

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
        // build panels above and below chessboard
        int length = boardHeight;
        north = new JPanel();
        north.setPreferredSize(new Dimension(screenSize.width, 
                                             (int) (0.5 * (screenSize.height - length))));
        north.setBackground(darkGreen);
        south = new JPanel();
        south.setPreferredSize(new Dimension(screenSize.width, 
                                             (int) (0.5 * (screenSize.height - length))));
        south.setBackground(darkGreen);

        // build panels to the left and right- with inner panels which will hold captured chesspieces
        west = new JPanel(new BorderLayout());
        west.setPreferredSize(new Dimension((int) (0.5 * (screenSize.width - length)), length));
        west.setBackground(darkGreen);
        northWest = new JPanel();
        JPanel filler1 = new JPanel();
        JPanel filler2 = new JPanel();
        northWest.setPreferredSize(new Dimension((int) (0.4 * (screenSize.width - length)), length));
        filler1.setPreferredSize(new Dimension((int) (0.05 * (screenSize.width - length)), length));
        filler2.setPreferredSize(new Dimension((int) (0.05 * (screenSize.width - length)), length));
        northWest.setBackground(darkGreen);
        filler1.setBackground(darkGreen);
        filler2.setBackground(darkGreen);
        west.add(filler1, BorderLayout.EAST);
        west.add(filler2, BorderLayout.WEST);
        west.add(northWest, BorderLayout.CENTER);

        east = new JPanel(new BorderLayout());
        east.setPreferredSize(new Dimension((int) (0.5 * (screenSize.width - length)), length));
        east.setBackground(darkGreen);
        southEast = new JPanel();
        JPanel filler3 = new JPanel();
        JPanel filler4 = new JPanel();
        southEast.setPreferredSize(new Dimension((int) (0.4 * (screenSize.width - length)), length));
        filler3.setPreferredSize(new Dimension((int) (0.05 * (screenSize.width - length)), length));
        filler4.setPreferredSize(new Dimension((int) (0.05 * (screenSize.width - length)), length));
        southEast.setBackground(darkGreen);
        filler3.setBackground(darkGreen);
        filler4.setBackground(darkGreen);
        east.add(filler3, BorderLayout.WEST);
        east.add(filler4, BorderLayout.EAST);
        east.add(southEast, BorderLayout.CENTER);

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

            // set the Layout manager for each jpanel
            jpanel.setLayout(new BorderLayout());

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

        // find the new dimensions we want to scale the images to 
        // NOTE: white chesspieces have the same dimenstions as black chesspieces
        int pawnHeight = (int) ((length / 8.0) * 0.6);
        int pawnWidth = (int) ((double) (bPawn.getWidth() * 
                              ((double) pawnHeight / (double) bPawn.getHeight())));
        int rookHeight = (int) ((length / 8.0) * 0.75);
        int rookWidth = (int) ((double) (bRook.getWidth() * 
                              ((double) rookHeight / (double) bRook.getHeight())));
        int knightHeight = (int) ((length / 8.0) * 0.75);
        int knightWidth = (int) ((double) (bKnight.getWidth() * 
                              ((double) knightHeight / (double) bKnight.getHeight())));
        int bishopHeight = (int) ((length / 8.0) * 0.8);
        int bishopWidth = (int) ((double) (bBishop.getWidth() * 
                              ((double) bishopHeight / (double) bKnight.getHeight())));
        int queenHeight = (int) ((length / 8.0) * 0.85);
        int queenWidth = (int) ((double) (bQueen.getWidth() * 
                              ((double) queenHeight / (double) bQueen.getHeight())));
        int kingHeight = (int) ((length / 8.0) * 0.9);
        int kingWidth = (int) ((double) (bKing.getWidth() * 
                              ((double) kingHeight / (double) bKing.getHeight())));

        // Add chesspieces to each necessary square w/ in list at the specified width and height
        pin(bRook, (JPanel) list.pop(0), rookWidth, rookHeight);
        pin(bKnight, (JPanel) list.pop(1), knightWidth, knightHeight);
        pin(bBishop, (JPanel) list.pop(2), bishopWidth, bishopHeight);
        pin(bQueen, (JPanel) list.pop(3), queenWidth, queenHeight);
        pin(bKing, (JPanel) list.pop(4), kingWidth, kingHeight);
        pin(bBishop, (JPanel) list.pop(5), bishopWidth, bishopHeight);
        pin(bKnight, (JPanel) list.pop(6), knightWidth, knightHeight);
        pin(bRook, (JPanel) list.pop(7), rookWidth, rookHeight);
        pin(bPawn, (JPanel) list.pop(8), pawnWidth, pawnHeight);
        pin(bPawn, (JPanel) list.pop(9), pawnWidth, pawnHeight);
        pin(bPawn, (JPanel) list.pop(10), pawnWidth, pawnHeight);
        pin(bPawn, (JPanel) list.pop(11), pawnWidth, pawnHeight);
        pin(bPawn, (JPanel) list.pop(12), pawnWidth, pawnHeight);
        pin(bPawn, (JPanel) list.pop(13), pawnWidth, pawnHeight);
        pin(bPawn, (JPanel) list.pop(14), pawnWidth, pawnHeight);
        pin(bPawn, (JPanel) list.pop(15), pawnWidth, pawnHeight);
        pin(wPawn, (JPanel) list.pop(48), pawnWidth, pawnHeight);
        pin(wPawn, (JPanel) list.pop(49), pawnWidth, pawnHeight);
        pin(wPawn, (JPanel) list.pop(50), pawnWidth, pawnHeight);
        pin(wPawn, (JPanel) list.pop(51), pawnWidth, pawnHeight);
        pin(wPawn, (JPanel) list.pop(52), pawnWidth, pawnHeight);
        pin(wPawn, (JPanel) list.pop(53), pawnWidth, pawnHeight);
        pin(wPawn, (JPanel) list.pop(54), pawnWidth, pawnHeight);
        pin(wPawn, (JPanel) list.pop(55), pawnWidth, pawnHeight);
        pin(wRook, (JPanel) list.pop(56), rookWidth, rookHeight);
        pin(wKnight, (JPanel) list.pop(57), knightWidth, knightHeight);
        pin(wBishop, (JPanel) list.pop(58), bishopWidth, bishopHeight);
        pin(wQueen, (JPanel) list.pop(59), queenWidth, queenHeight);
        pin(wKing, (JPanel) list.pop(60), kingWidth, kingHeight);
        pin(wBishop, (JPanel) list.pop(61), bishopWidth, bishopHeight);
        pin(wKnight, (JPanel) list.pop(62), knightWidth, knightHeight);
        pin(wRook, (JPanel) list.pop(63), rookWidth, rookHeight);

        this.add(board, BorderLayout.CENTER);
        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        this.setVisible(true);

        MouseAdapter ma = new MouseAdapter() {
            private Point offset;
            private Point clickPoint;
            private JPanel clickedPanel;

            @Override
            public void mousePressed(MouseEvent e) {
                // int x = e.getX();
                // int y = e.getY();
                // System.out.println(x + ", " + y);
            // }

                // Get the current clickPoint, this is used to determine if the
                // mouseRelease event was part of a drag operation or not
                clickPoint = e.getPoint();
                // Determine if there is currently a selected panel or not
                if (clickedPanel != null) {
                    // Move the selected panel to a new location
                    moveSelectedPanelTo(e.getPoint());
                    // Reset all the other stuff we might other was have set eailer
                    offset = null;
                    clickedPanel = null;
                } else {
                    // Other wise, find which component was clicked
                    findClickedComponent(e.getPoint());
                }

                north.setBackground(Color.blue);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Check to see if the current point is equal to the clickedPoint
                // or not.  If it is, then this is part of a "clicked" operation
                // meaning that the selected panel should remain "selected", otherwise
                // it's part of drag operation and should be discarded
                if (!e.getPoint().equals(clickPoint)) {
                    clickedPanel = null;
                }
                clickPoint = null;
            }

            /* @Override
            public void mouseDragged(MouseEvent e) {
                // Drag the selected component to a new location...
                if (clickedPanel != null) {
                    moveSelectedPanelTo(e.getPoint());
                }
            } */

            private void findClickedComponent(Point p) {
                Component comp = getComponentAt(p);
                if (comp instanceof JPanel && !comp.equals(east)) {
                    clickedPanel = (JPanel) comp;
                    int x = p.x - clickedPanel.getLocation().x;
                    int y = p.y - clickedPanel.getLocation().y;
                    offset = new Point(x, y);
                }

            }

            private void moveSelectedPanelTo(Point p) {
                if (clickedPanel != null) {
                    int x = p.x - offset.x;
                    int y = p.y - offset.y;
                    System.out.println(x + "x" + y);
                    clickedPanel.setLocation(x, y);
                }
            } 

        };

        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    /**
    Method that waits for a mouse event to occur before the method may conclude
    Chess rules that apply to piece movements are the following:
        1.In order to move a chesspiece, the piece moved must first be clicked, then the square to
          which the user wants to move
        2.A chesspiece may not be moved to any square in which a piece of the same color resides
        3.Each chesspiece has a certain pattern they are able to move in, as defined in their
          respective classes
        4.If a chesspiece is moved onto a square occupied by a chesspiece of the opposite color,
          the latter is deemed 'captured' and removed from the board, while the former now 
          occupies that square
        5.If a king is put in check, he is required to remove himself from this situation if possible
        6.If a chesspiece is in a position that blocks his King from being in check, that piece is 
          unable to be moved by his player
    */

    /*
    public void mouseClicked(MouseEvent e){ */

        /* square1.addMouseListener(e);
        square2.addMouseListener(e);
        square3.addMouseListener(e);
        square4.addMouseListener(e);
        square5.addMouseListener(e);
        square6.addMouseListener(e);
        square7.addMouseListener(e);
        square8.addMouseListener(e);
        square9.addMouseListener(e);
        square10.addMouseListener(e);
        square11.addMouseListener(e);
        square12.addMouseListener(e);
        square13.addMouseListener(e);
        square14.addMouseListener(e);
        square15.addMouseListener(e);
        square16.addMouseListener(e);
        square17.addMouseListener(e);
        square18.addMouseListener(e);
        square19.addMouseListener(e);
        square20.addMouseListener(e);
        square21.addMouseListener(e);
        square22.addMouseListener(e);
        square23.addMouseListener(e);
        square24.addMouseListener(e);
        square25.addMouseListener(e);
        square26.addMouseListener(e);
        square27.addMouseListener(e);
        square28.addMouseListener(e);
        square29.addMouseListener(e);
        square30.addMouseListener(e);
        square31.addMouseListener(e);
        square32.addMouseListener(e); */

        /* BufferedImage icon;
        int clickCount = 0;

        // board.addMouseListener();
        Object click = e.getSource();
        if(click == square0 || click == square1 || click == square2 || click == square3 ||
           click == square4 || click == square5 || click == square6 || click == square7 ||
           click == square8 || click == square9 || click == square10 || click == square11 ||
           click == square12 || click == square13 || click == square14 || click == square15 ||
           click == square16 || click == square17 || click == square18 || click == square19 ||
           click == square20 || click == square21 || click == square22 || click == square23 ||
           click == square24 || click == square25 || click == square26 || click == square27 ||
           click == square28 || click == square29 || click == square30 || click == square31 ||
           click == square32 || click == square33 || click == square34 || click == square35 ||
           click == square36 || click == square37 || click == square38 || click == square39 ||
           click == square40 || click == square41 || click == square42 || click == square43 ||
           click == square44 || click == square45 || click == square46 || click == square47 ||
           click == square48 || click == square49 || click == square50 || click == square51 ||
           click == square52 || click == square53 || click == square54 || click == square55 ||
           click == square56 || click == square57 || click == square58 || click == square59 ||
           click == square60 || click == square61 || click == square62 || click == square63)

        // if(click instanceof JPanel)
            ++clickCount;

        System.out.println("Click count: " + clickCount);
    }

    public void mousePressed(MouseEvent e){
    }

    public void mouseReleased(MouseEvent e){
    }

    public void mouseEntered(MouseEvent e){
    }

    public void mouseExited(MouseEvent e){
     } */

    /**
    Private helper method that adds a chesspiece image to the center of a JPanel using a JLabel 
    */
    public void pin(ChessPiece piece, JPanel panel, int width, int height){

        // rescale image to fit its jpanel
        piece.scaleImage(width, height);

        // create the label and set preferrences
        JLabel label = new JLabel(piece.toImageIcon(), JLabel.CENTER);
        label.setPreferredSize(panel.getPreferredSize());

        // pin JLabel onto JFrame
        panel.add(label, BorderLayout.CENTER);
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
