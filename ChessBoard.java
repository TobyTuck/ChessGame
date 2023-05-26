import javax.swing.*;
import java.awt.*;  // used for Dimension, GraphicsEnvironment, GraphicsDevice
import java.awt.event.*;  // used for MouseAdapter and MouseEvent
import java.util.Random;
import java.awt.image.BufferedImage;
import java.awt.geom.RoundRectangle2D;

public class ChessBoard extends JFrame{

    private int boardHeight;

    private JPanel defaultHolder;
    private JLayeredPane layeredPane;

    /**
    No-arg constructor that creates the board- and adds all pieces to their default positions
    */
    public ChessBoard(){
        // make 'Holder' panels for the background chess (board, whiteCapture, blackCaptured)
        RoundedPanel board;
        JPanel capturedWhite;
        JPanel capturedBlack1;
        JPanel capturedBlack2;

        // holds list of all individual squares and their pieces
        List list;

        int screenHeight;
        int screenWidth;

        // get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Color darkGreen = new Color(25, 45, 25);

        this.getContentPane().setBackground(darkGreen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // shall we make the size of the chessboard update as resized?
        this.setResizable(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // set applet's tab icon
        this.setIconImage(new Logo().getImage());
        this.setTitle("Pragmatic Chess");

        screenHeight = screenSize.height;
        screenWidth = screenSize.width;

        // initialize all JComponents 
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        defaultHolder = new JPanel(new GridBagLayout());

        board = new RoundedPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JPanel rightContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        capturedWhite = new JPanel(new FlowLayout());

        JPanel rightFiller = new JPanel();
        capturedBlack1 = new JPanel(new FlowLayout());
        capturedBlack2 = new JPanel(new FlowLayout());

        // round the corners of the chess board
        board.setBackground(new Color(210, 180, 140));

        // ensure the board is square- and divides evenly into 8 
        int eightDivisible = 0;
        do{
            boardHeight = (int)((screenHeight - 70) * 0.9) + eightDivisible;
            ++eightDivisible;
        }while(boardHeight % 8 != 0);

        // set the size of all JComponents as necessary
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // get the height of the taskbar
                Insets myInsets = ChessBoard.this.getInsets();
                int toolBarHeight = myInsets.top;

                // get the size of the JFrame container
                // to get height, subtract JFrame size from its toolbar height 
                int width = ChessBoard.this.getWidth();
                int height = ChessBoard.this.getHeight() - toolBarHeight;

                // set the size of the default holder to the same size as the JFrame
                defaultHolder.setSize(new Dimension(width, height));
                defaultHolder.revalidate();
            }
        });

        defaultHolder.setLayout(new GridBagLayout());

        Dimension containerDimension = new Dimension((int) (0.4 * (screenWidth - boardHeight)),
                                                     (int) (boardHeight * 0.7));

        board.setPreferredSize(new Dimension(boardHeight, boardHeight));
        capturedWhite.setPreferredSize(containerDimension);
        rightContainer.setPreferredSize(containerDimension);

        BlackQueen myQueen = new BlackQueen();

        rightFiller.setPreferredSize(new Dimension((int) (0.4 * (screenWidth - boardHeight)),
                                                   (int) ((boardHeight * 0.7) - 
                                                   (2 * ((int) (((double) myQueen.getHeight()) * 
                                                   ((double) (boardHeight / 40.0) / 
                                                   (double) myQueen.getWidth()) + 10)))))); 
        capturedBlack1.setPreferredSize(new Dimension((int) (0.4 * (screenWidth - boardHeight)),
                                                     (int) (((double) myQueen.getHeight()) * ((double)
                                                     (boardHeight / 40.0) / 
                                                     (double) myQueen.getWidth()) + 5)));
        capturedBlack2.setPreferredSize(new Dimension((int) (0.4 * (screenWidth - boardHeight)),
                                                     (int) (((double) myQueen.getHeight()) * ((double)
                                                     (boardHeight / 40.0) / 
                                                     (double) myQueen.getWidth()) + 5)));

