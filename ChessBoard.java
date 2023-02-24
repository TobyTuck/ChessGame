import javax.swing.*;
import java.awt.*;  // used for Dimension, GraphicsEnvironment, GraphicsDevice
import java.awt.event.*;  // used for MouseAdapter and MouseEvent
import java.util.Random;
import java.awt.image.BufferedImage;

public class ChessBoard extends JFrame{

    private int boardHeight;

    /**
    No-arg constructor that creates the board- and adds all pieces to their default positions
    */
    public ChessBoard(){

        JLayeredPane layeredPane;

        JPanel defaultHolder;

        // make 'Holder' panels for the background chess (board, whiteCapture, blackCaptured)
        JPanel board;
        JPanel capturedWhite;
        JPanel capturedBlack1;
        JPanel capturedBlack2;

        // holds list of all individual squares and their pieces
        List list;

        int screenHeight;
        int screenWidth;

        // get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Color darkGreen = new Color(25, 45, 25);

        this.getContentPane().setBackground(darkGreen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // shall we make the size of the chessboard update as resized?
        this.setResizable(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // set applet's tab icon
        this.setIconImage(new Logo().getImage());
        this.setTitle("ChessGame Application");

        screenHeight = screenSize.height;
        screenWidth = screenSize.width;

        // initialize all JComponents 
        layeredPane = new JLayeredPane();

        defaultHolder = new JPanel(new GridBagLayout());

        board = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JPanel rightContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        capturedWhite = new JPanel(new FlowLayout());

        JPanel rightFiller = new JPanel();
        capturedBlack1 = new JPanel(new FlowLayout());
        capturedBlack2 = new JPanel(new FlowLayout());

        // ensure the board is square- and divides evenly into 8 
        int eightDivisible = 0;
        do{
            boardHeight = (int)((screenHeight - 70) * 0.9) + eightDivisible;
            ++eightDivisible;
        }while(boardHeight % 8 != 0);

        Dimension containerDimension = new Dimension((int) (0.4 * (screenWidth - boardHeight)),
                                                     (int) (boardHeight * 0.7));

        // set the size of all JComponents as necessary
        /*defaultHolder.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // get the size of the container
                Dimension size = e.getComponent().getParent().getSize();
                int width = size.width;
                int height = size.height;

                if(width > 1000 && height > 600){
                    // defaultHolder.setBounds(0, 0, size.width, size.height);
                    // e.getComponent().setSize(size);

                    // delete
                    System.out.println("Width: " + size.width +
                                       "\nHeight " + size.height);}
            }
        });*/

        // find some way to set this to the size of the JLayeredPane- and the JFrame
        defaultHolder.setSize(1848, 1011);
        defaultHolder.setBackground(Color.red);
        defaultHolder.setLayout(new GridBagLayout());

        board.setPreferredSize(new Dimension(boardHeight, boardHeight));
        capturedWhite.setPreferredSize(containerDimension);
        rightContainer.setPreferredSize(containerDimension);

        rightFiller.setPreferredSize(new Dimension((int) (0.4 * (screenWidth - boardHeight)),
                                                   (int) ((boardHeight * 0.7) - 
                                                         ((boardHeight / 8.0) + 20)))); 
        capturedBlack1.setPreferredSize(new Dimension((int) (0.4 * (screenWidth - boardHeight)),
                                                     (int) (((boardHeight / 8.0) * 0.5) + 10)));
        capturedBlack2.setPreferredSize(new Dimension((int) (0.4 * (screenWidth - boardHeight)),
                                                     (int) (((boardHeight / 8.0) * 0.5) + 10)));

        // set the colors of JComponents (for testing purposes)
        capturedWhite.setBackground(Color.white);
        rightContainer.setBackground(Color.red);
        rightFiller.setBackground(Color.blue);
        capturedBlack1.setBackground(Color.yellow);
        capturedBlack2.setBackground(Color.pink);
        
        // add components of each square to the list
        list = new List(8);

        JPanel square0 = new JPanel(); list.push(square0, null);
        JPanel square1 = new JPanel(); list.push(square1, null);
        JPanel square2 = new JPanel(); list.push(square2, null);
        JPanel square3 = new JPanel(); list.push(square3, null);
        JPanel square4 = new JPanel(); list.push(square4, null);
        JPanel square5 = new JPanel(); list.push(square5, null);
        JPanel square6 = new JPanel(); list.push(square6, null);
        JPanel square7 = new JPanel(); list.push(square7, null);
        JPanel square8 = new JPanel(); list.push(square8, null);
        JPanel square9 = new JPanel(); list.push(square9, null);
        
        JPanel square10 = new JPanel(); list.push(square10, null);
        JPanel square11 = new JPanel(); list.push(square11, null);
        JPanel square12 = new JPanel(); list.push(square12, null);
        JPanel square13 = new JPanel(); list.push(square13, null);
        JPanel square14 = new JPanel(); list.push(square14, null);
        JPanel square15 = new JPanel(); list.push(square15, null);
        JPanel square16 = new JPanel(); list.push(square16, null);
        JPanel square17 = new JPanel(); list.push(square17, null);
        JPanel square18 = new JPanel(); list.push(square18, null);
        JPanel square19 = new JPanel(); list.push(square19, null);

        JPanel square20 = new JPanel(); list.push(square20, null);
        JPanel square21 = new JPanel(); list.push(square21, null);
        JPanel square22 = new JPanel(); list.push(square22, null);
        JPanel square23 = new JPanel(); list.push(square23, null);
        JPanel square24 = new JPanel(); list.push(square24, null);
        JPanel square25 = new JPanel(); list.push(square25, null);
        JPanel square26 = new JPanel(); list.push(square26, null);
        JPanel square27 = new JPanel(); list.push(square27, null);
        JPanel square28 = new JPanel(); list.push(square28, null);
        JPanel square29 = new JPanel(); list.push(square29, null);

        JPanel square30 = new JPanel(); list.push(square30, null);
        JPanel square31 = new JPanel(); list.push(square31, null);
        JPanel square32 = new JPanel(); list.push(square32, null);
        JPanel square33 = new JPanel(); list.push(square33, null);
        JPanel square34 = new JPanel(); list.push(square34, null);
        JPanel square35 = new JPanel(); list.push(square35, null);
        JPanel square36 = new JPanel(); list.push(square36, null);
        JPanel square37 = new JPanel(); list.push(square37, null);
        JPanel square38 = new JPanel(); list.push(square38, null);
        JPanel square39 = new JPanel(); list.push(square39, null);

        JPanel square40 = new JPanel(); list.push(square40, null);
        JPanel square41 = new JPanel(); list.push(square41, null);
        JPanel square42 = new JPanel(); list.push(square42, null);
        JPanel square43 = new JPanel(); list.push(square43, null);
        JPanel square44 = new JPanel(); list.push(square44, null);
        JPanel square45 = new JPanel(); list.push(square45, null);
        JPanel square46 = new JPanel(); list.push(square46, null);
        JPanel square47 = new JPanel(); list.push(square47, null);
        JPanel square48 = new JPanel(); list.push(square48, null);
        JPanel square49 = new JPanel(); list.push(square49, null);

        JPanel square50 = new JPanel(); list.push(square50, null);
        JPanel square51 = new JPanel(); list.push(square51, null);
        JPanel square52 = new JPanel(); list.push(square52, null);
        JPanel square53 = new JPanel(); list.push(square53, null);
        JPanel square54 = new JPanel(); list.push(square54, null);
        JPanel square55 = new JPanel(); list.push(square55, null);
        JPanel square56 = new JPanel(); list.push(square56, null);
        JPanel square57 = new JPanel(); list.push(square57, null);
        JPanel square58 = new JPanel(); list.push(square58, null);
        JPanel square59 = new JPanel(); list.push(square59, null);

        JPanel square60 = new JPanel(); list.push(square60, null);
        JPanel square61 = new JPanel(); list.push(square61, null);
        JPanel square62 = new JPanel(); list.push(square62, null);
        JPanel square63 = new JPanel(); list.push(square63, null);

        //Set properties of all the items
        // in the List
        int cellRemainder,
            count = 0;
        JPanel jpanel;

        for(int index = 0; index < list.getSize(); ++index){

            // type conversions
            jpanel = (JPanel) list.pop(index);
            
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

        // add components to their parent containers
        rightContainer.add(rightFiller);
        rightContainer.add(capturedBlack2);
        rightContainer.add(capturedBlack1);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        defaultHolder.add(capturedWhite, gbc);
        defaultHolder.add(board, gbc);
        defaultHolder.add(rightContainer, gbc);

        layeredPane.add(defaultHolder, JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane);

        // instantiate and add MouseAdapter to chessboard
        MyMouseAdapter mma = new MyMouseAdapter(list, boardHeight, screenWidth, layeredPane, 
                                                capturedWhite, capturedBlack1, capturedBlack2);

        board.addMouseListener(mma);
        layeredPane.addMouseMotionListener(mma);

        this.setVisible(true);
    }

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
}
        // frame.addMouseListener(ma);
        // frame.addMouseMotionListener(ma);

        /*frame.addMouseListener(new MouseAdapter() {
            // field declaration
            private JPanel selectedPanel1;
            private JPanel selectedPanel2;
            private ChessPiece selectedPiece;

            private ChessPiece chosenPiece;
            private JPanel chosenPanel;
            private int myX;
            private int myY;
            private int xLocation;
            private int yLocation;
            private JLabel chosenLabel;

            private List myMoves;

            // first move is made by white side
            private ChessPiece priorMove = new BlackPawn();
            private int myLocation;

            @Override
            /*
            Used in conjunction with the mouseDragged() method 
            Separate from the mouseReleased() method
            */
            /*
            public void mousePressed(MouseEvent e) {
                Component parent = e.getComponent();
                Component comp = parent.getComponentAt(e.getPoint());

                chosenPiece = null;
                if(comp instanceof JPanel){
                    // get the chesspiece that has been clicked
                    chosenPanel = (JPanel) comp;
                    chosenPiece = (ChessPiece) list.getComponent(chosenPanel);

                    // get the cordinates of the click
                    xLocation = e.getXOnScreen();
                    yLocation = e.getYOnScreen();

                    myX = e.getX();
                    myY = e.getY();

                    Component[] pComponents = chosenPanel.getComponents();
                    for(Component a : pComponents){
                        if(a instanceof JLabel){
                            chosenLabel = (JLabel) a;} }

                    layeredPane.add(chosenLabel, JLayeredPane.DRAG_LAYER);}
                    // layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }

            @Override
            /*
            In dragging the chesspiece, the user will not be able to see the potential moves of 
            His selected piece
            */
            /*
            public void mouseDragged(MouseEvent e){
                int newXLocation;
                int newYLocation;

                if(chosenPiece != null){
                    // get the coordinates of the new location of the chesspiece
                    newXLocation = (e.getXOnScreen() - xLocation) + myX;
                    newYLocation = (e.getYOnScreen() - yLocation) + myY;

                    // update the location of the JLabel representing the piece 
                    chosenLabel.setLocation(newXLocation, newYLocation);}
            }

            @Override
            /*
            By releasing a mouse click, the user will be able to see the potential moves of the 
            selected piece
            This is the major difference between a releasing a click and dragging a chesspiece 
            to its intended location
            */
            /*
            public void mouseReleased(MouseEvent e){
                // find the chesssquare that has just been selected 
                JPanel clickedPanel = getChessSquare(e);

                    // Determine if there a panel has been selected before this action 
                    if (selectedPanel1 != null) {
                        selectedPanel2 = clickedPanel;
                        ChessPiece selectedPiece2 = (ChessPiece) list.getComponent(selectedPanel2);

                        boolean legal = false;
                        // restrict choice of piece movement
                        for(int index = 0; index < myMoves.getSize(); ++index){
                            if((JPanel) list.pop((int) myMoves.pop(index)) == selectedPanel2)
                                legal = true;}

                        // chesspiece can't capture chesspiece of same color
                        if(legal || selectedPiece == selectedPiece2){ 

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
                                
                                // get number of captured pieces held by panel
                                Component[] pComponents = capturedBlack1.getComponents();
                                // check if size including one more label can fit
                                int numComponents = 1;
                                for(Component c : pComponents){
                                    if(c instanceof JLabel)
                                        ++numComponents;}                               
                                // check if the first panel can hold anymore captured pieces
                                if(((numComponents * ((double) (selectedPiece2.getWidth() * 
                                   ((double) (boardHeight / 8.0) * 0.5 /  
                                   (double) selectedPiece2.getHeight())))) + (numComponents * 5) + 5)
                                   < (0.4 * (screenWidth - boardHeight)))
                                    pin(selectedPiece2, capturedBlack1, capturedWidth, 
                                        capturedHeight, "FlowLayout", false);

                                // use upper panel
                                else{
                                    pin(selectedPiece2, capturedBlack2, capturedWidth, 
                                        capturedHeight, "FlowLayout", false);} }

                            // captured piece is white
                            else if(isWhite(selectedPiece2) && selectedPiece2 != null && 
                                    selectedPanel1 != selectedPanel2){
                                // determine height and width
                                capturedHeight = (int) ((boardHeight / 8.0) * 0.5);
                                capturedWidth = (int) ((double) (selectedPiece2.getWidth() * 
                                                ((double) capturedHeight / 
                                                (double) selectedPiece2.getHeight())));
                                pin(selectedPiece2, capturedWhite, capturedWidth, capturedHeight, 
                                    "FlowLayout", false);}

                            // add the chesspiece of the first selected panel to the next
                            list.replaceComponent(selectedPanel1, selectedPanel2);
                            pin(selectedPiece, selectedPanel2, 0, 0, "BorderLayout", true);

                            // remove outline from suggested move panels
                            for(int index = 0; index < myMoves.getSize(); ++index){
                                removeOutline((JPanel) list.pop((int) myMoves.pop(index)));}

                            // if pawn reaches opposite side of board, turn it into a queen
                            int location = 0;
                            for(int index = 0; index < list.getSize(); ++index){
                                if(list.pop(index) == selectedPanel2)
                                    location = index;}

                            // if pawn reaches the end of the board- make it a queen
                            if(selectedPiece instanceof BlackPawn && rowOf(location) == 8){
                                list.addComponent(selectedPanel2, new BlackQueen());
                                pin(new BlackQueen(), selectedPanel2, 0, 0, "BorderLayout", true);}

                            // if pawn reaches the end of the board- make it a queen
                            if(selectedPiece instanceof WhitePawn && rowOf(location) == 1){
                                list.addComponent(selectedPanel2, new WhiteQueen());
                                pin(new WhiteQueen(), selectedPanel2, 0, 0, "BorderLayout", true);}

                            // allow color to move again only if he didn't move the piece selected 
                            if(selectedPiece != selectedPiece2)
                                priorMove = selectedPiece;
                            
                            // Reset all the the fields
                            selectedPanel1 = null;
                            selectedPanel2 = null;
                            selectedPiece = null;} }
                    
                    // Other wise, find which component was clicked
                    else{
                        // find the selected chesspiece 
                        ChessPiece piece = (ChessPiece) list.getComponent(clickedPanel);

                        if(movable(piece, priorMove)){ 

                            // set fields for alternate method calls
                            selectedPanel1 = clickedPanel;
                            selectedPiece =  piece;

                            if(isBlack(selectedPiece))
                                outline(selectedPanel1, Color.black, 5);
                            else
                                outline(selectedPanel1, Color.white, 5);

                            // provide suggestions
                            // get my location
                            int myLocation = 0;
                            for(int index = 0; index < list.getSize(); ++index){
                                if(list.pop(index) == selectedPanel1)
                                    myLocation = index;}

                            int possibleMove;
                            Color option = new Color(127, 255, 0);
                            Color captureKing = new Color(128, 0, 128);
                            myMoves = selectedPiece.possibleMoves(myLocation, list, true);
                            for(int index = 0; index < myMoves.getSize(); ++index){
                                possibleMove = (int) myMoves.pop(index);
                                // if move is opponent, highlight it for user
                                if(isOpponent((ChessPiece) list.getComponent(myLocation), 
                                             (ChessPiece) list.getComponent(possibleMove))){
                                    if(!isKing((ChessPiece) list.getComponent(possibleMove)))
                                        outline((JPanel) list.pop(possibleMove), Color.red, 5);
                                    else{
                                        outline((JPanel) list.pop(possibleMove), captureKing, 5);} }

                                else{
                                    addSquare((JPanel) list.pop(possibleMove), 20, option);} }
                        } 
                    }
                } 
            });

            /**
            Method that identifies the location of MouseEvents and returns the JPanel
            At those cordinates
            */
            /*
            private JPanel getChessSquare(MouseEvent e){
                JPanel clickedPanel = null;
                Component parent = e.getComponent();
                Component comp = parent.getComponentAt(e.getPoint());

                if(comp instanceof JPanel)
                    clickedPanel = (JPanel) comp;

                return clickedPanel;
            }

            /**
            Method that checks the following criteria to see if a chesspiece is eligible to move:
                The chess square being selected contains a piece
                The piece being selected is a different color that the previous move
            */
            /*
            private boolean movable(ChessPiece move, ChessPiece priorMove){
                if(move != null && isOpponent(move, priorMove))
                    return true;

                return false;
            }

        // layeredPane.addMouseListener(ma);
        // layeredPane.addMouseMotionListener(ma);

        // board.addMouseListener(ma);
        // board.addMouseMotionListener(ma);

    /**
    Private helper method that adds a chesspiece image to the center of a JPanel using a JLabel 
    Height and width of the image is specified by the parameters
    'dimensionDecider' parameter determines if the label is set to the size of the panel or to the 
    size of the image
    */
    /*
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
    /*
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

    private void addSquare(JPanel panel, int x, Color color){
        panel.setLayout(new BorderLayout());

        JLabel center = new JLabel();
        JLabel north = new JLabel();
        JLabel south = new JLabel();
        JLabel east = new JLabel();
        JLabel west = new JLabel();

        center.setBackground(color);

        Color panelColor = panel.getBackground();
        north.setBackground(panelColor);
        south.setBackground(panelColor);
        east.setBackground(panelColor);
        west.setBackground(panelColor);

        center.setPreferredSize(new Dimension(x, x));
        north.setPreferredSize
            (new Dimension(panel.getWidth(), (int) (0.5 * (panel.getHeight() - x))));
        south.setPreferredSize
            (new Dimension(panel.getWidth(), (int) (0.5 * (panel.getHeight() - x))));
        east.setPreferredSize(new Dimension((int) (0.5 * (panel.getWidth() - x)), panel.getHeight()));
        west.setPreferredSize(new Dimension((int) (0.5 * (panel.getWidth() - x)), panel.getHeight()));

        center.setOpaque(true);
        north.setOpaque(true);
        south.setOpaque(true);
        east.setOpaque(true);
        west.setOpaque(true);

        panel.add(center, BorderLayout.CENTER);
        panel.add(north, BorderLayout.NORTH);
        panel.add(south, BorderLayout.SOUTH);
        panel.add(east, BorderLayout.EAST);
        panel.add(west, BorderLayout.WEST);

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
    /*
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
    Method that checks if an oppoent exists in a square
    */
    /*
    protected boolean isOpponent(ChessPiece piece1, ChessPiece piece2){
        // special case
        if(piece1 == null || piece2 == null)
            return false;

        // 2 options- piece1 is black
        else if(isBlack(piece1)){
            if(isBlack(piece2))
                return false;
            else{
                return true;} }

        // or white
        else{
            if(isWhite(piece2))
                return false;
            else{
                return true;} }
    }

    /**
    Method that returns the "level" of the chessboard the number is at
    */
    /*
    private int rowOf(int myLocation){
        return (myLocation / 8) + 1;
    }

    /**
    Method that checks if a ChessPiece is a king
    */
    /*
    private boolean isKing(ChessPiece piece){
        if(piece instanceof BlackKing || piece instanceof WhiteKing)
            return true;

        return false;
    }

    /**
    Method that checks if a piece is eligible to be captured
    */
    /*
    private boolean isCheck(List chessboard, ChessPiece king, int myLocation){
        ChessPiece piece;
        List myMoves;
        int location = 0;
        // sort through all chess squares 
        for(int index = 0; index < chessboard.getSize(); ++index){
            piece = (ChessPiece) chessboard.getComponent(index);
            // if chespiece is an opponent
            if(isOpponent(piece, king) && piece != null){
                // get my location
                for(int count = 0; count < chessboard.getSize(); ++count){
                    if(chessboard.getComponent(count) == piece)
                        location = count;}

                // find legal moves
                myMoves = piece.possibleMoves(location, chessboard, true);
                for(int count = 0; count < myMoves.getSize(); ++count){
                    // is one of the opponents move on the piece specified in the parameters
                    if((int) myMoves.pop(count) == myLocation)
                        return true;} } }

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
    /* public Dimension getCellDimension(){
        Random random = new Random();
        int index = random.nextInt(64);

        Object obj = list.pop(0);
        JPanel jpanel = (JPanel)obj;
        return jpanel.getSize();
    }

    // Method to return size of the chessboard
    public Dimension getBoardDimension(){
        return board.getSize();
    }

    // Method to return the dimensions of the entire JFrame
    public Dimension getWindowSize(){
        return frame.getSize();
    }
} */
