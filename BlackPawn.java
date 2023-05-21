/**
This is a class that models a Black Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackPawn extends ChessPiece{
    
    private List _moves;

    public BlackPawn(){
        _moves = new List(5);
        
        // set image file
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackPawn.png"));
           
            // pass image to parent class 
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Pawn image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
                              int startLastMove, int endLastMove){
        // remove moves from previous locations
        _moves.removeAll();

        int move;

        if(considerCheck){
            // search board for chess king
            BlackKing bKing = (BlackKing) getKing(chessboard, this);
            int kingLocation = getKingLocation(chessboard, this);
            boolean inCheck = bKing.check(kingLocation, chessboard);

            move = myLocation + 7;
            if((move / 8 == (myLocation / 8) + 1) && move < 64 && 
               isOpponent((ChessPiece) chessboard.getComponent(move), this) &&
               (!inCheck || removeCheck(myLocation, move, chessboard)))
                _moves.push(move, null);

            move = myLocation + 8;
            if((move / 8 == (myLocation / 8) + 1) && move < 64 && 
               chessboard.getComponent(move) == null && (!inCheck || 
               removeCheck(myLocation, move, chessboard)))
                _moves.push(move, null);

            move = myLocation + 9;
            if((move / 8 == (myLocation / 8) + 1) && move < 64 && 
               isOpponent((ChessPiece) chessboard.getComponent(move), this) && 
               (!inCheck || removeCheck(myLocation, move, chessboard)))
                _moves.push(move, null);

            move = myLocation + 16;
            if(myLocation / 8 == 1 && chessboard.getComponent(move) == null && 
               chessboard.getComponent(move - 8) == null && (!inCheck ||
               removeCheck(myLocation, move, chessboard)))
                _moves.push(move, null);

            // en passant
            if(sameRow(myLocation, 32) && (ChessPiece) chessboard.getComponent
               (endLastMove) instanceof WhitePawn && sameRow(startLastMove, 48) &&
               (myLocation == endLastMove - 1 || myLocation == endLastMove + 1) && 
               (!inCheck || removeCheck(myLocation, move, chessboard)))
                _moves.push(endLastMove + 8,  null);}

        else{
            move = myLocation + 7;
            if((move / 8 == (myLocation / 8) + 1) && move < 64)
                _moves.push(move, null);

            move = myLocation + 9;
            if((move / 8 == (myLocation / 8) + 1) && move < 64)
                _moves.push(move, null);}

        return _moves;
    }
}
