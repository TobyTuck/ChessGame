/**
This is a class that models a Black Bishop chesspiece
*/

import javax.swing.*;  // needed for ImageIcon class (et al.)

public class BlackBishop{

    private ImageIcon _image;

    public BlackBishop(){
        _image = new ImageIcon("BlackBishop.png");
    }

    public ImageIcon getImage(){
        return _image;
    }
}
