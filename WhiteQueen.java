/**
This is a class that models the White Queen chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class WhiteQueen{

    private BufferedImage _image;

    public WhiteQueen(){
        try{
            _image = ImageIO.read(new File("WhiteQueen.png"));
        }catch(IOException exception){
            System.out.println("Error locating file");
        }
    }

    /**
    Method to return the image associated w/ a White Queen chesspiece
    */
    public BufferedImage getImage(){
        return _image;
    }
}
