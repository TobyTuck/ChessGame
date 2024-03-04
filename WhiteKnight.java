/**
This is a class that models a White Knight chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteKnight extends ChessPiece{

    List _moves;

    public WhiteKnight(BufferedImage image){
        _moves = new List(5);

        // set file image
        super.setImage(image);
    }

    public List possibleMoves(int myLocation, List chessboard, boolean routineCheck,
                              int doNothing1, int doNothing2){
        // remove any moves from prior location
        _moves.removeAll();

        ChessPiece movePiece;
        int move;
        
        if(routineCheck){
            // search board for chess king
            WhiteKing wKing = (WhiteKing) getKing(chessboard, this);
            int kingLocation = getKingLocation(chessboard, this);
            boolean inCheck = wKing.check(kingLocation, chessboard);

            // 'reverse L' move
            move = myLocation - 17;
            if(move >= 0){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) - 2) && !isWhite(movePiece) && 
                   (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);}

            // 'L' move
            move = myLocation - 15;
            if(move >= 0){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) - 2) && !isWhite(movePiece) && 
                   (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);}

            // 'r' move
            move = myLocation - 6;
            if(move >= 0){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) - 1) && !isWhite(movePiece) && 
                   (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);}

            // 'reverse r' move
            move = myLocation - 10;
            if(move >= 0){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) - 1) && !isWhite(movePiece) && 
                   (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);}

            // 'upside down r' move
            move = myLocation + 6;
            if(move <= 63){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) + 1) && !isWhite(movePiece) && 
                   (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);}

            // 'upside down reverse r' move
            move = myLocation + 10;
            if(move <= 63){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) + 1) && !isWhite(movePiece) && 
                   (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);}
       
            // 'upside down reverse L' move
            move = myLocation + 17;
            if(move <= 63){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) + 2) && !isWhite(movePiece) && 
                   (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);}

            // 'upside down L' move
            move = myLocation + 15;
            if(move <= 63){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) + 2) && !isWhite(movePiece) && 
                   (!inCheck || removeCheck(myLocation, move, chessboard)))
                    _moves.push(move, null);} }

        else{
            // 'reverse L' move
            move = myLocation - 17;
            if(move >= 0){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) - 2) && !isWhite(movePiece))
                    _moves.push(move, null);}

            // 'L' move
            move = myLocation - 15;
            if(move >= 0){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) - 2) && !isWhite(movePiece))
                    _moves.push(move, null);}

            // 'r' move
            move = myLocation - 6;
            if(move >= 0){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) - 1) && !isWhite(movePiece))
                    _moves.push(move, null);}

            // 'reverse r' move
            move = myLocation - 10;
            if(move >= 0){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) - 1) && !isWhite(movePiece))
                    _moves.push(move, null);}

            // 'upside down r' move
            move = myLocation + 6;
            if(move <= 63){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) + 1) && !isWhite(movePiece))
                    _moves.push(move, null);}

            // 'upside down reverse r' move
            move = myLocation + 10;
            if(move <= 63){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) + 1) && !isWhite(movePiece))
                    _moves.push(move, null);}
       
            // 'upside down reverse L' move
            move = myLocation + 17;
            if(move <= 63){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) + 2) && !isWhite(movePiece))
                    _moves.push(move, null);}

            // 'upside down L' move
            move = myLocation + 15;
            if(move <= 63){
                movePiece = (ChessPiece) chessboard.getComponent(move);
                if((move / 8 == (myLocation / 8) + 2) && !isWhite(movePiece))
                    _moves.push(move, null);} }

        return _moves;
    }
}
