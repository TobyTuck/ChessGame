/**
This is a class that models a White Rook chesspiece
*/

import javax.swing.*;  // needed for ImageIcon class (et al.)

public class WhiteRook{

    private ImageIcon _image;

    public WhiteRook(){
        _image = new ImageIcon("WhiteRook.png");
    }

    /**
    Method to return the image
    */
    public ImageIcon getImage(){
        return _image;
    }
}
