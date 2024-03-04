import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

 public class SettingsActionListener implements ActionListener{

   private JButton _button;
   private int _buttonHeight;
   private int _buttonWidth;
   private JPanel _host;
   private RoundedPanel _panel;
   private Color _panelColor;
   private int _count;
   private ImageIcon _image;
    
   public SettingsActionListener(JButton button, int width, int height, JPanel host,
                                 Color panelColor){
        // set fields
        _button = button;
        _buttonHeight = height;
        _buttonWidth = width;
        _host = host;
        _panel = new RoundedPanel(30);
        _panelColor = panelColor;
        _count = 0;

        // get the image to use as a background
        ImageIcon image = new ImageIcon("Settings.png");
        Image scaledImage = image.getImage().getScaledInstance
                                                (width, height, Image.SCALE_SMOOTH);
        _image = new ImageIcon(scaledImage);

        // setup option panel
        _panel.setBackground(_panelColor);
        _panel.setPreferredSize(new Dimension(_host.getPreferredSize().width, 
                                              _host.getPreferredSize().height - 10));
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isEven(_count)){
            // setup option panel
            _host.add(_panel);
            _host.repaint();
            _host.revalidate();
        }
            

        // _count is odd
        else{
            // remove the option panel
            _host.removeAll();
            _host.repaint();
            _host.revalidate();
        }

        ++_count;
    }

    /**
    Method that determines whether or not a number is even
    */
    private boolean isEven(int num){
        if(num % 2 == 0)
            return true;

        return false;
    }
}
