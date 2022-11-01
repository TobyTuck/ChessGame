/**
This is a class that models a White King chesspiece
*/

import javax.swing.*;  // needed for ImageIcon class (et al.)

public class WhiteKing{

    private ImageIcon _image;

    public WhiteKing(){
        _image = new ImageIcon("WhiteKing.png");
    }

    /**
    Method to return the image associated w/ a White King chesspiece
    */
    public ImageIcon getImage(){
        return _image;
    }
}