        // set the colors of JComponents
        defaultHolder.setBackground(darkGreen);
        capturedWhite.setBackground(darkGreen);
        rightContainer.setBackground(darkGreen);
        // capturedWhite.setBackground(Color.black);
        rightFiller.setBackground(darkGreen);
        // rightFiller.setBackground(Color.red);
        capturedBlack1.setBackground(darkGreen);
        // capturedBlack1.setBackground(Color.white);
        capturedBlack2.setBackground(darkGreen);
        // capturedBlack2.setBackground(Color.blue);

        // add components of each square to the list
        list = new List(8);

        RoundedPanel square0 = new RoundedPanel(); list.push(square0, null);
        RoundedPanel square1 = new RoundedPanel(); list.push(square1, null);
        RoundedPanel square2 = new RoundedPanel(); list.push(square2, null);
        RoundedPanel square3 = new RoundedPanel(); list.push(square3, null);
        RoundedPanel square4 = new RoundedPanel(); list.push(square4, null);
        RoundedPanel square5 = new RoundedPanel(); list.push(square5, null);
        RoundedPanel square6 = new RoundedPanel(); list.push(square6, null);
        RoundedPanel square7 = new RoundedPanel(); list.push(square7, null);
        RoundedPanel square8 = new RoundedPanel(); list.push(square8, null);
        RoundedPanel square9 = new RoundedPanel(); list.push(square9, null);
        
        RoundedPanel square10 = new RoundedPanel(); list.push(square10, null);
        RoundedPanel square11 = new RoundedPanel(); list.push(square11, null);
        RoundedPanel square12 = new RoundedPanel(); list.push(square12, null);
        RoundedPanel square13 = new RoundedPanel(); list.push(square13, null);
        RoundedPanel square14 = new RoundedPanel(); list.push(square14, null);
        RoundedPanel square15 = new RoundedPanel(); list.push(square15, null);
        RoundedPanel square16 = new RoundedPanel(); list.push(square16, null);
        RoundedPanel square17 = new RoundedPanel(); list.push(square17, null);
        RoundedPanel square18 = new RoundedPanel(); list.push(square18, null);
        RoundedPanel square19 = new RoundedPanel(); list.push(square19, null);

        RoundedPanel square20 = new RoundedPanel(); list.push(square20, null);
        RoundedPanel square21 = new RoundedPanel(); list.push(square21, null);
        RoundedPanel square22 = new RoundedPanel(); list.push(square22, null);
        RoundedPanel square23 = new RoundedPanel(); list.push(square23, null);
        RoundedPanel square24 = new RoundedPanel(); list.push(square24, null);
        RoundedPanel square25 = new RoundedPanel(); list.push(square25, null);
        RoundedPanel square26 = new RoundedPanel(); list.push(square26, null);
        RoundedPanel square27 = new RoundedPanel(); list.push(square27, null);
        RoundedPanel square28 = new RoundedPanel(); list.push(square28, null);
        RoundedPanel square29 = new RoundedPanel(); list.push(square29, null);

        RoundedPanel square30 = new RoundedPanel(); list.push(square30, null);
        RoundedPanel square31 = new RoundedPanel(); list.push(square31, null);
        RoundedPanel square32 = new RoundedPanel(); list.push(square32, null);
        RoundedPanel square33 = new RoundedPanel(); list.push(square33, null);
        RoundedPanel square34 = new RoundedPanel(); list.push(square34, null);
        RoundedPanel square35 = new RoundedPanel(); list.push(square35, null);
        RoundedPanel square36 = new RoundedPanel(); list.push(square36, null);
        RoundedPanel square37 = new RoundedPanel(); list.push(square37, null);
        RoundedPanel square38 = new RoundedPanel(); list.push(square38, null);
        RoundedPanel square39 = new RoundedPanel(); list.push(square39, null);

