/**
This is a class that models a Black Queen chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class BlackQueen extends ChessPiece{

    private List _moves; 
    private List _chessboard;
    private int _location;
    private BlackKing _king;
    private int _kingLocation;

    public BlackQueen(BufferedImage image){
        _moves = new List(5);
        _chessboard = null;
        _location = 0;
        _king = null;
        _kingLocation = 0;

        // set file image
        super.setImage(image);
    }

    public BlackQueen(){
        _moves = new List(5);
        _chessboard = null;
        _location = 0;
        _king = null;
        _kingLocation = 0;

        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("BlackQueen.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating Black Queen image file");}
    }

    public List possibleMoves(int myLocation, List chessboard, boolean considerCheck,
                              int doNothing1, int doNothing2){
        // set fields
        _chessboard = chessboard;
        _location = myLocation;
        _king = (BlackKing) getKing(_chessboard, this);
        _kingLocation = getKingLocation(_chessboard, this);

        // remove any old moves that might be saved
        _moves.removeAll();

        // Bishop-like movements
        topRight(_location, considerCheck);
        topLeft(_location, considerCheck);
        bottomRight(_location, considerCheck);
        bottomLeft(_location, considerCheck);

        // Rook-like movements
        horizontalRight(_location, considerCheck);
        horizontalLeft(_location, considerCheck);
        verticalUp(_location, considerCheck);
        verticalDown(_location, considerCheck);

        return _moves;
    }

    /**
    Rook-like moves
    Recursive method that finds the horizonatal moves to the right
    */
    private void horizontalRight(int position, boolean considerCheck){
        int next = position + 1;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if(considerCheck && _king.check(_kingLocation, _chessboard)){
            if(validGeneralMove(position, next)){
                if(removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                horizontalRight(next, considerCheck);} }

        // standard move- king does not lie in check
        else{
            if(validGeneralMove(position, next)){
                _moves.push(next, null);
                horizontalRight(next, considerCheck);} }
    }

    /**
    Recursive method that finds the horizontal moves to the left of current position
    */
    private void horizontalLeft(int position, boolean considerCheck){
        int next = position - 1;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if(considerCheck && _king.check(_kingLocation, _chessboard)){
            if(validGeneralMove(position, next)){
                if(removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                horizontalLeft(next, considerCheck);} }

        // standard move- king does not lie in check
        else{
            if(validGeneralMove(position, next)){
                _moves.push(next, null);
                horizontalLeft(next, considerCheck);} }
    }

    /**
    Recursive method that finds any legal upward vertical moves
    */
    private void verticalUp(int position, boolean considerCheck){
        int next = position + 8;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if(considerCheck && _king.check(_kingLocation, _chessboard)){
            if(validGeneralMove(position, next)){
                if(removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                verticalUp(next, considerCheck);} }

        // standard move- king does not lie in check
        else{
            if(validGeneralMove(position, next)){
                _moves.push(next, null);
                verticalUp(next, considerCheck);} }
    }

    /**
    Recursive method that finds any legal downward vertical moves
    */
    private void verticalDown(int position, boolean considerCheck){
        int next = position - 8;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if(considerCheck && _king.check(_kingLocation, _chessboard)){
            if(validGeneralMove(position, next)){
                if(removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                verticalDown(next, considerCheck);} }

        // standard move- king does not lie in check
        else{
            if(validGeneralMove(position, next)){
                _moves.push(next, null);
                verticalDown(next, considerCheck);} }
    }

    /**
    Bishop-like Moves
    Recursive method that adds the angled top right moves
    */
    private void topRight(int position, boolean considerCheck){
        int next = position + 7;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if(considerCheck && _king.check(_kingLocation, _chessboard)){
            if(validGeneralMove(position, next)){
                if(removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                topRight(next, considerCheck);} }

        // standard move- king does not lie in check
        else{
            if(validGeneralMove(position, next)){
                _moves.push(next, null);
                topRight(next, considerCheck);} }
    }

    /**
    Recursive method that adds the angled top left moves
    */
    private void topLeft(int position, boolean considerCheck){
        int next = position + 9;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if(considerCheck && _king.check(_kingLocation, _chessboard)){
            if(validGeneralMove(position, next)){
                if(removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                topLeft(next, considerCheck);} }

        // standard move- king does not lie in check
        else{
            if(validGeneralMove(position, next)){
                _moves.push(next, null);
                topLeft(next, considerCheck);} }
    }

    /**
    Recursive method that adds the angled bottom right moves
    */
    private void bottomRight(int position, boolean considerCheck){
        int next = position - 7;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if(considerCheck && _king.check(_kingLocation, _chessboard)){
            if(validGeneralMove(position, next)){
                if(removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                bottomRight(next, considerCheck);} }

        // standard move- king does not lie in check
        else{
            if(validGeneralMove(position, next)){
                _moves.push(next, null);
                bottomRight(next, considerCheck);} }
    }

    /**
    Recursive method that adds the angled bottom left moves
    */
    private void bottomLeft(int position, boolean considerCheck){
        int next = position - 9;

        // considerCheck is true- king can't be in check for opponent's move (checkmate)
        if(considerCheck && _king.check(_kingLocation, _chessboard)){
            if(validGeneralMove(position, next)){
                if(removeCheck(_location, next, _chessboard))
                    _moves.push(next, null);

                bottomLeft(next, considerCheck);} }

        // standard move- king does not lie in check
        else{
            if(validGeneralMove(position, next)){
                _moves.push(next, null);
                bottomLeft(next, considerCheck);} }
    }

    /**
    Method that determines whether or not a potential move is valid according to queen rules
    */
    private boolean validGeneralMove(int position, int next){
        if(next > 63 || next < 0)
            return false;

        ChessPiece myPiece = (ChessPiece) _chessboard.getComponent(position);
        ChessPiece nextPiece = (ChessPiece) _chessboard.getComponent(next);

        if(!sameColor(this, nextPiece) && !overflow(position, next) &&
           (myPiece == null || myPiece == this))
            return true;

        return false;
    }

    /**
    Method that handles the 'chessboard overflow' error
    */
    private boolean overflow(int location, int moveTo){
        // rook movements
        if(location == moveTo + 8 || location == moveTo - 8 ||
           location == moveTo + 1 || location == moveTo - 1){
            if(sameRow(moveTo, location) || sameColumn(moveTo + 1, location + 1))
                return false;

            else{
                return true;} }

        // bishop movements
        else{
            if((rowOf(location + 1, 8) && rowOf(moveTo + 1, 1)) || 
               (rowOf(moveTo + 1, 8) && rowOf(location + 1, 1)))
                return true;

            else{
                return false;} }
    }
}
