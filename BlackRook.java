/**
This is a class that models a Black Rook chesspiece
*/

import java.awt.image.BufferedImage;                       
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackRook extends ChessPiece{

    public BlackRook(){
        // set file image
        try{ 
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackRook.png"));
           
            // pass image to parent file
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Rook image file");}

        // set possible movements by this piece
        List potentialMoves = new List(28);
        potentialMoves.push(8, true);
        potentialMoves.push(16, true);
        potentialMoves.push(24, true);
        potentialMoves.push(32, true);
        potentialMoves.push(40, true);
        potentialMoves.push(48, true);
        potentialMoves.push(56, true);
        potentialMoves.push(1, true);
        potentialMoves.push(2, true);
        potentialMoves.push(3, true);
        potentialMoves.push(4, true);
        potentialMoves.push(5, true);
        potentialMoves.push(6, true);
        potentialMoves.push(7, true);
        potentialMoves.push(-8, true);
        potentialMoves.push(-16, true);
        potentialMoves.push(-24, true);
        potentialMoves.push(-32, true);
        potentialMoves.push(-40, true);
        potentialMoves.push(-48, true);
        potentialMoves.push(-56, true);
        potentialMoves.push(-1, true);
        potentialMoves.push(-2, true);
        potentialMoves.push(-3, true);
        potentialMoves.push(-4, true);
        potentialMoves.push(-5, true);
        potentialMoves.push(-6, true);
        potentialMoves.push(-7, true);
        super.setMovement(potentialMoves);
    }

    public List removeOverflow(int myLocation, List chessboard){
        List potentialMoves = new List(5);
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

        /* for(int index = 0; index < extensiveMoves.getSize(); ++index){
            move = myLocation + (int) extensiveMoves.pop(index);
            if(move < 64 && !sameColor((ChessPiece) chessboard.getComponent
              (myLocation), (ChessPiece) chessboard.getComponent(move)) &&
              (sameMultiple(myLocation, move, 8) || sameBase(myLocation, move, 8)))
                potentialMoves.push(move, null);}*/

        return potentialMoves;
    }
}
