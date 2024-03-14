/*
This is an ActionListener for the Classic button from the Settings Panel
*/

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ClassicActionListener implements ActionListener {

    private BoardMouseAdapter _bma;

    // fields that correspond to instance of this button
    private boolean _isSelected;
    private JButton _myButton;
    private ImageIcon _selectedImage;
    private ImageIcon _unselectedImage;

    // fields that correspond to the other option
    private ModernActionListener _otherOption;

    public ClassicActionListener(int width, int height, JButton button, BoardMouseAdapter bma) {
        _bma = bma;
        _isSelected = true;
        _myButton = button;

        ImageIcon image1 = new ImageIcon("ClassicSelected.png");
        ImageIcon image2 = new ImageIcon("ClassicUnselected.png");

        Image scaledImage1 = image1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Image scaledImage2 = image2.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        _selectedImage = new ImageIcon(scaledImage1);
        _unselectedImage = new ImageIcon(scaledImage2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!_isSelected) {
            // set properties of this button
            _bma.makeClassic();
            _isSelected = true;
            _myButton.setIcon(_selectedImage);
            _myButton.revalidate();
            _myButton.repaint();

            // set properties of other option
            _otherOption.unselect();
        }
    }

    public void addModernActionListener(ModernActionListener otherOption) {
        _otherOption = otherOption;
    }

    public void unselect() {
        _isSelected = false;
        _myButton.setIcon(_unselectedImage);

        _myButton.revalidate();
        _myButton.repaint();
    }
}
