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
    private boolean _beenMoved;

    public BlackKing(){
        _possibleMoves = new List(5);
        _chessboard = null;
        _location = 0;
        _beenMoved = false;

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackKing.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black King image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
                              int doNothing1, int doNothing2){
        // remove any other former moves from Black King chesspiece
        _possibleMoves.removeAll();

        // set fields
        _chessboard = chessboard;
        _location = myLocation;

        int move;

        if(considerCheck){
            // '\^ left up diagonal' move
            move = myLocation - 9;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '|^ straight up vertical' move
            move = myLocation - 8;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '/^ right up diagonal' move
            move = myLocation - 7;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '<- left horizontal' move
            move = myLocation - 1;
            if(move >= 0 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '-> right horizontal' move
            move = myLocation + 1;
            if(move <= 63 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '\v left down diagonal' move
            move = myLocation + 9;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '|v straight down vertical' move
            move = myLocation + 8;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this) && 
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // '/v right down diagonal' move
            move = myLocation + 7;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this) &&
               !check(move, _chessboard))
                _possibleMoves.push(move, null);

            // castle moves- check left rook
            // NOTE: king can't castle to escape check
            move = 0;
            ChessPiece myRook1 = (ChessPiece) _chessboard.getComponent(move);
            if(!hasMoved() && _chessboard.getComponent(1) == null && _chessboard.getComponent(2) == 
               null && _chessboard.getComponent(3) == null && myRook1 instanceof BlackRook &&
               !check(myLocation, _chessboard)){
                BlackRook rook = (BlackRook) myRook1;
                if(!rook.hasMoved())
                    _possibleMoves.push(move, null);}
            
            // castle moves- check right rook
            move = 7; 
            ChessPiece myRook2 = (ChessPiece) _chessboard.getComponent(move);
            if(!hasMoved() && _chessboard.getComponent(5) == null && _chessboard.getComponent(6) == 
               null && myRook2 instanceof BlackRook && !check(myLocation, _chessboard)){ 
                BlackRook rook = (BlackRook) myRook2;
                if(!rook.hasMoved())
                    _possibleMoves.push(move, null);} }

        else{
            // '\^ left up diagonal' move
            move = myLocation - 9;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '|^ straight up vertical' move
            move = myLocation - 8;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '/^ right up diagonal' move
            move = myLocation - 7;
            if(move >= 0 && (move / 8 == (myLocation / 8) - 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '<- left horizontal' move
            move = myLocation - 1;
            if(move >= 0 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '-> right horizontal' move
            move = myLocation + 1;
            if(move <= 63 && (move / 8 == myLocation / 8) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '\v left down diagonal' move
            move = myLocation + 9;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '|v straight down vertical' move
            move = myLocation + 8;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this))
                _possibleMoves.push(move, null);

            // '/v right down diagonal' move
            move = myLocation + 7;
            if(move <= 63 && (move / 8 == (myLocation / 8) + 1) && 
               !sameColor((ChessPiece) (_chessboard.getComponent(move)), this))
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
        _chessboard = chessboard;
        List opponentMoves;
        ChessPiece opponent;

        // sort through opponent chesspieces from all possible chess squares 
        for(int index = 0; index < _chessboard.getSize(); ++index){
            opponent = (ChessPiece) (_chessboard.getComponent(index));
            if(isOpponent(opponent, this)){
                // get moves of opponent chesspiece
                opponentMoves = opponent.possibleMoves(index, _chessboard, false, 0, 0);

                // check if any opponent moves are the same as king piece move
                for(int i = 0; i < opponentMoves.getSize(); ++i){
                    if((int) opponentMoves.pop(i) == move)
                        return true;} } }

        return false;
    }

    /**
    Method that returns the piece that places our king in check
    */
    public ChessPiece getCheckedPiece(int move, List chessboard){
        _chessboard = chessboard;
        List opponentMoves;
        ChessPiece opponent;

        // sort through opponent chesspieces from all possible chess squares 
        for(int index = 0; index < _chessboard.getSize(); ++index){
            opponent = (ChessPiece) (_chessboard.getComponent(index));
            if(isOpponent(opponent, this)){
                // get moves of opponent chesspiece
                opponentMoves = opponent.possibleMoves(index, _chessboard, false, 0, 0);

                // check if any opponent moves are the same as king piece move
                for(int i = 0; i < opponentMoves.getSize(); ++i){
                    if((int) opponentMoves.pop(i) == move)
                        return opponent;} } }

        return null;
    }

    /**
    Method that returns the location of the opponent chess piece that holds the king in check
    */
    public int checkLocation(List chessboard){
        _chessboard = chessboard;
        ChessPiece piece,
                   opponent;
        List opponentMoves;
        BlackKing bKing = null;
        int kingLocation = 0;
        int position = 0;

        // search the chessboard for the position of the BlackKing
        for(int index = 0; index < _chessboard.getSize(); ++index){
            piece = (ChessPiece) _chessboard.getComponent(index);
            if(piece instanceof BlackKing){
                kingLocation = index;
                bKing = (BlackKing) piece;} }

        // sort through all opponent chesspieces from all possible chess squares 
        for(int index = 0; index < _chessboard.getSize(); ++index){
            opponent = (ChessPiece) (_chessboard.getComponent(index));
            if(isOpponent(opponent, this)){
                // get moves of opponent chesspiece
                opponentMoves = opponent.possibleMoves(index, _chessboard, false, 0, 0);

                // check if any opponent moves are the same as king piece location
                for(int i = 0; i < opponentMoves.getSize(); ++i){
                    if((int) opponentMoves.pop(i) == kingLocation)
                        // return the position of the piece that places the king in check
                        position = index;} } }

        return position;
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
