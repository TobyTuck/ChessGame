import javax.swing.*;
import java.awt.*; // used for Dimension, GraphicsEnvironment, GraphicsDevice
import java.awt.image.BufferedImage;
import java.awt.event.*; // used for MouseAdapter and MouseEvent
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BoardMouseAdapter extends MouseAdapter {
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
    private JPanel _nePanel;
    private JPanel _nwPanel;
    private JPanel _sePanel;
    private JPanel _swPanel;

    private List myMoves, capturedWPieces, capturedBPieces;

    private ChessPiece priorMove;

    private boolean dragged;
    private JPanel _board;
    private JLabel pressedLabel;
    private int offsetX;
    private int offsetY;

    // location of the currently selected chess pieces
    private int _p1Location;
    private int _p2Location;

    // location of the previous selected chess pieces
    private int _p1PriorLocation;
    private int _p2PriorLocation;

    private boolean optionsInterrupter;
    private boolean _isClassic; // determines what images are used

    public BoardMouseAdapter(List chessboard, int bH, int sW, JLayeredPane jlay, JPanel capWhite,
            JPanel capBlack1, JPanel capBlack2, JPanel board, JPanel ne, JPanel nw, JPanel se,
            JPanel sw) {
        list = chessboard;
        boardHeight = bH;
        screenWidth = sW;
        layeredPane = jlay;
        capturedWhite = capWhite;
        capturedBlack1 = capBlack1;
        capturedBlack2 = capBlack2;
        _board = board;
        _nePanel = ne;
        _nwPanel = nw;
        _sePanel = se;
        _swPanel = sw;

        _p1Location = 0;
        _p2Location = 0;
        _p1PriorLocation = 0;
        _p2PriorLocation = 0;
        _isClassic = true;

        // first move is made by white side
        priorMove = new BlackPawn();

        dragged = false;
        optionsInterrupter = false;
        selectedPiece = null;

        capturedWPieces = new List(5);
        capturedBPieces = new List(5);
    }

    @Override
    /*
     * Used in conjunction with the mouseDragged() method
     * Separate from the mouseReleased() method
     */
    public void mousePressed(MouseEvent e) {
        // get the pressed label
        pressedLabel = getChessLabel(getChessSquare(e));

        offsetX = e.getX();
        offsetY = e.getY();

        dragged = false;
    }

    @Override
    /*
     * In dragging the chesspiece, the user will not be able to see the potential
     * moves of
     * His selected piece
     */
    public void mouseDragged(MouseEvent e) {

        int newX = e.getXOnScreen() - offsetX;
        int newY = e.getYOnScreen() - offsetY;

        // first time component is dragged
        if (!dragged) {
            JPanel clickedPanel = getChessSquare(e);
            ChessPiece piece = (ChessPiece) list.getComponent(clickedPanel);

            // ensure that the choice is a valid one
            if (movablePiece(piece, priorMove) && !optionsInterrupter) {
                // set fields for alternate method calls
                selectedPanel1 = clickedPanel;
                selectedPiece = piece;

                // get the location of the piece
                for (int index = 0; index < list.getSize(); ++index) {
                    if (list.pop(index) == selectedPanel1)
                        _p1Location = index;
                }

                // get label from initial press
                pressedLabel.setLocation(newX, newY);

                // add label to top layer of the JLayeredPane
                layeredPane.add(pressedLabel, JLayeredPane.DRAG_LAYER);

                int location = 0;
                for (int index = 0; index < list.getSize(); ++index) {
                    if (list.pop(index) == clickedPanel)
                        location = index;
                }
                myMoves = selectedPiece.possibleMoves(location, list, true, _p1PriorLocation,
                        _p2PriorLocation);

                layeredPane.revalidate();
                layeredPane.repaint();

                dragged = true;
            }
        }

        // continuation of drag
        else {
            // update the position of the label
            pressedLabel.setLocation(newX, newY);
            // pressedLabel.setBackground(Color.red);

            layeredPane.revalidate();
            layeredPane.repaint();
        }
    }

    @Override
    /*
     * If the user presses a square, then drags and releases, no piece options will
     * be given
     * However, if the user releases the mouse without dragging, piece options to
     * move will be given
     */
    public void mouseReleased(MouseEvent e) {
        // find the chess square that has just been selected
        JPanel clickedPanel = getChessSquare(e);

        // get the location of this chess square
        int location = 0;
        for (int index = 0; index < list.getSize(); ++index) {
            if (list.pop(index) == clickedPanel)
                location = index;
        }

        // user dragged the selected piece before release
        if (dragged) {
            selectedPanel2 = clickedPanel;
            ChessPiece selectedPiece2 = (ChessPiece) list.getComponent(selectedPanel2);

            // move must be a valid one
            if (validMove(location, selectedPiece, selectedPiece2)) {
                _p2Location = location;

                // remove captured piece from its square, move to designated section for
                // captures
                removeCapturedPiece(selectedPanel1, selectedPanel2, selectedPiece, selectedPiece2);

                // add the chesspiece to its panel
                move(selectedPanel1, selectedPanel2, selectedPiece, selectedPiece2);

                // if pawn reaches the end of the board- make it a queen
                pawnReplacement(location);

                // allow color to move again only if he didn't move the piece selected
                if (selectedPiece != selectedPiece2) {
                    priorMove = selectedPiece;
                    _p1PriorLocation = _p1Location;
                    _p2PriorLocation = _p2Location;
                }

                // Reset all the the fields
                selectedPanel1 = null;
                selectedPanel2 = null;
                selectedPiece = null;
            }

            // move was not valid
            else {
                // visually add piece to its prior location
                pin(selectedPiece, selectedPanel1, 0, 0, "BorderLayout", true);
            }

            // remove label from the drag layer
            layeredPane.remove(pressedLabel);
            layeredPane.revalidate();
            layeredPane.repaint();
        }

        // user did not drag the selected piece before release- give options
        else {
            // Determine if a piece has been selected for movement yet or not
            // First click to chose piece to move, second to indicate sqaure to move to
            if (selectedPanel1 != null) {
                selectedPanel2 = clickedPanel;
                ChessPiece selectedPiece2 = (ChessPiece) list.getComponent(selectedPanel2);

                // move must be deemed valid
                if (validMove(location, selectedPiece, selectedPiece2)) {

                    _p2Location = location;

                    Component[] jcomponents = selectedPanel1.getComponents();

                    // find JLabel "pinned" to first JPanel selected
                    if (!sameColor(selectedPiece, selectedPiece2)) {
                        for (Component c : jcomponents) {
                            if (c instanceof JLabel) {
                                // remove label from its jpanel
                                selectedPanel1.remove(c);

                                selectedPanel1.revalidate();
                                selectedPanel1.repaint();
                            }
                        }
                    }

                    // remove captured piece from its square, move to designated section for
                    // captures
                    removeCapturedPiece(selectedPanel1, selectedPanel2, selectedPiece,
                            selectedPiece2);

                    // add the chesspiece of the first selected panel to the next
                    move(selectedPanel1, selectedPanel2, selectedPiece, selectedPiece2);

                    // remove outline from suggested move panels
                    for (int index = 0; index < list.getSize(); ++index) {
                        // delete
                        try {
                            removeOutline((JPanel) list.pop(index), (ChessPiece) list.getComponent(index),
                                    selectedPiece);
                        } catch (NullPointerException npe) {
                            System.out.println("Error at position " + index);
                            npe.printStackTrace();
                        }
                    }

                    // if pawn reaches the end of the board- make it a queen
                    pawnReplacement(location);

                    // allow color to move again only if he didn't move the piece selected
                    if (selectedPiece != selectedPiece2)
                        priorMove = selectedPiece;

                    if (selectedPiece != selectedPiece2) {
                        _p1PriorLocation = _p1Location;
                        _p2PriorLocation = _p2Location;
                    }

                    // Reset all the the fields
                    selectedPanel1 = null;
                    selectedPanel2 = null;
                    selectedPiece = null;
                    optionsInterrupter = false;
                }
            }

            // Other wise, find which component was clicked
            else {
                _p1Location = location;

                // find the selected chesspiece
                ChessPiece piece = (ChessPiece) list.getComponent(clickedPanel);

                if (movablePiece(piece, priorMove)) {

                    // set fields for alternate method calls
                    selectedPanel1 = clickedPanel;
                    selectedPiece = piece;

                    // provide suggestions
                    getMoves();
                    selectPanel(selectedPanel1, selectedPiece);

                    // throw flag so a dragged option is not able to interrupt
                    optionsInterrupter = true;
                }
            }
        }
    }

    /**
     * Verifies a piece selection is a valid one
     * In order for a piece to be valid for movement, it must:
     * 1) square selected must contain a chesspiece
     * 2) chesspiece must be the correct color
     */
    private boolean movablePiece(ChessPiece move, ChessPiece priorMove) {
        if (move != null && isOpponent(move, priorMove))
            return true;

        return false;
    }

    /**
     * Verifies a piece movement is a valid one
     * Chess moves are generated by the respective class for each chesspiece, and
     * are verified here
     */
    private boolean validMove(int position, ChessPiece firstSelected, ChessPiece secondSelected) {
        boolean legal = false;
        // compare requested move with legal moves possible given the chesspiece
        for (int index = 0; index < myMoves.getSize(); ++index) {
            if ((int) myMoves.pop(index) == position)
                legal = true;
        }

        // selection of the same piece is also allowed
        if (legal || firstSelected == secondSelected)
            return true;

        return false;
    }

    /**
     * Removes captured pieces from the board and adds them to a panel displaying
     * the captured pieces
     * Pieces of the same color are not considered "captured"
     */
    private void removeCapturedPiece(JPanel firstSelected, JPanel secondPanel,
            ChessPiece movedPiece, ChessPiece secondPiece) {

        if (movedPiece == secondPiece)
            pin(secondPiece, secondPanel, 0, 0, "BorderLayout", true);

        // move resulted in a capture
        if (!sameColor(movedPiece, secondPiece)) {
            JPanel capturedPanel = null;
            ChessPiece capturedPiece = null;

            ChessPiece standIn = (ChessPiece) list.getComponent(_p2PriorLocation);
            // special case- en passant capture
            if ((movedPiece instanceof BlackPawn || movedPiece instanceof WhitePawn) &&
                    (standIn instanceof BlackPawn || standIn instanceof WhitePawn) &&
                    (_p2Location == _p2PriorLocation + 8 || _p2Location == _p2PriorLocation - 8) &&
                    (_p1Location == _p2Location + 7 || _p1Location == _p2Location + 9 ||
                            _p1Location == _p2Location - 7 || _p1Location == _p2Location - 9)
                    &&
                    (_p1PriorLocation == _p2PriorLocation + 16 ||
                            _p1PriorLocation == _p2PriorLocation - 16)) {

                // find the piece that was captured in passing
                capturedPiece = standIn;
                capturedPanel = (JPanel) list.pop(_p2PriorLocation);
            }

            // standard capture
            else {
                capturedPiece = secondPiece;
                capturedPanel = secondPanel;
            }

            // add the captured piece to the corresponding location
            // captured piece is black
            int capturedWidth = 0,
                    capturedHeight = 0;
            if (isBlack(capturedPiece) && capturedPanel != null && firstSelected != capturedPanel) {
                // add the piece to list of captured pieces
                capturedBPieces.push(capturedPiece, null);

                capturedWidth = (int) (boardHeight / 40.0);
                capturedHeight = (int) (((double) capturedPiece.getHeight())
                        * ((double) capturedWidth / (double) capturedPiece.getWidth()));

                // get number of captured pieces held by panel
                Component[] pComponents = capturedBlack1.getComponents();
                // check if size including one more label can fit
                int numComponents = 1;
                for (Component c : pComponents) {
                    if (c instanceof JLabel)
                        ++numComponents;
                }

                // check if the first panel can hold anymore captured pieces
                if ((numComponents * (capturedWidth + 5) + 5) < (0.4 * (screenWidth - boardHeight)))
                    pin(capturedPiece, capturedBlack1, capturedWidth, capturedHeight,
                            "FlowLayout", false);

                // use 2nd (upper) panel
                else {
                    pin(capturedPiece, capturedBlack2, capturedWidth, capturedHeight,
                            "FlowLayout", false);
                }
            }

            // captured piece is white
            else if (isWhite(capturedPiece) && capturedPiece != null &&
                    firstSelected != capturedPanel) {
                // add the piece to the list of captured pieces
                capturedWPieces.push(capturedPiece, null);

                // determine height and width
                int offset = 0;
                do {
                    capturedWidth = (int) ((boardHeight / 8.0) / 5) - offset;
                    capturedHeight = (int) (((double) capturedPiece.getHeight())
                            * ((double) capturedWidth / (double) capturedPiece.getWidth()));
                    ++offset;

                    // ensure that the label is small enough to fit on the captured designated panel
                } while (capturedHeight > (((double) boardHeight / 8.0) * 0.5) + 10);

                pin(capturedPiece, capturedWhite, capturedWidth, capturedHeight, "FlowLayout",
                        false);
            }
        }
    }

    /**
     * Method that
     */

    /**
     * Method that removes all JLabel Components from a chess square (JPanel)
     */
    private void stripLabels(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component c : components) {
            // locate and remove the label
            if (c instanceof JLabel)
                panel.remove(c);
        }

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Method that removes all JPanels Components from a chess square (JPanel)
     */
    private void stripPanels(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component c : components) {
            // locate and remove the label
            if (c instanceof JPanel)
                panel.remove(c);
        }

        panel.revalidate();
        panel.repaint();
    }

    /**
     * Method that moves the selected piece to its location
     */
    private void move(JPanel firstSelected, JPanel secondSelected, ChessPiece firstPiece, ChessPiece secondPiece) {
        // move was a capture, or a "de-select"
        if (!sameColor(firstPiece, secondPiece) || firstPiece == secondPiece) {
            ChessPiece passed = (ChessPiece) list.getComponent(_p2PriorLocation);

            // special case- en passant capture
            if ((firstPiece instanceof BlackPawn || firstPiece instanceof WhitePawn) &&
                    (passed instanceof BlackPawn || passed instanceof WhitePawn) &&
                    (_p2Location == _p2PriorLocation + 8 || _p2Location == _p2PriorLocation - 8) &&
                    (_p1Location == _p2Location + 7 || _p1Location == _p2Location + 9 ||
                            _p1Location == _p2Location - 7 || _p1Location == _p2Location - 9)
                    &&
                    (_p1PriorLocation == _p2PriorLocation + 16 ||
                            _p1PriorLocation == _p2PriorLocation - 16)) {

                // remove pawn captured in passing
                JPanel capturedPanel = (JPanel) list.pop(_p2PriorLocation);
                list.addComponent(firstSelected, null);
                list.addComponent(capturedPanel, null);
                list.addComponent(secondSelected, firstPiece);

                // move labels to their appropriate places
                stripLabels(capturedPanel);
            }

            // standard capture
            else {
                list.replaceComponent(firstSelected, secondSelected);

                firstSelected.repaint();
                firstSelected.revalidate();
                secondSelected.repaint();
                secondSelected.revalidate();
            }

            stripLabels(firstSelected);
            stripLabels(secondSelected);
            pin(firstPiece, secondSelected, 0, 0, "BorderLayout", true);

            firstSelected.repaint();
            firstSelected.revalidate();
            secondSelected.repaint();
            secondSelected.revalidate();
        }

        // special case- move was a castle
        else if (sameColor(firstPiece, secondPiece) && firstPiece != secondPiece) {
            // set the new location for the captured piece
            if (firstPiece instanceof BlackRook || firstPiece instanceof WhiteRook) {
                // check whether it is the rook on the left or right side that the king castles
                // w/
                // rook stands to the left of its king
                if (findColumn(_p1Location) == 0) {
                    _p1Location += 3;
                    _p2Location -= 2;
                }

                // rook stands to the right of its king
                else {
                    _p1Location -= 2;
                    _p2Location += 2;
                }
            }

            // king is selected first
            else {
                if (findColumn(_p2Location) == 0) {
                    _p1Location -= 2;
                    _p2Location += 3;
                }

                else {
                    _p1Location += 2;
                    _p2Location -= 2;
                }
            }

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
            stripLabels(secondSelected);
        }

        // if any moved pieces were Kings or Rooks, indicate they have been moved
        if (firstPiece != secondPiece) {
            if (firstPiece instanceof BlackKing) {
                BlackKing bKing = (BlackKing) firstPiece;
                bKing.beenMoved();
            }

            if (firstPiece instanceof WhiteKing) {
                WhiteKing wKing = (WhiteKing) firstPiece;
                wKing.beenMoved();
            }

            if (firstPiece instanceof BlackRook) {
                BlackRook bRook = (BlackRook) firstPiece;
                bRook.beenMoved();
            }

            if (firstPiece instanceof WhiteRook) {
                WhiteRook wRook = (WhiteRook) firstPiece;
                wRook.beenMoved();
            }

            if (secondPiece instanceof BlackKing) {
                BlackKing bKing = (BlackKing) secondPiece;
                bKing.beenMoved();
            }

            if (secondPiece instanceof WhiteKing) {
                WhiteKing wKing = (WhiteKing) secondPiece;
                wKing.beenMoved();
            }

            if (secondPiece instanceof BlackRook) {
                BlackRook bRook = (BlackRook) secondPiece;
                bRook.beenMoved();
            }

            if (secondPiece instanceof WhiteRook) {
                WhiteRook wRook = (WhiteRook) secondPiece;
                wRook.beenMoved();
            }
        }
    }

    /**
     * Replaces any pawns at the ends of the board with queens of the same color
     */
    private void pawnReplacement(int position) {
        // if pawn reaches the end of the board- make it a queen
        if (selectedPiece instanceof BlackPawn && findRow(position) == 8) {
            BlackQueen queenPiece = new BlackQueen();
            if (_isClassic) {
                try {
                    queenPiece.setImage(ImageIO.read(new File("BlackQueen.png")));
                } catch (IOException exception) {
                    System.out.println("Error Black Queen image file");
                }
            }

            // board is using modern pieces
            else {
                try {
                    queenPiece.setImage(ImageIO.read(new File("BQueen.png")));
                } catch (IOException exception) {
                    System.out.println("Error Black Queen image file");
                }
            }
            list.addComponent(selectedPanel2, queenPiece);
            pin(queenPiece, selectedPanel2, 0, 0, "BorderLayout", true);
        }

        // if pawn reaches the end of the board- make it a queen
        if (selectedPiece instanceof WhitePawn && findRow(position) == 1) {
            WhiteQueen queenPiece = new WhiteQueen();
            if (_isClassic) {
                try {
                    queenPiece.setImage(ImageIO.read(new File("WhiteQueen.png")));
                } catch (IOException exception) {
                    System.out.println("Error White Queen image file");
                }
            }

            // board is using modern pieces
            else {
                try {
                    queenPiece.setImage(ImageIO.read(new File("WQueen.png")));
                } catch (IOException exception) {
                    System.out.println("Error White Queen image file");
                }
            }
            list.addComponent(selectedPanel2, queenPiece);
            pin(queenPiece, selectedPanel2, 0, 0, "BorderLayout", true);
        }
    }

    /**
     * Method that identifies the location of MouseEvents and returns the JPanel at
     * those cordinates
     */
    private JPanel getChessSquare(MouseEvent e) {
        JPanel panel = (JPanel) e.getComponent().getComponentAt(e.getPoint());

        // check that the panel acquired is a chess square
        if (isBoardMember(panel))
            return panel;

        return null;
    }

    /**
     * Method that checks if a JPanel is a member of the JPanels on the "board"
     */
    private boolean isBoardMember(JPanel panel) {
        JPanel bPanel = null;

        // get list of all components within the board
        Component[] bComponents = _board.getComponents();

        // look through all containers in the surface of the board
        for (Component b : bComponents) {
            if (b instanceof JPanel)
                bPanel = (JPanel) b;
            Component[] cComponents = bPanel.getComponents();

            // look through all jpanels of each container
            for (Component c : cComponents) {
                if (c instanceof JPanel)
                    // check if JPanel is equal to argument
                    if (c.equals(panel))
                        return true;
            }
        }

        return false;
    }

    /**
     * Method that returns the JLabel representing a ChessPiece from its Chess
     * Square
     */
    private JLabel getChessLabel(JPanel chessPanel) {
        JLabel pressedLabel = null;
        Component[] pComponents = chessPanel.getComponents();
        for (Component a : pComponents) {
            if (a instanceof JLabel)
                pressedLabel = (JLabel) a;
        }

        return pressedLabel;
    }

    /**
     * Private helper method that adds a chesspiece image to the center of a JPanel
     * using a JLabel
     * Height and width of the image is specified by the parameters
     * 'dimensionDecider' parameter determines if the label is set to the size of
     * the panel or to the
     * size of the image
     */
    private void pin(ChessPiece piece, JPanel panel, int width, int height, String layoutDecider,
            boolean dimensionDecider) {

        if (layoutDecider.equals("BorderLayout")) {
            panel.setLayout(new BorderLayout());
        }

        // default height and width for each chesspiece
        if (width == 0 && height == 0) {
            if (piece instanceof BlackPawn || piece instanceof WhitePawn) {
                height = (int) ((boardHeight / 8.0) * 0.6);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            else if (piece instanceof BlackRook || piece instanceof WhiteRook) {
                height = (int) ((boardHeight / 8.0) * 0.75);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            else if (piece instanceof BlackKnight || piece instanceof WhiteKnight) {
                height = (int) ((boardHeight / 8.0) * 0.75);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            else if (piece instanceof BlackBishop || piece instanceof WhiteBishop) {
                height = (int) ((boardHeight / 8.0) * 0.8);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            else if (piece instanceof BlackQueen || piece instanceof WhiteQueen) {
                height = (int) ((boardHeight / 8.0) * 0.85);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            // ChessPiece is a king
            else {
                height = (int) ((boardHeight / 8.0) * 0.9);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }
        }

        // rescale image to fit its jpanel
        piece.scaleImage(width, height);

        // create the label and set preferrences
        JLabel label = new JLabel(piece.toImageIcon(), JLabel.CENTER);

        // determine whether or not the label should be set to the size of the label or
        // not
        if (dimensionDecider == true)
            label.setPreferredSize(panel.getPreferredSize());

        else {
            label.setPreferredSize(new Dimension(width, height));
        }

        // use layout manager specified in the parameters
        if (layoutDecider.equals("BorderLayout"))
            panel.add(label, BorderLayout.CENTER);

        else if (layoutDecider.equals("FlowLayout"))
            panel.add(label);
    }

    /**
     * Private helper method that outlines a jpanel chessquare with a color
     * specified in the parameters
     * The width of this outline is specified w/ in the methods parameters
     */
    private void outline(JPanel panel, Color color, int depth) {
        panel.setLayout(new GridBagLayout());

        // get the contents of the current square
        Component[] pComponents = panel.getComponents();
        JLabel myLabel = null;

        for (Component c : pComponents) {
            if (c instanceof JLabel)
                myLabel = (JLabel) c;
        }
        stripLabels(panel);

        Color previousColor = panel.getBackground();
        panel.setBackground(color);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        if (myLabel != null) {
            // Board is made of RoundedPanels
            if (!_isClassic) {
                RoundedPanel front = new RoundedPanel(30);
                front.setLayout(new GridBagLayout());
                front.setBackground(previousColor);

                Dimension pDim = panel.getPreferredSize();
                Dimension fDim = new Dimension(pDim.width - (2 * depth), pDim.height - (2 * depth));
                front.setPreferredSize(fDim);

                front.add(myLabel, gbc);
                panel.add(front, gbc);
            }

            // Board is made of square JPanels
            else {
                JPanel front = new JPanel();
                front.setLayout(new GridBagLayout());
                front.setBackground(previousColor);

                Dimension pDim = panel.getPreferredSize();
                Dimension fDim = new Dimension(pDim.width - (2 * depth), pDim.height - (2 * depth));
                front.setPreferredSize(fDim);

                front.add(myLabel, gbc);
                panel.add(front, gbc);

                // Help the graphics look better- change background color if corner square
                JPanel square1 = (JPanel) list.pop(0);
                JPanel square8 = (JPanel) list.pop(7);
                JPanel square57 = (JPanel) list.pop(56);
                JPanel square64 = (JPanel) list.pop(63);

                if (panel == square1) {
                    _nwPanel.setBackground(color);
                    _nwPanel.revalidate();
                    _nwPanel.repaint();
                }

                else if (panel == square8) {
                    _nePanel.setBackground(color);
                    _nePanel.revalidate();
                    _nePanel.repaint();
                }

                else if (panel == square57) {
                    _swPanel.setBackground(color);
                    _swPanel.revalidate();
                    _swPanel.repaint();
                }

                else if (panel == square64) {
                    _sePanel.setBackground(color);
                    _sePanel.revalidate();
                    _sePanel.repaint();
                }
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    private void addCircle(JPanel panel, int x, Color color) {
        RoundLabel center = new RoundLabel(x);
        center.setPreferredSize(new Dimension(x, x));
        center.setBackground(color);
        center.setOpaque(true);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(center, gbc);

        panel.revalidate();
        panel.repaint();
    }

    private void removeOutline(JPanel currentPanel, ChessPiece currentPiece, ChessPiece selectedPiece) {
        JLabel label = null;
        JLabel pieceIcon = null;

        JPanel panel = null;
        Component[] pComponents = currentPanel.getComponents();
        for (Component c : pComponents) {
            if (c instanceof JLabel) {
                label = (JLabel) c;
                if (label.getIcon() == null)
                    currentPanel.remove(c);
                else
                    pieceIcon = label;

                currentPanel.revalidate();
                currentPanel.repaint();
            }
        }

        // remove the outline of any chesspiece
        pComponents = currentPanel.getComponents();
        for (Component c : pComponents) {
            if (c instanceof JPanel) {
                panel = (JPanel) c;
                // if (currentPiece != null && sameColor(currentPiece, selectedPiece)) {
                if (currentPiece != null) {
                    if (pieceIcon != null) {
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        gbc.weightx = 1.0;
                        gbc.weighty = 1.0;
                        gbc.anchor = GridBagConstraints.CENTER;
                        currentPanel.setLayout(new GridBagLayout());

                        currentPanel.add(pieceIcon, gbc);
                        stripPanels(currentPanel);
                    }
                } else {
                    stripLabels(currentPanel);
                    stripPanels(currentPanel);
                }

                currentPanel.setBackground(panel.getBackground());

                currentPanel.revalidate();
                currentPanel.repaint();
            }
        }

        if (_isClassic) {
            Color tan = new Color(210, 180, 140);
            Color brown = new Color(139, 69, 19);
            _nePanel.setBackground(brown);
            _nwPanel.setBackground(tan);
            _sePanel.setBackground(tan);
            _swPanel.setBackground(brown);
        }
    }

    /**
     * Method that takes the possible moves and places them on the board
     */
    private void getMoves() {
        // ensure panel is selected before getting the possible moves
        if (selectedPiece != null) {
            int possibleMove;

            ChessPiece past = (ChessPiece) list.getComponent(_p2PriorLocation);
            JPanel currentPanel;

            Color option = new Color(127, 255, 0);
            Color captureKing = new Color(128, 0, 128);
            myMoves = selectedPiece.possibleMoves(_p1Location, list, true, _p1PriorLocation,
                    _p2PriorLocation);

            // get the size of the circle or outline to be used
            final int diameter = (int) ((1.0 / 2) * (selectedPanel1.getPreferredSize().height));
            final int width = (int) ((1.0 / 20) * (selectedPanel1.getPreferredSize().height));

            for (int index = 0; index < myMoves.getSize(); ++index) {
                // get the components corresponding to the current square
                possibleMove = (int) myMoves.pop(index);
                currentPanel = (JPanel) list.pop(possibleMove);

                // if move is opponent, highlight it for user
                if (isOpponent((ChessPiece) list.getComponent(_p1Location),
                        (ChessPiece) list.getComponent(possibleMove))) {
                    if (!isKing((ChessPiece) list.getComponent(possibleMove)))
                        outline(currentPanel, Color.red, width);
                    else
                        outline(currentPanel, captureKing, width);
                }

                // castle
                else if (sameColor((ChessPiece) list.getComponent(_p1Location),
                        (ChessPiece) list.getComponent(possibleMove)))
                    outline(currentPanel, option, width);

                // en passant move to capture opponent
                else if ((selectedPiece instanceof BlackPawn ||
                        selectedPiece instanceof WhitePawn) &&
                        (past instanceof BlackPawn || past instanceof WhitePawn) &&
                        (possibleMove == _p2PriorLocation + 8 ||
                                possibleMove == _p2PriorLocation - 8)
                        &&
                        (_p1Location == possibleMove + 7 || _p1Location == possibleMove + 9 ||
                                _p1Location == possibleMove - 7 || _p1Location == possibleMove - 9)
                        &&
                        (_p1PriorLocation == _p2PriorLocation + 16 ||
                                _p1PriorLocation == _p2PriorLocation - 16))
                    addCircle(currentPanel, diameter, Color.red);

                // standard move
                else {
                    addCircle(currentPanel, diameter, option);
                }
            }
        }
    }

    /**
     * Method that outlines the selected panel
     */
    private void selectPanel(JPanel panel, ChessPiece piece) {
        // ensure that a panel is physically selected before outlining it
        if (selectedPiece != null) {
            final int width = (int) ((1.0 / 20) * (selectedPanel1.getPreferredSize().height));

            if (isWhite(piece))
                outline(panel, Color.white, width);

            else
                outline(panel, Color.black, width);
        }
    }

    /**
     * Chess rules that apply to piece movements are the following:
     * 1.In order to move a chesspiece, the piece moved must first be clicked, then
     * the square to which the user wants to move
     * 2.A chesspiece may not be moved to any square in which a piece of the same
     * color resides
     * 3.Each chesspiece has a certain pattern they are able to move in, as defined
     * in their respective classes
     * 4.If a chesspiece is moved onto a square occupied by a chesspiece of the
     * opposite color, the latter is deemed 'captured' and removed from the board,
     * while the former now occupies that square
     * 5.If a king is put in check, he is required to remove himself from this
     * situation if possible
     * 6.If a chesspiece is in a position that blocks his King from being in check,
     * that piece is unable to be moved by his player
     */
    /*
     * Method that checks to see if two chesspieces are of the same color
     */
    private boolean sameColor(ChessPiece piece1, ChessPiece piece2) {
        // special cases- one of the pieces is null, while the other is not; or both are
        // null
        if ((piece1 == null && piece2 != null) || (piece1 != null && piece2 == null) ||
                (piece1 == null && piece2 == null))
            return false;

        // 2 possibilities- piece1 belongs to black color, or white
        if (isBlack(piece1)) {
            // piece can be black
            if (isBlack(piece2))
                return true;

            // or white
            else {
                return false;
            }
        }

        // piece is white
        else {
            // piece can be white
            if (isWhite(piece2))
                return true;

            // or white
            else {
                return false;
            }
        }
    }

    private boolean isBlack(ChessPiece piece) {
        if (piece instanceof BlackPawn || piece instanceof BlackRook || piece instanceof BlackKnight ||
                piece instanceof BlackBishop || piece instanceof BlackQueen || piece instanceof BlackKing)
            return true;
        return false;
    }

    private boolean isWhite(ChessPiece piece) {
        if (piece instanceof WhitePawn || piece instanceof WhiteRook || piece instanceof WhiteKnight ||
                piece instanceof WhiteBishop || piece instanceof WhiteQueen || piece instanceof WhiteKing)
            return true;
        return false;
    }

    /**
     * Method that checks if an oppoent exists in a square
     */
    protected boolean isOpponent(ChessPiece piece1, ChessPiece piece2) {
        // special case
        if (piece1 == null || piece2 == null)
            return false;

        // 2 options- piece1 is black
        else if (isBlack(piece1)) {
            if (isBlack(piece2))
                return false;
            else {
                return true;
            }
        }

        // or white
        else {
            if (isWhite(piece2))
                return false;
            else {
                return true;
            }
        }
    }

    /**
     * Method that returns the horizontal "level" of the chessboard the number is at
     */
    private int findRow(int myLocation) {
        return (myLocation / 8) + 1;
    }

    /**
     * Method that returns the vertical column the piece is currently occupying
     */
    private int findColumn(int myLocation) {
        while (myLocation > 7)
            myLocation -= 8;

        return myLocation;
    }

    /**
     * Method that checks if a ChessPiece is a king
     */
    private boolean isKing(ChessPiece piece) {
        if (piece instanceof BlackKing || piece instanceof WhiteKing)
            return true;

        return false;
    }

    /**
     * Method that updates the chessboard so that is "modern"
     * Entails:
     * 1. Rounded JPanels that represent the chessboard spaces
     * 2. Modern chesspiece images
     */
    public void makeModern() {

        _isClassic = false;

        // Step 1: Make the JPanels of the chessboard and its background rounded
        // copy the contents of the first list into a new one, with rounded jpanels
        List newList = new List(list.getSize());

        RoundedPanel square0 = new RoundedPanel(30);
        newList.push(square0, null);
        RoundedPanel square1 = new RoundedPanel(30);
        newList.push(square1, null);
        RoundedPanel square2 = new RoundedPanel(30);
        newList.push(square2, null);
        RoundedPanel square3 = new RoundedPanel(30);
        newList.push(square3, null);
        RoundedPanel square4 = new RoundedPanel(30);
        newList.push(square4, null);
        RoundedPanel square5 = new RoundedPanel(30);
        newList.push(square5, null);
        RoundedPanel square6 = new RoundedPanel(30);
        newList.push(square6, null);
        RoundedPanel square7 = new RoundedPanel(30);
        newList.push(square7, null);

        RoundedPanel square8 = new RoundedPanel(30);
        newList.push(square8, null);
        RoundedPanel square9 = new RoundedPanel(30);
        newList.push(square9, null);
        RoundedPanel square10 = new RoundedPanel(30);
        newList.push(square10, null);
        RoundedPanel square11 = new RoundedPanel(30);
        newList.push(square11, null);
        RoundedPanel square12 = new RoundedPanel(30);
        newList.push(square12, null);
        RoundedPanel square13 = new RoundedPanel(30);
        newList.push(square13, null);
        RoundedPanel square14 = new RoundedPanel(30);
        newList.push(square14, null);
        RoundedPanel square15 = new RoundedPanel(30);
        newList.push(square15, null);

        RoundedPanel square16 = new RoundedPanel(30);
        newList.push(square16, null);
        RoundedPanel square17 = new RoundedPanel(30);
        newList.push(square17, null);
        RoundedPanel square18 = new RoundedPanel(30);
        newList.push(square18, null);
        RoundedPanel square19 = new RoundedPanel(30);
        newList.push(square19, null);
        RoundedPanel square20 = new RoundedPanel(30);
        newList.push(square20, null);
        RoundedPanel square21 = new RoundedPanel(30);
        newList.push(square21, null);
        RoundedPanel square22 = new RoundedPanel(30);
        newList.push(square22, null);
        RoundedPanel square23 = new RoundedPanel(30);
        newList.push(square23, null);

        RoundedPanel square24 = new RoundedPanel(30);
        newList.push(square24, null);
        RoundedPanel square25 = new RoundedPanel(30);
        newList.push(square25, null);
        RoundedPanel square26 = new RoundedPanel(30);
        newList.push(square26, null);
        RoundedPanel square27 = new RoundedPanel(30);
        newList.push(square27, null);
        RoundedPanel square28 = new RoundedPanel(30);
        newList.push(square28, null);
        RoundedPanel square29 = new RoundedPanel(30);
        newList.push(square29, null);
        RoundedPanel square30 = new RoundedPanel(30);
        newList.push(square30, null);
        RoundedPanel square31 = new RoundedPanel(30);
        newList.push(square31, null);

        RoundedPanel square32 = new RoundedPanel(30);
        newList.push(square32, null);
        RoundedPanel square33 = new RoundedPanel(30);
        newList.push(square33, null);
        RoundedPanel square34 = new RoundedPanel(30);
        newList.push(square34, null);
        RoundedPanel square35 = new RoundedPanel(30);
        newList.push(square35, null);
        RoundedPanel square36 = new RoundedPanel(30);
        newList.push(square36, null);
        RoundedPanel square37 = new RoundedPanel(30);
        newList.push(square37, null);
        RoundedPanel square38 = new RoundedPanel(30);
        newList.push(square38, null);
        RoundedPanel square39 = new RoundedPanel(30);
        newList.push(square39, null);

        RoundedPanel square40 = new RoundedPanel(30);
        newList.push(square40, null);
        RoundedPanel square41 = new RoundedPanel(30);
        newList.push(square41, null);
        RoundedPanel square42 = new RoundedPanel(30);
        newList.push(square42, null);
        RoundedPanel square43 = new RoundedPanel(30);
        newList.push(square43, null);
        RoundedPanel square44 = new RoundedPanel(30);
        newList.push(square44, null);
        RoundedPanel square45 = new RoundedPanel(30);
        newList.push(square45, null);
        RoundedPanel square46 = new RoundedPanel(30);
        newList.push(square46, null);
        RoundedPanel square47 = new RoundedPanel(30);
        newList.push(square47, null);

        RoundedPanel square48 = new RoundedPanel(30);
        newList.push(square48, null);
        RoundedPanel square49 = new RoundedPanel(30);
        newList.push(square49, null);
        RoundedPanel square50 = new RoundedPanel(30);
        newList.push(square50, null);
        RoundedPanel square51 = new RoundedPanel(30);
        newList.push(square51, null);
        RoundedPanel square52 = new RoundedPanel(30);
        newList.push(square52, null);
        RoundedPanel square53 = new RoundedPanel(30);
        newList.push(square53, null);
        RoundedPanel square54 = new RoundedPanel(30);
        newList.push(square54, null);
        RoundedPanel square55 = new RoundedPanel(30);
        newList.push(square55, null);

        RoundedPanel square56 = new RoundedPanel(30);
        newList.push(square56, null);
        RoundedPanel square57 = new RoundedPanel(30);
        newList.push(square57, null);
        RoundedPanel square58 = new RoundedPanel(30);
        newList.push(square58, null);
        RoundedPanel square59 = new RoundedPanel(30);
        newList.push(square59, null);
        RoundedPanel square60 = new RoundedPanel(30);
        newList.push(square60, null);
        RoundedPanel square61 = new RoundedPanel(30);
        newList.push(square61, null);
        RoundedPanel square62 = new RoundedPanel(30);
        newList.push(square62, null);
        RoundedPanel square63 = new RoundedPanel(30);
        newList.push(square63, null);

        // set the properties of the new rounded jpanels
        // Set properties of all the items in the List
        int cellRemainder,
                count = 0;
        RoundedPanel jpanel;

        Color tan = new Color(210, 180, 140);
        Color brown = new Color(139, 69, 19);

        stripPanels(_nePanel);
        stripPanels(_nwPanel);
        stripPanels(_sePanel);
        stripPanels(_swPanel);

        for (int i = 0; i < newList.getSize(); ++i) {

            jpanel = (RoundedPanel) newList.pop(i);

            // set size of the JPanel
            jpanel.setPreferredSize(new Dimension((int) (boardHeight / 8.0),
                    (int) (boardHeight / 8.0)));

            cellRemainder = i % 2;
            if ((int) (count / 8) % 2 == 0) {
                // test if index is even or odd
                // even
                if (cellRemainder == 0)
                    jpanel.setBackground(tan);

                // odd
                else
                    jpanel.setBackground(brown);
            }

            else {
                // test if index is even or odd
                // even
                if (cellRemainder == 0)
                    jpanel.setBackground(brown);

                // odd
                else
                    jpanel.setBackground(tan);
            }

            // set the Layout manager for each jpanel
            jpanel.setLayout(new BorderLayout());

            // add components to respective container
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 8 || i == 9 ||
                    i == 10 || i == 11 || i == 16 || i == 17 || i == 18 ||
                    i == 19 || i == 24 || i == 25 || i == 26 || i == 27)
                _nwPanel.add(jpanel);

            else if (i == 4 || i == 5 || i == 6 || i == 7 || i == 12 ||
                    i == 13 || i == 14 || i == 15 || i == 20 || i == 21 ||
                    i == 22 || i == 23 || i == 28 || i == 29 || i == 30 ||
                    i == 31)
                _nePanel.add(jpanel);

            else if (i == 32 || i == 33 || i == 34 || i == 35 || i == 40 ||
                    i == 41 || i == 42 || i == 43 || i == 48 || i == 49 ||
                    i == 50 || i == 51 || i == 56 || i == 57 || i == 58 ||
                    i == 59)
                _swPanel.add(jpanel);

            else
                _sePanel.add(jpanel);

            ++count;
        }

        // Change the color of the background JPanels
        _sePanel.setBackground(tan);
        _swPanel.setBackground(tan);
        _nePanel.setBackground(tan);
        _nwPanel.setBackground(tan);
        _board.setBackground(tan);

        // copy the locations of the chesspieces of the first into the second
        RoundedPanel currentPanel;
        ChessPiece currentPiece;
        for (int i = 0; i < newList.getSize(); ++i) {
            currentPanel = (RoundedPanel) newList.pop(i);
            currentPiece = (ChessPiece) list.getComponent(i);

            // copy the chesspiece represenations over
            newList.addComponent(currentPanel, currentPiece);
        }

        // Step 2: Update all the chesspiece images
        JPanel pastPanel;
        ChessPiece pastPiece;
        try {
            for (int i = 0; i < newList.getSize(); ++i) {
                currentPanel = (RoundedPanel) newList.pop(i);
                currentPiece = (ChessPiece) newList.getComponent(i);

                pastPanel = (JPanel) list.pop(i);
                pastPiece = (ChessPiece) list.getComponent(i);

                // update the fields
                if (pastPanel == selectedPanel1)
                    selectedPanel1 = currentPanel;

                else if (pastPanel == selectedPanel2)
                    selectedPanel2 = currentPanel;

                else if (pastPiece == selectedPiece)
                    selectedPiece = currentPiece;

                // decide what image file to use
                if (currentPiece instanceof WhitePawn) {
                    BufferedImage wPawnImage = ImageIO.read(new File("WPawn.png"));
                    currentPiece.setImage(wPawnImage);
                }

                else if (currentPiece instanceof WhiteRook) {
                    BufferedImage wRookImage = ImageIO.read(new File("WRook.png"));
                    currentPiece.setImage(wRookImage);
                }

                else if (currentPiece instanceof WhiteKnight) {
                    BufferedImage wKnightImage = ImageIO.read(new File("WKnight.png"));
                    currentPiece.setImage(wKnightImage);
                }

                else if (currentPiece instanceof WhiteBishop) {
                    BufferedImage wBishopImage = ImageIO.read(new File("WBishop.png"));
                    currentPiece.setImage(wBishopImage);
                }

                else if (currentPiece instanceof WhiteQueen) {
                    BufferedImage wQueenImage = ImageIO.read(new File("WQueen.png"));
                    currentPiece.setImage(wQueenImage);
                }

                else if (currentPiece instanceof WhiteKing) {
                    BufferedImage wKingImage = ImageIO.read(new File("WKing.png"));
                    currentPiece.setImage(wKingImage);
                }

                else if (currentPiece instanceof BlackPawn) {
                    BufferedImage bPawnImage = ImageIO.read(new File("BPawn.png"));
                    currentPiece.setImage(bPawnImage);
                }

                else if (currentPiece instanceof BlackRook) {
                    BufferedImage bRookImage = ImageIO.read(new File("BRook.png"));
                    currentPiece.setImage(bRookImage);
                }

                else if (currentPiece instanceof BlackKnight) {
                    BufferedImage bKnightImage = ImageIO.read(new File("BKnight.png"));
                    currentPiece.setImage(bKnightImage);
                }

                else if (currentPiece instanceof BlackBishop) {
                    BufferedImage bBishopImage = ImageIO.read(new File("BBishop.png"));
                    currentPiece.setImage(bBishopImage);
                }

                else if (currentPiece instanceof BlackQueen) {
                    BufferedImage bQueenImage = ImageIO.read(new File("BQueen.png"));
                    currentPiece.setImage(bQueenImage);
                }

                else if (currentPiece instanceof BlackKing) {
                    BufferedImage bKingImage = ImageIO.read(new File("BKing.png"));
                    currentPiece.setImage(bKingImage);
                }

                // pin the new image on its panel
                if (currentPiece != null && currentPiece instanceof ChessPiece) {
                    pin(currentPiece, currentPanel, 0, 0, "BorderLayout", true);
                }

                // Substitute the JPanel of each square for a rounded one

                currentPanel.revalidate();
                currentPanel.repaint();
            }
        } catch (IOException exception) {
            System.out.println("Error locating chesspiece image file(s)");
        }

        list = newList;

        // copy over the moves on the chessboard
        getMoves();
        selectPanel(selectedPanel1, selectedPiece);

        // Step 3: update the captured pieces to the classic image
        stripLabels(capturedWhite);
        stripLabels(capturedBlack2);
        stripLabels(capturedBlack1);
    }

    /**
     * Method that updates a chessboard so that is is "classic"
     * Entails:
     * 1. Square JPanels that make up the 64 chessboard spaces
     * 2. Classic chesspiece images
     * 3. Update the captured pieces
     */
    public void makeClassic() {

        _isClassic = true;

        // Step 1: Make the JPanels of the chessboard and its background rounded
        // copy the contents of the first list into a new one, with rounded jpanels
        List newList = new List(list.getSize());

        RoundedPanel square0 = new RoundedPanel(30);
        newList.push(square0, null);
        JPanel square1 = new JPanel();
        newList.push(square1, null);
        JPanel square2 = new JPanel();
        newList.push(square2, null);
        JPanel square3 = new JPanel();
        newList.push(square3, null);
        JPanel square4 = new JPanel();
        newList.push(square4, null);
        JPanel square5 = new JPanel();
        newList.push(square5, null);
        JPanel square6 = new JPanel();
        newList.push(square6, null);
        RoundedPanel square7 = new RoundedPanel(30);
        newList.push(square7, null);

        JPanel square8 = new JPanel();
        newList.push(square8, null);
        JPanel square9 = new JPanel();
        newList.push(square9, null);
        JPanel square10 = new JPanel();
        newList.push(square10, null);
        JPanel square11 = new JPanel();
        newList.push(square11, null);
        JPanel square12 = new JPanel();
        newList.push(square12, null);
        JPanel square13 = new JPanel();
        newList.push(square13, null);
        JPanel square14 = new JPanel();
        newList.push(square14, null);
        JPanel square15 = new JPanel();
        newList.push(square15, null);

        JPanel square16 = new JPanel();
        newList.push(square16, null);
        JPanel square17 = new JPanel();
        newList.push(square17, null);
        JPanel square18 = new JPanel();
        newList.push(square18, null);
        JPanel square19 = new JPanel();
        newList.push(square19, null);
        JPanel square20 = new JPanel();
        newList.push(square20, null);
        JPanel square21 = new JPanel();
        newList.push(square21, null);
        JPanel square22 = new JPanel();
        newList.push(square22, null);
        JPanel square23 = new JPanel();
        newList.push(square23, null);

        JPanel square24 = new JPanel();
        newList.push(square24, null);
        JPanel square25 = new JPanel();
        newList.push(square25, null);
        JPanel square26 = new JPanel();
        newList.push(square26, null);
        JPanel square27 = new JPanel();
        newList.push(square27, null);
        JPanel square28 = new JPanel();
        newList.push(square28, null);
        JPanel square29 = new JPanel();
        newList.push(square29, null);
        JPanel square30 = new JPanel();
        newList.push(square30, null);
        JPanel square31 = new JPanel();
        newList.push(square31, null);

        JPanel square32 = new JPanel();
        newList.push(square32, null);
        JPanel square33 = new JPanel();
        newList.push(square33, null);
        JPanel square34 = new JPanel();
        newList.push(square34, null);
        JPanel square35 = new JPanel();
        newList.push(square35, null);
        JPanel square36 = new JPanel();
        newList.push(square36, null);
        JPanel square37 = new JPanel();
        newList.push(square37, null);
        JPanel square38 = new JPanel();
        newList.push(square38, null);
        JPanel square39 = new JPanel();
        newList.push(square39, null);

        JPanel square40 = new JPanel();
        newList.push(square40, null);
        JPanel square41 = new JPanel();
        newList.push(square41, null);
        JPanel square42 = new JPanel();
        newList.push(square42, null);
        JPanel square43 = new JPanel();
        newList.push(square43, null);
        JPanel square44 = new JPanel();
        newList.push(square44, null);
        JPanel square45 = new JPanel();
        newList.push(square45, null);
        JPanel square46 = new JPanel();
        newList.push(square46, null);
        JPanel square47 = new JPanel();
        newList.push(square47, null);

        JPanel square48 = new JPanel();
        newList.push(square48, null);
        JPanel square49 = new JPanel();
        newList.push(square49, null);
        JPanel square50 = new JPanel();
        newList.push(square50, null);
        JPanel square51 = new JPanel();
        newList.push(square51, null);
        JPanel square52 = new JPanel();
        newList.push(square52, null);
        JPanel square53 = new JPanel();
        newList.push(square53, null);
        JPanel square54 = new JPanel();
        newList.push(square54, null);
        JPanel square55 = new JPanel();
        newList.push(square55, null);

        RoundedPanel square56 = new RoundedPanel(30);
        newList.push(square56, null);
        JPanel square57 = new JPanel();
        newList.push(square57, null);
        JPanel square58 = new JPanel();
        newList.push(square58, null);
        JPanel square59 = new JPanel();
        newList.push(square59, null);
        JPanel square60 = new JPanel();
        newList.push(square60, null);
        JPanel square61 = new JPanel();
        newList.push(square61, null);
        JPanel square62 = new JPanel();
        newList.push(square62, null);
        RoundedPanel square63 = new RoundedPanel(30);
        newList.push(square63, null);

        // set the properties of the new rounded jpanels
        // Set properties of all the items in the List
        int cellRemainder,
                count = 0;
        JPanel jpanel;

        Color tan = new Color(210, 180, 140);
        Color brown = new Color(139, 69, 19);

        stripPanels(_nePanel);
        stripPanels(_nwPanel);
        stripPanels(_sePanel);
        stripPanels(_swPanel);

        for (int i = 0; i < newList.getSize(); ++i) {

            jpanel = (JPanel) newList.pop(i);

            // set size of the JPanel
            jpanel.setPreferredSize(new Dimension((int) (boardHeight / 8.0),
                    (int) (boardHeight / 8.0)));

            cellRemainder = i % 2;
            if ((int) (count / 8) % 2 == 0) {
                // test if index is even or odd
                // even
                if (cellRemainder == 0)
                    jpanel.setBackground(tan);

                // odd
                else
                    jpanel.setBackground(brown);
            }

            else {
                // test if index is even or odd
                // even
                if (cellRemainder == 0)
                    jpanel.setBackground(brown);

                // odd
                else
                    jpanel.setBackground(tan);
            }

            // set the Layout manager for each jpanel
            jpanel.setLayout(new BorderLayout());

            // add components to respective container
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 8 || i == 9 ||
                    i == 10 || i == 11 || i == 16 || i == 17 || i == 18 ||
                    i == 19 || i == 24 || i == 25 || i == 26 || i == 27)
                _nwPanel.add(jpanel);

            else if (i == 4 || i == 5 || i == 6 || i == 7 || i == 12 ||
                    i == 13 || i == 14 || i == 15 || i == 20 || i == 21 ||
                    i == 22 || i == 23 || i == 28 || i == 29 || i == 30 ||
                    i == 31)
                _nePanel.add(jpanel);

            else if (i == 32 || i == 33 || i == 34 || i == 35 || i == 40 ||
                    i == 41 || i == 42 || i == 43 || i == 48 || i == 49 ||
                    i == 50 || i == 51 || i == 56 || i == 57 || i == 58 ||
                    i == 59)
                _swPanel.add(jpanel);

            else
                _sePanel.add(jpanel);

            ++count;
        }

        // Change the color of the background JPanels
        _sePanel.setBackground(tan);
        _swPanel.setBackground(brown);
        _nePanel.setBackground(brown);
        _nwPanel.setBackground(tan);

        // copy the locations of the chesspieces of the first into the second
        JPanel currentPanel;
        ChessPiece currentPiece;
        for (int i = 0; i < newList.getSize(); ++i) {
            currentPanel = (JPanel) newList.pop(i);
            currentPiece = (ChessPiece) list.getComponent(i);

            newList.addComponent(currentPanel, currentPiece);
        }

        // Step 2: Update all the chesspiece images
        JPanel pastPanel;
        ChessPiece pastPiece;
        try {
            for (int i = 0; i < newList.getSize(); ++i) {
                currentPanel = (JPanel) newList.pop(i);
                currentPiece = (ChessPiece) newList.getComponent(i);

                pastPanel = (JPanel) list.pop(i);
                pastPiece = (ChessPiece) list.getComponent(i);

                // update the fields
                if (pastPanel == selectedPanel1)
                    selectedPanel1 = currentPanel;

                else if (pastPanel == selectedPanel2)
                    selectedPanel2 = currentPanel;

                else if (pastPiece == selectedPiece)
                    selectedPiece = currentPiece;

                // decide what image file to use
                if (currentPiece instanceof WhitePawn) {
                    BufferedImage wPawnImage = ImageIO.read(new File("WhitePawn.png"));
                    currentPiece.setImage(wPawnImage);
                }

                else if (currentPiece instanceof WhiteRook) {
                    BufferedImage wRookImage = ImageIO.read(new File("WhiteRook.png"));
                    currentPiece.setImage(wRookImage);
                }

                else if (currentPiece instanceof WhiteKnight) {
                    BufferedImage wKnightImage = ImageIO.read(new File("WhiteKnight.png"));
                    currentPiece.setImage(wKnightImage);
                }

                else if (currentPiece instanceof WhiteBishop) {
                    BufferedImage wBishopImage = ImageIO.read(new File("WhiteBishop.png"));
                    currentPiece.setImage(wBishopImage);
                }

                else if (currentPiece instanceof WhiteQueen) {
                    BufferedImage wQueenImage = ImageIO.read(new File("WhiteQueen.png"));
                    currentPiece.setImage(wQueenImage);
                }

                else if (currentPiece instanceof WhiteKing) {
                    BufferedImage wKingImage = ImageIO.read(new File("WhiteKing.png"));
                    currentPiece.setImage(wKingImage);
                }

                else if (currentPiece instanceof BlackPawn) {
                    BufferedImage bPawnImage = ImageIO.read(new File("BlackPawn.png"));
                    currentPiece.setImage(bPawnImage);
                }

                else if (currentPiece instanceof BlackRook) {
                    BufferedImage bRookImage = ImageIO.read(new File("BlackRook.png"));
                    currentPiece.setImage(bRookImage);
                }

                else if (currentPiece instanceof BlackKnight) {
                    BufferedImage bKnightImage = ImageIO.read(new File("BlackKnight.png"));
                    currentPiece.setImage(bKnightImage);
                }

                else if (currentPiece instanceof BlackBishop) {
                    BufferedImage bBishopImage = ImageIO.read(new File("BlackBishop.png"));
                    currentPiece.setImage(bBishopImage);
                }

                else if (currentPiece instanceof BlackQueen) {
                    BufferedImage bQueenImage = ImageIO.read(new File("BlackQueen.png"));
                    currentPiece.setImage(bQueenImage);
                }

                else if (currentPiece instanceof BlackKing) {
                    BufferedImage bKingImage = ImageIO.read(new File("BlackKing.png"));
                    currentPiece.setImage(bKingImage);
                }

                // pin the new image on its panel
                if (currentPiece != null && currentPiece instanceof ChessPiece) {
                    pin(currentPiece, currentPanel, 0, 0, "BorderLayout", true);
                }

                // Substitute the JPanel of each square for a rounded one

                currentPanel.revalidate();
                currentPanel.repaint();
            }
        } catch (IOException exception) {
            System.out.println("Error locating chesspiece image file(s)");
        }

        list = newList;

        // copy over the moves on the chessboard
        getMoves();
        selectPanel(selectedPanel1, selectedPiece);

        // Step 3: update the captured pieces to the classic image
        stripLabels(capturedWhite);
        stripLabels(capturedBlack2);
        stripLabels(capturedBlack1);
    }
}
