/**
This is a class that models a Black Knight chesspiece
*/

import javax.swing.*;  // need ImageIcon class (et al.)

public class BlackKnight{

    private ImageIcon _image;

    public BlackKnight(){
        _image = new ImageIcon("BlackKnight.png");
    }

    /**
    Method to return the image associated with a Black Knight
    */
    public ImageIcon getImage(){
        return _image;
    }
}
