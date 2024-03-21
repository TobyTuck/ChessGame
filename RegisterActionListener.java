/*
This is an ActionListener for the Register button under the Account Settings
*/

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class RegisterActionListener implements ActionListener {

    // fields that correspond to instance of this button
    private JPanel _background;
    private JPanel _panel1;
    private JPanel _panel2;
    private JPanel _panel3;
    private JPanel _panel4;
    private JPanel _panel5;

    private Font _labelFont;
    private Dimension _mediumPanelSize;

    private boolean _isSelected;

    public RegisterActionListener(JPanel background, JPanel p1, JPanel p2, JPanel p3, JPanel p4,
            JPanel p5, Font f, Dimension d) {
        _background = background;
        _panel1 = p1;
        _panel2 = p2;
        _panel3 = p3;
        _panel4 = p4;
        _panel5 = p5;

        _labelFont = f;
        _mediumPanelSize = d;

        _isSelected = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!_isSelected) {
            // change what buttons are available under the Account settings
            removeComponents();
            addComponents();

            _isSelected = true;
        }
    }

    /**
     * Method that removes the old Account components
     */
    private void removeComponents() {
        _background.remove(_panel1);
        _background.remove(_panel2);
        _background.remove(_panel3);
        _background.remove(_panel4);
        _background.remove(_panel5);
    }

    /**
     * Method that adds the new Account components
     */
    private void addComponents() {
        // strip components from each of the panels
        stripComponents(_panel1);
        stripComponents(_panel2);
        stripComponents(_panel3);
        stripComponents(_panel4);
        stripComponents(_panel5);

        // create the components belonging to the Accounts section of Settings
        JLabel label1 = new JLabel("username: ");
        label1.setFont(_labelFont);
        label1.setForeground(Color.black);

        JLabel label2 = new JLabel("X");
        label2.setFont(_labelFont);
        label2.setForeground(Color.red);

        JLabel label3 = new JLabel("rating: ");
        label3.setFont(_labelFont);
        label3.setForeground(Color.black);

        JLabel label4 = new JLabel("X");
        label4.setFont(_labelFont);
        label4.setForeground(Color.red);

        RoundedButton button1 = new RoundedButton(65);
        button1.setPreferredSize(new Dimension((3 * _mediumPanelSize.height),
                _mediumPanelSize.height));
        ImageIcon register = new ImageIcon("History.png");
        Image scaledRegister = register.getImage().getScaledInstance((3 * _mediumPanelSize.height),
                _mediumPanelSize.height, Image.SCALE_SMOOTH);
        ImageIcon scaledRegisterIcon = new ImageIcon(scaledRegister);
        button1.setIcon(scaledRegisterIcon);
        HistoryActionListener hal = new HistoryActionListener();
        button1.addActionListener(hal);

        RoundedButton button2 = new RoundedButton(70);
        button2.setPreferredSize(new Dimension((3 * _mediumPanelSize.height), _mediumPanelSize.height));
        ImageIcon login = new ImageIcon("Logout.png");
        Image scaledLogin = login.getImage().getScaledInstance((3 * _mediumPanelSize.height),
                _mediumPanelSize.height, Image.SCALE_SMOOTH);
        ImageIcon scaledLoginIcon = new ImageIcon(scaledLogin);
        button2.setIcon(scaledLoginIcon);
        LogoutActionListener lal = new LogoutActionListener(_background, _panel1, _panel2, _panel3,
                _panel4, _panel5, _labelFont, _mediumPanelSize);
        button2.addActionListener(lal);

        // add the components to the Accounts section of Settings
        _panel1.add(label1);
        _panel1.add(label2);
        _panel2.add(label3);
        _panel2.add(label4);
        _panel3.add(button1);
        _panel4.add(button2);

        _background.add(_panel1);
        _background.add(_panel2);
        _background.add(_panel3);
        _background.add(_panel4);
        _background.add(_panel5);

        // refresh the components
        _background.revalidate();
        _background.repaint();
    }

    /**
     * Method that strips all JComponents from a JPanel
     */
    private void stripComponents(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component c : components) {
            panel.remove(c);
        }
    }
}