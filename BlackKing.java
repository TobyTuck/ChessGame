/**
This is a class that models a Black King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackKing{

    private BufferedImage _image;

    public BlackKing(){
        try{
            _image = ImageIO.read(new File("BlackKing.png"));
        }catch(IOException exception){
            System.out.println("Error locating file");
        }
    }

    /**
    Method to return the image associated w/ a Black King chesspiece
    */
    public BufferedImage getImage(){
        return _image;
    }
}
