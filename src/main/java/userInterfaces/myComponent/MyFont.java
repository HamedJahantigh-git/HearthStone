package userInterfaces.myComponent;

import defaults.FilesPath;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyFont {
    private Font font;
    private String  fontName;
    private int fontSize;

    public MyFont(String fontName, int fontSize){
           this.fontName = fontName;
           this.fontSize = fontSize;
           loadFont();
    }

    private void loadFont(){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(
                    FilesPath.graphicsPath.fontsPath+ "/"+fontName+".ttf")).deriveFont((float) fontSize);
            //create the font to use. Specify the size!
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public String getFontName() {
        return fontName;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Font getFont() {
        return font;
    }
}
