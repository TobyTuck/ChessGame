import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SettingsActionListener implements ActionListener {

    // Containers for the settings options
    private JPanel _host;
    private RoundedPanel _backdrop;
    private JPanel _filler;

    private int _count; // global counter

    public SettingsActionListener(int width, int height, JPanel host, Color panelColor, Color hostColor) {
        // set fields
        _host = host;
        _count = 0;
        _backdrop = new RoundedPanel(30);

        _backdrop.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        _backdrop.setBackground(panelColor);

        // setup option panel
        int panelWidth = _host.getPreferredSize().width;
        int panelHeight = _host.getPreferredSize().height - 10;
        _backdrop.setPreferredSize(new Dimension(panelWidth, panelHeight));

        // make jpanels for the background panel
        RoundedPanel panel1 = new RoundedPanel(30);
        RoundedPanel panel2 = new RoundedPanel(30);
        RoundedPanel panel3 = new RoundedPanel(30);
        RoundedPanel panel4 = new RoundedPanel(30);
        RoundedPanel panel5 = new RoundedPanel(30);
        RoundedPanel panel6 = new RoundedPanel(30);
        RoundedPanel panel7 = new RoundedPanel(30);
        RoundedPanel panel8 = new RoundedPanel(30);
        RoundedPanel panel9 = new RoundedPanel(30);
        RoundedPanel panel10 = new RoundedPanel(30);
        RoundedPanel panel11 = new RoundedPanel(30);
        RoundedPanel panel12 = new RoundedPanel(30);
        RoundedPanel panel13 = new RoundedPanel(30);
        RoundedPanel panel14 = new RoundedPanel(30);
        RoundedPanel panel15 = new RoundedPanel(30);

        // set the color of the jpanels
        panel1.setBackground(panelColor);
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
        panel15.setBackground(panelColor);

        // set the size of the jpanels
        Dimension smallPanelSize = new Dimension(panelWidth, (int) ((1.0 / 24.0) * panelHeight));
        Dimension mediumPanelSize = new Dimension(panelWidth, (int) ((1.0 / 12.0) * panelHeight));
        Dimension largePanelSize = new Dimension(panelWidth, (int) ((1.0 / 8.0) * panelHeight));
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

        // Set necessary Layout Managers for the jpanels
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        // make the components for the jpanels
        Font titleFont = new Font("Arial", Font.BOLD, 45);
        Font labelFont = new Font("Arial", Font.PLAIN, 25);
        JLabel title1 = new JLabel("Game Mode");
        title1.setFont(titleFont);
        title1.setForeground(Color.black);

        JButton button1 = new JButton();
        button1.setPreferredSize(new Dimension(60, largePanelSize.height));
        ImageIcon bot = new ImageIcon("ComputerBot.png");
        Image scaledBot = bot.getImage().getScaledInstance(60, largePanelSize.height, Image.SCALE_SMOOTH);
        ImageIcon scaledBotIcon = new ImageIcon(scaledBot);
        button1.setIcon(scaledBotIcon);

        JPanel filler1 = new JPanel();
        filler1.setBackground(panelColor);
        filler1.setPreferredSize(new Dimension(40, largePanelSize.height));

        JButton button2 = new JButton();
        button2.setPreferredSize(new Dimension(40, largePanelSize.height));
        ImageIcon dual = new ImageIcon("TwoPlayer.png");
        Image scaledDual = dual.getImage().getScaledInstance(40, largePanelSize.height, Image.SCALE_SMOOTH);
        ImageIcon scaledDualIcon = new ImageIcon(scaledDual);
        button2.setIcon(scaledDualIcon);

        JLabel label1 = new JLabel("stockfish rating: ");
        label1.setFont(labelFont);
        label1.setForeground(Color.black);

        CustomJSlider slider1 = new CustomJSlider(400, 3000);
        slider1.setOrientation(javax.swing.JSlider.HORIZONTAL);

        JLabel label2 = new JLabel("1700");
        slider1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    String sliderValue = Integer.toString(value);
                    label2.setText(sliderValue);
                }
            }
        });
        label2.setFont(labelFont);
        label2.setForeground(Color.black);

        JLabel title2 = new JLabel("Graphics");
        title2.setFont(titleFont);
        title2.setForeground(Color.black);

        JButton button3 = new JButton();
        button3.setPreferredSize(new Dimension(60, largePanelSize.height));
        ImageIcon classic = new ImageIcon("Classic.png");
        Image scaledClassic = classic.getImage().getScaledInstance(60, largePanelSize.height, Image.SCALE_SMOOTH);
        ImageIcon scaledClassicIcon = new ImageIcon(scaledClassic);
        button3.setIcon(scaledClassicIcon);

        JPanel filler2 = new JPanel();
        filler2.setBackground(panelColor);
        filler2.setPreferredSize(new Dimension(40, largePanelSize.height));

        JButton button4 = new JButton();
        button4.setPreferredSize(new Dimension(60, largePanelSize.height));
        ImageIcon modern = new ImageIcon("Modern.png");
        Image scaledModern = modern.getImage().getScaledInstance(60, largePanelSize.height, Image.SCALE_SMOOTH);
        ImageIcon scaledModernIcon = new ImageIcon(scaledModern);
        button4.setIcon(scaledModernIcon);

        JLabel title3 = new JLabel("Account");
        title3.setFont(titleFont);
        title3.setForeground(Color.black);

        JLabel label3 = new JLabel("username: ");
        label3.setFont(labelFont);
        label3.setForeground(Color.black);

        JLabel label4 = new JLabel("X");
        label4.setFont(labelFont);
        label4.setForeground(Color.red);

        JLabel label5 = new JLabel("rating: ");
        label5.setFont(labelFont);
        label5.setForeground(Color.black);

        JLabel label6 = new JLabel("X");
        label6.setFont(labelFont);
        label6.setForeground(Color.red);

        JButton button5 = new JButton();
        button5.setPreferredSize(new Dimension(200, mediumPanelSize.height));
        button5.setFont(labelFont);
        button5.setText("View History");

        JButton button6 = new JButton();
        button6.setPreferredSize(new Dimension(150, mediumPanelSize.height));
        button6.setFont(labelFont);
        button6.setText("Logout");

        // add the components to the jpanels
        panel2.add(title1);
        panel3.add(button1);
        panel3.add(filler1);
        panel3.add(button2);
        panel4.add(label1);
        panel4.add(label2);
        panel5.add(slider1);
        panel7.add(title2);
        panel8.add(button3);
        panel8.add(filler2);
        panel8.add(button4);
        panel10.add(title3);
        panel11.add(label3);
        panel11.add(label4);
        panel12.add(label5);
        panel12.add(label6);
        panel13.add(button5);
        panel14.add(button6);

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

        // Make filler panel for extra space in the host jpanel
        _filler = new JPanel();
        _filler.setPreferredSize(new Dimension(panelWidth, 10));
        _filler.setBackground(hostColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isEven(_count)) {
            // setup option panel
            _host.add(_backdrop);
            _host.add(_filler);
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
