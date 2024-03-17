
/**
This is a class that models a Black Bishop chesspiece
*/

import java.awt.image.BufferedImage;

public class BlackBishop extends ChessPiece {

    private List _moves; // list that holds available moves for a piece given its environment
    private List _chessboard; // list that represents the chessboard
    private int _location; // integer that represents the location of this piece
    private BlackKing _king;
    private int _kingLocation;

    public BlackBishop(BufferedImage image, int location) {
        _moves = new List(5);
        _chessboard = null;
        _location = location;
        _king = null;
        _kingLocation = 0;

        // set file image
        super.setImage(image);
    }

    public List possibleMoves(int location, List chessboard, boolean considerCheck,
            int doNothing1, int doNothing2) {
        // set fields
        _chessboard = chessboard;
        _location = location;
        _king = (BlackKing) getKing(_chessboard, this);
        _kingLocation = getKingLocation(_chessboard, this);

        // remove any old moves that might have been carried over
        _moves.removeAll();

        // piece's king does not lie in check
        topRight(_location, considerCheck);
        topLeft(_location, considerCheck);
        bottomRight(_location, considerCheck);
        bottomLeft(_location, considerCheck);

        return _moves;
    }

    /**
     * Recursive method that adds the angled top right moves
     */
    private void topRight(int position, boolean considerCheck) {
        int next = position + 7;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if (considerCheck && _king.check(_kingLocation, _chessboard)) {
            if (validGeneralMove(position, next)) {
                if (removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                topRight(next, considerCheck);
            }
        }

        // standard move- king does not lie in check
        else {
            if (validGeneralMove(position, next)) {
                _moves.push(next, null);
                topRight(next, considerCheck);
            }
        }
    }

    /**
     * Recursive method that adds the angled top left moves
     */
    private void topLeft(int position, boolean considerCheck) {
        int next = position + 9;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if (considerCheck && _king.check(_kingLocation, _chessboard)) {
            if (validGeneralMove(position, next)) {
                if (removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                topLeft(next, considerCheck);
            }
        }

        // standard move- king does not lie in check
        else {
            if (validGeneralMove(position, next)) {
                _moves.push(next, null);
                topLeft(next, considerCheck);
            }
        }
    }

    /**
     * Recursive method that adds the angled bottom right moves
     */
    private void bottomRight(int position, boolean considerCheck) {
        int next = position - 7;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if (considerCheck && _king.check(_kingLocation, _chessboard)) {
            if (validGeneralMove(position, next)) {
                if (removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                bottomRight(next, considerCheck);
            }
        }

        // standard move- king does not lie in check
        else {
            if (validGeneralMove(position, next)) {
                _moves.push(next, null);
                bottomRight(next, considerCheck);
            }
        }
    }

    /**
     * Recursive method that adds the angled bottom left moves
     */
    private void bottomLeft(int position, boolean considerCheck) {
        int next = position - 9;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if (considerCheck && _king.check(_kingLocation, _chessboard)) {
            if (validGeneralMove(position, next)) {
                if (removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                bottomLeft(next, considerCheck);
            }
        }

        // standard move- king does not lie in check
        else {
            if (validGeneralMove(position, next)) {
                _moves.push(next, null);
                bottomLeft(next, considerCheck);
            }
        }
    }

    /**
     * Method that checks if a movement legal according to general standards
     * These standards are:
     * - no overflow
     * - chess piece at potential move is not same color as this piece
     * - piece at prior move is not of the opposite color
     * This method ignores the special case of our king being in check
     */
    private boolean validGeneralMove(int position, int next) {
        // special case- moves fall outside the dimensions of our chessboard
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
        if ((rowOf(location + 1, 8) && rowOf(moveTo + 1, 1)) ||
                (rowOf(moveTo + 1, 8) && rowOf(location + 1, 1)))
            return true;

        return false;
    }

    public String toString() {
        return "Black Bishop: " + Integer.toString(_location);
    }
}
