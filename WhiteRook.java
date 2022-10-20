/**
This is a class that models a White Rook chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class WhiteRook{

    private BufferedImage _image;

    public WhiteRook(){
        try{
            _image = ImageIO.read(new File("WhiteRook.png"));
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
