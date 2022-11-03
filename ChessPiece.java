/**
This is a class that will provide a general 
template for all types of Chess-pieces
*/

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class ChessPiece{

    private BufferedImage _image; 

    /**
    Method used to scale the image
    */
    public void scaleImage(double rate){
        int width = _image.getWidth();
        int height = _image.getHeight();

        BufferedImage scaled = 
            new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(rate, rate);
        AffineTransformOp scaleOp = new AffineTransformOp
            (at, AffineTransformOp.TYPE_BILINEAR);
        scaled = scaleOp.filter(_image, scaled);

        _image = scaled;
    }

    /** 
    Method to convert BufferedImage into an ImageIcon
    */
    public ImageIcon toImageIcon(){
        ImageIcon image = new ImageIcon(_image);
        return image;
    }

    /**
    Mutator method for the image field
    */
    public void setImage(BufferedImage image){
        _image = image;
    }

    /**
    Used to return the image property of a chesspiece
    */
    public BufferedImage getImage(){
        return _image;
    }

    /**
    Method that will return the height of the image
    */
    public int getHeight(){
        return _image.getHeight();
    }
}
