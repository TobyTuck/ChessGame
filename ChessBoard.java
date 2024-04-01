import javax.swing.*;
import java.awt.*; // used for Dimension, GraphicsEnvironment, GraphicsDevice
import java.awt.event.*; // used for MouseAdapter and MouseEvent
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ChessBoard extends JFrame {

    private int boardHeight;

    private JPanel defaultHolder;
    private JLayeredPane layeredPane;

    private BoardMouseAdapter bma;

    private RoundedPanel board,
            nwContainer,
            neContainer,
            swContainer,
            seContainer;

    /**
     * No-arg constructor that creates the board- and adds all pieces to their
     * default positions
     */
    public ChessBoard() {
        // make 'Holder' panels for the background chess (board, whiteCapture,
        // blackCaptured)

        JPanel rightContainer,
                rightFiller,
                settingsFiller,
                settingsPanel,
                leftContainer,
                leftFiller1,
                leftFiller2;

        RoundedPanel capturedWhite,
                     capturedBlackHolder,
                     capturedBlack1,
                     capturedBlack2;

        SettingsButton settings;

        // holds list of all individual squares and their pieces
        List list;

        int screenHeight;
        int screenWidth;

        // get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Color darkGreen = new Color(25, 45, 25);
        Color tan = new Color(210, 180, 140);
        Color brown = new Color(139, 69, 19);
        Color lightGray = new Color(155, 173, 183);

        this.getContentPane().setBackground(darkGreen);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // shall we make the size of the chessboard update as resized?
        this.setResizable(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // set applet's tab icon
        this.setIconImage(new Logo().getImage());
        this.setTitle("Chess");

        screenHeight = screenSize.height;
        screenWidth = screenSize.width;

        // initialize all JComponents
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        defaultHolder = new JPanel(new GridBagLayout());

        board = new RoundedPanel(30);
        nwContainer = new RoundedPanel(30);
        neContainer = new RoundedPanel(30);
        swContainer = new RoundedPanel(30);
        seContainer = new RoundedPanel(30);

        board.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        nwContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        neContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        swContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        seContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        rightContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        rightFiller = new JPanel();
        settingsFiller = new JPanel();
        settingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        leftContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        leftFiller1 = new JPanel();
        capturedWhite = new RoundedPanel(30);
        leftFiller2 = new JPanel();
        capturedBlackHolder = new RoundedPanel(30);
        capturedBlack1 = new RoundedPanel(30);
        capturedBlack2 = new RoundedPanel(30);

        capturedWhite.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        capturedBlackHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        capturedBlack1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        capturedBlack2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        // ensure the board is square- and divides evenly into 8
        int eightDivisible = 0;
        do {
            boardHeight = (int) ((screenHeight - 70) * 0.9) + eightDivisible;
            ++eightDivisible;
        } while (boardHeight % 8 != 0);

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

        int containerWidth = (int) (0.4 * (screenWidth - boardHeight));
        BlackQueen myQueen = new BlackQueen();
        Dimension containerDimension = new Dimension(containerWidth, boardHeight);
        Dimension captureDimension = new Dimension(containerWidth,
                (int) (((double) myQueen.getHeight()) * ((double) (boardHeight / 40.0) /
                        (double) myQueen.getWidth()) + 5));

        // set the size of the board components
        board.setPreferredSize(new Dimension(boardHeight, boardHeight));
        nwContainer.setPreferredSize(new Dimension(boardHeight / 2, boardHeight / 2));
        neContainer.setPreferredSize(new Dimension(boardHeight / 2, boardHeight / 2));
        swContainer.setPreferredSize(new Dimension(boardHeight / 2, boardHeight / 2));
        seContainer.setPreferredSize(new Dimension(boardHeight / 2, boardHeight / 2));

        // set the size of the components that hold the captured pieces
        leftContainer.setPreferredSize(containerDimension);
        leftFiller1.setPreferredSize(new Dimension(containerWidth, (int) (0.1 * boardHeight)));
        capturedWhite.setPreferredSize(new Dimension(containerWidth, captureDimension.height * 2));
        leftFiller2.setPreferredSize(new Dimension(containerWidth, (int) ((boardHeight - 
                                            ((4 * captureDimension.height) + (2 * (0.1 * boardHeight)))))));
        capturedBlackHolder.setPreferredSize(new Dimension(containerWidth, captureDimension.height * 2));
        capturedBlack1.setPreferredSize(captureDimension);
        capturedBlack2.setPreferredSize(captureDimension);

        // set the size of the components corresponding to the settings panel
        rightContainer.setPreferredSize(containerDimension);
        rightFiller.setPreferredSize(new Dimension(containerWidth, (int) (boardHeight / 8.0) + 10));
        settingsFiller.setPreferredSize(new Dimension(containerWidth, 10));
        settingsPanel.setPreferredSize(new Dimension(containerWidth, (int) (boardHeight - (boardHeight / 8.0) - 10)));
        settings = new SettingsButton((int) (boardHeight / 8.0), (int) (boardHeight / 8.0));

        // set the colors of objects belonging to the chessboard
        nwContainer.setBackground(tan);
        neContainer.setBackground(brown);
        swContainer.setBackground(brown);
        seContainer.setBackground(tan);

        // set the colors of the background objects
        defaultHolder.setBackground(darkGreen);
        rightContainer.setBackground(darkGreen);
        rightFiller.setBackground(darkGreen);
        settingsFiller.setBackground(darkGreen);
        settingsPanel.setBackground(darkGreen);
        leftContainer.setBackground(darkGreen);
        capturedWhite.setBackground(lightGray);
        leftFiller1.setBackground(darkGreen);
        capturedBlackHolder.setBackground(lightGray);
        capturedBlack1.setBackground(lightGray);
        capturedBlack2.setBackground(lightGray);
        leftFiller2.setBackground(darkGreen);

        // add components of each square to the list
        list = new List(8);

        RoundedPanel square0 = new RoundedPanel(30);
        list.push(square0, null);
        JPanel square1 = new JPanel();
        list.push(square1, null);
        JPanel square2 = new JPanel();
        list.push(square2, null);
        JPanel square3 = new JPanel();
        list.push(square3, null);
        JPanel square4 = new JPanel();
        list.push(square4, null);
        JPanel square5 = new JPanel();
        list.push(square5, null);
        JPanel square6 = new JPanel();
        list.push(square6, null);
        RoundedPanel square7 = new RoundedPanel(30);
        list.push(square7, null);

        JPanel square8 = new JPanel();
        list.push(square8, null);
        JPanel square9 = new JPanel();
        list.push(square9, null);
        JPanel square10 = new JPanel();
        list.push(square10, null);
        JPanel square11 = new JPanel();
        list.push(square11, null);
        JPanel square12 = new JPanel();
        list.push(square12, null);
        JPanel square13 = new JPanel();
        list.push(square13, null);
        JPanel square14 = new JPanel();
        list.push(square14, null);
        JPanel square15 = new JPanel();
        list.push(square15, null);

        JPanel square16 = new JPanel();
        list.push(square16, null);
        JPanel square17 = new JPanel();
        list.push(square17, null);
        JPanel square18 = new JPanel();
        list.push(square18, null);
        JPanel square19 = new JPanel();
        list.push(square19, null);
        JPanel square20 = new JPanel();
        list.push(square20, null);
        JPanel square21 = new JPanel();
        list.push(square21, null);
        JPanel square22 = new JPanel();
        list.push(square22, null);
        JPanel square23 = new JPanel();
        list.push(square23, null);

        JPanel square24 = new JPanel();
        list.push(square24, null);
        JPanel square25 = new JPanel();
        list.push(square25, null);
        JPanel square26 = new JPanel();
        list.push(square26, null);
        JPanel square27 = new JPanel();
        list.push(square27, null);
        JPanel square28 = new JPanel();
        list.push(square28, null);
        JPanel square29 = new JPanel();
        list.push(square29, null);
        JPanel square30 = new JPanel();
        list.push(square30, null);
        JPanel square31 = new JPanel();
        list.push(square31, null);

        JPanel square32 = new JPanel();
        list.push(square32, null);
        JPanel square33 = new JPanel();
        list.push(square33, null);
        JPanel square34 = new JPanel();
        list.push(square34, null);
        JPanel square35 = new JPanel();
        list.push(square35, null);
        JPanel square36 = new JPanel();
        list.push(square36, null);
        JPanel square37 = new JPanel();
        list.push(square37, null);
        JPanel square38 = new JPanel();
        list.push(square38, null);
        JPanel square39 = new JPanel();
        list.push(square39, null);

        JPanel square40 = new JPanel();
        list.push(square40, null);
        JPanel square41 = new JPanel();
        list.push(square41, null);
        JPanel square42 = new JPanel();
        list.push(square42, null);
        JPanel square43 = new JPanel();
        list.push(square43, null);
        JPanel square44 = new JPanel();
        list.push(square44, null);
        JPanel square45 = new JPanel();
        list.push(square45, null);
        JPanel square46 = new JPanel();
        list.push(square46, null);
        JPanel square47 = new JPanel();
        list.push(square47, null);

        JPanel square48 = new JPanel();
        list.push(square48, null);
        JPanel square49 = new JPanel();
        list.push(square49, null);
        JPanel square50 = new JPanel();
        list.push(square50, null);
        JPanel square51 = new JPanel();
        list.push(square51, null);
        JPanel square52 = new JPanel();
        list.push(square52, null);
        JPanel square53 = new JPanel();
        list.push(square53, null);
        JPanel square54 = new JPanel();
        list.push(square54, null);
        JPanel square55 = new JPanel();
        list.push(square55, null);

        RoundedPanel square56 = new RoundedPanel(30);
        list.push(square56, null);
        JPanel square57 = new JPanel();
        list.push(square57, null);
        JPanel square58 = new JPanel();
        list.push(square58, null);
        JPanel square59 = new JPanel();
        list.push(square59, null);
        JPanel square60 = new JPanel();
        list.push(square60, null);
        JPanel square61 = new JPanel();
        list.push(square61, null);
        JPanel square62 = new JPanel();
        list.push(square62, null);
        RoundedPanel square63 = new RoundedPanel(30);
        list.push(square63, null);

        // Set properties of all the items
        // in the List
        int cellRemainder,
                count = 0;
        JPanel jpanel;

        for (int index = 0; index < list.getSize(); ++index) {

            // type conversions
            jpanel = (JPanel) list.pop(index);

            // set size of the JPanel- (8.57, 8.56)
            jpanel.setPreferredSize(new Dimension((int) (boardHeight / 8.0),
                    (int) (boardHeight / 8.0)));

            cellRemainder = index % 2;
            if ((int) (count / 8) % 2 == 0) {
                // test if index is even or odd
                // even
                if (cellRemainder == 0)
                    jpanel.setBackground(tan);

                // odd
                else
                    jpanel.setBackground(brown);
            }

            else {
                // test if index is even or odd
                // even
                if (cellRemainder == 0)
                    jpanel.setBackground(brown);

                // odd
                else
                    jpanel.setBackground(tan);
            }

            // set the Layout manager for each jpanel
            jpanel.setLayout(new BorderLayout());

            // add components to respective container
            if (index == 0 || index == 1 || index == 2 || index == 3 || index == 8 || index == 9 ||
                    index == 10 || index == 11 || index == 16 || index == 17 || index == 18 ||
                    index == 19 || index == 24 || index == 25 || index == 26 || index == 27)
                nwContainer.add(jpanel);

            else if (index == 4 || index == 5 || index == 6 || index == 7 || index == 12 ||
                    index == 13 || index == 14 || index == 15 || index == 20 || index == 21 ||
                    index == 22 || index == 23 || index == 28 || index == 29 || index == 30 ||
                    index == 31)
                neContainer.add(jpanel);

            else if (index == 32 || index == 33 || index == 34 || index == 35 || index == 40 ||
                    index == 41 || index == 42 || index == 43 || index == 48 || index == 49 ||
                    index == 50 || index == 51 || index == 56 || index == 57 || index == 58 ||
                    index == 59)
                swContainer.add(jpanel);

            else
                seContainer.add(jpanel);

            ++count;
        }

        // add chesspieces to their default starting points
        try {
            // classic chesspiece images
            BufferedImage whitePawnImage = ImageIO.read(new File("WhitePawn.png"));
            BufferedImage whiteRookImage = ImageIO.read(new File("WhiteRook.png"));
            BufferedImage whiteKnightImage = ImageIO.read(new File("WhiteKnight.png"));
            BufferedImage whiteBishopImage = ImageIO.read(new File("WhiteBishop.png"));
            BufferedImage whiteQueenImage = ImageIO.read(new File("WhiteQueen.png"));
            BufferedImage whiteKingImage = ImageIO.read(new File("WhiteKing.png"));
            BufferedImage blackPawnImage = ImageIO.read(new File("BlackPawn.png"));
            BufferedImage blackRookImage = ImageIO.read(new File("BlackRook.png"));
            BufferedImage blackKnightImage = ImageIO.read(new File("BlackKnight.png"));
            BufferedImage blackBishopImage = ImageIO.read(new File("BlackBishop.png"));
            BufferedImage blackQueenImage = ImageIO.read(new File("BlackQueen.png"));
            BufferedImage blackKingImage = ImageIO.read(new File("BlackKing.png"));

            // "modern" chesspiece images
            BufferedImage wPawnImage = ImageIO.read(new File("WPawn.png"));
            BufferedImage wRookImage = ImageIO.read(new File("WRook.png"));
            BufferedImage wKnightImage = ImageIO.read(new File("WKnight.png"));
            BufferedImage wBishopImage = ImageIO.read(new File("WBishop.png"));
            BufferedImage wQueenImage = ImageIO.read(new File("WQueen.png"));
            BufferedImage wKingImage = ImageIO.read(new File("WKing.png"));
            BufferedImage bPawnImage = ImageIO.read(new File("BPawn.png"));
            BufferedImage bRookImage = ImageIO.read(new File("BRook.png"));
            BufferedImage bKnightImage = ImageIO.read(new File("BKnight.png"));
            BufferedImage bBishopImage = ImageIO.read(new File("BBishop.png"));
            BufferedImage bQueenImage = ImageIO.read(new File("BQueen.png"));
            BufferedImage bKingImage = ImageIO.read(new File("BKing.png"));

            list.addComponent(list.pop(0), new BlackRook(blackRookImage, 1));
            list.addComponent(list.pop(1), new BlackKnight(blackKnightImage, 2));
            list.addComponent(list.pop(2), new BlackBishop(blackBishopImage, 3));
            list.addComponent(list.pop(3), new BlackQueen(blackQueenImage, 4));
            list.addComponent(list.pop(4), new BlackKing(blackKingImage, 5));
            list.addComponent(list.pop(5), new BlackBishop(blackBishopImage, 6));
            list.addComponent(list.pop(6), new BlackKnight(blackKnightImage, 7));
            list.addComponent(list.pop(7), new BlackRook(blackRookImage, 8));
            list.addComponent(list.pop(8), new BlackPawn(blackPawnImage, 9));
            list.addComponent(list.pop(9), new BlackPawn(blackPawnImage, 10));
            list.addComponent(list.pop(10), new BlackPawn(blackPawnImage, 11));
            list.addComponent(list.pop(11), new BlackPawn(blackPawnImage, 12));
            list.addComponent(list.pop(12), new BlackPawn(blackPawnImage, 13));
            list.addComponent(list.pop(13), new BlackPawn(blackPawnImage, 14));
            list.addComponent(list.pop(14), new BlackPawn(blackPawnImage, 15));
            list.addComponent(list.pop(15), new BlackPawn(blackPawnImage, 16));
            list.addComponent(list.pop(48), new WhitePawn(whitePawnImage, 49));
            list.addComponent(list.pop(49), new WhitePawn(whitePawnImage, 50));
            list.addComponent(list.pop(50), new WhitePawn(whitePawnImage, 51));
            list.addComponent(list.pop(51), new WhitePawn(whitePawnImage, 52));
            list.addComponent(list.pop(52), new WhitePawn(whitePawnImage, 53));
            list.addComponent(list.pop(53), new WhitePawn(whitePawnImage, 54));
            list.addComponent(list.pop(54), new WhitePawn(whitePawnImage, 55));
            list.addComponent(list.pop(55), new WhitePawn(whitePawnImage, 56));
            list.addComponent(list.pop(56), new WhiteRook(whiteRookImage, 57));
            list.addComponent(list.pop(57), new WhiteKnight(whiteKnightImage, 58));
            list.addComponent(list.pop(58), new WhiteBishop(whiteBishopImage, 59));
            list.addComponent(list.pop(59), new WhiteQueen(whiteQueenImage, 60));
            list.addComponent(list.pop(60), new WhiteKing(whiteKingImage, 61));
            list.addComponent(list.pop(61), new WhiteBishop(whiteBishopImage, 62));
            list.addComponent(list.pop(62), new WhiteKnight(whiteKnightImage, 63));
            list.addComponent(list.pop(63), new WhiteRook(whiteRookImage, 64));
        } catch (IOException exception) {
            System.out.println("Error locating chesspiece image file(s)");
        }

        // add chesspiece images to their squares
        for (int index = 0; index < list.getSize(); ++index) {
            Object component = list.getComponent(index);

            if (component != null && component instanceof ChessPiece) {
                // place each piece on their squares
                pin((ChessPiece) component, (JPanel) list.pop(index), 0, 0, "BorderLayout", true);
            }
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);

        // add components to their parent containers
        board.add(nwContainer);
        board.add(neContainer);
        board.add(swContainer);
        board.add(seContainer);

        // add the panels that hold the captured black pieces to their holder
        capturedBlackHolder.add(capturedBlack2);
        capturedBlackHolder.add(capturedBlack1);

        // add the panels where captured pieces are placed
        leftContainer.add(leftFiller1);
        leftContainer.add(capturedWhite);
        leftContainer.add(leftFiller2);
        leftContainer.add(capturedBlackHolder);

        // add panels where settings options are placed
        rightFiller.add(settings, gbc);
        rightContainer.add(rightFiller);
        rightContainer.add(settingsFiller);
        rightContainer.add(settingsPanel);

        // add all container panels to the default panel
        defaultHolder.add(leftContainer, gbc);
        defaultHolder.add(board, gbc);
        defaultHolder.add(rightContainer, gbc);

        // add the default panel to its correct "layer"
        layeredPane.add(defaultHolder, JLayeredPane.DEFAULT_LAYER);
        this.add(layeredPane);

        // instantiate and add MouseAdapter to chessboard
        bma = new BoardMouseAdapter(list, boardHeight, screenWidth, layeredPane,
                capturedWhite, capturedBlack1, capturedBlack2, board, neContainer,
                nwContainer, seContainer, swContainer);

        // instantiate and add MouseAdapter to settings
        SettingsActionListener sma = new SettingsActionListener((int) (boardHeight / 9.0),
                (int) (boardHeight / 9.0), settingsPanel, lightGray, darkGreen, bma);

        // How can I make this representative of the layeredPane without getting LP
        nwContainer.addMouseListener(bma);
        neContainer.addMouseListener(bma);
        swContainer.addMouseListener(bma);
        seContainer.addMouseListener(bma);

        nwContainer.addMouseMotionListener(bma);
        neContainer.addMouseMotionListener(bma);
        swContainer.addMouseMotionListener(bma);
        seContainer.addMouseMotionListener(bma);

        settings.addActionListener(sma);

        this.setVisible(true);
    }

    private void pin(ChessPiece piece, JPanel panel, int width, int height, String layoutDecider,
            boolean dimensionDecider) {

        // default height and width for each chesspiece
        if (width == 0 && height == 0) {
            if (piece instanceof BlackPawn || piece instanceof WhitePawn) {
                height = (int) ((boardHeight / 8.0) * 0.6);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            else if (piece instanceof BlackRook || piece instanceof WhiteRook) {
                height = (int) ((boardHeight / 8.0) * 0.75);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            else if (piece instanceof BlackKnight || piece instanceof WhiteKnight) {
                height = (int) ((boardHeight / 8.0) * 0.75);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            else if (piece instanceof BlackBishop || piece instanceof WhiteBishop) {
                height = (int) ((boardHeight / 8.0) * 0.8);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            else if (piece instanceof BlackQueen || piece instanceof WhiteQueen) {
                height = (int) ((boardHeight / 8.0) * 0.85);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }

            // ChessPiece is a king
            else {
                height = (int) ((boardHeight / 8.0) * 0.9);
                width = (int) ((double) (piece.getWidth() *
                        ((double) height / (double) piece.getHeight())));
            }
        }

        // rescale image to fit its jpanel
        piece.scaleImage(width, height);

        // create the label and set preferrences
        JLabel label = new JLabel(piece.toImageIcon(), JLabel.CENTER);

        // determine whether or not the label should be set to the size of the label or
        // not
        if (dimensionDecider == true)
            label.setPreferredSize(panel.getPreferredSize());

        else {
            label.setPreferredSize(new Dimension(width, height));
        }

        // use layout manager specified in the parameters
        if (layoutDecider.equals("BorderLayout"))
            panel.add(label, BorderLayout.CENTER);

        else if (layoutDecider.equals("FlowLayout"))
            panel.add(label);
    }

    /**
     * Method that returns the size of the Panel that represents the default panel
     * in the JLayeredPane
     */
    public Dimension getLayeredSize() {
        return layeredPane.getSize();
    }

    private class SettingsButton extends RoundButton {

        private ImageIcon _image;

        public SettingsButton(int width, int height) {
            super();
            setPreferredSize(new Dimension(width, height));

            ImageIcon image = new ImageIcon("Settings.png");
            Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

            _image = new ImageIcon(scaledImage);
            setIcon(_image);
        }

        protected void paintComponent(Graphics g) {
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
            super.paintComponent(g);
        }
    }
}
