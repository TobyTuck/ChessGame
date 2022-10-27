/**
This is an abstract class that will provide a general 
template for all types of Chess-pieces
*/
public abstract class ChessPiece{

    private BufferedImage _image; 

    // no-arg constructor used b/c each chesspiece uses different image
    public abstract ChessPiece();

    public BufferedImage getImage(){
        return _image;
    }
}
