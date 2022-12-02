/**
This is a class that models a White Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhitePawn extends ChessPiece{

    public WhitePawn(){
        // set image file
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhitePawn.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Pawn image file");}

        // set potential moves
        List potentialMoves = new List(1);
        potentialMoves.push(-7, true);
        potentialMoves.push(-8, true);
        potentialMoves.push(-9, true);
        potentialMoves.push(-16, true);
        super.setMovement(potentialMoves);
     }

    public List removeOverflow(int myLocation, List chessboard){
        List potentialMoves = new List(5);
        int move;

        move = myLocation - 7;
        if((move / 8 == (myLocation / 8) - 1) && move < 64 && 
           isOpponent((ChessPiece) chessboard.getComponent(move), 
           (ChessPiece) chessboard.getComponent(myLocation)))
            potentialMoves.push(move, null);

        move = myLocation - 8;
        if((move / 8 == (myLocation / 8) - 1) && move > -1 && 
           chessboard.getComponent(move) == null)
            potentialMoves.push(move, null);

        move = myLocation - 9;
        if((move / 8 == (myLocation / 8) - 1) && move > -1 && 
           isOpponent((ChessPiece) chessboard.getComponent(move), 
           (ChessPiece) chessboard.getComponent(myLocation)))
            potentialMoves.push(move, null);

        move = myLocation - 16;
        if(myLocation / 8 == 6 && chessboard.getComponent(move) == null &&
           chessboard.getComponent(move + 8) == null)
            potentialMoves.push(move, null);

        return potentialMoves;
    }
}
