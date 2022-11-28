/**
This is a class that models a White Rook chesspiece
*/

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class WhiteRook extends ChessPiece{

    public WhiteRook(){
        // set file image
        try{
            // open image file
            BufferedImage image = ImageIO.read(new File("WhiteRook.png"));

            // pass image to parent class
            super.setImage(image);
        }catch(IOException exception){
            System.out.println("Error locating White Rook image file");}

        // set possible moments by this piece
        List potentialMoves = new List(28);
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
        super.setMovement(potentialMoves);
    }
}
