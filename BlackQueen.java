/**
This is a class that models a Black Queen chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackQueen extends ChessPiece{

    public BlackQueen(){
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File
            ("BlackQueen.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Queen image file");}
    }
}
