/**
This is a class that models a White Pawn chesspiece
*/

import javax.swing.*;  // need ImageIcon class (et al.)

public class WhitePawn{

    private ImageIcon _image;

    public WhitePawn(){
        _image = new ImageIcon("WhitePawn.png");
    }

    /**
    Method to return the image
    */
    public ImageIcon getImage(){
        return _image;
    }
}
