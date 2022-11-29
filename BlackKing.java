/**
This is a class that models a Black King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackKing extends ChessPiece{

    public BlackKing(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackKing.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black King image file");}

        // set possible movements by this piece
        List potentialMoves = new List(8);
        potentialMoves.push(1, true);
        potentialMoves.push(9, true);
        potentialMoves.push(8, true);
        potentialMoves.push(7, true);
        potentialMoves.push(-1, true);
        potentialMoves.push(-9, true);
        potentialMoves.push(-8, true);
        potentialMoves.push(-7, true);
        super.setMovement(potentialMoves);
    }

    public List removeOverflow(int myLocation, List chessboard){
        List potentialMoves = new List(5);
        List extensiveMoves = super.getMovement();
        int move;

        for(int index = 0; index < extensiveMoves.getSize(); ++index){
            move = myLocation + (int) extensiveMoves.pop(index);
            if(move < 64 && !sameColor((ChessPiece) chessboard.getComponent(myLocation), 
               (ChessPiece) chessboard.getComponent(move)))
                potentialMoves.push(move, null);}

        return potentialMoves;
    }
}

