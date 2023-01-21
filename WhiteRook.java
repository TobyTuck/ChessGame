/**
This is a class that models a White Rook chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class WhiteRook extends ChessPiece{

    private List _possibleMoves;

    public WhiteRook(){
        _possibleMoves = new List(5);

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteRook.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Rook image file");}
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
            if(!sameColor(startPiece, nextPiece) && !overflow(position, next) &&
               (myPiece == null || myPiece == startPiece))
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
}
