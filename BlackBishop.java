/**
This is a class that models a Black Bishop chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackBishop extends ChessPiece{

    private List _moves; // list that holds available moves for a piece given its environment
    private List _chessboard; // list that represents the chessboard
    private List _possibleMoves; // potentially legal moves, used when our king is placed in check
    private int _location; // integer that represents the location of this piece

    public BlackBishop(){
        _moves = new List(5);
        _chessboard = null;
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

    public List possibleMoves(int location, List chessboard, boolean considerCheck,
                              int doNothing1, int doNothing2){
        // set fields
        _chessboard = chessboard;
        _location = location;

        // remove any old moves that might have been carried over 
        _moves.removeAll();
        _possibleMoves.removeAll();

        // search board for black king
        BlackKing bKing = (BlackKing) getKing(chessboard, this);
        int kingLocation = getKingLocation(chessboard, this);
        // boolean inCheck = bKing.check(kingLocation, chessboard);

        // piece's king does not lie in check
        topRight(_location, considerCheck, bKing, kingLocation);
        topLeft(_location, considerCheck, bKing, kingLocation);
        bottomRight(_location, considerCheck, bKing, kingLocation);
        bottomLeft(_location, considerCheck, bKing, kingLocation);

        return _moves;
    }

    /**
    Recursive method that adds the angled top right moves
    */
    private void topRight(int position, boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position + 7;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, considerCheck)){
            // king must both be in check and bishop move must be a "true" one
            if(considerCheck && bKing.check(next, _chessboard)){
                // our move removes check move, therefore it and all earlier moves are allowed
                if(removeCheck(_location, next, bKingLocation, _chessboard)){
                    for(int i = 0; i < _possibleMoves.getSize(); ++i){
                        _moves.push(_possibleMoves.pop(i), null);}
                    _possibleMoves.removeAll();} }

            // don't care how move affects our king and its ability to be placed in check
            else
                _moves.push(next, null);

            // call while generalized the move is valid according to generalized standards
            topRight(next, considerCheck, bKing, bKingLocation);}
    }

    /**
    Recursive method that adds the angled top left moves
    */
    private void topLeft(int position, boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position + 9;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, considerCheck)){
            if(considerCheck && bKing.check(next, _chessboard)){
                if(removeCheck(_location, next, bKingLocation, _chessboard)){
                    for(int i = 0; i < _possibleMoves.getSize(); ++i){
                        _moves.push(_possibleMoves.pop(i), null);}
                    _possibleMoves.removeAll();} }

            else
                _moves.push(next, null);

            topLeft(next, considerCheck, bKing, bKingLocation);}
    }

    /**
    Recursive method that adds the angled bottom right moves
    */
    private void bottomRight(int position, boolean considerCheck, BlackKing bKing, int bKingLocation){
        int next = position - 7;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, considerCheck)){
            if(considerCheck && bKing.check(next, _chessboard)){
                if(removeCheck(_location, next, bKingLocation, _chessboard)){
                    for(int i = 0; i < _possibleMoves.getSize(); ++i){
                        _moves.push(_possibleMoves.pop(i), null);}
                    _possibleMoves.removeAll();} }

            else
                _moves.push(next, null);

            bottomRight(next, considerCheck, bKing, bKingLocation);}
    }

    /**
    Recursive method that adds the angled bottom left moves
    */
    private void bottomLeft(int position, boolean considerCheck, BlackKing bKing, int bKingLocation){
        // delete
        System.out.println("Made it");

        int next = position - 9;

        // is movement is valid according to the chesspiece rules? 
        if(validMovement(position, next, considerCheck)){
            if(considerCheck && bKing.check(next, _chessboard)){
                if(removeCheck(_location, next, bKingLocation, _chessboard)){
                    for(int i = 0; i < _possibleMoves.getSize(); ++i){
                        _moves.push(_possibleMoves.pop(i), null);}
                    _possibleMoves.removeAll();} }

            else
                _moves.push(next, null);

            bottomLeft(next, considerCheck, bKing, bKingLocation);}
    }

    /**
    Method that checks if a movement legal according to general standards
    These standards are:
        - no overflow
        - chess piece at potential move is not same color as this piece
        - piece at prior move is not of the opposite color
    This method ignores the special case of our king being in check
    */
    private boolean validMovement(int position, int next, boolean considerCheck){ 
        if(next > 63 || next < 0)
                return false;

        ChessPiece myPiece = (ChessPiece) _chessboard.getComponent(position); 
        ChessPiece nextPiece = (ChessPiece) _chessboard.getComponent(next);

        if(considerCheck){
            if(!sameColor(this, nextPiece) && !overflow(position, next) &&
               (myPiece == null || myPiece == this))
                return true;

            return false;}

        // only look at instances of check (as applies to opponent king)
        // only difference is that WBishop's moves "extend through" a BKing 
        // eg: if a king in check moves diagonally away from WBishop, piece is still in check
        else{
            if((isBlack(nextPiece) || (isWhite(myPiece) && !(myPiece instanceof WhiteKing))) ||
               overflow(position, next))
                return false;

            return true;}

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

    /*
    Method that returns the number of moves available for a black bishop before board overflow
    
    private int movesBeforeOverflow(int location, int move, List chessboard){
        int index = 0;
        int jump = move - location;

        while(!overflow(location, move) && !isBlack((ChessPiece) chessboard.getComponent(move))){
            location = move;
            move += jump;
            ++index;}

        return index;
    }
    */
}
