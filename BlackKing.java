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
        _chessboard = chessboard;
        _location = myLocation;
        int move;

        // '\^ left up diagonal' move
        move = myLocation - 9;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && !check(move))
            _possibleMoves.push(move, null);

        // '|^ straight up vertical' move
        move = myLocation - 8;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && !check(move))
            _possibleMoves.push(move, null);

        // '/^ right up diagonal' move
        move = myLocation - 7;
        if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && !check(move))
            _possibleMoves.push(move, null);

        // '<- left horizontal' move
        move = myLocation - 1;
        if(move >= 0 && (move / 8 == myLocation / 8) && !check(move))
            _possibleMoves.push(move, null);

        // '-> right horizontal' move
        move = myLocation + 1;
        if(move < 63 && (move / 8 == myLocation / 8) && !check(move))
            _possibleMoves.push(move, null);

        // '\v left down diagonal' move
        move = myLocation + 9;
        if(move < 63 && (move / 8 == (myLocation / 8) + 1) && !check(move))
            _possibleMoves.push(move, null);

        // '|v straight down vertical' move
        move = myLocation + 8;
        if(move < 63 && (move / 8 == (myLocation / 8) + 1) && !check(move))
            _possibleMoves.push(move, null);

        // '/v right down diagonal' move
        move = myLocation + 7;
        if(move < 63 && (move / 8 == (myLocation / 8) + 1) && !check(move))
            _possibleMoves.push(move, null);

        return _possibleMoves;
    }

    /**
    Method that checks if a potential move by the king places him in check
    */
    private boolean check(int move){
        List opponentMoves;

        // sort through opponent chesspieces from all possible chess squares 
        for(int index = 0; index < _chessboard.getSize(); ++index){
            if(isOpponent((ChessPiece) (_chessboard.getComponent(index)), new BlackKing())){
                // get moves of each single opponent chesspiece
                opponentMoves = ((ChessPiece) (_chessboard.getComponent(index))).
                                              possibleMoves(index, _chessboard);

                // check if any opponent moves are the same as king piece move
                for(int i = 0; i < opponentMoves.getSize(); ++i){
                    if((int) opponentMoves.pop(i) == move)
                        return true;} } }

        return false;
    }
}
