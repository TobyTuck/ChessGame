/**
This is a class that models a White Rook chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class WhiteRook extends ChessPiece{

    private List _possibleMoves;
    private boolean _beenMoved;

    public WhiteRook(){
        _possibleMoves = new List(5);
        _beenMoved = false;

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteRook.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Rook image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
                              int doNothing1, int doNothing2){
        // remove any old moves that might be saved
        _possibleMoves.removeAll();
        
        // get the rook's king and its location
        WhiteKing wKing = (WhiteKing) getKing(chessboard, this);
        int kingLocation = getKingLocation(chessboard, this);       

        if(considerCheck){
            horizontalRight(myLocation, myLocation, chessboard, considerCheck, wKing, kingLocation);
            horizontalLeft(myLocation, myLocation, chessboard, considerCheck, wKing, kingLocation);
            verticalUp(myLocation, myLocation, chessboard, considerCheck, wKing, kingLocation);
            verticalDown(myLocation, myLocation, chessboard, considerCheck, wKing, kingLocation);

            // check casteling
            castle(chessboard, myLocation);}

        else{
            // White king and its location will not be used to calculate whether or not the opponent
            // king is in check
            WhiteKing doesNotMatter = wKing;
            int doesntMatter = kingLocation;

            horizontalRight(myLocation, myLocation, chessboard, considerCheck, doesNotMatter, 
                            doesntMatter);
            horizontalLeft(myLocation, myLocation, chessboard, considerCheck, doesNotMatter, 
                           doesntMatter);
            verticalUp(myLocation, myLocation, chessboard, considerCheck, doesNotMatter, 
                       doesntMatter);
            verticalDown(myLocation, myLocation, chessboard, considerCheck, doesNotMatter, 
                         doesntMatter);}

        return _possibleMoves;
    }

    /**
    Recursive method that finds the horizonatal moves to the right
    */
    private void horizontalRight(int position, int startLocation, List chessboard,
                                 boolean considerCheck, WhiteKing myKing, int kingLocation){
        int next = position + 1;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck, myKing, 
                         kingLocation)){
            _possibleMoves.push(next, null);
            horizontalRight(next, startLocation, chessboard, considerCheck, myKing, kingLocation);}
    }

    /**
    Recursive method that finds the horizontal moves to the left of current position
    */
    private void horizontalLeft(int position, int startLocation, List chessboard,
                                boolean considerCheck, WhiteKing myKing, int kingLocation){
        int next = position - 1;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck, myKing, 
                         kingLocation)){
            _possibleMoves.push(next, null);
            horizontalLeft(next, startLocation, chessboard, considerCheck, myKing, kingLocation);}
    }

    /**
    Recursive method that finds any legal upward vertical moves
    */
    private void verticalUp(int position, int startLocation, List chessboard,
                            boolean considerCheck, WhiteKing myKing, int kingLocation){
        int next = position + 8;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck, myKing, 
                         kingLocation)){
            _possibleMoves.push(next, null);
            verticalUp(next, startLocation, chessboard, considerCheck, myKing, kingLocation);}
    }

    /**
    Recursive method that finds any legal downward vertical moves
    */
    private void verticalDown(int position, int startLocation, List chessboard, 
                              boolean considerCheck, WhiteKing myKing, int kingLocation){
        int next = position - 8;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck, myKing, 
                         kingLocation)){
            _possibleMoves.push(next, null);
            verticalDown(next, startLocation, chessboard, considerCheck, myKing, kingLocation);}
    }

    /**
    Method that checks if the rook is able to castle
    */
    private void castle(List chessboard, int position){
        // check rook on left side of the chessboard
        leftSideCastle(chessboard, position);

        // check rook on right side of the chessboard
        rightSideCastle(chessboard, position);
    }

    /**
    Method that checks the left-side rook for a castle
    */
    private void leftSideCastle(List chessboard, int position){
        int move = 60;
        ChessPiece piece = (ChessPiece) chessboard.getComponent(move);
        
        if(!hasMoved() && chessboard.getComponent(57) == null && chessboard.getComponent(58) == null 
           && chessboard.getComponent(59) == null && position == 56 && piece instanceof WhiteKing){
            WhiteKing king = (WhiteKing) piece;
            if(!king.hasMoved())
                _possibleMoves.push(move, null);}
    }

    /**
    Method that checks the right-side rook for a castle
    */
    private void rightSideCastle(List chessboard, int position){
        int move = 60;
        ChessPiece piece = (ChessPiece) chessboard.getComponent(move);
        
        if(!hasMoved() && chessboard.getComponent(61) == null && chessboard.getComponent(62) == null 
           && position == 63 && piece instanceof WhiteKing){
            WhiteKing king = (WhiteKing) piece;
            if(!king.hasMoved())
                _possibleMoves.push(move, null);}
    }

    /**
    Method that determines whether or not a potential move is valid according to rook rules
    */
    private boolean validMovement(int position, int next, int startLocation, List chessboard,
                                  boolean considerCheck, WhiteKing myKing, int kingLocation){
        if(next > 63 || next < 0)
            return false;

        ChessPiece startPiece = (ChessPiece) chessboard.getComponent(startLocation);
        ChessPiece myPiece = (ChessPiece) chessboard.getComponent(position);
        ChessPiece nextPiece = (ChessPiece) chessboard.getComponent(next);

        if(considerCheck){
            if(!sameColor(startPiece, nextPiece) && !overflow(position, next) && (myPiece == null || 
               myPiece == startPiece) && (!myKing.check(kingLocation, chessboard)))
                return true;

            return false;}

        // only look at instances of check (as applies to opponent king)
        // only difference is that WRook's moves "extend through" a BKing 
            // eg: if a king in check moves diagonally away from WRook, piece is still in check
        else{
            // illegal moves
            if((isWhite(nextPiece) || (isBlack(myPiece) && !(myPiece instanceof BlackKing))) ||
               overflow(position, next))
                return false;

            // legal moves
            else{
                return true;} }
    }

    /**
    Method that handles the 'chessboard overflow' error
    */
    private boolean overflow(int start, int moveTo){
        // check if move is vertical
        if(moveTo == start + 8 || moveTo == start - 8){
            if(sameColumn(moveTo + 1, start + 1))
                return false;}

        // check if move is horizontal
        if(moveTo == start + 1 || moveTo == start - 1){
            if(sameRow(moveTo, start)) 
                return false;}

        return true;
    }

    /**
    Method that sets the 'beenMoved' field to true
    Should be called after a rook chess piece is initially moved
    */
    public void beenMoved(){
        _beenMoved = true;
    }

    /**
    Method that returns the 'beenMoved' field
    Indicates whether or not a rook has been moved
    */
    public boolean hasMoved(){
        return _beenMoved;
    }
}
