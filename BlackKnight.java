/**
This is a class that models a Black Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackKnight extends ChessPiece{

    public BlackKnight(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackKnight.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Knight image file");}

        // set possible movements by this piece
        List potentialMoves = new List(4);
        potentialMoves.push(10, true);
        potentialMoves.push(17, true);
        potentialMoves.push(15, true);
        potentialMoves.push(6, true);
        potentialMoves.push(-10, true);
        potentialMoves.push(-17, true);
        potentialMoves.push(-15, true);
        potentialMoves.push(-6, true);
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
