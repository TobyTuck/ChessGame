/**
This is a class that models a Black Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackKnight extends ChessPiece{

    public BlackKnight(){
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File
            ("BlackKnight.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Knight image file");}
    }
}
