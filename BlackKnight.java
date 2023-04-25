/**
This is a class that models a Black Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BlackKnight extends ChessPiece{

    private List _possibleMoves;

    public BlackKnight(){
        _possibleMoves = new List(5);

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackKnight.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Knight image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean notApplicable,
                              int doNothing1, int doNothing2){
        // remove moves from previous locations
        _possibleMoves.removeAll();

        int move;
        ChessPiece movePiece;

        // search board for chess king
        ChessPiece piece;
        BlackKing bKing = null;
        int kingLocation = 0;
        for(int index = 0; index < chessboard.getSize(); ++index){
            piece = (ChessPiece) chessboard.getComponent(index);
            if(piece instanceof BlackKing){
                kingLocation = index;
                bKing = (BlackKing) piece;} }

        // 'reverse L' move
        move = myLocation - 17;
        if(move >= 0){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) - 2) && !isBlack(movePiece) &&
               (!bKing.check(kingLocation, chessboard) || move == bKing.checkLocation(chessboard) ||
                blockCheck(myLocation, move, kingLocation, chessboard)))
                _possibleMoves.push(move, null);}

        // 'L' move
        move = myLocation - 15;
        if(move >= 0){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) - 2) && !isBlack(movePiece) &&
               (!bKing.check(kingLocation, chessboard) || move == bKing.checkLocation(chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard)))
                _possibleMoves.push(move, null);}

        // 'r' move
        move = myLocation - 6;
        if(move >= 0){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) - 1) && !isBlack(movePiece) &&
               (!bKing.check(kingLocation, chessboard) || move == bKing.checkLocation(chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard)))
                _possibleMoves.push(move, null);}

        // 'reverse r' move
        move = myLocation - 10;
        if(move >= 0){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) - 1) && !isBlack(movePiece) &&
               (!bKing.check(kingLocation, chessboard) || move == bKing.checkLocation(chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard)))
                _possibleMoves.push(move, null);}

        // 'upside down r' move
        move = myLocation + 6;
        if(move <= 63){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) + 1) && !isBlack(movePiece) &&
               (!bKing.check(kingLocation, chessboard) || move == bKing.checkLocation(chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard)))
                _possibleMoves.push(move, null);}

        // 'upside down reverse r' move
        move = myLocation + 10;
        if(move <= 63){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) + 1) && !isBlack(movePiece) &&
               (!bKing.check(kingLocation, chessboard) || move == bKing.checkLocation(chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard)))
                _possibleMoves.push(move, null);}

        // 'upside down reverse L' move
        move = myLocation + 17;
        if(move <= 63){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) + 2) && !isBlack(movePiece) &&
               (!bKing.check(kingLocation, chessboard) || move == bKing.checkLocation(chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard)))
                _possibleMoves.push(move, null);}

        // 'upside down L' move
        move = myLocation + 15;
        if(move <= 63){
            movePiece = (ChessPiece) chessboard.getComponent(move);
            if((move / 8 == (myLocation / 8) + 2) && !isBlack(movePiece) &&
               (!bKing.check(kingLocation, chessboard) || move == bKing.checkLocation(chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard)))
                _possibleMoves.push(move, null);}

        return _possibleMoves;
    }
}
