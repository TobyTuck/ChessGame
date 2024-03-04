/**
This is a class that models a Black King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackKing extends ChessPiece{

    private List _possibleMoves;
    private boolean _beenMoved;

    public BlackKing(BufferedImage image){
        _possibleMoves = new List(5);
        _beenMoved = false;

        // set file image
        super.setImage(image);
    }

    public List possibleMoves(int location, List chessboard, boolean considerCheck,
                              int doNothing1, int doNothing2){
        // remove any other former moves from Black King chesspiece
        _possibleMoves.removeAll();

        int move;

        if(considerCheck){
            // '\^ left up diagonal' move
            move = location - 9;
            if(move >= 0 && (move / 8 == (location / 8) - 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this) && 
               removeCheck(location, move, chessboard))
                _possibleMoves.push(move, null);

            // '|^ straight up vertical' move
            move = location - 8;
            if(move >= 0 && (move / 8 == (location / 8) - 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this) && 
               removeCheck(location, move, chessboard))
                _possibleMoves.push(move, null);

            // '/^ right up diagonal' move
            move = location - 7;
            if(move >= 0 && (move / 8 == (location / 8) - 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this) && 
               removeCheck(location, move, chessboard))
                _possibleMoves.push(move, null);

            // '<- left horizontal' move
            move = location - 1;
            if(move >= 0 && (move / 8 == location / 8) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this) && 
               removeCheck(location, move, chessboard))
                _possibleMoves.push(move, null);

            // '-> right horizontal' move
            move = location + 1;
            if(move <= 63 && (move / 8 == location / 8) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this) && 
               removeCheck(location, move, chessboard))
                _possibleMoves.push(move, null);

            // '\v left down diagonal' move
            move = location + 9;
            if(move <= 63 && (move / 8 == (location / 8) + 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this) && 
               removeCheck(location, move, chessboard))
                _possibleMoves.push(move, null);

            // '|v straight down vertical' move
            move = location + 8;
            if(move <= 63 && (move / 8 == (location / 8) + 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this) && 
               removeCheck(location, move, chessboard))
                _possibleMoves.push(move, null);

            // '/v right down diagonal' move
            move = location + 7;
            if(move <= 63 && (move / 8 == (location / 8) + 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this) &&
               removeCheck(location, move, chessboard))
                _possibleMoves.push(move, null);

            // castle moves- check left rook
            // NOTE: king can't castle to escape check
            move = 0;
            ChessPiece myRook1 = (ChessPiece) chessboard.getComponent(move);
            if(!hasMoved() && chessboard.getComponent(1) == null && chessboard.getComponent(2) == 
               null && chessboard.getComponent(3) == null && myRook1 instanceof BlackRook &&
               !check(location, chessboard)){
                BlackRook rook = (BlackRook) myRook1;
                if(!rook.hasMoved())
                    _possibleMoves.push(move, null);}
            
            // castle moves- check right rook
            move = 7; 
            ChessPiece myRook2 = (ChessPiece) chessboard.getComponent(move);
            if(!hasMoved() && chessboard.getComponent(5) == null && chessboard.getComponent(6) == 
               null && myRook2 instanceof BlackRook && !check(location, chessboard)){ 
                BlackRook rook = (BlackRook) myRook2;
                if(!rook.hasMoved())
                    _possibleMoves.push(move, null);} }

        else{
            // '\^ left up diagonal' move
            move = location - 9;
            if(move >= 0 && (move / 8 == (location / 8) - 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '|^ straight up vertical' move
            move = location - 8;
            if(move >= 0 && (move / 8 == (location / 8) - 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '/^ right up diagonal' move
            move = location - 7;
            if(move >= 0 && (move / 8 == (location / 8) - 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '<- left horizontal' move
            move = location - 1;
            if(move >= 0 && (move / 8 == location / 8) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '-> right horizontal' move
            move = location + 1;
            if(move <= 63 && (move / 8 == location / 8) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '\v left down diagonal' move
            move = location + 9;
            if(move <= 63 && (move / 8 == (location / 8) + 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '|v straight down vertical' move
            move = location + 8;
            if(move <= 63 && (move / 8 == (location / 8) + 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '/v right down diagonal' move
            move = location + 7;
            if(move <= 63 && (move / 8 == (location / 8) + 1) && 
               !sameColor((ChessPiece) (chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // castling is not considered when placing a king in check
        }

        return _possibleMoves;
    }

    /**
    Method that checks if a potential move by the king places him in check
    NOTE: inputing the king's location will check if the piece is currently in check
    */
    public boolean check(int move, List chessboard){
        chessboard = chessboard;
        List opponentMoves;
        ChessPiece opponent;

        // sort through opponent chesspieces from all possible chess squares 
        for(int index = 0; index < chessboard.getSize(); ++index){
            opponent = (ChessPiece) (chessboard.getComponent(index));
            if(isOpponent(opponent, this)){
                // get moves of opponent chesspiece
                opponentMoves = opponent.possibleMoves(index, chessboard, false, 0, 0);

                // check if any opponent moves are the same as king piece move
                for(int i = 0; i < opponentMoves.getSize(); ++i){
                    if((int) opponentMoves.pop(i) == move)
                        return true;} } }

        return false;
    }

    /**
    Method that sets the 'beenMoved' field to true
    Should be called after a king chess piece is initially moved
    */
    public void beenMoved(){
        _beenMoved = true;
    }

    /**
    Method that returns the 'beenMoved' field
    Indicates whether or not a king has been moved
    */
    public boolean hasMoved(){
        return _beenMoved;
    }
}
