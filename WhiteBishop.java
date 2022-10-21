/**
This is a class that models a White Bishop chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class WhiteBishop{

    private BufferedImage _image;

    public WhiteBishop(){
        try{
            _image = ImageIO.read(new File("WhiteBishop.png"));
        }catch(IOException exception){
            System.out.println("Error locating file");
        }
    }

    /**
    Method to return the image associated w/ a White Bishop chesspiece
    */
    public BufferedImage getImage(){
        return _image;
    }
}
