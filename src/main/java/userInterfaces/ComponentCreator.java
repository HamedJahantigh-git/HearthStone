package userInterfaces;

import defaults.FilesPath;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ComponentCreator {
    private static final ComponentCreator instance = new ComponentCreator();

    private ComponentCreator() {
    }

    public static ComponentCreator getInstance() {
        return instance;
    }

    public JLabel setText(String text, JPanel panel, String fontName, int fontSize, Color color, Bounds bounds) {
        JLabel jLabel = new JLabel(text, SwingConstants.CENTER);
        jLabel.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        jLabel.setFont(new Font(fontName, Font.ITALIC, fontSize));
        jLabel.setForeground(color);
        panel.add(jLabel);
        return jLabel;
    }

    public JTextField setImportBox(JPanel panel, int fontSize, Color color, Bounds bounds) {
        JTextField jTextField = new JTextField();
        jTextField.setFont(new Font("Times New Roman", Font.ITALIC, fontSize));
        jTextField.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        jTextField.setForeground(color);
        panel.add(jTextField);
        return jTextField;
    }

    public JButton setButton(String text, JPanel panel, String buttonBackgroundName,
                             Bounds bounds, Color color, int fontSize) {
        JButton button;
        if (buttonBackgroundName != null)
            button = new JButton(text, new ImageIcon(setImage(
                    bounds.getWidth(), bounds.getHeight(),
                    FilesPath.graphicsPath.backgroundsPath + "/" + buttonBackgroundName)));
        else button = new JButton(text);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Belwe Bd BT Bold", Font.ITALIC, fontSize));
        button.setForeground(color);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        panel.add(button);
        return button;
    }

    public JPasswordField setPasswordField(JPanel panel, Bounds bounds, int fontSize, Color color) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        passwordField.setForeground(color);
        passwordField.setFont(new Font("Times New Roman", Font.ITALIC, fontSize));
        panel.add(passwordField);
        return passwordField;
    }

    private Image setImage(int width, int height, String path) {
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
