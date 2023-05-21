/**
This is a class that models a White King chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteKing extends ChessPiece{

    private List _possibleMoves;
    private boolean _beenMoved;

    public WhiteKing(){
        // initialize fields
        _possibleMoves = new List(5);
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
            move = 56;
            ChessPiece myRook1 = (ChessPiece) chessboard.getComponent(move);
            if(!hasMoved() && chessboard.getComponent(57) == null && chessboard.getComponent(58) == 
               null && chessboard.getComponent(59) == null && myRook1 instanceof WhiteRook){
                WhiteRook rook = (WhiteRook) myRook1;
                if(!rook.hasMoved())
                    _possibleMoves.push(move, null);}
            
            // castle moves- check right rook
            move = 63; 
            ChessPiece myRook2 = (ChessPiece) chessboard.getComponent(move);
            if(!hasMoved() && chessboard.getComponent(61) == null && chessboard.getComponent(62) == 
               null && myRook2 instanceof WhiteRook){ 
                WhiteRook rook = (WhiteRook) myRook2;
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
                _possibleMoves.push(move, null);}

        return _possibleMoves;
    }

    /**
    Method that checks if a potential move by the king places him in check
    */
    public boolean check(int move, List chessboard){
        chessboard = chessboard;
        List opponentMoves;
        ChessPiece opponent;

        // sort through opponent chesspieces from all possible chess squares 
        for(int index = 0; index < chessboard.getSize(); ++index){
            opponent = (ChessPiece) (chessboard.getComponent(index));
            if(isOpponent(opponent, this)){
                opponentMoves = opponent.possibleMoves(index, chessboard, false, 0, 0);

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
                        return opponent;} } }

        return null;
    }

    /**
    Method that returns the location of the opponent chess piece that holds the king in check
    */
    public int checkLocation(List chessboard){
        chessboard = chessboard;
        ChessPiece piece,
                   opponent;
        List opponentMoves;
        WhiteKing wKing = null;
        int kingLocation = 0;
        int position = 0;

        // search the chessboard for the position of the BlackKing
        for(int index = 0; index < chessboard.getSize(); ++index){
            piece = (ChessPiece) chessboard.getComponent(index);
            if(piece instanceof WhiteKing){
                kingLocation = index;
                wKing = (WhiteKing) piece;} }

        // sort through all opponent chesspieces from all possible chess squares 
        for(int index = 0; index < chessboard.getSize(); ++index){
            opponent = (ChessPiece) (chessboard.getComponent(index));
            if(isOpponent(opponent, this)){
                // get moves of opponent chesspiece
                opponentMoves = opponent.possibleMoves(index, chessboard, false, 0, 0);

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
