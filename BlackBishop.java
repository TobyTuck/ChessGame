/**
This is a class that models a Black Bishop chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackBishop extends ChessPiece{

    private List _possibleMoves;
    private List _checkMoves;

    public BlackBishop(){
        _possibleMoves = new List(5);

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackBishop.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Bishop image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
                              int doNothing1, int doNothing2){
        // remove any old moves that might be saved
        _possibleMoves.removeAll();

        // search board for black king
        ChessPiece piece;
        BlackKing bKing = null;
        int kingLocation = 0;
        for(int index = 0; index < chessboard.getSize(); ++index){
            piece = (ChessPiece) chessboard.getComponent(index);
            if(piece instanceof BlackKing){
                kingLocation = index;
                bKing = (BlackKing) piece;} }

        topRight(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);
        topLeft(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);
        bottomRight(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);
        bottomLeft(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);

        return _possibleMoves;
    }

    /**
    Recursive method that adds the angled top right moves
    */
    private void topRight(int position, int startLocation, List chessboard,
                          boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position + 7;
        int move;

        // special case- king is in check
        if(bKing.check(bKingLocation, chessboard)){
            // find all moves until overflow
            for(int index = 0; index < 8; ++index){
                if(!overflow(position, next))
                    _checkMoves.push(next, null);
                next = next + 7;}

            // go over these moves, starting w/ the last
            // if any move captures piece placing the king in check, moves after are allowed, unless 
            // any move goes through another piece
            for(int index = 0; index < _checkMoves.getSize(); ++index){
                move = (int) _checkMoves.pop(index);
                if(bKing.checkLocation(chessboard) == move)
                    _possibleMoves.push(move, null);} }

        // standard movement
        else{
            // is movement is valid according to the chesspiece rules? 
            if(validMovement(position, next, startLocation, chessboard, considerCheck,
                             bKing, bKingLocation)){
                _possibleMoves.push(next, null);
                topRight(next, startLocation, chessboard, considerCheck, bKing, 
                         bKingLocation);} }
    }

    /**
    Recursive method that adds the angled top left moves
    */
    private void topLeft(int position, int startLocation, List chessboard,
                         boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position + 9;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard, considerCheck,
                         bKing, bKingLocation)){
            _possibleMoves.push(next, null);
            topLeft(next, startLocation, chessboard, considerCheck, bKing, 
                    bKingLocation);}
    }

    /**
    Recursive method that adds the angled bottom right moves
    */
    private void bottomRight(int position, int startLocation, List chessboard,
                             boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position - 7;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard, considerCheck,
                         bKing, bKingLocation)){
            _possibleMoves.push(next, null);
            bottomRight(next, startLocation, chessboard, considerCheck, bKing,
                        bKingLocation);}
    }

    /**
    Recursive method that adds the angled bottom left moves
    */
    private void bottomLeft(int position, int startLocation, List chessboard,
                            boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position - 9;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard, considerCheck,
                         bKing, bKingLocation)){
            _possibleMoves.push(next, null);
            bottomLeft(next, startLocation, chessboard, considerCheck, bKing,
                       bKingLocation);}
    }

    /**
    Method that determines whether or not a potential move is valid according to Bishop rules 
    */
    private boolean validMovement(int position, int next, int startLocation, List chessboard,
                                  boolean considerCheck, BlackKing bKing, int bKingLocation){

        if(next > 63 || next < 0)
                return false;

        ChessPiece startPiece = (ChessPiece) chessboard.getComponent(startLocation);
        ChessPiece myPiece = (ChessPiece) chessboard.getComponent(position); 
        ChessPiece nextPiece = (ChessPiece) chessboard.getComponent(next);

        if(considerCheck){
            if(!sameColor(startPiece, nextPiece) && !overflow(position, next) &&
               (myPiece == null || myPiece == startPiece) && 
               (!bKing.check(bKingLocation, chessboard) || next == bKing.checkLocation(chessboard)))
                return true; 

            return false;}

        // only look at instances of check (as applies to opponent king)
        // only difference is that BBishop's moves "extend through" a WKing 
            // eg: if a king in check moves diagonally away from BBishop, piece is still in check
        else{
            // illegal moves
            if((isBlack(nextPiece) || (isWhite(myPiece) && !(myPiece instanceof WhiteKing))) ||
               overflow(position, next))
                return false;

            // legal moves
            else{
                return true;} }
    }

    /**
    Method that handles the 'chessboard overflow' error 
    */
    private boolean overflow(int location, int moveTo){
        if((rowOf(location + 1, 8) && rowOf(moveTo + 1, 1)) || 
           (rowOf(moveTo + 1, 8) && rowOf(location + 1, 1)))
            return true;

        return false;
    }
}
