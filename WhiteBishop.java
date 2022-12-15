/**
This is a class that models a White Bishop chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteBishop extends ChessPiece{

    public WhiteBishop(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteBishop.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Bishop image file");}
    }

    public List possibleMoves(int myLocation, List chessboard){
        List potentialMoves = new List(5);
        List extensiveMoves = super.getMovement();
        boolean isDone = false;
        ChessPiece currentPiece = (ChessPiece) chessboard.getComponent(myLocation);
        ChessPiece movePiece = null;

        int move = myLocation + 9;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        while(!sameColor(currentPiece, movePiece) && 
              !overflow(move + 1, move - 8, 8, new WhiteBishop()) 
              && !isDone && move < 64){
            potentialMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move += 9;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        move = myLocation + 7;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        isDone = false;
        while(!sameColor(currentPiece, movePiece) && 
              !overflow(move + 1, move - 6, 8, new WhiteBishop())
              && !isDone && move < 64){
            potentialMoves.push(move, null);
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
              !overflow(move + 1, move + 10, 8, new WhiteBishop())
              && !isDone && move > -1){
            potentialMoves.push(move, null);
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
              !overflow(move + 1, move + 8, 8, new WhiteBishop()) &&
              !isDone && move > -1){
            potentialMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move -= 7;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        return potentialMoves;
    }
}
