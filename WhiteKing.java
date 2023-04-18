/**
This is a class that models a White King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteKing extends ChessPiece{

    private List _possibleMoves;
    private List _chessboard;
    private int _location;
    private boolean _beenMoved;

    public WhiteKing(){
        // initialize fields
        _possibleMoves = new List(5);
        _chessboard = null;
        _location = 0;
        _beenMoved = false;

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteKing.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating the White King image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
                              int doNothing1, int doNothing2){
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
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '|^ straight up vertical' move
            move = myLocation - 8;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && 
               !check(move, chessboard))
                _possibleMoves.push(move, null);

            // '/^ right up diagonal' move
            move = myLocation - 7;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '<- left horizontal' move
            move = myLocation - 1;
            if(move >= 0 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '-> right horizontal' move
            move = myLocation + 1;
            if(move <= 63 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '\v left down diagonal' move
            move = myLocation + 9;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '|v straight down vertical' move
            move = myLocation + 8;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '/v right down diagonal' move
            move = myLocation + 7;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // castle moves- check left rook
            move = 56;
            ChessPiece myRook1 = (ChessPiece) _chessboard.getComponent(move);
            if(!hasMoved() && _chessboard.getComponent(57) == null && _chessboard.getComponent(58) == 
               null && _chessboard.getComponent(59) == null && myRook1 instanceof WhiteRook){
                WhiteRook rook = (WhiteRook) myRook1;
                if(!rook.hasMoved())
                    _possibleMoves.push(move, null);}
            
            // castle moves- check right rook
            move = 63; 
            ChessPiece myRook2 = (ChessPiece) _chessboard.getComponent(move);
            if(!hasMoved() && _chessboard.getComponent(61) == null && _chessboard.getComponent(62) == 
               null && myRook2 instanceof WhiteRook){ 
                WhiteRook rook = (WhiteRook) myRook2;
                if(!rook.hasMoved())
                    _possibleMoves.push(move, null);} }

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
            if(move <= 63 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '\v left down diagonal' move
            move = myLocation + 9;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '|v straight down vertical' move
            move = myLocation + 8;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);

            // '/v right down diagonal' move
            move = myLocation + 7;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), myPiece))
                _possibleMoves.push(move, null);}

        return _possibleMoves;
    }

    /**
    Method that checks if a potential move by the king places him in check
    */
    public boolean check(int move, List chessboard){
        _chessboard = chessboard;
        List opponentMoves;
        ChessPiece opponent;

        // sort through opponent chesspieces from all possible chess squares 
        for(int index = 0; index < _chessboard.getSize(); ++index){
            opponent = (ChessPiece) (_chessboard.getComponent(index));
            if(isOpponent(opponent, this)){
                opponentMoves = opponent.possibleMoves(index, _chessboard, false, 0, 
                                                       0);

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
