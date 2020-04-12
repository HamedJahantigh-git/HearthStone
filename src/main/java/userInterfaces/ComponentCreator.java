package userInterfaces;

import defaults.FilesPath;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ComponentCreator {

    public static JTextField setTitle(String text, JLabel background, String font, int fontSize, Color color, int[] bounds) {
        JTextField jTextField = new JTextField(text);
        jTextField.setFont(new Font(font, Font.ITALIC, fontSize));
        jTextField.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        jTextField.setForeground(color);
        jTextField.setOpaque(false);
        jTextField.setEditable(false);
        jTextField.setBorder(null);
        background.setLayout(null);
        background.add(jTextField, 2, 0);
        return jTextField;
    }

    public static JButton setButton(String text, JLabel background, String buttonBackgroundName,
                                    int[] bounds, Color color) {

        JButton button = new JButton(text,new ImageIcon(setImage(
                bounds[2],bounds[3],FilesPath.graphicsPath.backgroundsPath + "/" + buttonBackgroundName)));
        button.setFont(new Font("Frostbite Boss Fight", Font.ITALIC, 60));
        button.setForeground(color);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        background.setLayout(null);
        background.add(button);
        return button;
    }

    public static Image setImage(int width, int height, String path){
        Image image = null;
        try {
            image = ImageIO.read(new File(path))
                    .getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
