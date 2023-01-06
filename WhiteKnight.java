/**
This is a class that models a White Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteKnight extends ChessPiece{

    List _possibleMoves;

    public WhiteKnight(){
        _possibleMoves = new List(5);

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteKnight.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Knight image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean notApplicable){
        // remove any moves from prior location
        _possibleMoves.removeAll();

        ChessPiece movePiece;
        int move;

        // 'reverse L' move
        move = myLocation - 17;
        if(move >= 0){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) - 2) && !isWhite(movePiece))
                _possibleMoves.push(move, null);}

        // 'L' move
        move = myLocation - 15;
        if(move >= 0){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) - 2) && !isWhite(movePiece))
                _possibleMoves.push(move, null);}

        // 'r' move
        move = myLocation - 6;
        if(move >= 0){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) - 1) && !isWhite(movePiece))
                _possibleMoves.push(move, null);}

        // 'reverse r' move
        move = myLocation - 10;
        if(move >= 0){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) - 1) && !isWhite(movePiece))
                _possibleMoves.push(move, null);}

        // 'upside down r' move
        move = myLocation + 6;
        if(move < 63){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) + 1) && !isWhite(movePiece))
                _possibleMoves.push(move, null);}

        // 'upside down reverse r' move
        move = myLocation + 10;
        if(move < 63){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) + 1) && !isWhite(movePiece))
                _possibleMoves.push(move, null);}
       
        // 'upside down reverse L' move
        move = myLocation + 17;
        if(move < 63){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) + 2) && !isWhite(movePiece))
                _possibleMoves.push(move, null);}

        // 'upside down L' move
        move = myLocation + 15;
        if(move < 63){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) + 2) && !isWhite(movePiece))
                _possibleMoves.push(move, null);}

        return _possibleMoves;
    }
}
