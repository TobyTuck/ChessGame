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

    public List possibleMoves(int myLocation, List chessboard){
        /* List potentialMoves = new List(5);
        boolean isDone = false;
        ChessPiece currentPiece = (ChessPiece) chessboard.getComponent(myLocation);
        ChessPiece movePiece = null;

        int move = myLocation + 1;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        while(!sameColor(currentPiece, movePiece) && 
              !overflow(myLocation, move, 8, new BlackRook()) && !isDone && 
              move < 64){
            potentialMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move += 1;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        move = myLocation - 1;
        isDone = false;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        while(!sameColor(currentPiece, movePiece) && 
              !overflow(myLocation, move, 8, new BlackRook()) && !isDone && 
              move > -1){
            potentialMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move -= 1;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        move = myLocation + 8;
        isDone = false;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        while(!sameColor(currentPiece, movePiece) && !isDone && move < 64){
            potentialMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move += 8;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        move = myLocation - 8;
        isDone = false;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        while(!sameColor(currentPiece, movePiece) && !isDone && move > -1){
            potentialMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move -= 8;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        // for(int index = 0; index < extensiveMoves.getSize(); ++index){
            move = myLocation + (int) extensiveMoves.pop(index);
            if(move < 64 && !sameColor((ChessPiece) chessboard.getComponent
              (myLocation), (ChessPiece) chessboard.getComponent(move)) &&
              (sameMultiple(myLocation, move, 8) || sameBase(myLocation, move, 8)))
                potentialMoves.push(move, null);}*/ 

        // remove any old moves that might be saved
        _possibleMoves.removeAll();

        horizontalRight(myLocation, myLocation, chessboard);
        horizontalLeft(myLocation, myLocation, chessboard);
        verticalUp(myLocation, myLocation, chessboard);
        verticalDown(myLocation, myLocation, chessboard);

        return _possibleMoves;
    }

    /**
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
        int next = position + 8;

        // check whether or not the position is valid according to rook rules
        if(validMovement(position, next, startLocation, chessboard)){
            _possibleMoves.push(next, null);
            verticalDown(next, startLocation, chessboard);}
    }

    /**
    Method that determines whether or not a potential move is valid according to rook rules
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
        if((moveTo / 8 == location / 8) || (moveTo == location + 8) ||
           (moveTo == location - 8))
            return true;

        return false;
    }
}
