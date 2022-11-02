/**
This is a class that models a White Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhitePawn extends ChessPiece{

    public WhitePawn(){
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File
            ("WhitePawn.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Pawn image file");}
     }
}
