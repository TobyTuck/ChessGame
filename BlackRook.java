/**
This is a class that models a Black Rook chesspiece
*/

import java.awt.image.BufferedImage;                       
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackRook extends ChessPiece{

    private List _possibleMoves;

    public BlackRook(){
        _possibleMoves = new List(5);

        // set file image
        try{ 
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackRook.png"));
           
            // pass image to parent file
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Rook image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck){
        // remove any old moves that might be saved
        _possibleMoves.removeAll();

        horizontalRight(myLocation, myLocation, chessboard, considerCheck);
        horizontalLeft(myLocation, myLocation, chessboard, considerCheck);
        verticalUp(myLocation, myLocation, chessboard, considerCheck);
        verticalDown(myLocation, myLocation, chessboard, considerCheck);

        return _possibleMoves;
    }

    /**
    Recursive method that finds the horizonatal moves to the right
    */
    private void horizontalRight(int position, int startLocation, List chessboard,
                                 boolean considerCheck){
        int next = position + 1;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck)){
            _possibleMoves.push(next, null);
            horizontalRight(next, startLocation, chessboard, considerCheck);}
    }

    /**
    Recursive method that finds the horizontal moves to the left of current position
    */
    private void horizontalLeft(int position, int startLocation, List chessboard,
                                boolean considerCheck){
        int next = position - 1;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck)){
            _possibleMoves.push(next, null);
            horizontalLeft(next, startLocation, chessboard, considerCheck);}
    }

    /**
    Recursive method that finds any legal upward vertical moves
    */
    private void verticalUp(int position, int startLocation, List chessboard,
                            boolean considerCheck){
        int next = position + 8;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck)){
            _possibleMoves.push(next, null);
            verticalUp(next, startLocation, chessboard, considerCheck);}
    }

    /**
    Recursive method that finds any legal downward vertical moves
    */
    private void verticalDown(int position, int startLocation, List chessboard,
                              boolean considerCheck){
        int next = position - 8;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard, considerCheck)){
            _possibleMoves.push(next, null);
            verticalDown(next, startLocation, chessboard, considerCheck);}
    }

    /**
    Method that determines whether or not a potential move is valid according to rook rules
    */
    private boolean validMovement(int position, int next, int startLocation, List chessboard,
                                  boolean considerCheck){
        if(next > 63 || next < 0)
            return false;

        ChessPiece startPiece = (ChessPiece) chessboard.getComponent(startLocation);
        ChessPiece myPiece = (ChessPiece) chessboard.getComponent(position);
        ChessPiece nextPiece = (ChessPiece) chessboard.getComponent(next);

        if(considerCheck){
            if(!sameColor(startPiece, nextPiece) &&
               !overflow(startLocation, next) &&
               (myPiece == null || myPiece == startPiece))
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
        if(sameRow(moveTo, start) || sameColumn(moveTo + 1, start + 1)) 
            return false;

        return true;
    }
}
