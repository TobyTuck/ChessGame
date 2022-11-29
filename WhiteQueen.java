/**
This is a class that models the White Queen chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;  // used for File and IOException classes
import javax.imageio.ImageIO;

public class WhiteQueen extends ChessPiece{

    public WhiteQueen(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteQueen.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Queen image file");}

        // set possible movements by piece
        List potentialMoves = new List(58);
        // diagonal bishop-like movements
        potentialMoves.push(8, true);
        potentialMoves.push(16, true);
        potentialMoves.push(24, true);
        potentialMoves.push(32, true);
        potentialMoves.push(40, true);
        potentialMoves.push(48, true);
        potentialMoves.push(56, true);
        potentialMoves.push(-8, true);
        potentialMoves.push(-16, true);
        potentialMoves.push(-24, true);
        potentialMoves.push(-32, true);
        potentialMoves.push(-40, true);
        potentialMoves.push(-48, true);
        potentialMoves.push(-56, true);
        potentialMoves.push(1, true);
        potentialMoves.push(2, true);
        potentialMoves.push(3, true);
        potentialMoves.push(4, true);
        potentialMoves.push(5, true);
        potentialMoves.push(6, true);
        potentialMoves.push(7, true);
        potentialMoves.push(-1, true);
        potentialMoves.push(-2, true);
        potentialMoves.push(-3, true);
        potentialMoves.push(-4, true);
        potentialMoves.push(-5, true);
        potentialMoves.push(-6, true);
        potentialMoves.push(-7, true);
        // diagonal rook-like movements
        potentialMoves.push(9, true);
        potentialMoves.push(18, true);
        potentialMoves.push(27, true);
        potentialMoves.push(36, true);
        potentialMoves.push(45, true);
        potentialMoves.push(54, true);
        potentialMoves.push(63, true);
        potentialMoves.push(-9, true);
        potentialMoves.push(-18, true);
        potentialMoves.push(-27, true);
        potentialMoves.push(-36, true);
        potentialMoves.push(-45, true);
        potentialMoves.push(-54, true);
        potentialMoves.push(-63, true);
        potentialMoves.push(14, true);
        potentialMoves.push(21, true);
        potentialMoves.push(28, true);
        potentialMoves.push(35, true);
        potentialMoves.push(42, true);
        potentialMoves.push(49, true);
        potentialMoves.push(-14, true);
        potentialMoves.push(-21, true);
        potentialMoves.push(-28, true);
        potentialMoves.push(-35, true);
        potentialMoves.push(-42, true);
        potentialMoves.push(-49, true);
        super.setMovement(potentialMoves);
    }

    public List removeOverflow(int myLocation, List chessboard){
        List potentialMoves = new List(5);
        List extensiveMoves = super.getMovement();
        int move;

        for(int index = 0; index < extensiveMoves.getSize(); ++index){
            move = myLocation + (int) extensiveMoves.pop(index);
            if(move < 64 && !sameColor((ChessPiece) chessboard.getComponent(myLocation), 
               (ChessPiece) chessboard.getComponent(move)))

                potentialMoves.push(move, null);}

        return potentialMoves;
    }
}
