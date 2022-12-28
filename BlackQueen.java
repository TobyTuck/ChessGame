/**
This is a class that models a Black Queen chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackQueen extends ChessPiece{

    private List _possibleMoves; 

    public BlackQueen(){
        _possibleMoves = new List(5);

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackQueen.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Queen image file");}
    }

    public List possibleMoves(int myLocation, List chessboard){
        // remove any old moves that might be saved
        _possibleMoves.removeAll();

        // Bishop-like movements
        topRight(myLocation, myLocation, chessboard);
        topLeft(myLocation, myLocation, chessboard);
        bottomRight(myLocation, myLocation, chessboard);
        bottomLeft(myLocation, myLocation, chessboard);

        // Rook-like movements
        horizontalRight(myLocation, myLocation, chessboard);
        horizontalLeft(myLocation, myLocation, chessboard);
        verticalUp(myLocation, myLocation, chessboard);
        verticalDown(myLocation, myLocation, chessboard);

        return _possibleMoves;
    }

    /**
    Rook-like moves
    Recursive method that finds the horizonatal moves to the right
    */
    private void horizontalRight(int position, int startLocation, List chessboard){
        int next = position + 1;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            horizontalRight(next, startLocation, chessboard);}
    }

    /**
    Recursive method that finds the horizontal moves to the left of current position
    */
    private void horizontalLeft(int position, int startLocation, List chessboard){
        int next = position - 1;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            horizontalLeft(next, startLocation, chessboard);}
    }

    /**
    Recursive method that finds any legal upward vertical moves
    */
    private void verticalUp(int position, int startLocation, List chessboard){
        int next = position + 8;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            verticalUp(next, startLocation, chessboard);}
    }

    /**
    Recursive method that finds any legal downward vertical moves
    */
    private void verticalDown(int position, int startLocation, List chessboard){
        int next = position - 8;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            verticalDown(next, startLocation, chessboard);}
    }

    /**
    Bishop-like Moves
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
    Method that determines whether or not a potential move is valid according to queen rules
    */
    private boolean validMovement(int position, int next, int startLocation, List chessboard){
        if(next > 63 || next < 0)
            return false;

        ChessPiece startPiece = (ChessPiece) chessboard.getComponent(startLocation);
        ChessPiece myPiece = (ChessPiece) chessboard.getComponent(position);
        ChessPiece nextPiece = (ChessPiece) chessboard.getComponent(next);

        if(!sameColor(startPiece, nextPiece) &&
           !overflow(startLocation, position, next) &&
           (myPiece == null || myPiece == startPiece))
            return true;

        return false;
    }

    /**
    Method that handles the 'chessboard overflow' error
    */
    private boolean overflow(int start, int location, int moveTo){
        // rook movements
        if(location == moveTo + 8 || location == moveTo - 8 ||
           location == moveTo + 1 || location == moveTo - 1){
            if(sameRow(moveTo, start) || sameColumn(moveTo + 1, start + 1))
                return false;

            else{
                return true;} }

        // bishop movements
        else{
            if((rowOf(location + 1, 8) && rowOf(moveTo + 1, 1)) || 
               (rowOf(moveTo + 1, 8) && rowOf(location + 1, 1)))
                return true;

            else{
                return false;} }
    }
}
