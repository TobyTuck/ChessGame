public class ChessGame{

    public static void main(String[] args){
        ChessBoard board = new ChessBoard();
    
        // print the dimensions of the JFrame and its components
        System.out.println
                        ("Chess cell dimensions: " + board.getCellDimension() + "\n" +
                       "Chess Board dimensions: " + board.getBoardDimension() + "\n" +
                       "Program Window dimensions: " + board.getWindowSize());
    }
}
