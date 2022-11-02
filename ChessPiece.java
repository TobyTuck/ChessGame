/**
This is an abstract class that will provide a general 
template for all types of Chess-pieces
*/

import javax.swing.*;  // need for ImageIcon class (et al.)

public abstract class ChessPiece{

    private ImageIcon _image; 

    public ChessPiece(ImageIcon image){
        _image = image;
    }

    /**
    Used to return the image property of a chesspiece
    */
    public ImageIcon getImage(){
        return _image;
    }
}
