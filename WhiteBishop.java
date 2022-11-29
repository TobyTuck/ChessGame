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

        // set possible movements by piece
        List potentialMoves = new List(30);
        potentialMoves.push(9, true);
        potentialMoves.push(18, true);
        potentialMoves.push(27, true);
        potentialMoves.push(36, true);
        potentialMoves.push(45, true);
        potentialMoves.push(54, true);
        potentialMoves.push(63, true);
        potentialMoves.push(-9, true);
        potentialMoves.push(-18, true);
        potentialMoves.push(-27, true);
        potentialMoves.push(-36, true);
        potentialMoves.push(-45, true);
        potentialMoves.push(-54, true);
        potentialMoves.push(-63, true);
        potentialMoves.push(7, true);
        potentialMoves.push(14, true);
        potentialMoves.push(21, true);
        potentialMoves.push(28, true);
        potentialMoves.push(35, true);
        potentialMoves.push(42, true);
        potentialMoves.push(49, true);
        potentialMoves.push(56, true);
        potentialMoves.push(-7, true);
        potentialMoves.push(-14, true);
        potentialMoves.push(-21, true);
        potentialMoves.push(-28, true);
        potentialMoves.push(-35, true);
        potentialMoves.push(-42, true);
        potentialMoves.push(-49, true);
        potentialMoves.push(-56, true);
        super.setMovement(potentialMoves);
    }

    public List removeOverflow(int myLocation, List chessboard){
        List potentialMoves = new List(5);
        List extensiveMoves = super.getMovement();
        boolean isDone = false;
        ChessPiece currentPiece = (ChessPiece) chessboard.getComponent(myLocation);
        ChessPiece movePiece = null;

        int move = myLocation + 9;
        if(move < 63)
            movePiece = (ChessPiece) chessboard.getComponent(move);
        while(!sameColor(currentPiece, movePiece) && !overflow(move + 1, move - 8, 8)
              && !isDone && move < 63){
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
        while(!sameColor(currentPiece, movePiece) && !overflow(move + 1, move - 6, 8)
              && !isDone && move < 63){
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
        while(!sameColor(currentPiece, movePiece) && !overflow(move + 1, move + 10, 8)
              && !isDone && move < 63){
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
        while(!sameColor(currentPiece, movePiece) && !overflow(move + 1, move + 7, 8)
              && !isDone && move < 63){
            potentialMoves.push(move, null);
            if(isOpponent(currentPiece, movePiece))
                isDone = true;
            move -= 7;
            if(move < 63)
                movePiece = (ChessPiece) chessboard.getComponent(move);}

        /* for(int index = 0; index < extensiveMoves.getSize(); ++index){
            move = myLocation + (int) extensiveMoves.pop(index);
            if(move < 64 && !sameColor((ChessPiece) chessboard.getComponent
              (myLocation), (ChessPiece) chessboard.getComponent(move)) && 
              (samePanelColor((JPanel) chessboard.pop(move), (JPanel)
              chessboard.pop(myLocation))))
                potentialMoves.push(move, null);} */

        return potentialMoves;
    }
}
