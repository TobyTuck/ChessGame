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

        // piece's king does not lie in check
        if(!bKing.check(kingLocation, chessboard)){
            topRight(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);
            topLeft(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);
            bottomRight(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);
            bottomLeft(myLocation, myLocation, chessboard, considerCheck, bKing, kingLocation);}

        else{
            checkTopRight(myLocation, myLocation, chessboard, considerCheck, bKing, 
                          kingLocation);
            checkTopLeft(myLocation, myLocation, chessboard, considerCheck, bKing, 
                         kingLocation);
            checkBottomRight(myLocation, myLocation, chessboard, considerCheck, bKing, 
                             kingLocation);
            checkBottomLeft(myLocation, myLocation, chessboard, considerCheck, bKing, 
                            kingLocation);}

        return _possibleMoves;
    }

    /**
    Recursive method that adds the angled top right moves
    */
    private void topRight(int position, int startLocation, List chessboard,
                          boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position + 7;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, startLocation, chessboard, considerCheck,
                         bKing, bKingLocation)){
            _possibleMoves.push(next, null);
            topRight(next, startLocation, chessboard, considerCheck, bKing, 
                     bKingLocation);}
    }

    /**
    Method that determines the moves toward the upper right diagnoal 
    when its king lies in check
    */
    private void checkTopRight(int position, int startLocation, List chessboard, 
                               boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position + 7;

        // get list of all legal moves in a non-check format
        topRight(next, startLocation, chessboard, considerCheck, bKing, bKingLocation);
        List potentialMoves = _possibleMoves;
        _possibleMoves.removeAll();

        // moves are not allowed until the opponent's path to our king is blocked 
        // then all subsequent moves are pushed to the list 
        boolean flag = false;
        for(int index = 0; index < potentialMoves.getSize(); ++index){
            if(validBlock(bKing, bKingLocation, startLocation, next, chessboard) || flag){
                _possibleMoves.push(potentialMoves.pop(index), null);
                flag = true;} }
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
    Method that determines the moves toward the upper left diagnoal 
    when its king lies in check
    */
    private void checkTopLeft(int position, int startLocation, List chessboard, 
                              boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position + 9;

        // get list of all legal moves in a non-check format
        topRight(next, startLocation, chessboard, considerCheck, bKing, bKingLocation);
        List potentialMoves = _possibleMoves;
        _possibleMoves.removeAll();

        // moves are not allowed until the opponent's path to our king is blocked 
        // then all subsequent moves are pushed to the list 
        boolean flag = false;
        for(int index = 0; index < potentialMoves.getSize(); ++index){
            if(validBlock(bKing, bKingLocation, startLocation, next, chessboard) || flag){
                _possibleMoves.push(potentialMoves.pop(index), null);
                flag = true;} }
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
    Method that determines the moves toward the bottom right diagnoal 
    when its king lies in check
    */
    private void checkBottomRight(int position, int startLocation, List chessboard, 
                                  boolean considerCheck, BlackKing bKing, 
                                  int bKingLocation){
        int next = position - 7;

        // get list of all legal moves in a non-check format
        topRight(next, startLocation, chessboard, considerCheck, bKing, bKingLocation);
        List potentialMoves = _possibleMoves;
        _possibleMoves.removeAll();

        // moves are not allowed until the opponent's path to our king is blocked 
        // then all subsequent moves are pushed to the list 
        boolean flag = false;
        for(int index = 0; index < potentialMoves.getSize(); ++index){
            if(validBlock(bKing, bKingLocation, startLocation, next, chessboard) || flag){
                _possibleMoves.push(potentialMoves.pop(index), null);
                flag = true;} }
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
    Method that determines the moves toward the bottom right diagnoal 
    when its king lies in check
    */
    private void checkBottomLeft(int position, int startLocation, List chessboard, 
                                    boolean considerCheck, BlackKing bKing, 
                                    int bKingLocation){
        int next = position - 9;

        // get list of all legal moves in a non-check format
        topRight(next, startLocation, chessboard, considerCheck, bKing, bKingLocation);
        List potentialMoves = _possibleMoves;
        _possibleMoves.removeAll();

        // moves are not allowed until the opponent's path to our king is blocked 
        // then all subsequent moves are pushed to the list 
        boolean flag = false;
        for(int index = 0; index < potentialMoves.getSize(); ++index){
            if(validBlock(bKing, bKingLocation, startLocation, next, chessboard) || flag){
                _possibleMoves.push(potentialMoves.pop(index), null);
                flag = true;} }
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

    /**
    Method that only returns false when it is impossible for a piece to block its king in check
    */
    private boolean validBlock(BlackKing myKing, int myBKing, int startLocation, int move, 
                               List chessboard){
        // find the opponent piece that places our king in check
        ChessPiece opponent = myKing.getCheckedPiece(move, chessboard); 

        if(opponent instanceof WhiteBishop || opponent instanceof WhiteRook || opponent instanceof WhiteQueen){
            if(opponent instanceof WhiteBishop){
                WhiteBishop complex = (WhiteBishop) opponent;
                if(complex.inSequence(startLocation, move, chessboard))
                    return true;

                return false;}

            else if(opponent instanceof WhiteRook){
                WhiteRook complex = (WhiteRook) opponent;
                if(complex.inSequence(startLocation, move, chessboard))
                    return true;

                return false;}

            else{
                WhiteQueen complex = (WhiteQueen) opponent;
                if(complex.inSequence(startLocation, move, chessboard))
                    return true;

                return false;} }

        return false;
    }
}
