import javax.swing.*;
import java.awt.*;  // used for Dimension, GraphicsEnvironment, GraphicsDevice
import java.util.Random;
import java.awt.image.BufferedImage;
import java.awt.event.*;  // used for MouseAdapter and MouseEvent

public class MyMouseAdapter extends MouseAdapter{
    // field declaration
    private JPanel selectedPanel1;
    private JPanel selectedPanel2;
    private ChessPiece selectedPiece;

    private ChessPiece chosenPiece;
    // private JPanel chosenPanel;
    private int myX;
    private int myY;
    private int xLocation;
    private int yLocation;

    // group of fields that I need to decide what to do with
    private List list;
    private int boardHeight;
    private int screenWidth;
    private JLayeredPane layeredPane;
    private JPanel capturedWhite;
    private JPanel capturedBlack1;
    private JPanel capturedBlack2;

    private List myMoves;

    private ChessPiece priorMove;
    private int myLocation;






    private boolean dragged;
    private Point initialClick;
    // private JPanel parentPanel;
    private JPanel pressedPanel;
    private JPanel invisiblePanel;
    private JLabel pressedLabel;

    public MyMouseAdapter(List chessboard, int bH, int sW, JLayeredPane jlay, JPanel capWhite,
                          JPanel capBlack1, JPanel capBlack2, JPanel invisible){
        list = chessboard;
        boardHeight = bH;
        screenWidth = sW;
        layeredPane = jlay;
        capturedWhite = capWhite;
        capturedBlack1 = capBlack1;
        capturedBlack2 = capBlack2;
        invisiblePanel = invisible;
       
        // first move is made by white side
        priorMove = new BlackPawn();

        dragged = false;
    }

    @Override
    /*
    Used in conjunction with the mouseDragged() method 
    Separate from the mouseReleased() method
    */
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed!"); 

        // get the pressed piece
        pressedPanel = getClickedSquare(e);
        // pressedPiece = (ChessPiece) list.getComponent(getClickedSquare(e));
        // get the selected label
        pressedLabel = null;
        Component[] pComponents = pressedPanel.getComponents();
        for(Component a : pComponents){
            if(a instanceof JLabel)
                pressedLabel = (JLabel) a;}

        // get the cordinates of the click
        xLocation = e.getXOnScreen();
        yLocation = e.getYOnScreen();

        myX = e.getX();
        myY = e.getY();

        initialClick = e.getPoint();

        dragged = false;


