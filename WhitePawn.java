/**
This is a class that models a White Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhitePawn extends ChessPiece{

    public WhitePawn(){
        // set image file
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhitePawn.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Pawn image file");}

        // set potential moves
        List potentialMoves = new List(1);
        potentialMoves.push(7, true);
        potentialMoves.push(8, true);
        potentialMoves.push(9, true);
        super.setMovement(potentialMoves);
     }
}
