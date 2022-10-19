/**
This is a class that models a Black Rook chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackRook{

    private BufferedImage _image;

    public BlackRook(){
        try{
            _image = ImageIO.read(new File("BlackRook.png"));
        }catch(IOException exception){
            System.out.println("Error locating file");
        }
    }

    /**
    Method to return the image
    */
    public BufferedImage getImage(){
        return _image;
    }
}
