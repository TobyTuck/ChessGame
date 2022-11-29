/**
This is a class that models a White King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteKing extends ChessPiece{

    public WhiteKing(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteKing.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating the White King image file");}

        // set possible movements of this piece
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

            if(move < 64 && !sameColor((ChessPiece) chessboard.getComponent
              (myLocation), (ChessPiece) chessboard.getComponent(move)) && 
              !((baseOf(myLocation, 8, 8) && baseOf(move, 7, 8)) || 
              (baseOf(myLocation, 7, 8) && baseOf(move, 8, 8))) && 
              !((myLocation == 0 && move == 7) || (myLocation == 7 && move == 0)))
                potentialMoves.push(move, null);}

        return potentialMoves;
    }
}

