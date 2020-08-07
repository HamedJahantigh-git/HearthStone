package userInterfaces.myComponent;

import defaults.FilesPath;
import enums.FontEnum;
import userInterfaces.Sounds;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class ComponentCreator {
    private static final ComponentCreator instance = new ComponentCreator();

    private ComponentCreator() {
    }

    public static ComponentCreator getInstance() {
        return instance;
    }

    public JLabel setText(String text, JPanel panel, MyFont font, Color color, Bounds bounds) {
        JLabel jLabel = new JLabel(text, SwingConstants.CENTER);
        jLabel.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        jLabel.setFont(new MyFont(font.getFontName() ,font.getFontSize()).getFont());
        jLabel.setForeground(color);
        panel.add(jLabel);
        return jLabel;
    }

    public JTextField setImportBox(JPanel panel, int fontSize, Color color, Bounds bounds) {
        JTextField jTextField = new JTextField();
        jTextField.setFont(new MyFont(FontEnum.IMPORT_BOX.getName(), fontSize).getFont());
        jTextField.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        jTextField.setForeground(color);
        panel.add(jTextField);
        return jTextField;
    }

    public JButton setButton(String text, JPanel panel, String buttonBackgroundName,
                             Bounds bounds, Color color, int fontSize, int state) {

        JButton button;
        if (buttonBackgroundName != null)
            button = new JButton(text, new ImageIcon(setImage(
                    bounds.getWidth(), bounds.getHeight(),
                    FilesPath.graphicsPath.backgroundsPath + "/" + buttonBackgroundName)));
        else button = new JButton(text);
        Sounds click = new Sounds("crossButton.wav");
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFont(new MyFont(FontEnum.BUTTON.getName(), fontSize).getFont());
        button.setForeground(color);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        button.addMouseListener(new MouseAdapter() {
            Color oldColor = button.getForeground();

            public void mouseEntered(MouseEvent me) {
                click.playOne();
                oldColor = button.getForeground();
                button.setForeground(Color.red);
                if (state == 1)
                    button.setLocation(bounds.getX() - 20, bounds.getY());
                if (state == 2) {
                    button.setLocation(bounds.getX() - 10, bounds.getY());
                }
                if (state == 3) {
                    button.setLocation(bounds.getX() + 10, bounds.getY());
                }
                if (state == 4) {
                    button.setBorder(BorderFactory.createRaisedSoftBevelBorder());
                }
                //button.setSize(bounds.getWidth()+100, bounds.getHeight()+10);

            }

            public void mouseExited(MouseEvent me) {
                button.setForeground(oldColor);
                if (state == 1 || state == 2 || state == 3)
                    button.setLocation(bounds.getX(), bounds.getY());
                //button.setSize(bounds.getWidth(), bounds.getHeight());
                if (state == 4) {
                    button.setBorder(null);
                }
            }
        });
        panel.add(button);
        return button;
    }

    public JComboBox<Integer> setIntComboBox(JPanel panel, int first, int last, int step, Bounds bounds, int fontSize) {
        JComboBox<Integer> combo = new JComboBox<>(creatNumberForCombo(first, last));
        combo.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        combo.setFont(new MyFont(FontEnum.COMBO_BOX.getName(), fontSize).getFont());
        combo.setForeground(Color.BLUE);
        combo.setMaximumRowCount(step);
        combo.setLayout(null);
        panel.add(combo);
        return combo;
    }

    public JComboBox<String> setStrComboBox(JPanel panel, String[] choice, int step, Bounds bounds, int fontSize) {
        JComboBox<String> combo = new JComboBox<>(choice);
        combo.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        combo.setFont(new MyFont(FontEnum.COMBO_BOX.getName(), fontSize).getFont());
        combo.setForeground(Color.BLUE);
        combo.setMaximumRowCount(step);
        combo.setLayout(null);
        panel.add(combo);
        return combo;
    }

    private static Integer[] creatNumberForCombo(int first, int last) {
        Integer[] result = new Integer[last - first + 1];
        for (int i = 0; i < last - first + 1; i++) {
            result[i] = first + i;
        }
        return result;
    }

    public JPasswordField setPasswordField(JPanel panel, Bounds bounds, int fontSize, Color color) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        passwordField.setForeground(color);
        passwordField.setFont(new MyFont(FontEnum.PASSWORD_FIELD.getName(), fontSize).getFont());
        panel.add(passwordField);
        return passwordField;
    }

    public Image setImage(int width, int height, String path) {
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
