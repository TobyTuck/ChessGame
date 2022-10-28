/**
This is a class that models a Black Bishop chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackBishop{

    private BufferedImage _image;

    public BlackBishop(){
        try{
            _image = ImageIO.read(new File("BlackBishop.png"));
        }catch(IOException exception){
            System.out.println("Error locating file");
        }
    }

    public BufferedImage getImage(){
        return _image;
    }
}
