/**
This is a class that models a White Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteKnight extends ChessPiece{

    public WhiteKnight(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteKnight.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Knight image file");}

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
}
