import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class SettingsActionListener implements ActionListener {

    private JPanel _host;
    private RoundedPanel _backdrop;

    private int _count;

    public SettingsActionListener(int width, int height, JPanel host, Color panelColor) {
        // set fields
        _host = host;
        _count = 0;
        _backdrop = new RoundedPanel(30);

        // setup option panel
        _backdrop.setPreferredSize(new Dimension(_host.getPreferredSize().width,
                _host.getPreferredSize().height - 10));

        // make jpanels for the background panel
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();
        JPanel panel11 = new JPanel();
        JPanel panel12 = new JPanel();
        JPanel panel13 = new JPanel();
        JPanel panel14 = new JPanel();
        JPanel panel15 = new JPanel();

        // set the color of the jpanels
        // panel1.setBackground(panelColor);
        panel1.setBackground(Color.red);
        panel2.setBackground(panelColor);
        panel3.setBackground(panelColor);
        panel4.setBackground(panelColor);
        panel5.setBackground(panelColor);
        panel6.setBackground(panelColor);
        panel7.setBackground(panelColor);
        panel8.setBackground(panelColor);
        panel9.setBackground(panelColor);
        panel10.setBackground(panelColor);
        panel11.setBackground(panelColor);
        panel12.setBackground(panelColor);
        panel13.setBackground(panelColor);
        panel14.setBackground(panelColor);
        // panel15.setBackground(panelColor);
        panel15.setBackground(Color.red);

        // set the size of the jpanels
        int panelWidth = _backdrop.getPreferredSize().width;
        int panelHeight = _backdrop.getPreferredSize().height;
        Dimension smallPanelSize = new Dimension(panelWidth, (int) ((1 / 24) * panelHeight));
        Dimension mediumPanelSize = new Dimension(panelWidth, (int) ((1 / 12) * panelHeight));
        Dimension largePanelSize = new Dimension(panelWidth, (int) ((1 / 8) * panelHeight));
        panel1.setPreferredSize(smallPanelSize);
        panel2.setPreferredSize(mediumPanelSize);
        panel3.setPreferredSize(largePanelSize);
        panel4.setPreferredSize(smallPanelSize);
        panel5.setPreferredSize(smallPanelSize);
        panel6.setPreferredSize(smallPanelSize);
        panel7.setPreferredSize(mediumPanelSize);
        panel8.setPreferredSize(largePanelSize);
        panel9.setPreferredSize(smallPanelSize);
        panel10.setPreferredSize(mediumPanelSize);
        panel11.setPreferredSize(smallPanelSize);
        panel12.setPreferredSize(smallPanelSize);
        panel13.setPreferredSize(mediumPanelSize);
        panel14.setPreferredSize(mediumPanelSize);
        panel15.setPreferredSize(smallPanelSize);

        // make the components for the jpanels
        JLabel title1 = new JLabel();
        title1.setText("Game Mode");

        JLabel title2 = new JLabel();
        title2.setText("Graphics");

        JLabel title3 = new JLabel();
        title3.setText("Account");

        // add the components to the jpanels
        panel2.add(title1);
        panel7.add(title2);
        panel10.add(title3);

        // add the jpanels to the background panel
        _backdrop.add(panel1);
        _backdrop.add(panel2);
        _backdrop.add(panel3);
        _backdrop.add(panel4);
        _backdrop.add(panel5);
        _backdrop.add(panel6);
        _backdrop.add(panel7);
        _backdrop.add(panel8);
        _backdrop.add(panel9);
        _backdrop.add(panel10);
        _backdrop.add(panel11);
        _backdrop.add(panel12);
        _backdrop.add(panel13);
        _backdrop.add(panel14);
        _backdrop.add(panel15);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isEven(_count)) {
            // setup option panel
            _host.add(_backdrop);
            _host.repaint();
            _host.revalidate();
        }

        // _count is odd
        else {
            // remove the option panel
            _host.removeAll();
            _host.repaint();
            _host.revalidate();
        }

        ++_count;
    }

    /**
     * Method that determines whether or not a number is even
     */
    private boolean isEven(int num) {
        if (num % 2 == 0)
            return true;

        return false;
    }
}
