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

        public static Bounds heroesButtonBounds(int column) {
            return new Bounds(GraphicsDefault.Collection.cardPanel.getX() + GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 11
                    + (column - 1) * GraphicsDefault.Collection.cardPanel.getWidth() / 10, GraphicsDefault.screenSize.height * 1 / 30,
                    GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 30, GraphicsDefault.Collection.cardPanel.getWidth() * 2 / 30);
        }

        public static Bounds searchSection(int state) {
            Bounds result = null;
            int height = UserMenu.mainBounds.getHeight() / 20;
            int y = GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() * 22 / 100;

            if (state == 1)
                result = new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        y, GraphicsDefault.UserMenu.mainBounds.getWidth() / 10, height);
            if (state == 2)
                result = new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() / 10 + GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        y, GraphicsDefault.UserMenu.mainBounds.getWidth() / 10, height);
            if (state == 3)
                result = new Bounds(2 * GraphicsDefault.UserMenu.mainBounds.getWidth() / 10 + GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        y, GraphicsDefault.UserMenu.mainBounds.getWidth() / 15, height);
            if (state == 4)
                result = new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 26 / 100 + GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        y, GraphicsDefault.UserMenu.mainBounds.getWidth() / 40, height);
            if (state == 5)
                result = new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 28 / 100 + GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        y, GraphicsDefault.UserMenu.mainBounds.getWidth() / 10, height);
            if (state == 6)
                result = new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 37 / 100 + GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        y, GraphicsDefault.UserMenu.mainBounds.getWidth() / 16, height);
            if (state == 7)
                result = new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 45 / 100 + GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                        y, GraphicsDefault.UserMenu.mainBounds.getWidth() / 14, height);
            return result;
        }

        public static Bounds cardsSection(int i, int state) {
            Bounds result = null;
            int row;
            int aroundX = cardPanel.getWidth() * 2 / 10;
            ;
            if (Math.floorMod(i, 8) < 4) {
                row = 0;
            } else {
                row = 1;
            }
            int column = Math.floorMod(i, 4);
            //title
            if (state == 0)
                result = new Bounds(aroundX, cardPanel.getHeight() / 13,
                        cardPanel.getWidth() - 2 * aroundX, cardPanel.getHeight() / 14);
            //right arrow
            if (state == 1)
                result = new Bounds(cardPanel.getWidth() * 10 / 20 + 5, cardPanel.getHeight() * 15 / 17,
                        cardPanel.getWidth() / 20, cardPanel.getHeight() / 17);
            //left arrow
            if (state == 2)
                result = new Bounds(cardPanel.getWidth() * 9 / 20 - 5, cardPanel.getHeight() * 15 / 17,
                        cardPanel.getWidth() / 20, cardPanel.getHeight() / 17);
            //card text
            if (state == 4) {
                result = new Bounds(cardPanel.getWidth() * 15 / 100 + column * cardPanel.getWidth() * 18 / 100 + 10,
                        cardPanel.getHeight() / 8 + row * cardPanel.getHeight() * 38 / 100 + cardPanel.getHeight() * 30 / 100,
                        cardPanel.getWidth() * 12 / 100, cardPanel.getHeight() * 5 / 100);
            }
            //card location
            if (state == 5) {
                result = new Bounds(cardPanel.getWidth() * 15 / 100 + column * cardPanel.getWidth() * 18 / 100 + 10,
                        cardPanel.getHeight() / 8 + row * cardPanel.getHeight() * 38 / 100,
                        cardPanel.getWidth() * 12 / 100, cardPanel.getHeight() * 30 / 100);
            }

            return result;
        }

        public static Bounds deckSection(int index, int state) {
            Bounds result = null;
            int partRow = 14;
            int aroundX = rightPanel.getWidth() / 8;
            int aroundY = rightPanel.getHeight() / 10;
            int width = (rightPanel.getWidth() - 2 * aroundX);
            int heights = rightPanel.getHeight() / partRow;
            int row = Math.floorMod((index / 2), 3);
            int column = Math.floorMod(index, 2);
            //set title
            if (state == 1) {
                result = new Bounds(aroundX, 0, rightPanel.getWidth() - 2 * aroundX, heights);
            }
            //set big button
            if (state == 2) {

                result = new Bounds((rightPanel.getWidth() - ((rightPanel.getWidth() - 2 * aroundX) - 2 * rightPanel.getWidth() / 8 - 10)) / 2,
                        rightPanel.getHeight() * 83 / 100 - index * heights,
                        (rightPanel.getWidth() - 2 * aroundX) - 2 * rightPanel.getWidth() / 8 - 10, heights);
            }
            //set small button
            if (state == 3) {
                result = new Bounds(aroundX + index * ((rightPanel.getWidth() - 2 * aroundX) / 3),
                        rightPanel.getHeight() * 71 / 100,
                        (rightPanel.getWidth() - 2 * aroundX) / 3, heights * 2 / 3);
            }
            //right arrow
            if (state == 4)
                result = new Bounds(rightPanel.getWidth() - rightPanel.getWidth() / 4,
                        rightPanel.getHeight() * 85 / 100 - (index + 1) * heights,
                        rightPanel.getWidth() / 8, rightPanel.getHeight() / 25);
            //left arrow
            if (state == 5)
                result = new Bounds(rightPanel.getWidth() / 8,
                        rightPanel.getHeight() * 85 / 100 - (index + 1) * heights,
                        rightPanel.getWidth() / 8, rightPanel.getHeight() / 25);
            //deck hero location
            if (state == 6) {
                result = new Bounds(aroundX, aroundY + index * heights * 19 / 10, width, heights);
            }
            //deck name location
            if (state == 7) {
                result = new Bounds(aroundX, aroundY + index * heights * 19 / 10 + heights, width, heights * 2 / 3);
            }
            //current deck text
            if (state == 8) {
                result = new Bounds(aroundX, rightPanel.getHeight() * 83 / 100, width, heights);
            }
            //deck card location
            if (state == 9) {
                result = new Bounds(aroundX + column * width / 2,
                        aroundY + heights + row * heights * 10 / 4,
                        width / 2, heights * 10 / 4);
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
                result = new Bounds(cardPanel.getWidth() * 15 / 100 + column * cardPanel.getWidth() * 20 / 100 + 10,
                        cardPanel.getHeight() / 20 + row * cardPanel.getHeight() * 40 / 100,
                        cardPanel.getWidth() * 13 / 100, cardPanel.getHeight() * 33 / 100);
            }
            //right arrow
            if (state == 1) {
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
                result = new Bounds(cardPanel.getWidth() * 15 / 100 + column * cardPanel.getWidth() * 20 / 100 + 10,
                        cardPanel.getHeight() / 20 + row * cardPanel.getHeight() * 40 / 100 + cardPanel.getHeight() * 33 / 100,
                        cardPanel.getWidth() * 13 / 100, cardPanel.getHeight() * 5 / 100);
            }
            return result;
        }
    }

    public static class message {
        public static Bounds messagePanel = new Bounds(screenSize.width / 6, screenSize.height / 5,
                screenSize.width * 2 / 4, screenSize.height / 2);

        public static Bounds component(int state) {
            Bounds result = null;
            int height = messagePanel.getHeight() / 9;
            int width = messagePanel.getWidth() / 7;
            // one button
            if (state == 1) {
                result = new Bounds((messagePanel.getWidth() - width) / 2, messagePanel.getHeight() * 9 / 10 - height * 3 / 2,
                        width, height);
            }
            //right button
            if (state == 2) {
                result = new Bounds(messagePanel.getWidth() / 2 + 20, messagePanel.getHeight() * 9 / 10 - height * 3 / 2,
                        width, height);
            }
            //left button
            if (state == 3) {
                result = new Bounds(messagePanel.getWidth() / 2 - width - 20, messagePanel.getHeight() * 9 / 10 - height * 3 / 2,
                        width, height);
            }
            //comboBox Title
            if (state == 4) {
                result = new Bounds(messagePanel.getWidth() / 2 - messagePanel.getWidth() * 7 / 24 - 10, messagePanel.getHeight() * 50 / 100,
                        messagePanel.getWidth() / 3, height * 3 / 4);
            }
            //comboBox box
            if (state == 5) {
                result = new Bounds(messagePanel.getWidth() / 2 + 10, messagePanel.getHeight() * 50 / 100,
                        messagePanel.getWidth() / 4, height * 3 / 4);
            }
            //text input Title
            if (state == 6) {
                result = new Bounds(messagePanel.getWidth() / 2 - messagePanel.getWidth() * 7 / 24 - 10, messagePanel.getHeight() * 35 / 100,
                        messagePanel.getWidth() / 3, height * 3 / 4);

            }
            //text input box
            if (state == 7) {
                result = new Bounds(messagePanel.getWidth() / 2 + 10, messagePanel.getHeight() * 35 / 100,
                        messagePanel.getWidth() / 4, height * 3 / 4);
            }
            return result;
        }
    }
}
