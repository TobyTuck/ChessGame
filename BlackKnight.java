/**
This is a class that models a Black Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackKnight{

    private BufferedImage _image;

    public BlackKnight(){
        try{
            _image = ImageIO.read(new File("BlackKnight.png"));
        }catch(IOException exception){
            System.out.println("Error locating file");
        }
    }

    /**
    Method to return the image associated with a Black Knight
    */
    public BufferedImage getImage(){
        return _image;
    }
}