        /* JComponent comp = (JComponent) e.getComponent();

        if(comp instanceof JLayeredPane)
            System.out.println("We're screwed!");

        if(comp instanceof JPanel){
        // get the square the click was registered at
        JPanel clickedPanel = getClickedSquare(e);
        chosenPiece = (ChessPiece) list.getComponent(getClickedSquare(e));

        if(chosenPiece != null){
            // get the label corresponding to the chess piece
            Component[] pComponents = clickedPanel.getComponents();
            for(Component a : pComponents){
                if(a instanceof JLabel){
                    chosenLabel = (JLabel) a; 

                    // remove the JLabel from its location
                    clickedPanel.remove(chosenLabel);} }

            chosenLabel.setVisible(true);

            parentPanel.add(chosenLabel);
            // layeredPane.add(parentPanel, new GridBagConstraints(), JLayeredPane.DRAG_LAYER);

            layeredPane.revalidate();
            layeredPane.repaint();

            // get the cordinates of the click
            xLocation = e.getXOnScreen();
            yLocation = e.getYOnScreen();

            myX = e.getX();
            myY = e.getY();

            initialClick = e.getPoint();

            dragged = false;

            System.out.println("Mouse pressed!");} }*/
    }

    @Override
    /*
    In dragging the chesspiece, the user will not be able to see the potential moves of 
    His selected piece
    */
    public void mouseDragged(MouseEvent e){
        JComponent comp = (JComponent) e.getComponent();
        JLayeredPane parent = (JLayeredPane) comp.getParent();
        ChessPiece pressedPiece = (ChessPiece) list.getComponent(pressedPanel);

        // get the selected label
        /* JLabel pressedLabel = null;
        Component[] pComponents = pressedPanel.getComponents();
        for(Component a : pComponents){
            if(a instanceof JLabel)
                pressedLabel = (JLabel) a;}*/

        if(pressedPiece != null){
            System.out.println("Mouse Dragged!");

            // first time component is being dragged
            // JPanel invisiblePanel;
            if(!dragged){
                pressedPanel.remove(pressedLabel);
                /* // get the label corresponding to the chess piece
                Component[] pComponents = pressedPanel.getComponents();
                for(Component a : pComponents){
                    if(a instanceof JLabel){
                        chosenLabel = (JLabel) a; 

                        // remove the JLabel from its location
                        pressedPanel.remove(chosenLabel);} }*/
    
                /* // get invisible jpanel in the drag layer
                JPanel invisiblePanel = null;
                Component[] dComponents = parent.getComponentsInLayer(JLayeredPane.DRAG_LAYER);
                for(Component a : dComponents){
                    if(a instanceof JPanel)
                        invisiblePanel = (JPanel) a;}*/

                // add image to the invisible layer and set it visible
                // pressed
                // pressedPanel.setLocation(400, 500);
                invisiblePanel.add(pressedLabel);
                invisiblePanel.setVisible(true);
                invisiblePanel.setBackground(Color.red);
                pressedLabel.setVisible(true);

                layeredPane.revalidate();
                layeredPane.repaint();}

            // continuation of drag
            else{
                // update the position of the label
                // int newXLocation = (e.getXOnScreen() - xLocation) + myX;
                // int newYLocation = (e.getYOnScreen() - yLocation) + myY;
                // pressedLabel.setLocation(newXLocation, newYLocation);

                int mom = comp.getX() + e.getX() - initialClick.x;
                int dad = comp.getY() + e.getY() - initialClick.y;
                pressedLabel.setLocation(mom, dad);

                layeredPane.revalidate();
                layeredPane.repaint();}

            dragged = true;}

        /* // locate the clicked chesspiece
        JPanel chosenPanel = getClickedSquare(e);
        chosenPiece = (ChessPiece) list.getComponent(getClickedSquare(e));

        if(chosenPiece != null){
            layeredPane.setLayout(null);

            // get the coordinates of the chesspiece's new location
            int newXLocation = (e.getXOnScreen() - xLocation) + myX;
            int newYLocation = (e.getYOnScreen() - yLocation) + myY;
            chosenLabel.setLocation(newXLocation, newYLocation);
            // int mom = comp.getX() + e.getX() - initialClick.x;
            // int dad = comp.getY() + e.getY() - initialClick.y;
            // chosenLabel.setLocation(mom, dad);

            layeredPane.revalidate();
            layeredPane.repaint();

            dragged = true;} */
    }

    @Override
    /*
    If the user presses a square, then drags and releases, no piece options will be given
    However, if the user releases the mouse without dragging, piece options to move will be given
    */
    public void mouseReleased(MouseEvent e){
        System.out.println("Mouse Released!");

        // find the chesssquare that has just been selected 
        JPanel clickedPanel = getClickedSquare(e);

        // user dragged the selected piece before release
        if(dragged){
            System.out.println("Drag released");

            // remove panel from drag layer
            invisiblePanel.removeAll();

            // add panel to square user was hovering over 
            list.replaceComponent(pressedPanel, clickedPanel);
            pin((ChessPiece) list.getComponent(pressedPanel), clickedPanel, 0, 0, 
                "BorderLayout", true);
        }

        // user did not drag the selected piece before release- give options
        else{
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
                        capturedWidth = (int) ((double) (selectedPiece2.getWidth() * ((double) 
                                              capturedHeight / (double) selectedPiece2.getHeight())));
                                
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
                           (double) selectedPiece2.getHeight())))) + 
                           (numComponents * 5) + 5) < (0.4 * (screenWidth - boardHeight)))
                            pin(selectedPiece2, capturedBlack1, capturedWidth, capturedHeight, 
                                "FlowLayout", false);

                        // use upper panel
                        else{
                            pin(selectedPiece2, capturedBlack2, capturedWidth, capturedHeight, 
                                "FlowLayout", false);} }

                    // captured piece is white
                    else if(isWhite(selectedPiece2) && selectedPiece2 != null && 
                            selectedPanel1 != selectedPanel2){
                        // determine height and width
                        capturedHeight = (int) ((boardHeight / 8.0) * 0.5);
                        capturedWidth = (int) ((double) (selectedPiece2.getWidth() * ((double) 
                                              capturedHeight / (double) selectedPiece2.getHeight())));
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
                            addSquare((JPanel) list.pop(possibleMove), 20, option);} } } } }
    } 

    /**
    Method that identifies the location of MouseEvents and returns the JPanel at those cordinates
    */
    private JPanel getClickedSquare(MouseEvent e){
        Component comp = e.getComponent().getComponentAt(e.getPoint());

        return (JPanel) comp;
    }

    /**
    Method that checks the following criteria to see if a chesspiece is eligible to move:
    The chess square being selected contains a piece
    The piece being selected is a different color that the previous move
    */
    private boolean movable(ChessPiece move, ChessPiece priorMove){
        if(move != null && isOpponent(move, priorMove))
            return true;

        return false;
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
    private int rowOf(int myLocation){
        return (myLocation / 8) + 1;
    }

    /**
    Method that checks if a ChessPiece is a king
    */
    private boolean isKing(ChessPiece piece){
        if(piece instanceof BlackKing || piece instanceof WhiteKing)
            return true;

        return false;
    }

    /**
    Method that checks if a piece is eligible to be captured
    */
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
}
