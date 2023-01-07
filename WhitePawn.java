/**
This is a class that models a White Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhitePawn extends ChessPiece{

    private List _possibleMoves;

    public WhitePawn(){
        _possibleMoves = new List(5);

        // set image file
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhitePawn.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Pawn image file");}
     }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck){
        // remove moves from previous locations
        _possibleMoves.removeAll();

        int move;

        if(considerCheck){
            move = myLocation - 7;
            if((move / 8 == (myLocation / 8) - 1) && move < 64 && 
               isOpponent((ChessPiece) chessboard.getComponent(move), 
               (ChessPiece) chessboard.getComponent(myLocation)))
                _possibleMoves.push(move, null);

            move = myLocation - 8;
            if((move / 8 == (myLocation / 8) - 1) && move < 64 && 
               chessboard.getComponent(move) == null)
                _possibleMoves.push(move, null);

            move = myLocation - 9;
            if((move / 8 == (myLocation / 8) - 1) && move < 64 && 
               isOpponent((ChessPiece) chessboard.getComponent(move), 
               (ChessPiece) chessboard.getComponent(myLocation)))
                _possibleMoves.push(move, null);

            move = myLocation - 16;
            if(myLocation / 8 == 6 && chessboard.getComponent(move) == null && 
               chessboard.getComponent(move + 8) == null)
                _possibleMoves.push(move, null);}

        else{
            move = myLocation - 7;
            if((move / 8 == (myLocation / 8) - 1) && move < 64)
                _possibleMoves.push(move, null);

            move = myLocation - 9;
            if((move / 8 == (myLocation / 8) - 1) && move < 64)
                _possibleMoves.push(move, null);}

        return _possibleMoves;
    }
}
