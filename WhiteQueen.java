/**
This is a class that models the White Queen chesspiece
*/

import javax.swing.*;  // needed for ImageIcon class (et al.)

public class WhiteQueen{

    private ImageIcon _image;

    public WhiteQueen(){
        _image = new ImageIcon("WhiteQueen.png");
    }

    /**
    Method to return the image associated w/ a White Queen chesspiece
    */
    public ImageIcon getImage(){
        return _image;
    }
}
