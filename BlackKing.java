/**
This is a class that models a Black King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BlackKing extends ChessPiece{

    private List _possibleMoves;
    private List _chessboard;
    private int _location;

    public BlackKing(){
        _possibleMoves = new List(5);
        _chessboard = null;
        _location = 0;

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackKing.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black King image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck){
        // remove any other former moves from Black King chesspiece
        _possibleMoves.removeAll();

        // set fields
        _chessboard = chessboard;
        _location = myLocation;

        int move;
        ChessPiece myPiece = (ChessPiece) (_chessboard.getComponent(myLocation));

        if(considerCheck){
            // '\^ left up diagonal' move
            move = myLocation - 9;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && !check(move))
                _possibleMoves.push(move, null);

            // '|^ straight up vertical' move
            move = myLocation - 8;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && !check(move))
                _possibleMoves.push(move, null);

            // '/^ right up diagonal' move
            move = myLocation - 7;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && !check(move))
                _possibleMoves.push(move, null);

            // '<- left horizontal' move
            move = myLocation - 1;
            if(move >= 0 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && !check(move))
                _possibleMoves.push(move, null);

            // '-> right horizontal' move
            move = myLocation + 1;
            if(move < 63 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && !check(move))
                _possibleMoves.push(move, null);

            // '\v left down diagonal' move
            move = myLocation + 9;
            if(move < 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && !check(move))
                _possibleMoves.push(move, null);

            // '|v straight down vertical' move
            move = myLocation + 8;
            if(move < 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && !check(move))
                _possibleMoves.push(move, null);

            // '/v right down diagonal' move
            move = myLocation + 7;
            if(move < 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && !check(move))
                _possibleMoves.push(move, null);}

        else{
            // '\^ left up diagonal' move
            move = myLocation - 9;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '|^ straight up vertical' move
            move = myLocation - 8;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '/^ right up diagonal' move
            move = myLocation - 7;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '<- left horizontal' move
            move = myLocation - 1;
            if(move >= 0 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '-> right horizontal' move
            move = myLocation + 1;
            if(move < 63 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '\v left down diagonal' move
            move = myLocation + 9;
            if(move < 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '|v straight down vertical' move
            move = myLocation + 8;
            if(move < 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '/v right down diagonal' move
            move = myLocation + 7;
            if(move < 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);}

        return _possibleMoves;
    }

    /**
    Method that checks if a potential move by the king places him in check
    */
    private boolean check(int move){
        List opponentMoves;
        ChessPiece opponent;

        // sort through opponent chesspieces from all possible chess squares 
        for(int index = 0; index < _chessboard.getSize(); ++index){
            opponent = (ChessPiece) (_chessboard.getComponent(index));
            if(isOpponent(opponent, this)){
                // get moves of opponent chesspiece
                opponentMoves = opponent.possibleMoves(index, _chessboard, false);

                // check if any opponent moves are the same as king piece move
                for(int i = 0; i < opponentMoves.getSize(); ++i){
                    if((int) opponentMoves.pop(i) == move)
                        return true;} } }

        return false;
    }
}
