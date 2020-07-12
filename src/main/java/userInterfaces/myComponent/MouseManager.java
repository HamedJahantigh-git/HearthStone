package userInterfaces.myComponent;

import controller.FileManagement;
import defaults.FilesPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MouseManager {

    private String defaultPath;
    private Toolkit toolkit;
    private Cursor cursor;

    private static MouseManager instance = null;

    private MouseManager() {
        defaultPath = FilesPath.graphicsPath.backgroundsPath + "/";
    }

    public static MouseManager getInstance() {
        if (instance == null)
            instance = new MouseManager();
        return instance;
    }

    public void ChangeCursorImage(JFrame frame, String imageName) {
        toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(defaultPath + imageName);
        cursor = toolkit.createCustomCursor(image, new Point(frame.getX(),
                frame.getY()), "");
        frame.setCursor(cursor);
    }

    public void defaultCursorImage(JFrame frame){
        cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        frame.setCursor(cursor);
    }

    public void setHandCursorButton(JButton button) {
        cursor = button.getCursor();
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void moveMouseCursor(int newX, int newY) {
        try {
            Robot r = new Robot();
            r.mouseMove(newX, newY);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
