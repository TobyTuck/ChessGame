/**
This is a class that models a Black Bishop chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;
import javax.swing.JPanel;

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

        /* boolean isDone = false;
        ChessPiece currentPiece = (ChessPiece) chessboard.getComponent(myLocation);
        ChessPiece movePiece = null;

        int move = myLocation + 9;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        while(!sameColor(currentPiece, movePiece) && 
              !overflow(move + 1, move - 8, 8, new BlackBishop())
              && !isDone && move < 64){
            _possibleMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move += 9;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        topRight(myLocation, myLocation, chessboard); 

        move = myLocation + 7;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        isDone = false;
        while(!sameColor(currentPiece, movePiece) && 
              !overflow(move + 1, move - 6, 8, new BlackBishop())
              && !isDone && move < 64){
            _possibleMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move += 7;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        move = myLocation - 9;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        isDone = false;
        while(!sameColor(currentPiece, movePiece) && 
              !overflow(move + 1, move + 10, 8, new BlackBishop())
              && !isDone && move > -1){
            _possibleMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move -= 9;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        move = myLocation -7;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        isDone = false;
        while(!sameColor(currentPiece, movePiece) && 
              !overflow(move + 1, move + 8, 8, new BlackBishop())
              && !isDone && move > -1){
            _possibleMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move -= 7;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        return _possibleMoves; */

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
            topRight(next, startLocation, chessboard);}
    }

    /**
    Recursive method that adds the angled bottom right moves
    */
    private void bottomRight(int position, int startLocation, List chessboard){
        int next = position - 7;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            topRight(next, startLocation, chessboard);}
    }

    /**
    Recursive method that adds the angled bottom left moves
    */
    private void bottomLeft(int position, int startLocation, List chessboard){
        int next = position - 9;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            topRight(next, startLocation, chessboard);}
    }

    /**
    Method that determines whether or not a potential move is valid according to Bishop rules 
    */
    private boolean validMovement(int position, int next, int startLocation, List chessboard){
        if(next > 63 || next < 0){
            // delete
            System.out.println("Too much: " + next);

            return false;}

        ChessPiece startPiece = (ChessPiece) chessboard.getComponent(startLocation);
        ChessPiece myPiece = (ChessPiece) chessboard.getComponent(position); 
        ChessPiece nextPiece = (ChessPiece) chessboard.getComponent(next);

        // delete
        if(sameColor(startPiece, nextPiece))
            System.out.println("Same Color: " + next);

        if(overflow(position + 1, next + 1))
            System.out.println("Overflow: " + next);

        if(!(myPiece == null || myPiece == startPiece))
            System.out.println("Null: " + next);

        if(!sameColor(startPiece, nextPiece) && 
           !overflow(position + 1, next + 1) &&
           (myPiece == null || myPiece == startPiece))
            return true; 

        return false;
    }

    /**
    Method that handles the 'chessboard overflow' exception
    */
    private boolean overflow(int location, int moveTo){
        if((baseOf(location, 8, 8) && baseOf(moveTo, 1, 8)) || 
           (baseOf(moveTo, 8, 8) && baseOf(location, 1, 8)))
            return true;

        return false;
    }
}
