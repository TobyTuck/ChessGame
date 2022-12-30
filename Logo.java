/**
This is a class that models the logo of the program
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class Logo{
    
    private BufferedImage _image;

    public Logo(){
        // set image file
        try{
            // open image file
            _image = ImageIO.read(new File("Logo.png"));
        }catch(IOException exception){
            System.out.println("Error locating Logo image file");}
    }

    /**
    Used to return the image property of a chesspiece
    */
    public BufferedImage getImage(){
        return _image;
    }
}
