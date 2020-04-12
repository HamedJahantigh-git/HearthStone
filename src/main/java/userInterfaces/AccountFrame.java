package userInterfaces;

import defaults.FilesPath;
import defaults.GraphicsDefault;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AccountFrame extends JFrame {
    private JLabel background;
    private JTextField title;
    private JButton buttonSignIn;
    private JPanel test;

    {
        initFrame();
    }

    private void initFrame() {
        //Background
        setLayout(null);
       // setLayout(new BorderLayout());
        /*setLayout(new BorderLayout());
        background = new JLabel();
        background.setLayout(new FlowLayout());
        background = new JLabel(new ImageIcon(ComponentCreator.setImageSize(GraphicsDefault.AccountMenu.widthFrame,
                GraphicsDefault.AccountMenu.heightFrame,FilesPath.graphicsPath.backgroundsPath + "/accountMenu3.jpg")));
        add(background);
        background.setLayout(new FlowLayout());*/
        //Buttons
       /* buttonSignIn = ComponentCreator.setButton("Sign In", background, "buttons1.png",
                new int[]{100, 100, 300, 300}, Color.BLACK);*/
        //Frame
        Image image = null;
        try {
             image = ImageIO.read(new File(FilesPath.graphicsPath.backgroundsPath + "/accountMenu1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MyJPanel a = new MyJPanel(image);
        MyJPanel b =  new MyJPanel(image);

        a.setLayout(null);
        a.setBounds(100, 150,50,150);
        add(a);
        b.setLayout(null);
        b.setBounds(100, 300,55,100);
        add(b);
        setTitle("HeartStone");
        setResizable(false);
        setVisible(true);
        setSize(GraphicsDefault.AccountMenu.widthFrame, GraphicsDefault.AccountMenu.heightFrame);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