        RoundedPanel square40 = new RoundedPanel(); list.push(square40, null);
        RoundedPanel square41 = new RoundedPanel(); list.push(square41, null);
        RoundedPanel square42 = new RoundedPanel(); list.push(square42, null);
        RoundedPanel square43 = new RoundedPanel(); list.push(square43, null);
        RoundedPanel square44 = new RoundedPanel(); list.push(square44, null);
        RoundedPanel square45 = new RoundedPanel(); list.push(square45, null);
        RoundedPanel square46 = new RoundedPanel(); list.push(square46, null);
        RoundedPanel square47 = new RoundedPanel(); list.push(square47, null);
        RoundedPanel square48 = new RoundedPanel(); list.push(square48, null);
        RoundedPanel square49 = new RoundedPanel(); list.push(square49, null);

        RoundedPanel square50 = new RoundedPanel(); list.push(square50, null);
        RoundedPanel square51 = new RoundedPanel(); list.push(square51, null);
        RoundedPanel square52 = new RoundedPanel(); list.push(square52, null);
        RoundedPanel square53 = new RoundedPanel(); list.push(square53, null);
        RoundedPanel square54 = new RoundedPanel(); list.push(square54, null);
        RoundedPanel square55 = new RoundedPanel(); list.push(square55, null);
        RoundedPanel square56 = new RoundedPanel(); list.push(square56, null);
        RoundedPanel square57 = new RoundedPanel(); list.push(square57, null);
        RoundedPanel square58 = new RoundedPanel(); list.push(square58, null);
        RoundedPanel square59 = new RoundedPanel(); list.push(square59, null);

        RoundedPanel square60 = new RoundedPanel(); list.push(square60, null);
        RoundedPanel square61 = new RoundedPanel(); list.push(square61, null);
        RoundedPanel square62 = new RoundedPanel(); list.push(square62, null);
        RoundedPanel square63 = new RoundedPanel(); list.push(square63, null);

        //Set properties of all the items
        // in the List
        int cellRemainder,
            count = 0;
        JPanel jpanel;

        for(int index = 0; index < list.getSize(); ++index){

            // type conversions
            jpanel = (JPanel) list.pop(index);
            
            // set size of the JPanel- (8.57, 8.56)
            jpanel.setPreferredSize(new Dimension((int)(boardHeight / 8.0), 
                                                  (int)(boardHeight / 8.0)));

            // make the two colors of the chessboard
            Color tan = new Color(210, 180, 140);
            Color brown = new Color(139, 69, 19);

            cellRemainder = index % 2;
            if((int)(count / 8) % 2 == 0){
                // test if index is even or odd
                // even
                if(cellRemainder == 0)
                    jpanel.setBackground(tan);

                // odd
                else
                    jpanel.setBackground(brown);}

            else{
                // test if index is even or odd
                // even
                if(cellRemainder == 0)
                    jpanel.setBackground(brown);

                // odd
                else
                    jpanel.setBackground(tan);}

            // set the Layout manager for each jpanel
            jpanel.setLayout(new BorderLayout());

            // add components to board
            board.add(jpanel);
            ++count;}

        // add chesspieces to their default starting points
        list.addComponent(list.pop(0), new BlackRook());
        list.addComponent(list.pop(1), new BlackKnight());
        list.addComponent(list.pop(2), new BlackBishop());
        list.addComponent(list.pop(3), new BlackQueen());
        list.addComponent(list.pop(4), new BlackKing());
        list.addComponent(list.pop(5), new BlackBishop());
        list.addComponent(list.pop(6), new BlackKnight());
        list.addComponent(list.pop(7), new BlackRook());
        list.addComponent(list.pop(8), new BlackPawn());
        list.addComponent(list.pop(9), new BlackPawn());
        list.addComponent(list.pop(10), new BlackPawn());
        list.addComponent(list.pop(11), new BlackPawn());
        list.addComponent(list.pop(12), new BlackPawn());
        list.addComponent(list.pop(13), new BlackPawn());
        list.addComponent(list.pop(14), new BlackPawn());
        list.addComponent(list.pop(15), new BlackPawn());
        list.addComponent(list.pop(48), new WhitePawn());
        list.addComponent(list.pop(49), new WhitePawn());
        list.addComponent(list.pop(50), new WhitePawn());
        list.addComponent(list.pop(51), new WhitePawn());
        list.addComponent(list.pop(52), new WhitePawn());
        list.addComponent(list.pop(53), new WhitePawn());
        list.addComponent(list.pop(54), new WhitePawn());
        list.addComponent(list.pop(55), new WhitePawn());
        list.addComponent(list.pop(56), new WhiteRook());
        list.addComponent(list.pop(57), new WhiteKnight());
        list.addComponent(list.pop(58), new WhiteBishop());
        list.addComponent(list.pop(59), new WhiteQueen());
        list.addComponent(list.pop(60), new WhiteKing());
        list.addComponent(list.pop(61), new WhiteBishop());
        list.addComponent(list.pop(62), new WhiteKnight());
        list.addComponent(list.pop(63), new WhiteRook());

