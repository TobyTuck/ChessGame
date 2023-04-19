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
    private JPanel pressedPanel;
    private JPanel _board;
    private JLabel pressedLabel;
    private int myX;
    private int myY;
    private int mouseX;
    private int mouseY;

    // location of the currently selected chess pieces
    private int _p1Location;
    private int _p2Location;
    
    // location of the previous selected chess pieces
    private int _p1PriorLocation;
    private int _p2PriorLocation;

    private boolean optionsInterrupter;

    public MyMouseAdapter(List chessboard, int bH, int sW, JLayeredPane jlay, JPanel capWhite,
                          JPanel capBlack1, JPanel capBlack2, JPanel board){
        list = chessboard;
        boardHeight = bH;
        screenWidth = sW;
        layeredPane = jlay;
        capturedWhite = capWhite;
        capturedBlack1 = capBlack1;
        capturedBlack2 = capBlack2;
        _board = board;

        _p1Location = 0;
        _p2Location = 0;
        _p1PriorLocation = 0;
        _p2PriorLocation = 0;
       
        // first move is made by white side
        priorMove = new BlackPawn();

        dragged = false;
        optionsInterrupter = false;
    }

    @Override
    /*
    Used in conjunction with the mouseDragged() method 
    Separate from the mouseReleased() method
    */
    public void mousePressed(MouseEvent e) {
        // get the pressed Chess Square panel 
        pressedPanel = getChessSquare(e);

        // get the pressed label
        pressedLabel = getChessLabel(getChessSquare(e));

        myX = e.getX();
        myY = e.getY();

        mouseX = e.getXOnScreen();
        mouseY = e.getYOnScreen();

        dragged = false;
    }

    @Override
    /*
    In dragging the chesspiece, the user will not be able to see the potential moves of 
    His selected piece
    */
    public void mouseDragged(MouseEvent e){
        JComponent comp = (JComponent) e.getComponent();
        ChessPiece pressedPiece = (ChessPiece) list.getComponent(pressedPanel);

        int tryX = MouseInfo.getPointerInfo().getLocation().x;
        int tryY = MouseInfo.getPointerInfo().getLocation().y;

        // first time component is dragged
        if(!dragged){
            JPanel clickedPanel = getChessSquare(e);
            ChessPiece piece = (ChessPiece) list.getComponent(clickedPanel); 

            // ensure that the choice is a valid one
            if(movablePiece(piece, priorMove) && !optionsInterrupter){
                // set fields for alternate method calls
                selectedPanel1 = clickedPanel;
                selectedPiece =  piece;

                // get the location of the piece
                for(int index = 0; index < list.getSize(); ++index){
                    if(list.pop(index) == selectedPanel1)
                        _p1Location = index;}

                // get label from initial press
                // mouseX + 400
                pressedLabel.setLocation(mouseX, mouseY);

                // add label to top layer of the JLayeredPane
                layeredPane.add(pressedLabel, JLayeredPane.DRAG_LAYER);

                int location = 0;
                for(int index = 0; index < list.getSize(); ++index){
                    if(list.pop(index) == clickedPanel)
                        location = index;}
                myMoves = selectedPiece.possibleMoves(location, list, true, _p1PriorLocation, 
                                                      _p2PriorLocation);

                layeredPane.revalidate();
                layeredPane.repaint();

                dragged = true;} }

        // continuation of drag
        else{
            // update the position of the label
            int deltaX = e.getXOnScreen() - mouseX;
            int deltaY = e.getYOnScreen() - mouseY;
            pressedLabel.setLocation(myX + deltaX + 400, myY + deltaY); 

            layeredPane.revalidate();
            layeredPane.repaint();}
    }

    @Override
    /*
    If the user presses a square, then drags and releases, no piece options will be given
    However, if the user releases the mouse without dragging, piece options to move will be given
    */
    public void mouseReleased(MouseEvent e){
        // find the chess square that has just been selected 
        JPanel clickedPanel = getChessSquare(e);

        // get the location of this chess square
        int location = 0;
        for(int index = 0; index < list.getSize(); ++index){
            if(list.pop(index) == clickedPanel)
                location = index;}

        // user dragged the selected piece before release
        if(dragged){
            selectedPanel2 = clickedPanel;
            ChessPiece selectedPiece2 = (ChessPiece) list.getComponent(selectedPanel2);

            // move must be a valid one
            if(validMove(location, selectedPiece, selectedPiece2)){ 
                _p2Location = location;

                // remove captured piece from its square, move to designated section for captures
                removeCapturedPiece(selectedPanel1, selectedPanel2, selectedPiece, selectedPiece2);

                // add the chesspiece to its panel 
                move(selectedPanel1, selectedPanel2, selectedPiece, selectedPiece2);

                // if pawn reaches the end of the board- make it a queen
                pawnReplacement(location);

                // allow color to move again only if he didn't move the piece selected 
                if(selectedPiece != selectedPiece2){
                    priorMove = selectedPiece;
                    _p1PriorLocation = _p1Location;
                    _p2PriorLocation = _p2Location;}

                // Reset all the the fields
                selectedPanel1 = null;
                selectedPanel2 = null;
                selectedPiece = null;}

            // move was not valid
            else{
                // visually add piece to its prior location
                pin(selectedPiece, selectedPanel1, 0, 0, "BorderLayout", true);} 

            // remove label from the drag layer
            layeredPane.remove(pressedLabel); 
            layeredPane.revalidate();
            layeredPane.repaint();}

        // user did not drag the selected piece before release- give options
        else{
            int possibleLocation = 0;

            // Determine if a piece has been selected for movement yet or not
            // First click to chose piece to move, second to indicate sqaure to move to
            if (selectedPanel1 != null) {
                selectedPanel2 = clickedPanel;
                ChessPiece selectedPiece2 = (ChessPiece) list.getComponent(selectedPanel2);

                // move must be deemed valid 
                if(validMove(location, selectedPiece, selectedPiece2)){ 

                    /*if(selectedPiece != selectedPiece2){
                        _p1PriorLocation = possibleLocation;
                        _p2PriorLocation = _p2Location;}*/

                    _p2Location = location;

                    Component[] jcomponents = selectedPanel1.getComponents();

                    // find JLabel "pinned" to first JPanel selected
                    if(!sameColor(selectedPiece, selectedPiece2)){
                        for(Component c : jcomponents){
                            if(c instanceof JLabel){
                                // remove label from its jpanel
                                selectedPanel1.remove(c);
                                
                                selectedPanel1.revalidate();
                                selectedPanel1.repaint();} } }

                    // remove captured piece from its square, move to designated section for captures
                    removeCapturedPiece(selectedPanel1, selectedPanel2, selectedPiece, 
                                        selectedPiece2);

                    // add the chesspiece of the first selected panel to the next
                    move(selectedPanel1, selectedPanel2, selectedPiece, selectedPiece2);

                    // remove outline from suggested move panels
                    for(int index = 0; index < myMoves.getSize(); ++index){
                        removeOutline((JPanel) list.pop((int) myMoves.pop(index)));}

                    // if pawn reaches the end of the board- make it a queen
                    pawnReplacement(location);

                    // allow color to move again only if he didn't move the piece selected 
                    if(selectedPiece != selectedPiece2)
                        priorMove = selectedPiece;

                    if(selectedPiece != selectedPiece2){
                        _p1PriorLocation = _p1Location;
                        _p2PriorLocation = _p2Location;}
                            
                    // Reset all the the fields
                    selectedPanel1 = null;
                    selectedPanel2 = null;
                    selectedPiece = null;
                    optionsInterrupter = false;} }

            // Other wise, find which component was clicked
            else{
                _p1Location = location;

                // find the selected chesspiece 
                ChessPiece piece = (ChessPiece) list.getComponent(clickedPanel);

                if(movablePiece(piece, priorMove)){

                    // set fields for alternate method calls
                    selectedPanel1 = clickedPanel;
                    selectedPiece =  piece;

                    if(isBlack(selectedPiece))
                       outline(selectedPanel1, Color.black, 5);
                    else
                       outline(selectedPanel1, Color.white, 5);

                    // provide suggestions
                    int possibleMove;
                    ChessPiece past = (ChessPiece) list.getComponent(_p2PriorLocation);
                    Color option = new Color(127, 255, 0);
                    Color captureKing = new Color(128, 0, 128);
                    myMoves = selectedPiece.possibleMoves(_p1Location, list, true, _p1PriorLocation,
                                                          _p2PriorLocation);
                    for(int index = 0; index < myMoves.getSize(); ++index){
                        possibleMove = (int) myMoves.pop(index);
                        // if move is opponent, highlight it for user
                        if(isOpponent((ChessPiece) list.getComponent(_p1Location), 
                           (ChessPiece) list.getComponent(possibleMove))){
                            if(!isKing((ChessPiece) list.getComponent(possibleMove)))
                                outline((JPanel) list.pop(possibleMove), Color.red, 5);
                            else
                                outline((JPanel) list.pop(possibleMove), captureKing, 5);}

                        // castle
                        else if(sameColor((ChessPiece) list.getComponent(_p1Location), 
                                          (ChessPiece) list.getComponent(possibleMove)))
                            outline((JPanel) list.pop(possibleMove), option, 5);

                        // en passant move to capture opponent
                        else if((selectedPiece instanceof BlackPawn ||
                                selectedPiece instanceof WhitePawn) &&
                                (past instanceof BlackPawn || past instanceof WhitePawn) &&
                                (possibleMove == _p2PriorLocation + 8 || 
                                possibleMove == _p2PriorLocation - 8) &&
                                (_p1Location == possibleMove + 7 || _p1Location == possibleMove + 9 ||
                                _p1Location == possibleMove - 7 || _p1Location == possibleMove - 9) &&
                                (_p1PriorLocation == _p2PriorLocation + 16 || 
                                _p1PriorLocation == _p2PriorLocation - 16))
                            addSquare((JPanel) list.pop(possibleMove), 20, Color.red);
                            
                        // standard move
                        else
                            addSquare((JPanel) list.pop(possibleMove), 20, option);} 

                    // throw flag so a dragged option is not able to interrupt
                    optionsInterrupter = true;} } }
    } 

    /**
    Verifies a piece selection is a valid one
    In order for a piece to be valid for movement, it must:
        1) square selected must contain a chesspiece
        2) chesspiece must be the correct color
    */
    private boolean movablePiece(ChessPiece move, ChessPiece priorMove){
        if(move != null && isOpponent(move, priorMove))
            return true;

        return false;
    }

    /**
    Verifies a piece movement is a valid one
    Chess moves are generated by the respective class for each chesspiece, and are verified here
    */
    private boolean validMove(int position, ChessPiece firstSelected, ChessPiece secondSelected){
        boolean legal = false;
        // compare requested move with legal moves possible given the chesspiece
        for(int index = 0; index < myMoves.getSize(); ++index){
            if((int) myMoves.pop(index) == position)
                legal = true;}

        // selection of the same piece is also allowed
        if(legal || firstSelected == secondSelected)
            return true;

        return false;
    }

    /**
    Removes captured pieces from the board and adds them to a panel displaying the captured pieces
    Pieces of the same color are not considered "captured"
    */
    private void removeCapturedPiece(JPanel firstSelected, JPanel secondPanel, 
                                     ChessPiece movedPiece, ChessPiece secondPiece){

        if(movedPiece == secondPiece)
            pin(secondPiece, secondPanel, 0, 0, "BorderLayout", true);

        // move resulted in a capture
        if(!sameColor(movedPiece, secondPiece)){
            JPanel capturedPanel = null;
            ChessPiece capturedPiece = null;

            ChessPiece standIn = (ChessPiece) list.getComponent(_p2PriorLocation);
            // special case- en passant capture
            if((movedPiece instanceof BlackPawn || movedPiece instanceof WhitePawn) &&
               (standIn instanceof BlackPawn || standIn instanceof WhitePawn) &&
               (_p2Location == _p2PriorLocation + 8 || _p2Location == _p2PriorLocation - 8) &&
               (_p1Location == _p2Location + 7 || _p1Location == _p2Location + 9 ||
                _p1Location == _p2Location - 7 || _p1Location == _p2Location - 9) &&
               (_p1PriorLocation == _p2PriorLocation + 16 || 
                _p1PriorLocation == _p2PriorLocation - 16)){
                
                // find the piece that was captured in passing
                capturedPiece = standIn;
                capturedPanel = (JPanel) list.pop(_p2PriorLocation);}

            // standard capture
            else{
                capturedPiece = secondPiece;
                capturedPanel = secondPanel;}

            // add the captured piece to the corresponding location
            // captured piece is black
            int capturedWidth,
                capturedHeight;
            if(isBlack(capturedPiece) && capturedPanel != null && firstSelected != capturedPanel){
                // determine height and width of the captured piece
                capturedHeight = (int) ((boardHeight / 8.0) * 0.5);
                capturedWidth = (int) ((double) (capturedPanel.getWidth() * ((double) 
                                       capturedHeight / (double) capturedPanel.getHeight())));
                               
                // get number of captured pieces held by panel
                Component[] pComponents = capturedBlack1.getComponents();
                // check if size including one more label can fit
                int numComponents = 1;
                for(Component c : pComponents){
                    if(c instanceof JLabel)
                        ++numComponents;}                               
                // check if the first panel can hold anymore captured pieces
                if(((numComponents * ((double) (capturedPanel.getWidth() * 
                  ((double) (boardHeight / 8.0) * 0.5 / (double) capturedPanel.getHeight())))) + 
                   (numComponents * 5) + 5) < (0.4 * (screenWidth - boardHeight)))
                    pin(capturedPiece, capturedBlack1, capturedWidth, capturedHeight, 
                        "FlowLayout", false);

                // use 2nd (upper) panel
                else{
                    pin(capturedPiece, capturedBlack2, capturedWidth, capturedHeight, 
                        "FlowLayout", false);} }

            // captured piece is white
            else if(isWhite(capturedPiece) && capturedPiece != null && 
                    firstSelected != capturedPanel){
                // determine height and width
                capturedHeight = (int) ((boardHeight / 8.0) * 0.5);
                capturedWidth = (int) ((double) (capturedPanel.getWidth() * ((double) 
                                       capturedHeight / (double) capturedPanel.getHeight())));
                pin(capturedPiece, capturedWhite, capturedWidth, capturedHeight, "FlowLayout", 
                    false);} }
    }

    /**
    Method that removes all JLabel Components from a chess square (JPanel)
    */
    private void stripLabels(JPanel panel){
        Component[] components = panel.getComponents();
            for(Component c : components){
                // locate and remove the label
                if(c instanceof JLabel)
                    panel.remove(c);}

            panel.revalidate();
            panel.repaint();
    }

    /**
    Method that moves the selected piece to its location
    */
    private void move(JPanel firstSelected, JPanel secondSelected, ChessPiece firstPiece, ChessPiece
                      secondPiece){
        // move was a capture, or a "de-select" 
        if(!sameColor(firstPiece, secondPiece) || firstPiece == secondPiece){
            ChessPiece passed = (ChessPiece) list.getComponent(_p2PriorLocation);

            // special case- en passant capture
            if((firstPiece instanceof BlackPawn || firstPiece instanceof WhitePawn) &&
               (passed instanceof BlackPawn || passed instanceof WhitePawn) &&
               (_p2Location == _p2PriorLocation + 8 || _p2Location == _p2PriorLocation - 8) &&
               (_p1Location == _p2Location + 7 || _p1Location == _p2Location + 9 ||
                _p1Location == _p2Location - 7 || _p1Location == _p2Location - 9) &&
               (_p1PriorLocation == _p2PriorLocation + 16 || 
                _p1PriorLocation == _p2PriorLocation - 16)){

                // remove pawn captured in passing
                JPanel capturedPanel = (JPanel) list.pop(_p2PriorLocation);
                list.addComponent(firstSelected, null);
                list.addComponent(capturedPanel, null);
                list.addComponent(secondSelected, firstPiece);

                // move labels to their appropriate places
                stripLabels(capturedPanel);}
                
            // standard capture
            else{
                list.replaceComponent(firstSelected, secondSelected);

                firstSelected.repaint();
                firstSelected.revalidate();
                secondSelected.repaint();
                secondSelected.revalidate();}

            stripLabels(firstSelected);
            stripLabels(secondSelected);
            pin(firstPiece, secondSelected, 0, 0, "BorderLayout", true);

            firstSelected.repaint();
            firstSelected.revalidate();
            secondSelected.repaint();
            secondSelected.revalidate();}
        
        // special case- move was a castle
        else if(sameColor(firstPiece, secondPiece) && firstPiece != secondPiece){
            // set the new location for the captured piece
            if(firstPiece instanceof BlackRook || firstPiece instanceof WhiteRook){
                // check whether it is the rook on the left or right side that the king castles w/
                // rook stands to the left of its king
                if(findColumn(_p1Location) == 0){
                    _p1Location += 3;
                    _p2Location -= 2;}

                // rook stands to the right of its king
                else{
                    _p1Location -= 2;
                    _p2Location += 2;} }

            // king is selected first 
            else{
                if(findColumn(_p2Location) == 0){
                    _p1Location -= 2; 
                    _p2Location += 3;}

                else{
                    _p1Location += 2;
                    _p2Location -= 2;} }

            // move pieces to their locations
            JPanel firstPanel = (JPanel) list.pop(_p1Location);
            JPanel secondPanel = (JPanel) list.pop(_p2Location);
            
            list.addComponent(firstSelected, null);
            list.addComponent(secondSelected, null);
            list.addComponent(firstPanel, firstPiece);
            list.addComponent(secondPanel, secondPiece);

            pin(firstPiece, firstPanel, 0, 0, "BorderLayout", true);  
            pin(secondPiece, secondPanel, 0, 0, "BorderLayout", true);

            firstPanel.repaint();
            firstPanel.revalidate();
            secondPanel.repaint();
            secondPanel.revalidate();

            stripLabels(firstSelected);
            stripLabels(secondSelected);}

        // if any moved pieces were Kings or Rooks, indicate they have been moved
        if(firstPiece != secondPiece){
            if(firstPiece instanceof BlackKing){
                BlackKing bKing = (BlackKing) firstPiece;
                bKing.beenMoved();}

            if(firstPiece instanceof WhiteKing){
                WhiteKing wKing = (WhiteKing) firstPiece;
                wKing.beenMoved();}

            if(firstPiece instanceof BlackRook){
                BlackRook bRook = (BlackRook) firstPiece;
                bRook.beenMoved();}

            if(firstPiece instanceof WhiteRook){
                WhiteRook wRook = (WhiteRook) firstPiece;
                wRook.beenMoved();}

            if(secondPiece instanceof BlackKing){
                BlackKing bKing = (BlackKing) secondPiece;
                bKing.beenMoved();}

            if(secondPiece instanceof WhiteKing){
                WhiteKing wKing = (WhiteKing) secondPiece;
                wKing.beenMoved();}
    
            if(secondPiece instanceof BlackRook){
                BlackRook bRook = (BlackRook) secondPiece;
                bRook.beenMoved();}

            if(secondPiece instanceof WhiteRook){
                WhiteRook wRook = (WhiteRook) secondPiece;
                wRook.beenMoved();} }
    }

    /**
    Replaces any pawns at the ends of the board with queens of the same color
    */
    private void pawnReplacement(int position){
        // if pawn reaches the end of the board- make it a queen
        if(selectedPiece instanceof BlackPawn && findRow(position) == 8){
            list.addComponent(selectedPanel2, new BlackQueen());
            pin(new BlackQueen(), selectedPanel2, 0, 0, "BorderLayout", true);}

        // if pawn reaches the end of the board- make it a queen
        if(selectedPiece instanceof WhitePawn && findRow(position) == 1){
            list.addComponent(selectedPanel2, new WhiteQueen());
            pin(new WhiteQueen(), selectedPanel2, 0, 0, "BorderLayout", true);}
    }

    /**
    Method that identifies the location of MouseEvents and returns the JPanel at those cordinates
    */
    private JPanel getChessSquare(MouseEvent e){
        JPanel panel = (JPanel) e.getComponent().getComponentAt(e.getPoint());

        // check that the panel acquired is a chess square
        if(isBoardMember(panel))
            return panel;

        return null;
    }

    /**
    Method that checks if a JPanel is a member of the JPanels on the "board"
    */
    private boolean isBoardMember(JPanel panel){
        // get list of all components within the board
        Component[] pComponents = _board.getComponents();
        for(Component c : pComponents){
            if(c instanceof JPanel)
                // check if JPanel is equal to argument
                if(c.equals(panel))
                    return true;}

        return false;
    }

    /**
    Method that returns the JLabel representing a ChessPiece from its Chess Square
    */
    private JLabel getChessLabel(JPanel chessPanel){
        JLabel pressedLabel = null;
        Component[] pComponents = chessPanel.getComponents();
        for(Component a : pComponents){
            if(a instanceof JLabel)
                pressedLabel = (JLabel) a;}

        return pressedLabel;
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
    Method that returns the horizontal "level" of the chessboard the number is at
    */
    private int findRow(int myLocation){
        return (myLocation / 8) + 1;
    }

    /**
    Method that returns the vertical column the piece is currently occupying
    */
    private int findColumn(int myLocation){
        while(myLocation > 7)
            myLocation -= 8;

        return myLocation;
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
                myMoves = piece.possibleMoves(location, chessboard, true, _p1PriorLocation, 
                                              _p2PriorLocation);
                for(int count = 0; count < myMoves.getSize(); ++count){
                    // is one of the opponents move on the piece specified in the parameters
                    if((int) myMoves.pop(count) == myLocation)
                        return true;} } }

        return false;
    }
}
