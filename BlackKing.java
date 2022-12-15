/**
This is a class that models a Black King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackKing extends ChessPiece{

    public BlackKing(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackKing.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black King image file");}
    }

    public List possibleMoves(int myLocation, List chessboard){
        List potentialMoves = new List(5);
        int move;

        // '\^ left up diagonal' move
        move = myLocation - 9;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 1))
            potentialMoves.push(move, null);

        // '|^ straight up vertical' move
        move = myLocation - 8;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 1))
            potentialMoves.push(move, null);

        // '/^ right up diagonal' move
        move = myLocation - 7;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 1))
            potentialMoves.push(move, null);

        // '<- left horizontal' move
        move = myLocation - 1;
        if(move >= 0 && (move / 8 == myLocation / 8))
            potentialMoves.push(move, null);

        // '-> right horizontal' move
        move = myLocation + 1;
        if(move < 63 && (move / 8 == myLocation / 8))
            potentialMoves.push(move, null);

        // '\v left down diagonal' move
        move = myLocation + 9;
        if(move < 63 && (move / 8 == (myLocation / 8) + 1))
            potentialMoves.push(move, null);

        // '|v straight down vertical' move
        move = myLocation + 8;
        if(move < 63 && (move / 8 == (myLocation / 8) + 1))
            potentialMoves.push(move, null);

        // '/v right down diagonal' move
        move = myLocation + 7;
        if(move < 63 && (move / 8 == (myLocation / 8) + 1))
            potentialMoves.push(move, null);

        return potentialMoves;
    }
}

