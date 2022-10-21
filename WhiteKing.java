/**
This is a class that models a White King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class WhiteKing{

    private BufferedImage _image;

    public WhiteKing(){
        try{
            _image = ImageIO.read(new File("WhiteKing.png"));
        }catch(IOException exception){
            System.out.println("Error locating file");
        }
    }

    /**
    Method to return the image associated w/ a White King chesspiece
    */
    public BufferedImage getImage(){
        return _image;
    }
}