        // add chesspiece images to their squares
        for(int index = 0; index < list.getSize(); ++index){
            Object component = list.getComponent(index);

            if(component != null && component instanceof ChessPiece){
                // place each piece on their squares
                pin((ChessPiece) component, (JPanel) list.pop(index), 0, 0, "BorderLayout", true);} }

        // add components to their parent containers
        rightContainer.add(rightFiller);
        rightContainer.add(capturedBlack2);
        rightContainer.add(capturedBlack1);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        defaultHolder.add(capturedWhite, gbc);
        defaultHolder.add(board, gbc);
        defaultHolder.add(rightContainer, gbc);

        layeredPane.add(defaultHolder, JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane);

        // instantiate and add MouseAdapter to chessboard
        MyMouseAdapter mma = new MyMouseAdapter(list, boardHeight, screenWidth, layeredPane, 
                                                capturedWhite, capturedBlack1, capturedBlack2, board);

        // How can I make this representative of the layeredPane without getting LP clicks?
        board.addMouseListener(mma);
        board.addMouseMotionListener(mma);

        this.setVisible(true);
    }

    private void pin(ChessPiece piece, JPanel panel, int width, int height, String layoutDecider,
                     boolean dimensionDecider){
                
        // default height and width for each chesspiece
        if(width == 0 && height == 0){
            if(piece instanceof BlackPawn || piece instanceof WhitePawn){
                height = (int) ((boardHeight / 8.0) * 0.6);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            else if(piece instanceof BlackRook || piece instanceof WhiteRook){
                height = (int) ((boardHeight / 8.0) * 0.75);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            else if(piece instanceof BlackKnight || piece instanceof WhiteKnight){
                height = (int) ((boardHeight / 8.0) * 0.75);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            else if(piece instanceof BlackBishop || piece instanceof WhiteBishop){ 
                height = (int) ((boardHeight / 8.0) * 0.8);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            else if(piece instanceof BlackQueen || piece instanceof WhiteQueen){ 
                height = (int) ((boardHeight / 8.0) * 0.85);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));}

            // ChessPiece is a king
            else{ 
                height = (int) ((boardHeight / 8.0) * 0.9);
                width = (int) ((double) (piece.getWidth() * 
                        ((double) height / (double) piece.getHeight())));} } 

        // rescale image to fit its jpanel
        piece.scaleImage(width, height);

        // create the label and set preferrences
        JLabel label = new JLabel(piece.toImageIcon(), JLabel.CENTER);

        // determine whether or not the label should be set to the size of the label or not
        if(dimensionDecider == true)
            label.setPreferredSize(panel.getPreferredSize());

        else{
            label.setPreferredSize(new Dimension(width, height));}

        // use layout manager specified in the parameters
        if(layoutDecider.equals("BorderLayout"))
            panel.add(label, BorderLayout.CENTER);

        else if(layoutDecider.equals("FlowLayout"))
            panel.add(label);
    }

    /**
    Method that returns the size of the Panel that represents the default panel in the JLayeredPane
    */
    public Dimension getLayeredSize(){
        return layeredPane.getSize();
    }
}
