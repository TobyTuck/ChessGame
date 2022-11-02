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
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File
            ("BlackPawn.png"));
           
            /* int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(1.5, 1.5);
            AffineTransformOp scaleOp =
                new AffineTransformOp
                (at, AffineTransformOp.TYPE_BILINEAR);
            after = scaleOp.filter(image, after); */

            // pass image to parent class 
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Pawn image file");}
    }
}
