/**
This is a class that models a Black Rook chesspiece
*/

import java.awt.image.BufferedImage;                       
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackRook extends ChessPiece{

    private List _possibleMoves;
    private boolean _beenMoved;

    public BlackRook(){
        _possibleMoves = new List(5);
        _beenMoved = false;

        // set file image
        try{ 
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackRook.png"));
           
            // pass image to parent file
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Rook image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
                              int doNothing1, int doNothing2){
        // remove any old moves that might be saved
        _possibleMoves.removeAll();

        // search board for black king
        BlackKing bKing = (BlackKing) getKing(chessboard, this);
        int kingLocation = getKingLocation(chessboard, this);

        if(considerCheck){
            horizontalRight(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);
            horizontalLeft(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);
            verticalUp(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);
            verticalDown(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);

            // check casteling
            castle(chessboard, myLocation, bKing, kingLocation);}

        else{
            // Black king and its location will not be used to calculate whether or not the opponent
            // king is in check
            BlackKing doesntMatter = bKing;
            int doesNotMatter = kingLocation;

            horizontalRight(myLocation, myLocation, chessboard, considerCheck, doesntMatter, 
                            doesNotMatter);
            horizontalLeft(myLocation, myLocation, chessboard, considerCheck, doesntMatter, 
                           doesNotMatter);
            verticalUp(myLocation, myLocation, chessboard, considerCheck, doesntMatter, 
                       doesNotMatter);
            verticalDown(myLocation, myLocation, chessboard, considerCheck, doesntMatter, 
                         doesNotMatter);}

        return _possibleMoves;
    }

    /**
    Recursive method that finds the horizonatal moves to the right
    */
    private void horizontalRight(int position, int startLocation, List chessboard,
                                 boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position + 1;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck, bKing, 
                         bKingLocation)){
            _possibleMoves.push(next, null);
            horizontalRight(next, startLocation, chessboard, considerCheck, bKing, bKingLocation);}
    }

    /**
    Recursive method that finds the horizontal moves to the left of current position
    */
    private void horizontalLeft(int position, int startLocation, List chessboard,
                                boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position - 1;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck, bKing, 
                         bKingLocation)){
            _possibleMoves.push(next, null);
            horizontalLeft(next, startLocation, chessboard, considerCheck, bKing, bKingLocation);}
    }

    /**
    Recursive method that finds any legal upward vertical moves
    */
    private void verticalUp(int position, int startLocation, List chessboard,
                            boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position + 8;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck, bKing, 
                         bKingLocation)){
            _possibleMoves.push(next, null);
            verticalUp(next, startLocation, chessboard, considerCheck, bKing, bKingLocation);}
    }

    /**
    Recursive method that finds any legal downward vertical moves
    */
    private void verticalDown(int position, int startLocation, List chessboard,
                              boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position - 8;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck, bKing, 
                         bKingLocation)){
            _possibleMoves.push(next, null);
            verticalDown(next, startLocation, chessboard, considerCheck, bKing, bKingLocation);}
    }

    /**
    Method that checks if the rook is able to castle
    */
    private void castle(List chessboard, int position, BlackKing bKing, int bKingLocation){
        // check rook on left side of the chessboard
        leftSideCastle(chessboard, position, bKing, bKingLocation);

        // check rook on right side of the chessboard
        rightSideCastle(chessboard, position, bKing, bKingLocation);
    }

    /**
    Method that checks the left-side rook for a castle
    */
    private void leftSideCastle(List chessboard, int position, BlackKing bKing, int bKingLocation){
        ChessPiece piece = (ChessPiece) chessboard.getComponent(4);
        
        if(!hasMoved() && chessboard.getComponent(1) == null && chessboard.getComponent(2) == null &&
           chessboard.getComponent(3) == null && position == 0 && piece instanceof BlackKing &&
           (!bKing.check(bKingLocation, chessboard))){
            BlackKing king = (BlackKing) piece;
            if(!king.hasMoved())
                _possibleMoves.push(4, null);}
    }

    /**
    Method that checks the right-side rook for a castle
    */
    private void rightSideCastle(List chessboard, int position, BlackKing bKing, int bKingLocation){
        ChessPiece piece = (ChessPiece) chessboard.getComponent(4);
        
        if(!hasMoved() && chessboard.getComponent(5) == null && chessboard.getComponent(6) == null &&
           position == 7 && piece instanceof BlackKing && (!bKing.check(bKingLocation, chessboard))){
            BlackKing king = (BlackKing) piece;
            if(!king.hasMoved())
                _possibleMoves.push(4, null);}
    }

    /**
    Method that determines whether or not a potential move is valid according to rook rules
    */
    private boolean validMovement(int position, int next, int startLocation, List chessboard,
                                  boolean considerCheck, BlackKing bKing, int bKingLocation){
        if(next > 63 || next < 0)
            return false;

        ChessPiece startPiece = (ChessPiece) chessboard.getComponent(startLocation);
        ChessPiece myPiece = (ChessPiece) chessboard.getComponent(position);
        ChessPiece nextPiece = (ChessPiece) chessboard.getComponent(next);

        if(considerCheck){
            if(!sameColor(startPiece, nextPiece) && !overflow(position, next) && (myPiece == null || 
               myPiece == startPiece) && (!bKing.check(bKingLocation, chessboard)))
                return true;

            return false;}

        // only look at instances of check (as applies to opponent king)
        // only difference is that BRook's moves "extend through" a WKing 
            // eg: if a king in check moves diagonally away from BRook, piece is still in check
        else{
            // illegal moves
            if((isBlack(nextPiece) || (isWhite(myPiece) && !(myPiece instanceof WhiteKing))) ||
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
        // look at vertical movements
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
