/**
This is a class that models a Black Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackPawn{
    
    private BufferedImage _image;

    public BlackPawn(){
        try{
            _image = ImageIO.read(new File("BPawn.png"));
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
