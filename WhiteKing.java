/**
This is a class that models a White King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteKing extends ChessPiece{

    public WhiteKing(){
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File
            ("WhiteKing.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating the White King image file");}
    }
}
