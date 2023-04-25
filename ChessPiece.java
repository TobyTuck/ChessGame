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
    public boolean isBlack(ChessPiece piece){
        if(piece instanceof BlackPawn || piece instanceof BlackRook || piece instanceof BlackKnight ||
           piece instanceof BlackBishop || piece instanceof BlackQueen || piece instanceof BlackKing)
            return true;
        return false;
    }

    /**
    Private helper method for the sameColor() function
    */
    public boolean isWhite(ChessPiece piece){
        if(piece instanceof WhitePawn || piece instanceof WhiteRook || piece instanceof WhiteKnight ||
           piece instanceof WhiteBishop || piece instanceof WhiteQueen || piece instanceof WhiteKing)
            return true;
        return false;
    }

    public boolean isKing(ChessPiece piece){
        if(piece instanceof BlackKing || piece instanceof WhiteKing)
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

    /* 
    Checks if a number lies on a particular row
    */
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
    Horizontal chess row 
    */
    protected boolean sameRow(int number1, int number2){
        if(number1 / 8 == number2 / 8)
            return true;

        return false;
    }

    /**
    Checks if two squares (identified by their number) would lie on the same vertical 
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
    Checks whethor or not the king chesspiece is in checkmate
    */
    public boolean checkMate(int kingLocation, List chessboard){
        BlackKing king = (BlackKing) (chessboard.getComponent(kingLocation));

        List kingMoves = king.possibleMoves(kingLocation, chessboard, true, 0, 0);
        kingMoves.push(kingLocation, null);

        List listHolder = new List(5);    // temp. holder for single opponent moves
        List opponentMoves = new List(5);
        List sameMoves = new List(5);     // moves which belong to both BK and opponent pieces


        // iterate through all chesspieces, and find opponent piece moves
        for(int index = 0; index < chessboard.getSize(); ++index){
            if(isOpponent((ChessPiece) chessboard.getComponent(index), new BlackKing())){
                listHolder = ((ChessPiece) (chessboard.getComponent(index))).
                                            possibleMoves(index, chessboard, false,
                                                          0, 0);
                // copy over moves from one piece to list repository of all opponent moves
                for(int i = 0; i < listHolder.getSize(); ++i){
                    opponentMoves.push(listHolder.pop(i), null);} } }
                
        // make a list of moves both king and opponent pieces may use 
        for(int index = 0; index < opponentMoves.getSize(); ++index){
            for(int i = 0; i < kingMoves.getSize(); ++i){ 
                if((int) (opponentMoves.pop(index)) == (int) (kingMoves.pop(i)))
                    sameMoves.push(kingMoves.pop(i), null);} }

        boolean checkmate = true;
        boolean equal = false;
        // check if all of the king's moves are blocked by opponent chesspieces 
        for(int index = 0; index < sameMoves.getSize(); ++index){
            for(int i = 0; i < kingMoves.getSize(); ++i){
                if((int) (sameMoves.pop(index)) == (int) (kingMoves.pop(i)))
                    equal = true;} 
            
            if(!equal)
                checkmate = false;}
        
        return checkmate;
    }

    /**
    Method that checks if a piece blocks check from occuring
    */
    public boolean blockCheck(int myLocation, int myMove, int kingLocation, List chessboard){
        // simulate move, and check if king is now in check
        // make a 'simulation' list
        List simulation = new List(63);

        // copy over chessboard into the simulation
        for(int index = 0; index < chessboard.getSize(); ++index){
            simulation.push((JPanel) chessboard.pop(index), 
                            (ChessPiece) chessboard.getComponent(index));}

        // perform simulation move
        simulation.addComponent((JPanel) chessboard.pop(myMove), 
                                (ChessPiece) chessboard.getComponent(myLocation));
        simulation.addComponent((JPanel) chessboard.pop(myLocation), null);

        // does king remain in check w/ in this simulation?
        ChessPiece myKing = (ChessPiece) simulation.getComponent(kingLocation);
        if(myKing instanceof BlackKing){
            BlackKing myBKing = (BlackKing) myKing;
            if(myBKing.check(kingLocation, simulation))
                return false;}

        else{
            WhiteKing myWKing = (WhiteKing) myKing;
            if(myWKing.check(kingLocation, simulation))
                return false;}

        return true;
    }

    /**
    Returns the available moves for the piece 
    Boolean Parameter: Used to differentiate between standard moves of the chesspiece
                       and moves which will determine whether the opponent king may
                       occupy a position w/o being in "check." 
                       For example, if an opponent king is placed in check by a rook,
                       that king may not move linear, as it is still in check. Under
                       normal circumstances, the rook would not be allowed to move to
                       the position behind an opponent, which would have allowed the 
                       opponent king to move linear, an illegal move.
    */
    public abstract List possibleMoves(int myLocation, List chessboard, boolean hold,
                                       int startLastMove, int endLastMove);
}
