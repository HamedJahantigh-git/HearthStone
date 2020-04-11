package userInterfaces;

import defaults.FilesPath;
import defaults.GraphicsDefault;

import javax.swing.*;
import java.awt.*;

public class AccountFrame extends JFrame {
    private JLabel background;

    {
        initFrame();
    }

    private void initFrame (){
        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon(FilesPath.graphicsPath.backgroundsPath + "/accountMenu.jpg"));
        add(background);
        background.setLayout(new FlowLayout());
        setTitle("HeartStone");
        setResizable(false);
        setVisible(true);
        setSize(GraphicsDefault.AccountMenu.widthFrame, GraphicsDefault.AccountMenu.heightFrame);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
