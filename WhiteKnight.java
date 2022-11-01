/**
This is a class that models a White Knight chesspiece
*/

import javax.swing.*;  //used for ImageIcon class (et al.)

public class WhiteKnight{

    private ImageIcon _image;

    public WhiteKnight(){
        _image = new ImageIcon("WhiteKnight.png");
    }

    /**
    Method to return the image associated with a White Knight
    */
    public ImageIcon getImage(){
        return _image;
    }
}
