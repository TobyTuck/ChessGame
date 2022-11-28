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
    private List _movement;

    /**
    Method used to scale the image to a new width and height as specified in the 
    parameters
    */
    public void scaleImage(int newWidth, int newHeight){

        // Make sure the aspect ratio is maintained, so the image is not distorted
        double thumbRatio = (double) newWidth / (double) newHeight;
        int imageWidth = _image.getWidth();
        int imageHeight = _image.getHeight();
        double aspectRatio = (double) imageWidth / (double) imageHeight;

        // Draw the scaled image
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, 
                                 BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(_image, 0, 0, newWidth, newHeight, null);

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
    Used for subclass to set desired image based on type of ChessPiece
    */
    protected void setImage(BufferedImage image){
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

    /**
    Method that will return the movement of the chesspiece
    */
    public List getMovement(){
        return _movement;
    }

    protected void setMovement(List movement){
        _movement = movement;
    }

    public int getMovementSize(){
        return _movement.getSize();
    }
}
