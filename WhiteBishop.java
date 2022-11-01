/**
This is a class that models a White Bishop chesspiece
*/

import javax.swing.*;  // needed for ImageIcon class (et al.)

public class WhiteBishop{

    private ImageIcon _image;

    public WhiteBishop(){
        _image = new ImageIcon("WhiteBishop.png");
    }

    /**
    Method to return the image associated w/ a White Bishop chesspiece
    */
    public ImageIcon getImage(){
        return _image;
    }
}
