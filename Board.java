import javax.swing.*;
import java.awt.*;  // used for Dimension
import java.awt.event.*;
// import java.lang.Math;

public class Board extends JFrame 
                   /* implements ActionListener */{

    // JPanel that consists of the entire 8 * 8 chessboard
    private JPanel board;

    //holds all the individual squares
    List list;

    // individual squares of the chessboard
    private JPanel square0;
    private JPanel square1;
    private JPanel square2;
    private JPanel square3;
    private JPanel square4;
    private JPanel square5;
    private JPanel square6;
    private JPanel square7;
    private JPanel square8;
    private JPanel square9;

    private JPanel square10;
    private JPanel square11;
    private JPanel square12;
    private JPanel square13;
    private JPanel square14;
    private JPanel square15;
    private JPanel square16;
    private JPanel square17;
    private JPanel square18;
    private JPanel square19;

    private JPanel square20;
    private JPanel square21;
    private JPanel square22;
    private JPanel square23;
    private JPanel square24;
    private JPanel square25;
    private JPanel square26;
    private JPanel square27;
    private JPanel square28;
    private JPanel square29;

    private JPanel square30;
    private JPanel square31;
    private JPanel square32;
    private JPanel square33;
    private JPanel square34;
    private JPanel square35;
    private JPanel square36;
    private JPanel square37;
    private JPanel square38;
    private JPanel square39;

    private JPanel square40;
    private JPanel square41;
    private JPanel square42;
    private JPanel square43;
    private JPanel square44;
    private JPanel square45;
    private JPanel square46;
    private JPanel square47;
    private JPanel square48;
    private JPanel square49;

    private JPanel square50;
    private JPanel square51;
    private JPanel square52;
    private JPanel square53;
    private JPanel square54;
    private JPanel square55;
    private JPanel square56;
    private JPanel square57;
    private JPanel square58;
    private JPanel square59;

    private JPanel square60;
    private JPanel square61;
    private JPanel square62;
    private JPanel square63;

    /**
    No-arg constructor that  creates the board
    */
    public Board(){
        // build the JFrame
        this.setDefaultCloseOperation
            (JFrame.EXIT_ON_CLOSE);
        // get the screen size
        Dimension screenSize = 
            Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLayout(new BorderLayout());
        getContentPane().setBackground(Color.green);

        // build the board
        board = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        board.setPreferredSize(new Dimension
            (Math.round(screenSize.width * 0.75), 
            Math.round(screenSize.height * 0.75)));

        // add components of each square to the list
        list = new List();

        square0 = new JPanel(); list.push(square0);
        square1 = new JPanel(); list.push(square1);
        square2 = new JPanel(); list.push(square2);
        square3 = new JPanel(); list.push(square3);
        square4 = new JPanel(); list.push(square4);
        square5 = new JPanel(); list.push(square5);
        square6 = new JPanel(); list.push(square6);
        square7 = new JPanel(); list.push(square7);
        square8 = new JPanel(); list.push(square8);
        square9 = new JPanel(); list.push(square9);
        
        square10 = new JPanel(); list.push(square10);
        square11 = new JPanel(); list.push(square11);
        square12 = new JPanel(); list.push(square12);
        square13 = new JPanel(); list.push(square13);
        square14 = new JPanel(); list.push(square14);
        square15 = new JPanel(); list.push(square15);
        square16 = new JPanel(); list.push(square16);
        square17 = new JPanel(); list.push(square17);
        square18 = new JPanel(); list.push(square18);
        square19 = new JPanel(); list.push(square19);

        square20 = new JPanel(); list.push(square20);
        square21 = new JPanel(); list.push(square21);
        square22 = new JPanel(); list.push(square22);
        square23 = new JPanel(); list.push(square23);
        square24 = new JPanel(); list.push(square24);
        square25 = new JPanel(); list.push(square25);
        square26 = new JPanel(); list.push(square26);
        square27 = new JPanel(); list.push(square27);
        square28 = new JPanel(); list.push(square28);
        square29 = new JPanel(); list.push(square29);

        square30 = new JPanel(); list.push(square30);
        square31 = new JPanel(); list.push(square31);
        square32 = new JPanel(); list.push(square32);
        square33 = new JPanel(); list.push(square33);
        square34 = new JPanel(); list.push(square34);
        square35 = new JPanel(); list.push(square35);
        square36 = new JPanel(); list.push(square36);
        square37 = new JPanel(); list.push(square37);
        square38 = new JPanel(); list.push(square38);
        square39 = new JPanel(); list.push(square39);

        square40 = new JPanel(); list.push(square40);
        square41 = new JPanel(); list.push(square41);
        square42 = new JPanel(); list.push(square42);
        square43 = new JPanel(); list.push(square43);
        square44 = new JPanel(); list.push(square44);
        square45 = new JPanel(); list.push(square45);
        square46 = new JPanel(); list.push(square46);
        square47 = new JPanel(); list.push(square47);
        square48 = new JPanel(); list.push(square48);
        square49 = new JPanel(); list.push(square49);

        square50 = new JPanel(); list.push(square50);
        square51 = new JPanel(); list.push(square51);
        square52 = new JPanel(); list.push(square52);
        square53 = new JPanel(); list.push(square53);
        square54 = new JPanel(); list.push(square54);
        square55 = new JPanel(); list.push(square55);
        square56 = new JPanel(); list.push(square56);
        square57 = new JPanel(); list.push(square57);
        square58 = new JPanel(); list.push(square58);
        square59 = new JPanel(); list.push(square59);

        square60 = new JPanel(); list.push(square60);
        square61 = new JPanel(); list.push(square61);
        square62 = new JPanel(); list.push(square62);
        square63 = new JPanel(); list.push(square63);

        // Instantiate and set properties of all the items
        // in the List
        int remainder;
        for(int index = 0; index < list.getSize(); ++index){
            // test if index is even or odd
            remainder = index % 2;

            // even
            if(remainder == 0){
                list.pop(index).setBackground(Color.white);}

            // odd
            else{
                list.pop(index).setBackground(Color.black);}

            // add components to board
            board.add(list.pop(index));}

        this.add(board, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
