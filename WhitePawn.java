/**
This is a class that models a White Pawn chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhitePawn extends ChessPiece{

    private List _possibleMoves;

    public WhitePawn(){
        _possibleMoves = new List(5);

        // set image file
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhitePawn.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Pawn image file");}
     }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
                              int startLastMove, int endLastMove){
        // remove moves from previous locations
        _possibleMoves.removeAll();

        int move;

        if(considerCheck){
            // search board for my king
            WhiteKing wKing = (WhiteKing) getKing(chessboard, this);
            int kingLocation = getKingLocation(chessboard, this);

            move = myLocation - 7;
            if((move / 8 == (myLocation / 8) - 1) && move < 64 && 
               isOpponent((ChessPiece) chessboard.getComponent(move), this) && 
               (!wKing.check(kingLocation, chessboard) || move == wKing.checkLocation(chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard))){
                // delete
                System.out.println("Somehow made it (0)");

                _possibleMoves.push(move, null);}

            move = myLocation - 8;
            if((move / 8 == (myLocation / 8) - 1) && move < 64 && 
               chessboard.getComponent(move) == null && (!wKing.check(kingLocation, chessboard) || 
               blockCheck(myLocation, move, kingLocation, chessboard))){
                // delete
                System.out.println("Somehow made it (1)");

                _possibleMoves.push(move, null);}

            move = myLocation - 9;
            if((move / 8 == (myLocation / 8) - 1) && move < 64 && 
               isOpponent((ChessPiece) chessboard.getComponent(move), this) && 
               (!wKing.check(kingLocation, chessboard) || move == wKing.checkLocation(chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard))){
                // delete
                System.out.println("Somehow made it (2)");

                _possibleMoves.push(move, null);}

            move = myLocation - 16;
            if(myLocation / 8 == 6 && chessboard.getComponent(move) == null && 
               chessboard.getComponent(move + 8) == null && (!wKing.check(kingLocation, chessboard) ||
               blockCheck(myLocation, move, kingLocation, chessboard))){
                // delete
                System.out.println("Somehow made it (3)");

                _possibleMoves.push(move, null);}

            // en passant
            if(sameRow(myLocation, 24) && (ChessPiece) chessboard.getComponent
               (endLastMove) instanceof BlackPawn && sameRow(startLastMove, 8) &&
               (myLocation == endLastMove - 1 || myLocation == endLastMove + 1) &&
               (!wKing.check(kingLocation, chessboard) || 
               blockCheck(myLocation, move, kingLocation, chessboard)))

                _possibleMoves.push(endLastMove - 8,  null);}

        else{
            move = myLocation - 7;
            if((move / 8 == (myLocation / 8) - 1) && move < 64)
                _possibleMoves.push(move, null);

            move = myLocation - 9;
            if((move / 8 == (myLocation / 8) - 1) && move < 64)
                _possibleMoves.push(move, null);}

        return _possibleMoves;
    }
}
