
/**
This is a class that models a Black Rook chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*; // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackRook extends ChessPiece {

    private List _moves; // list that holds available moves for a piece given its environment
    private boolean _beenMoved;
    private List _chessboard; // list that represents the chessboard
    private int _location; // integer that represents the location of this piece
    private BlackKing _king;
    private int _kingLocation;

    public BlackRook(BufferedImage image, int location) {
        _moves = new List(5);
        _chessboard = null;
        _beenMoved = false;
        _king = null;
        _kingLocation = 0;
        _location = location;

        // set file image
        super.setImage(image);
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
            int doNothing1, int doNothing2) {
        // set fields
        _chessboard = chessboard;
        _location = myLocation;
        _king = (BlackKing) getKing(_chessboard, this);
        _kingLocation = getKingLocation(_chessboard, this);

        // remove any old moves that might be saved
        _moves.removeAll();

        horizontalRight(_location, considerCheck);
        horizontalLeft(_location, considerCheck);
        verticalUp(_location, considerCheck);
        verticalDown(_location, considerCheck);

        // don't consider castling unless it is a direct move as it is never used to
        // capture
        if (considerCheck)
            castle();

        return _moves;
    }

    /**
     * Recursive method that finds the horizonatal moves to the right
     */
    private void horizontalRight(int position, boolean considerCheck) {
        int next = position + 1;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if (considerCheck && _king.check(_kingLocation, _chessboard)) {
            if (validGeneralMove(position, next)) {
                if (removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                horizontalRight(next, considerCheck);
            }
        }

        // standard move- king does not lie in check
        else {
            if (validGeneralMove(position, next)) {
                _moves.push(next, null);
                horizontalRight(next, considerCheck);
            }
        }
    }

    /**
     * Recursive method that finds the horizontal moves to the left of current
     * position
     */
    private void horizontalLeft(int position, boolean considerCheck) {
        int next = position - 1;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if (considerCheck && _king.check(_kingLocation, _chessboard)) {
            if (validGeneralMove(position, next)) {
                if (removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                horizontalLeft(next, considerCheck);
            }
        }

        // standard move- king does not lie in check
        else {
            if (validGeneralMove(position, next)) {
                _moves.push(next, null);
                horizontalLeft(next, considerCheck);
            }
        }
    }

    /**
     * Recursive method that finds any legal upward vertical moves
     */
    private void verticalUp(int position, boolean considerCheck) {
        int next = position + 8;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if (considerCheck && _king.check(_kingLocation, _chessboard)) {
            if (validGeneralMove(position, next)) {
                if (removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                verticalUp(next, considerCheck);
            }
        }

        // standard move- king does not lie in check
        else {
            if (validGeneralMove(position, next)) {
                _moves.push(next, null);
                verticalUp(next, considerCheck);
            }
        }
    }

    /**
     * Recursive method that finds any legal downward vertical moves
     */
    private void verticalDown(int position, boolean considerCheck) {
        int next = position - 8;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if (considerCheck && _king.check(_kingLocation, _chessboard)) {
            if (validGeneralMove(position, next)) {
                if (removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                verticalDown(next, considerCheck);
            }
        }

        // standard move- king does not lie in check
        else {
            if (validGeneralMove(position, next)) {
                _moves.push(next, null);
                verticalDown(next, considerCheck);
            }
        }
    }

    /**
     * Method that checks if the rook is able to castle
     */
    private void castle() {
        // general requirements for castling
        if (!hasMoved() && !_king.hasMoved() && !_king.check(_kingLocation, _chessboard)) {
            // check rook on left side of the chessboard
            leftSideCastle();

            // check rook on right side of the chessboard
            rightSideCastle();
        }
    }

    /**
     * Method that checks the left-side rook for a castle
     */
    private void leftSideCastle() {
        if (_chessboard.getComponent(1) == null && _chessboard.getComponent(2) == null &&
                _chessboard.getComponent(3) == null)
            _moves.push(4, null);
    }

    /**
     * Method that checks the right-side rook for a castle
     */
    private void rightSideCastle() {
        if (_chessboard.getComponent(5) == null && _chessboard.getComponent(6) == null)
            _moves.push(4, null);
    }

    /**
     * Method that determines whether or not a potential move is valid according to
     * rook rules
     */
    private boolean validGeneralMove(int position, int next) {
        if (next > 63 || next < 0)
            return false;

        ChessPiece myPiece = (ChessPiece) _chessboard.getComponent(position);
        ChessPiece nextPiece = (ChessPiece) _chessboard.getComponent(next);

        if (!sameColor(this, nextPiece) && !overflow(position, next) &&
                (myPiece == null || myPiece == this))
            return true;

        return false;
    }

    /**
     * Method that handles the 'chessboard overflow' error
     */
    private boolean overflow(int location, int moveTo) {
        // look at vertical movements
        if (moveTo == location + 8 || moveTo == location - 8) {
            if (sameColumn(moveTo + 1, location + 1))
                return false;
        }

        // check if move is horizontal
        if (moveTo == location + 1 || moveTo == location - 1) {
            if (sameRow(moveTo, location))
                return false;
        }

        return true;
    }

    /**
     * Method that sets the 'beenMoved' field to true
     * Should be called after a rook chess piece is initially moved
     */
    public void beenMoved() {
        _beenMoved = true;
    }

    /**
     * Method that returns the 'beenMoved' field
     * Indicates whether or not a rook has been moved
     */
    public boolean hasMoved() {
        return _beenMoved;
    }

    public String toString() {
        return "Black Rook: " + Integer.toString(_location);
    }
}
