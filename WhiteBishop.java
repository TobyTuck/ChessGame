/**
This is a class that models a White Bishop chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteBishop extends ChessPiece{

    private List _possibleMoves;

    public WhiteBishop(){
        _possibleMoves = new List(5);

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteBishop.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Bishop image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
                              int doNothing1, int doNothing2){
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

        ChessPiece myPiece = (ChessPiece) chessboard.getComponent(position); 
        ChessPiece nextPiece = (ChessPiece) chessboard.getComponent(next);

        if(considerCheck){
            if(!sameColor(this, nextPiece) && !overflow(position, next) &&
               (myPiece == null || myPiece == this))
                return true; 

            return false;}

        // only look at instances of check (as applies to opponent king)
        // only difference is that WBishop's moves "extend through" a BKing 
        // eg: if a king in check moves diagonally away from WBishop, piece is still in check
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
    private boolean overflow(int location, int moveTo){
        if((rowOf(location + 1, 8) && rowOf(moveTo + 1, 1)) || 
           (rowOf(moveTo + 1, 8) && rowOf(location + 1, 1)))
            return true;

        return false;
    }

    /*
    Method that returns true if a piece's move is in sequence between a bishop and
    a position on the board

    public boolean inSequence(int myLocation, int move, List chessboard){
        // remove any old moves that might be saved
        _possibleMoves.removeAll();

        topRight(myLocation, myLocation, chessboard, true);
        topLeft(myLocation, myLocation, chessboard, true);
        bottomRight(myLocation, myLocation, chessboard, true);
        bottomLeft(myLocation, myLocation, chessboard, true);       

        // search possible moves for the given location
        for(int index = 0; index < _possibleMoves.getSize(); ++index){
            if((int) _possibleMoves.pop(index) == move)
                return true;}

        return false;
    }*/
}
