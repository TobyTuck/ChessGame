/**
This is a class that models a Black Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class BlackPawn extends ChessPiece{
    
    public BlackPawn(){
        // set image file
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackPawn.png"));
           
            // pass image to parent class 
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Pawn image file");}

        // set possible movements
        List potentialMoves = new List(1);
        potentialMoves.push(7, true);
        potentialMoves.push(8, true);
        potentialMoves.push(9, true);
        super.setMovement(potentialMoves);
    }
}
