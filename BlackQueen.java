/**
This is a class that models a Black Queen chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackQueen{
    
    private BufferedImage _image;

    public BlackQueen(){
        try{
            _image = ImageIO.read(new File("BlackQueen.png"));
        }catch(IOException exception){
            System.out.println("Error locating file");
        }
    }

    /**
    Method to return the image associated w/ a Black Queen chesspiece
    */
    public BufferedImage getImage(){
        return _image;
    }
}
