/**
This is a class that models a Black Queen chesspiece
*/

import javax.swing.*;  // needed for ImageIcon class (et al.)

public class BlackQueen{
    
    private ImageIcon _image;

    public BlackQueen(){
        _image = new ImageIcon("BlackQueen.png");
    }

    /**
    Method to return the image associated w/ a Black Queen chesspiece
    */
    public ImageIcon getImage(){
        return _image;
    }
}
