/**
This is a class that models a Black Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class BlackPawn extends ChessPiece{
    
    List _possibleMoves;

    public BlackPawn(){
        _possibleMoves = new List(5);
        
        // set image file
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackPawn.png"));
           
            // pass image to parent class 
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Pawn image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck){
        // remove moves from previous locations
        _possibleMoves.removeAll();

        int move;

        if(considerCheck){
            move = myLocation + 7;
            if((move / 8 == (myLocation / 8) + 1) && move < 64 && 
               isOpponent((ChessPiece) chessboard.getComponent(move), 
               (ChessPiece) chessboard.getComponent(myLocation)))
                _possibleMoves.push(move, null);

            move = myLocation + 8;
            if((move / 8 == (myLocation / 8) + 1) && move < 64 && 
               chessboard.getComponent(move) == null)
                _possibleMoves.push(move, null);

            move = myLocation + 9;
            if((move / 8 == (myLocation / 8) + 1) && move < 64 && 
               isOpponent((ChessPiece) chessboard.getComponent(move), 
               (ChessPiece) chessboard.getComponent(myLocation)))
                _possibleMoves.push(move, null);

            move = myLocation + 16;
            if(myLocation / 8 == 1 && chessboard.getComponent(move) == null && 
               chessboard.getComponent(move - 8) == null)
                _possibleMoves.push(move, null);}

        else{
            move = myLocation + 7;
            if((move / 8 == (myLocation / 8) + 1) && move < 64)
                _possibleMoves.push(move, null);

            move = myLocation + 9;
            if((move / 8 == (myLocation / 8) + 1) && move < 64)
                _possibleMoves.push(move, null);}

        return _possibleMoves;
    }
}
