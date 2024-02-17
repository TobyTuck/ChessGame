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
   private ImageIcon _image1;
   private ImageIcon _image2;
    
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
        ImageIcon image1 = new ImageIcon("Settings1.png");
        ImageIcon image2 = new ImageIcon("Settings2.png");
        Image scaledImage1 = image1.getImage().getScaledInstance
                                                (width, height, Image.SCALE_SMOOTH);
        Image scaledImage2 = image2.getImage().getScaledInstance
                                                (width, height, Image.SCALE_SMOOTH);
        _image1 = new ImageIcon(scaledImage1);
        _image2 = new ImageIcon(scaledImage2); 

        // setup option panel
        _panel.setBackground(_panelColor);
        _panel.setPreferredSize(_host.getPreferredSize());
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isEven(_count)){
            // _button.setIcon(_image1);
            _button.setIcon(_image2);

            // setup option panel
            _host.add(_panel);
            _host.repaint();
            _host.revalidate();
        }
            

        // _count is odd
        else{
            // _button.setIcon(_image2);
            _button.setIcon(_image1);

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
