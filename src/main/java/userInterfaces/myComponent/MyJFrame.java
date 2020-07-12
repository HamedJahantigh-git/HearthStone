package userInterfaces.myComponent;

import javax.swing.*;

public class MyJFrame extends JFrame {
    private String title;
    private Bounds bounds;

    public MyJFrame(String title, Bounds bounds) {
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
