/**
This is a class that models a Black Pawn chesspiece
*/

import javax.swing.*;  // need ImageIcon class (et al.)

public class BlackPawn{
    
    private ImageIcon _image;

    public BlackPawn(){
        _image = new ImageIcon("BlackPawn.png");
    }

    /**
    Method to return the image
    */
    public ImageIcon getImage(){
        return _image;
    }
}
