/**
This is a class that models the White Queen chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteQueen extends ChessPiece{

    public WhiteQueen(){
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File
            ("WhiteQueen.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Queen image file");}
    }
}
