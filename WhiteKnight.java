/**
This is a class that models a White Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class WhiteKnight{

    private BufferedImage _image;

    public WhiteKnight(){
        try{
            _image = ImageIO.read(new File("WhiteKnight.png"));
        }catch(IOException exception){
            System.out.println("Error locating file");
        }
    }

    /**
    Method to return the image associated with a White Knight
    */
    public BufferedImage getImage(){
        return _image;
    }
}
