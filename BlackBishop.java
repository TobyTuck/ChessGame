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

    public List possibleMoves(int myLocation, List chessboard){
        // remove any old moves that might be saved
        _possibleMoves.removeAll();

        topRight(myLocation, myLocation, chessboard);
        topLeft(myLocation, myLocation, chessboard);
        bottomRight(myLocation, myLocation, chessboard);
        bottomLeft(myLocation, myLocation, chessboard);

        return _possibleMoves;
    }

    /**
    Recursive method that adds the angled top right moves
    */
    private void topRight(int position, int startLocation, List chessboard){
        int next = position + 7;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            topRight(next, startLocation, chessboard);}
    }

    /**
    Recursive method that adds the angled top left moves
    */
    private void topLeft(int position, int startLocation, List chessboard){
        int next = position + 9;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            topLeft(next, startLocation, chessboard);}
    }

    /**
    Recursive method that adds the angled bottom right moves
    */
    private void bottomRight(int position, int startLocation, List chessboard){
        int next = position - 7;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            bottomRight(next, startLocation, chessboard);}
    }

    /**
    Recursive method that adds the angled bottom left moves
    */
    private void bottomLeft(int position, int startLocation, List chessboard){
        int next = position - 9;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            bottomLeft(next, startLocation, chessboard);}
    }

    /**
    Method that determines whether or not a potential move is valid according to Bishop rules 
    */
    private boolean validMovement(int position, int next, int startLocation, List chessboard){
        if(next > 63 || next < 0)
            return false;

        ChessPiece startPiece = (ChessPiece) chessboard.getComponent(startLocation);
        ChessPiece myPiece = (ChessPiece) chessboard.getComponent(position); 
        ChessPiece nextPiece = (ChessPiece) chessboard.getComponent(next);

        if(!sameColor(startPiece, nextPiece) && 
           !overflow(position + 1, next + 1) &&
           (myPiece == null || myPiece == startPiece))
            return true; 

        return false;
    }

    /**
    Method that handles the 'chessboard overflow' error 
    */
    private boolean overflow(int location, int moveTo){
        if((baseOf(location, 8, 8) && baseOf(moveTo, 1, 8)) || 
           (baseOf(moveTo, 8, 8) && baseOf(location, 1, 8)))
            return true;

        return false;
    }
}
