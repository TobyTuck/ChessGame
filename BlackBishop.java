/**
This is a class that models a Black Bishop chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackBishop extends ChessPiece{

    private List _possibleMoves;

    public BlackBishop(){
        _possibleMoves = new List(5);

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackBishop.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Bishop image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck){
        // remove any old moves that might be saved
        _possibleMoves.removeAll();

        topRight(myLocation, myLocation, chessboard, considerCheck);
        topLeft(myLocation, myLocation, chessboard, considerCheck);
        bottomRight(myLocation, myLocation, chessboard, considerCheck);
        bottomLeft(myLocation, myLocation, chessboard, considerCheck);

        return _possibleMoves;
    }

    /**
    Recursive method that adds the angled top right moves
    */
    private void topRight(int position, int startLocation, List chessboard,
                          boolean considerCheck){
        int next = position + 7;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard, considerCheck)){
            _possibleMoves.push(next, null);
            topRight(next, startLocation, chessboard, considerCheck);}
    }

    /**
    Recursive method that adds the angled top left moves
    */
    private void topLeft(int position, int startLocation, List chessboard,
                         boolean considerCheck){
        int next = position + 9;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard, considerCheck)){
            _possibleMoves.push(next, null);
            topLeft(next, startLocation, chessboard, considerCheck);}
    }

    /**
    Recursive method that adds the angled bottom right moves
    */
    private void bottomRight(int position, int startLocation, List chessboard,
                             boolean considerCheck){
        int next = position - 7;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard, considerCheck)){
            _possibleMoves.push(next, null);
            bottomRight(next, startLocation, chessboard, considerCheck);}
    }

    /**
    Recursive method that adds the angled bottom left moves
    */
    private void bottomLeft(int position, int startLocation, List chessboard,
                            boolean considerCheck){
        int next = position - 9;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard, considerCheck)){
            _possibleMoves.push(next, null);
            bottomLeft(next, startLocation, chessboard, considerCheck);}
    }

    /**
    Method that determines whether or not a potential move is valid according to Bishop rules 
    */
    private boolean validMovement(int position, int next, int startLocation, List chessboard,
                                  boolean considerCheck){

        if(next > 63 || next < 0)
                return false;

        ChessPiece startPiece = (ChessPiece) chessboard.getComponent(startLocation);
        ChessPiece myPiece = (ChessPiece) chessboard.getComponent(position); 
        ChessPiece nextPiece = (ChessPiece) chessboard.getComponent(next);

        if(considerCheck){
            if(!sameColor(startPiece, nextPiece) && !overflow(position + 1, next + 1) &&
               (myPiece == null || myPiece == startPiece))
                return true; 

            return false;}

        // only look at instances of check (as applies to opponent king)
        // only difference is that BBishop's moves "extend through" a WKing 
            // eg: if a king in check moves diagonally away from BBishop, piece is still in check
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
    private boolean overflow(int location, int moveTo){
        if((rowOf(location, 8) && rowOf(moveTo, 1)) || 
           (rowOf(moveTo, 8) && rowOf(location, 1)))
            return true;

        return false;
    }
}
