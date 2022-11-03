/**
This is a class that will provide a general 
template for all types of Chess-pieces
*/

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class ChessPiece{

    private BufferedImage _image; 

    /**
    Method used to scale the image
    */
    public void scaleImage(int rate){
        /* int width = _image.getWidth();
        int height = _image.getHeight();

        BufferedImage scaled = 
            new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(rate, rate);
        AffineTransformOp scaleOp = new AffineTransformOp
            (at, AffineTransformOp.TYPE_BILINEAR);
        scaled = scaleOp.filter(_image, scaled);

        _image = scaled; */

        // Make sure the aspect ratio is maintained, so the image is not distorted
        int newHeight,
            newWidth;

        double thumbRatio = (double) rate / (double) rate;
        int imageWidth = _image.getWidth(null);
        int imageHeight = _image.getHeight(null);
        double aspectRatio = (double) imageWidth / (double) imageHeight;

        if (thumbRatio < aspectRatio) {
            newHeight = (int) (rate / aspectRatio);
        } else {
            newWidth = (int) (rate * aspectRatio);
        }

        // Draw the scaled image
        BufferedImage newImage = new BufferedImage(rate, rate, 
                                 BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(_image, 0, 0, rate, rate, null);

        _image =  newImage;

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

    /**
    Method that will return the width of the image
    */
    public int getWidth(){
        return _image.getWidth();
    }
}
