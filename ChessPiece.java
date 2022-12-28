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
import javax.swing.JPanel;

public abstract class ChessPiece{

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

    /**
    Method that tests 2 ChessPieces to see if they are the same color
    */
    protected boolean sameColor(ChessPiece piece1, ChessPiece piece2){
        // special cases- one of the pieces is null, while the other is not; or both are null
        if((piece1 == null && piece2 != null) || (piece1 != null && piece2 == null) || 
           (piece1 == null && piece2 == null))
            return false;

        // 2 possibilities- piece1 belongs to black color, or white
        if(isBlack(piece1)){ 
            // piece can be black
            if(isBlack(piece2))
                return true;

            // or white
            else{
                return false;} }

        // piece is white
        else{
            // piece can be white
            if(isWhite(piece2))
                return true;

            // or white
            else{
                return false;} }
    }

    /**
    Private helper method for the sameColor() function
    */
    private boolean isBlack(ChessPiece piece){
        if(piece instanceof BlackPawn || piece instanceof BlackRook || piece instanceof BlackKnight ||
           piece instanceof BlackBishop || piece instanceof BlackQueen || piece instanceof BlackKing)
            return true;
        return false;
    }

    /**
    Private helper method for the sameColor() function
    */
    private boolean isWhite(ChessPiece piece){
        if(piece instanceof WhitePawn || piece instanceof WhiteRook || piece instanceof WhiteKnight ||
           piece instanceof WhiteBishop || piece instanceof WhiteQueen || piece instanceof WhiteKing)
            return true;
        return false;
    }

    /**
    Method that checks if an oppoent exists in a square
    */
    protected boolean isOpponent(ChessPiece piece1, ChessPiece piece2){
        // special case
        if(piece1 == null || piece2 == null)
            return false;

        // 2 options- piece1 is black
        else if(isBlack(piece1)){
            if(isBlack(piece2))
                return false;
            else{
                return true;} }

        // or white
        else{
            if(isWhite(piece2))
                return false;
            else{
                return true;} }
    }

    protected boolean rowOf(int number, int base){
        while(number > 8){
            number -= 8;}

        if(number == base)
            return true;

        return false;
    }

    protected boolean samePanelColor(JPanel panel1, JPanel panel2){
        if(panel1.getBackground() == panel2.getBackground())
            return true;

        return false;
    }

    /**
    Checks if two squares (identified by their number) would lie on the same 
    Chess row 
    */
    protected boolean sameRow(int number1, int number2){
        if(number1 / 8 == number2 / 8)
            return true;

        return false;
    }

    /**
    Checks if two squares (identified by their number) would lie on the same 
    Chess column
    */
    protected boolean sameColumn(int number1, int number2){
        while(number1 > 8){
            number1 -= 8;}

        while(number2 > 8){
            number2 -= 8;}

        if(number1 == number2)
            return true;

        return false;
    }

    /**
    Returns the available moves for the piece 
    */
    public abstract List possibleMoves(int myLocation, List chessboard);
}
