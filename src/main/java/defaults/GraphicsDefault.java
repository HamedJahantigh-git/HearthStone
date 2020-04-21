package defaults;

import userInterfaces.myComponent.Bounds;

import java.awt.*;

public class GraphicsDefault {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static class AccountMenu {
        public static final int numberMenuPart = 6;
        public static Bounds mainBounds = new Bounds(0, 0, screenSize.width * 3 / 10, screenSize.height * 2 / 3);
        public static Bounds messageBounds = new Bounds(15, mainBounds.getHeight() / 4,
                mainBounds.getWidth() - 45, mainBounds.getHeight() / 2);
        public static Bounds messageButtonBounds = new Bounds((messageBounds.getWidth() - messageBounds.getWidth() / 3) / 2,
                messageBounds.getHeight() - messageBounds.getWidth() / 4 - 20,
                messageBounds.getWidth() / 3, messageBounds.getWidth() / 5);
        public static int componentDistance = mainBounds.getHeight() / numberMenuPart;
        public static int componentHeight = componentDistance * 2 / 3;
        public static int componentWidth = mainBounds.getWidth() / 2;
    }

    public static class UserMenu {
        public static Bounds mainBounds = new Bounds(0, 0, screenSize.width, screenSize.height);
        public static Bounds boxMainBounds = new Bounds(screenSize.width / 10, screenSize.height / 4,
                screenSize.width / 4, screenSize.height / 2);
    }

    public static class Collection {
        public static Bounds rightPanel = new Bounds(screenSize.width * 71 / 100, screenSize.height / 20,
                screenSize.width * 27 / 100, screenSize.height * 9 / 10);
        public static Bounds cardPanel = new Bounds(10, screenSize.height * 3 / 24,
                screenSize.width * 71 / 100 - 10, screenSize.height * 65 / 100);
        public static Bounds heroesUpper = new Bounds(GraphicsDefault.Collection.cardPanel.getX() + GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 11
                + 4 * GraphicsDefault.Collection.cardPanel.getWidth() / 10, GraphicsDefault.screenSize.height * 1 / 30,
                GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 30, GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 30);

        public static Bounds deckSection(int row, int column, int state) {
            Bounds result = null;
            int partColumn = 2;
            int partRow = 14;
            int aroundX = rightPanel.getWidth() / 8;
            int aroundY = rightPanel.getHeight() / 10;
            int width = (rightPanel.getWidth() - 2 * aroundX);
            int heights = rightPanel.getHeight() / partRow;
            if (state == 1) {
                result = new Bounds(aroundX, aroundY + row * heights * 9 / 8, width, heights);
            }
            return result;
        }
    }

    public static class ShopMenu {
        public static Bounds cardPanel = new Bounds(10, screenSize.height * 1 / 12,
                screenSize.width * 82 / 100, screenSize.height * 75 / 100);
        public static Bounds rightButton1 = new Bounds(screenSize.width * 82 / 100, screenSize.height * 4 / 12,
                screenSize.width * 15 / 100, screenSize.height / 12);
        public static Bounds rightButton2 = new Bounds(screenSize.width * 82 / 100, screenSize.height * 6 / 12,
                screenSize.width * 15 / 100, screenSize.height / 12);

        public static Bounds cardsSection(int i, int state) {
            Bounds result = null;
            int row;
            if (Math.floorMod(i, 8) < 4) {
                row = 0;
            } else {
                row = 1;
            }
            int column = Math.floorMod(i, 4);
            //card location
            if (state == 0) {
                result = new Bounds(cardPanel.getWidth() * 15 / 100 + column * cardPanel.getWidth() * 20 / 100+10,
                        cardPanel.getHeight() / 20 + row * cardPanel.getHeight() * 40 / 100,
                        cardPanel.getWidth() * 13 / 100, cardPanel.getHeight() * 33 / 100);
            }
            //right arrow
            if (state == 1){
                result = new Bounds(cardPanel.getWidth() * 10 / 20 + 5, cardPanel.getHeight() * 15 / 17,
                        cardPanel.getWidth() / 20, cardPanel.getHeight() / 17);
            }
            //left arrow
            if (state == 2) {
                result = new Bounds(cardPanel.getWidth() * 9 / 20 - 5, cardPanel.getHeight() * 15 / 17,
                        cardPanel.getWidth() / 20, cardPanel.getHeight() / 17);
            }
            //money text
            if (state == 3) {
                result = new Bounds(cardPanel.getWidth() * 10 / 100, cardPanel.getHeight() * 15 / 17,
                        cardPanel.getWidth() * 9 / 20 - 5 - cardPanel.getWidth() * 10 / 100, cardPanel.getHeight() / 17);
            }
            //card price text
            if (state == 4) {
                result = new Bounds(cardPanel.getWidth() * 15 / 100 + column * cardPanel.getWidth() * 20 / 100+10,
                        cardPanel.getHeight() / 20 + row * cardPanel.getHeight() * 40 / 100+cardPanel.getHeight() * 33 / 100,
                        cardPanel.getWidth() * 13 / 100, cardPanel.getHeight() * 5 / 100);
            }
            return result;
        }
    }

    public static class message {
        public static Bounds messagePanel = new Bounds(screenSize.width / 6, screenSize.height / 5,
                screenSize.width * 2 / 4, screenSize.height / 2);

        public static Bounds button(int state) {
            Bounds result = null;
            int height = messagePanel.getHeight() / 9;
            int width = messagePanel.getWidth() / 7;
            // one button
            if (state == 1) {
                result = new Bounds((messagePanel.getWidth()-width)/2, messagePanel.getHeight()*9/10-height*3/2,
                        width, height);
            }
            //right button
            if (state == 2) {
                result = new Bounds(messagePanel.getWidth()/2+20, messagePanel.getHeight()*9/10-height*3/2,
                        width, height);
            }
            //left button
            if (state == 3) {
                result = new Bounds(messagePanel.getWidth()/2-width-20, messagePanel.getHeight()*9/10-height*3/2,
                        width, height);
            }
            return result;
        }
    }
}
