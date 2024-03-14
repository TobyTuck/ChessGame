/*
This is an ActionListener for the Bot Game Mode button on the Settings Panel
*/

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class BotActionListener implements ActionListener {

    private BoardMouseAdapter _bma;

    // fields that correspond to instance of this button
    private boolean _isSelected;
    private JButton _myButton;
    private ImageIcon _selectedImage;
    private ImageIcon _unselectedImage;

    // fields that correspond to the other option
    private DualActionListener _otherOption;

    private int _rating;

    public BotActionListener(int width, int height, JButton button, BoardMouseAdapter bma) {
        _bma = bma;
        _isSelected = false;
        _myButton = button;

        ImageIcon image1 = new ImageIcon("ComputerBotSelected.png");
        ImageIcon image2 = new ImageIcon("ComputerBotUnselected.png");

        Image scaledImage1 = image1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Image scaledImage2 = image2.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        _selectedImage = new ImageIcon(scaledImage1);
        _unselectedImage = new ImageIcon(scaledImage2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!_isSelected) {
            // set properties of this button
            _isSelected = true;
            _myButton.setIcon(_selectedImage);
            _myButton.revalidate();
            _myButton.repaint();

            // set properties of other option
            _otherOption.unselect();

            // delete
            System.out.println(_rating);
        }
    }

    public void addDualActionListener(DualActionListener otherOption) {
        _otherOption = otherOption;
    }

    public void unselect() {
        _isSelected = false;
        _myButton.setIcon(_unselectedImage);

        _myButton.revalidate();
        _myButton.repaint();
    }

    /**
     * Method that sets the value of the stockfish rating field value
     */
    public void setRating(int rating) {
        _rating = rating;
    }
}