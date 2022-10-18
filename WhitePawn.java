/**
This is a class that models a White Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class WhitePawn{

    private BufferedImage _image;

    public WhitePawn(){
        try{
            _image = ImageIO.read(new File("WPawn.png"));
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
