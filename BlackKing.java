/**
This is a class that models a Black King chesspiece
*/

import javax.swing.*;  // needed for ImageIcon class (et al.)

public class BlackKing{

    private ImageIcon _image;

    public BlackKing(){
        _image = new ImageIcon("BlackKing.png");
    }

    /**
    Method to return the image associated w/ a Black King chesspiece
    */
    public ImageIcon getImage(){
        return _image;
    }
}
