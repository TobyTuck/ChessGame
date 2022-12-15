/**
This is a class that models a White Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteKnight extends ChessPiece{

    public WhiteKnight(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteKnight.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Knight image file");}
    }

    public List possibleMoves(int myLocation, List chessboard){
        List potentialMoves = new List(5);
        int move;

        // 'reverse L' move
        move = myLocation - 17;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 2))
            potentialMoves.push(move, null);

        // 'L' move
        move = myLocation - 15;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 2))
            potentialMoves.push(move, null);

        // 'r' move
        move = myLocation - 6;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 1))
            potentialMoves.push(move, null);

        // 'reverse r' move
        move = myLocation - 10;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 1))
            potentialMoves.push(move, null);

        // 'upside down r' move
        move = myLocation + 6;
        if(move < 63 && (move / 8 == (myLocation / 8) + 1))
            potentialMoves.push(move, null);

        // 'upside down reverse r' move
        move = myLocation + 10;
        if(move < 63 && (move / 8 == (myLocation / 8) + 1))
            potentialMoves.push(move, null);

        // 'upside down reverse L' move
        move = myLocation + 17;
        if(move < 63 && (move / 8 == (myLocation / 8) + 2))
            potentialMoves.push(move, null);

        // 'upside down L' move
        move = myLocation + 15;
        if(move < 63 && (move / 8 == (myLocation / 8) + 2))
            potentialMoves.push(move, null);

        return potentialMoves;
    }
}
