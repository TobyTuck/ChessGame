public class Delete{

    public static void main(String[] args){
    ChessBoard board = new ChessBoard();
    
    // print the dimensions of the JFrame and its components
    System.out.println("Chess cell dimensions: " + board.getCellDimension() + "\n" +
                       "Chess Board dimensions: " + board.getBoardDimension() + "\n" +
                       "Chess North dimensions: " + board.getNorthDimension() + "\n" +
                       "Chess South dimensions: " + board.getSouthDimension() + "\n" +
                       "Chess West dimensions: " + board.getWestDimension() + "\n" +
                       "Chess East dimensions: " + board.getEastDimension() + "\n" +
                       "Program Window dimensions: " + board.getWindowSize());
    }
}
