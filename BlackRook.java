/**
This is a class that models a Black Rook chesspiece
*/

import javax.swing.*;  // needed for ImageIcon class (et al.)

public class BlackRook{

    private ImageIcon _image;

    public BlackRook(){
        _image = new ImageIcon("BlackRook.png");
    }

    /**
    Method to return the image
    */
    public ImageIcon getImage(){
        return _image;
    }
}
