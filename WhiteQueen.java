/**
This is a class that models the White Queen chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteQueen extends ChessPiece{

    public WhiteQueen(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteQueen.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Queen image file");}
    }

    public List possibleMoves(int myLocation, List chessboard){
        List potentialMoves = new List(5);
        boolean isDone = false;
        ChessPiece currentPiece = (ChessPiece) chessboard.getComponent(myLocation);
        ChessPiece movePiece = null;

        // Rook-like properties
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

        // Bishop-like properties
        move = myLocation + 9;
        isDone = false;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        while(!sameColor(currentPiece, movePiece) && 
              !overflow(move + 1, move - 8, 8, new BlackBishop())
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
              !overflow(move + 1, move - 6, 8, new BlackBishop())
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
              !overflow(move + 1, move + 10, 8, new BlackBishop())
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
              !overflow(move + 1, move + 8, 8, new BlackBishop()) && 
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
