
/**
This is a class that models a Black Knight chesspiece
*/

import java.awt.image.BufferedImage;

public class BlackKnight extends ChessPiece {

    private List _moves;
    private int _location;

    public BlackKnight(BufferedImage image, int location) {
        _moves = new List(5);
        _location = location;

        // set file image
        super.setImage(image);
    }

    public List possibleMoves(int myLocation, List chessboard, boolean routineCheck,
            int doNothing1, int doNothing2) {
        // remove moves from previous locations
        _moves.removeAll();
        _location = myLocation;

        int move;
        ChessPiece movePiece;

        if (routineCheck) {
            // search board for chess king
            BlackKing bKing = (BlackKing) getKing(chessboard, this);
            int kingLocation = getKingLocation(chessboard, this);
            boolean inCheck = bKing.check(kingLocation, chessboard);

            // 'reverse L' move
            move = myLocation - 17;
            if (move >= 0) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) - 2) && !isBlack(movePiece) &&
                        (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);
            }

            // 'L' move
            move = myLocation - 15;
            if (move >= 0) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) - 2) && !isBlack(movePiece) &&
                        (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);
            }

            // 'r' move
            move = myLocation - 6;
            if (move >= 0) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) - 1) && !isBlack(movePiece) &&
                        (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);
            }

            // 'reverse r' move
            move = myLocation - 10;
            if (move >= 0) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) - 1) && !isBlack(movePiece) &&
                        (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);
            }

            // 'upside down r' move
            move = myLocation + 6;
            if (move <= 63) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) + 1) && !isBlack(movePiece) &&
                        (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);
            }

            // 'upside down reverse r' move
            move = myLocation + 10;
            if (move <= 63) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) + 1) && !isBlack(movePiece) &&
                        (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);
            }

            // 'upside down reverse L' move
            move = myLocation + 17;
            if (move <= 63) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) + 2) && !isBlack(movePiece) &&
                        (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);
            }

            // 'upside down L' move
            move = myLocation + 15;
            if (move <= 63) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) + 2) && !isBlack(movePiece) &&
                        (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);
            }
        }

        else {
            // 'reverse L' move
            move = myLocation - 17;
            if (move >= 0) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) - 2) && !isBlack(movePiece))
                    _moves.push(move, null);
            }

            // 'L' move
            move = myLocation - 15;
            if (move >= 0) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) - 2) && !isBlack(movePiece))
                    _moves.push(move, null);
            }

            // 'r' move
            move = myLocation - 6;
            if (move >= 0) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) - 1) && !isBlack(movePiece))
                    _moves.push(move, null);
            }

            // 'reverse r' move
            move = myLocation - 10;
            if (move >= 0) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) - 1) && !isBlack(movePiece))
                    _moves.push(move, null);
            }

            // 'upside down r' move
            move = myLocation + 6;
            if (move <= 63) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) + 1) && !isBlack(movePiece))
                    _moves.push(move, null);
            }

            // 'upside down reverse r' move
            move = myLocation + 10;
            if (move <= 63) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) + 1) && !isBlack(movePiece))
                    _moves.push(move, null);
            }

            // 'upside down reverse L' move
            move = myLocation + 17;
            if (move <= 63) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) + 2) && !isBlack(movePiece))
                    _moves.push(move, null);
            }

            // 'upside down L' move
            move = myLocation + 15;
            if (move <= 63) {
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if ((move / 8 == (myLocation / 8) + 2) && !isBlack(movePiece))
                    _moves.push(move, null);
            }
        }

        return _moves;
    }

    public String toString() {
        return "Black Knight: " + Integer.toString(_location);
    }
}
