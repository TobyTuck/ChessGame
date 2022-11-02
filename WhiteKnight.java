/**
This is a class that models a White Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteKnight extends ChessPiece{

    public WhiteKnight(){
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File
            ("WhiteKnight.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Knight image file");}
    }
}
