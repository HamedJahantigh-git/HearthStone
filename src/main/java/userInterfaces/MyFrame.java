package userInterfaces;

import defaults.GraphicsDefault;

import javax.swing.*;

public class MyFrame extends JFrame {
    private String title;
    private Bounds bounds;

    public MyFrame(String title, Bounds bounds) {
        this.title = title;
        this.bounds = bounds;
        initFrame();
    }

    private void initFrame() {
        setLayout(null);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setSize(bounds.getWidth(), bounds.getHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
