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

    private int boardHeight;

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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // build the chessboard
        board = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        // ensure the board is square- and divides evenly into 8 
        int eightDivisible = 0;
        do{
            boardHeight = (int)(screenSize.height * 0.9) + eightDivisible;
            ++eightDivisible;
        }while(boardHeight % 8 != 0);
        board.setPreferredSize(new Dimension(boardHeight, boardHeight));
        
        // build a dark green color for side panels
        // old green: 0, 100, 0
        Color darkGreen = new Color(25, 45, 25);

        // build supporting panels
        // build panels above and below chessboard
        north = new JPanel();
        north.setPreferredSize(new Dimension(screenSize.width, 
                                             (int) (0.5 * (screenSize.height - boardHeight))));
        north.setBackground(darkGreen);
        south = new JPanel();
        south.setPreferredSize(new Dimension(screenSize.width, 
                                             (int) (0.5 * (screenSize.height - boardHeight))));
        south.setBackground(darkGreen);

        // build panels to the left and right- with inner panels which will hold captured chesspieces
        west = new JPanel(new BorderLayout());
        west.setPreferredSize(new Dimension((int) (0.5 * (screenSize.width - boardHeight)),
                                                   boardHeight));
        west.setBackground(darkGreen);
        northWest = new JPanel();
        JPanel filler1 = new JPanel();
        JPanel filler2 = new JPanel();
        
        northWest.setPreferredSize(new Dimension((int) (0.4 * (screenSize.width - boardHeight)), 
                                                        boardHeight));
        filler1.setPreferredSize(new Dimension((int) (0.05 * (screenSize.width - boardHeight)), 
                                                      boardHeight));
        filler2.setPreferredSize(new Dimension((int) (0.05 * (screenSize.width - boardHeight)), 
                                                      boardHeight));

        northWest.setBackground(darkGreen);
        northWest.setLayout(new FlowLayout());
        filler1.setBackground(darkGreen);
        filler2.setBackground(darkGreen);
        west.add(filler1, BorderLayout.EAST);
        west.add(filler2, BorderLayout.WEST);
        west.add(northWest, BorderLayout.CENTER);

        east = new JPanel(new BorderLayout());
        east.setPreferredSize(new Dimension((int) (0.5 * (screenSize.width - boardHeight)), 
                                                   boardHeight));
        east.setBackground(darkGreen);

        southEast = new JPanel(new FlowLayout());
        JPanel filler4 = new JPanel(new BorderLayout());
        JPanel filler5 = new JPanel();
        JPanel filler6 = new JPanel();
        JPanel filler7 = new JPanel();
        
        // panel that will hold the SE panel for captured pieces
        filler4.setPreferredSize(new Dimension((int) (0.4 * (screenSize.width - boardHeight)), 
                                                        boardHeight));
        // panels that provide "borders" that prevent pieces from touching board
        filler5.setPreferredSize(new Dimension((int) (0.05 * (screenSize.width - boardHeight)), 
                                                      boardHeight));
        filler6.setPreferredSize(new Dimension((int) (0.05 * (screenSize.width - boardHeight)), 
                                                      boardHeight));
        // panel that fills the extra space of filler4
        filler7.setPreferredSize(new Dimension((int) (0.4 * (screenSize.width - boardHeight)), 
                                               (int) (boardHeight * 0.86)));
        southEast.setPreferredSize(new Dimension((int) (0.4 * (screenSize.width - boardHeight)),
                                                 (int) (boardHeight * 0.14)));
        filler4.add(filler7, BorderLayout.NORTH);
        filler4.add(southEast, BorderLayout.SOUTH);
        

        southEast.setBackground(darkGreen);
        filler4.setBackground(darkGreen);
        filler5.setBackground(darkGreen);
        filler6.setBackground(darkGreen);
        filler7.setBackground(darkGreen);
        east.add(filler5, BorderLayout.WEST);
        east.add(filler6, BorderLayout.EAST);
        east.add(filler4, BorderLayout.CENTER);

        // add components of each square to the list
        list = new List(8);

        square0 = new JPanel(); list.push(square0, null);
        square1 = new JPanel(); list.push(square1, null);
        square2 = new JPanel(); list.push(square2, null);
        square3 = new JPanel(); list.push(square3, null);
        square4 = new JPanel(); list.push(square4, null);
        square5 = new JPanel(); list.push(square5, null);
        square6 = new JPanel(); list.push(square6, null);
        square7 = new JPanel(); list.push(square7, null);
        square8 = new JPanel(); list.push(square8, null);
        square9 = new JPanel(); list.push(square9, null);
        
        square10 = new JPanel(); list.push(square10, null);
        square11 = new JPanel(); list.push(square11, null);
        square12 = new JPanel(); list.push(square12, null);
        square13 = new JPanel(); list.push(square13, null);
        square14 = new JPanel(); list.push(square14, null);
        square15 = new JPanel(); list.push(square15, null);
        square16 = new JPanel(); list.push(square16, null);
        square17 = new JPanel(); list.push(square17, null);
        square18 = new JPanel(); list.push(square18, null);
        square19 = new JPanel(); list.push(square19, null);

        square20 = new JPanel(); list.push(square20, null);
        square21 = new JPanel(); list.push(square21, null);
        square22 = new JPanel(); list.push(square22, null);
        square23 = new JPanel(); list.push(square23, null);
        square24 = new JPanel(); list.push(square24, null);
        square25 = new JPanel(); list.push(square25, null);
        square26 = new JPanel(); list.push(square26, null);
        square27 = new JPanel(); list.push(square27, null);
        square28 = new JPanel(); list.push(square28, null);
        square29 = new JPanel(); list.push(square29, null);

        square30 = new JPanel(); list.push(square30, null);
        square31 = new JPanel(); list.push(square31, null);
        square32 = new JPanel(); list.push(square32, null);
        square33 = new JPanel(); list.push(square33, null);
        square34 = new JPanel(); list.push(square34, null);
        square35 = new JPanel(); list.push(square35, null);
        square36 = new JPanel(); list.push(square36, null);
        square37 = new JPanel(); list.push(square37, null);
        square38 = new JPanel(); list.push(square38, null);
        square39 = new JPanel(); list.push(square39, null);

        square40 = new JPanel(); list.push(square40, null);
        square41 = new JPanel(); list.push(square41, null);
        square42 = new JPanel(); list.push(square42, null);
        square43 = new JPanel(); list.push(square43, null);
        square44 = new JPanel(); list.push(square44, null);
        square45 = new JPanel(); list.push(square45, null);
        square46 = new JPanel(); list.push(square46, null);
        square47 = new JPanel(); list.push(square47, null);
        square48 = new JPanel(); list.push(square48, null);
        square49 = new JPanel(); list.push(square49, null);

        square50 = new JPanel(); list.push(square50, null);
        square51 = new JPanel(); list.push(square51, null);
        square52 = new JPanel(); list.push(square52, null);
        square53 = new JPanel(); list.push(square53, null);
        square54 = new JPanel(); list.push(square54, null);
        square55 = new JPanel(); list.push(square55, null);
        square56 = new JPanel(); list.push(square56, null);
        square57 = new JPanel(); list.push(square57, null);
        square58 = new JPanel(); list.push(square58, null);
        square59 = new JPanel(); list.push(square59, null);

        square60 = new JPanel(); list.push(square60, null);
        square61 = new JPanel(); list.push(square61, null);
        square62 = new JPanel(); list.push(square62, null);
        square63 = new JPanel(); list.push(square63, null);

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
            jpanel.setPreferredSize(new Dimension((int)(boardHeight / 8.0), 
                                                  (int)(boardHeight / 8.0)));

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

        // add chesspieces to their default starting points
        list.addComponent(list.pop(0), new BlackRook());
        list.addComponent(list.pop(1), new BlackKnight());
        list.addComponent(list.pop(2), new BlackBishop());
        list.addComponent(list.pop(3), new BlackQueen());
        list.addComponent(list.pop(4), new BlackKing());
        list.addComponent(list.pop(5), new BlackBishop());
        list.addComponent(list.pop(6), new BlackKnight());
        list.addComponent(list.pop(7), new BlackRook());
        list.addComponent(list.pop(8), new BlackPawn());
        list.addComponent(list.pop(9), new BlackPawn());
        list.addComponent(list.pop(10), new BlackPawn());
        list.addComponent(list.pop(11), new BlackPawn());
        list.addComponent(list.pop(12), new BlackPawn());
        list.addComponent(list.pop(13), new BlackPawn());
        list.addComponent(list.pop(14), new BlackPawn());
        list.addComponent(list.pop(15), new BlackPawn());
        list.addComponent(list.pop(48), new WhitePawn());
        list.addComponent(list.pop(49), new WhitePawn());
        list.addComponent(list.pop(50), new WhitePawn());
        list.addComponent(list.pop(51), new WhitePawn());
        list.addComponent(list.pop(52), new WhitePawn());
        list.addComponent(list.pop(53), new WhitePawn());
        list.addComponent(list.pop(54), new WhitePawn());
        list.addComponent(list.pop(55), new WhitePawn());
        list.addComponent(list.pop(56), new WhiteRook());
        list.addComponent(list.pop(57), new WhiteKnight());
        list.addComponent(list.pop(58), new WhiteBishop());
        list.addComponent(list.pop(59), new WhiteQueen());
        list.addComponent(list.pop(60), new WhiteKing());
        list.addComponent(list.pop(61), new WhiteBishop());
        list.addComponent(list.pop(62), new WhiteKnight());
        list.addComponent(list.pop(63), new WhiteRook());

        // add chesspiece images to their squares
        for(int index = 0; index < list.getSize(); ++index){
            Object component = list.getComponent(index);

            if(component != null && component instanceof ChessPiece)
                pin((ChessPiece) component, (JPanel) list.pop(index), 0, 0, "BorderLayout", true);}

        this.add(board, BorderLayout.CENTER);
        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        this.setVisible(true);

        MouseAdapter ma = new MouseAdapter() {
            private JPanel selectedPanel1;
            private JPanel selectedPanel2;
            private ChessPiece selectedPiece;
            private List myMoves;

            @Override
            public void mousePressed(MouseEvent e) {

                Component parent = e.getComponent();
                Component comp = parent.getComponentAt(e.getPoint());

                if(comp instanceof JPanel){
                    JPanel clickedPanel = (JPanel) comp;

                    // Determine if there a panel has been selected before this action 
                    if (selectedPanel1 != null) {
                        selectedPanel2 = clickedPanel;
                        ChessPiece selectedPiece2 = (ChessPiece) list.getComponent(selectedPanel2);

                        // chesspiece can't capture chesspiece of same color
                        if((!sameColor(selectedPiece, selectedPiece2)) || 
                            selectedPiece == selectedPiece2){
                            Component[] jcomponents = selectedPanel1.getComponents();

                            // find JLabel "pinned" to first JPanel selected
                            for(Component c : jcomponents){
                                if(c instanceof JLabel){
                                    // remove label from its jpanel
                                    selectedPanel1.remove(c);
                                
                                    selectedPanel1.revalidate();
                                    selectedPanel1.repaint();} }

                            Component[] scomponents = selectedPanel2.getComponents();

                            // find JLabel "pinned" to second JPanel selected
                            for(Component c : scomponents){
                                // locate and remove the label
                                if(c instanceof JLabel){
                                    selectedPanel2.remove(c);

                                    selectedPanel2.revalidate();
                                    selectedPanel2.repaint();} }

                            // add the captured piece to the corresponding location
                            // captured piece is black
                            int capturedWidth,
                                capturedHeight;
                            if(isBlack(selectedPiece2) && selectedPiece2 != null && 
                               selectedPanel1 != selectedPanel2){
                                // determine height and width of the captured piece
                                capturedHeight = (int) ((boardHeight / 8.0) * 0.5);
                                capturedWidth = (int) ((double) (selectedPiece2.getWidth() * 
                                                ((double) capturedHeight / 
                                                (double) selectedPiece2.getHeight())));
                                pin(selectedPiece2, northWest, capturedWidth, capturedHeight, 
                                    "FlowLayout", false);}

                            // captured piece is white
                            else if(isWhite(selectedPiece2) && selectedPiece2 != null && 
                                    selectedPanel1 != selectedPanel2){
                                // determine height and width
                                capturedHeight = (int) ((boardHeight / 8.0) * 0.5);
                                capturedWidth = (int) ((double) (selectedPiece2.getWidth() * 
                                                ((double) capturedHeight / 
                                                (double) selectedPiece2.getHeight())));
                                pin(selectedPiece2, southEast, capturedWidth, capturedHeight, 
                                    "FlowLayout", false);}

                            // add the chesspiece of the first selected panel to the next
                            list.replaceComponent(selectedPanel1, selectedPanel2);
                            pin(selectedPiece, selectedPanel2, 0, 0, "BorderLayout", true);

                            // remove outline from suggested move panels
                            /* int myLocation = 0;
                            for(int index = 0; index < list.getSize(); ++index){
                                if(list.pop(index) == selectedPanel1)
                                    myLocation = index;}*/
                            for(int index = 0; index < myMoves.getSize(); ++index){
                                removeOutline((JPanel) list.pop((int) myMoves.pop(index)));}

                            // Reset all the the fields 
                            selectedPanel1 = null;
                            selectedPanel2 = null;
                            selectedPiece = null;} }
                    
                    // Other wise, find which component was clicked
                    else{
                        // check that panel selected by user has a chesspiece component
                        if(list.getComponent(clickedPanel) != null){
                            selectedPanel1 = clickedPanel;
                            selectedPiece =  (ChessPiece) list.getComponent(selectedPanel1);
                            outline(selectedPanel1, Color.red, 5);
                            
                            // provide suggestions
                            // get my location
                            int myLocation = 0;
                            for(int index = 0; index < list.getSize(); ++index){
                                if(list.pop(index) == selectedPanel1)
                                    myLocation = index;}

                            myMoves = selectedPiece.removeOverflow(myLocation, list);
                            for(int index = 0; index < myMoves.getSize(); ++index){
                                outline((JPanel) list.pop((int) myMoves.pop(index)), Color.blue, 5);} } } }
            }
        };

        board.addMouseListener(ma);
        board.addMouseMotionListener(ma);
    }

    /**
    Private helper method that adds a chesspiece image to the center of a JPanel using a JLabel 
    Height and width of the image is specified by the parameters
    'dimensionDecider' parameter determines if the label is set to the size of the panel or to the 
    size of the image
    */
    private void pin(ChessPiece piece, JPanel panel, int width, int height, String layoutDecider,
                     boolean dimensionDecider){
                
        // default height and width for each chesspiece
        if(width == 0 && height == 0){
            if(piece instanceof BlackPawn || piece instanceof WhitePawn){
                height = (int) ((boardHeight / 8.0) * 0.6);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            else if(piece instanceof BlackRook || piece instanceof WhiteRook){
                height = (int) ((boardHeight / 8.0) * 0.75);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            else if(piece instanceof BlackKnight || piece instanceof WhiteKnight){
                height = (int) ((boardHeight / 8.0) * 0.75);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            else if(piece instanceof BlackBishop || piece instanceof WhiteBishop){ 
                height = (int) ((boardHeight / 8.0) * 0.8);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            else if(piece instanceof BlackQueen || piece instanceof WhiteQueen){ 
                height = (int) ((boardHeight / 8.0) * 0.85);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            // ChessPiece is a king
            else{ 
                height = (int) ((boardHeight / 8.0) * 0.9);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));} } 

        // rescale image to fit its jpanel
        piece.scaleImage(width, height);

        // create the label and set preferrences
        JLabel label = new JLabel(piece.toImageIcon(), JLabel.CENTER);

        // determine whether or not the label should be set to the size of the label or not
        if(dimensionDecider == true)
            label.setPreferredSize(panel.getPreferredSize());

        else{
            label.setPreferredSize(new Dimension(width, height));}

        // use layout manager specified in the parameters
        if(layoutDecider.equals("BorderLayout"))
            panel.add(label, BorderLayout.CENTER);

        else if(layoutDecider.equals("FlowLayout"))
            panel.add(label);
    }

    /**
    Private helper method that outlines a jpanel chessquare with a color specified in the parameters
    The width of this outline is specified w/ in the methods parameters
    */
    private void outline(JPanel panel, Color color, int depth){
        panel.setLayout(new BorderLayout());

        // get the contents of the current square
        Component[] pComponents = panel.getComponents();
        JLabel myLabel = null;
        for(Component c : pComponents){
            if(c instanceof JLabel)
                myLabel = (JLabel) c;}

        // create the jlabels that acts as the outline
        JLabel northOutliner = new JLabel();
        JLabel southOutliner = new JLabel();
        JLabel eastOutliner = new JLabel();
        JLabel westOutliner = new JLabel();

        northOutliner.setBackground(color);
        southOutliner.setBackground(color);
        eastOutliner.setBackground(color);
        westOutliner.setBackground(color);

        // set Dimensions of the outline panels
        northOutliner.setPreferredSize(new Dimension(panel.getWidth(), depth));
        southOutliner.setPreferredSize(new Dimension(panel.getWidth(), depth));
        eastOutliner.setPreferredSize(new Dimension(depth, panel.getHeight()));
        westOutliner.setPreferredSize(new Dimension(depth, panel.getHeight()));

        northOutliner.setOpaque(true);
        southOutliner.setOpaque(true);
        eastOutliner.setOpaque(true);
        westOutliner.setOpaque(true);

        if(myLabel != null){
            panel.add(myLabel, BorderLayout.CENTER);
            panel.add(northOutliner, BorderLayout.NORTH);
            panel.add(southOutliner, BorderLayout.SOUTH);
            panel.add(eastOutliner, BorderLayout.EAST);
            panel.add(westOutliner, BorderLayout.WEST);}

        else{
            Color myColor = panel.getBackground();
            JLabel center = new JLabel();
            center.setPreferredSize(new Dimension(panel.getWidth() - (2 * depth), 
                                                  panel.getHeight() - (2 * depth)));
            center.setBackground(myColor);
            panel.add(center, BorderLayout.CENTER);
            panel.add(northOutliner, BorderLayout.NORTH);
            panel.add(southOutliner, BorderLayout.SOUTH);
            panel.add(eastOutliner, BorderLayout.EAST);
            panel.add(westOutliner, BorderLayout.WEST);}

        panel.revalidate();
        panel.repaint();
    }

    private void removeOutline(JPanel panel){
        // sort through components and strip all of panel
        Component[] cComponents = panel.getComponents();
        JLabel label = null;
        for(Component c : cComponents){
            if(c instanceof JLabel)
                label = (JLabel) c;
                if(label.getIcon() == null)
                    panel.remove(c);}

        panel.revalidate();
        panel.repaint();
    }

    /**
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
    Method that checks to see if two chesspieces are of the same color
    */
    private boolean sameColor(ChessPiece piece1, ChessPiece piece2){
        // special cases- one of the pieces is null, while the other is not; or both are null
        if((piece1 == null && piece2 != null) || (piece1 != null && piece2 == null) || 
           (piece1 == null && piece2 == null))
            return false;

        // 2 possibilities- piece1 belongs to black color, or white
        if(isBlack(piece1)){ 
            // piece can be black
            if(isBlack(piece2))
                return true;

            // or white
            else{
                return false;} }

        // piece is white
        else{
            // piece can be white
            if(isWhite(piece2))
                return true;

            // or white
            else{
                return false;} }
    }

    private boolean isBlack(ChessPiece piece){
        if(piece instanceof BlackPawn || piece instanceof BlackRook || piece instanceof BlackKnight ||
           piece instanceof BlackBishop || piece instanceof BlackQueen || piece instanceof BlackKing)
            return true;
        return false;
    }

    private boolean isWhite(ChessPiece piece){
        if(piece instanceof WhitePawn || piece instanceof WhiteRook || piece instanceof WhiteKnight ||
           piece instanceof WhiteBishop || piece instanceof WhiteQueen || piece instanceof WhiteKing)
            return true;
        return false;
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
