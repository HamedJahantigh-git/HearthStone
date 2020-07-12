package defaults;

import enums.CardType;
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

    public static class GameBoard {
        public static Bounds mainBounds = new Bounds(0, 0, screenSize.width, screenSize.height);
        public static Bounds manaBar = new Bounds(mainBounds.getWidth() * 181 / 200, mainBounds.getHeight() * 65 / 100,
                mainBounds.getWidth() * 18 / 200, mainBounds.getHeight() * 23 / 200);
        public static Bounds menuIcon = new Bounds(mainBounds.getWidth() - mainBounds.getHeight() / 8 - 40, 20,
                mainBounds.getHeight() / 8, mainBounds.getHeight() / 8);
        public static Bounds menuMessage = new Bounds(mainBounds.getWidth() * 35 / 100, mainBounds.getHeight() / 6, mainBounds.getWidth() * 3 / 10, mainBounds.getHeight() * 2 / 3);
        public static Bounds turnButton = new Bounds(mainBounds.getWidth() * 159 / 200, mainBounds.getHeight() * 41 / 100,
                mainBounds.getWidth() * 20 / 200, mainBounds.getHeight() * 17 / 200);
        public static Bounds aroundDeckPanel = new Bounds(mainBounds.getWidth() * 50 / 200, mainBounds.getHeight() * 20 / 100,
                mainBounds.getWidth() * 100 / 200, mainBounds.getHeight() * 120 / 200);
        public static Bounds clockBounds = new Bounds(mainBounds.getWidth() * 181 / 200, mainBounds.getHeight() * 41 / 100,
                mainBounds.getWidth() * 15 / 200, mainBounds.getHeight() * 18 / 200);

        //Info Passive Bounds
        public static Bounds infoPassivePanelBounds = new Bounds(mainBounds.getWidth() * 60 / 200, mainBounds.getHeight() * 20 / 100,
                mainBounds.getWidth() * 80 / 200, mainBounds.getHeight() * 120 / 200);

        public static Bounds infoPassiveBounds(int state, int index) {
            // info Button
            if (state == 1)
                return new Bounds(infoPassivePanelBounds.getWidth() / 6,
                        index * infoPassivePanelBounds.getHeight() * 2 / 9, infoPassivePanelBounds.getWidth() * 2 / 3,
                        infoPassivePanelBounds.getHeight() / 5);
            // title
            if (state == 2)
                return new Bounds(0, 0, infoPassivePanelBounds.getWidth(), infoPassivePanelBounds.getHeight() / 3);
            return null;
        }

        // SelectCompetitor
        public static Bounds selectCompetitorBound(int state) {
            Bounds panelBounds = new Bounds(mainBounds.getWidth() * 70 / 200, mainBounds.getHeight() * 60 / 200,
                    mainBounds.getWidth() * 60 / 200, mainBounds.getHeight() * 80 / 200);
            if (state == 0)
                return panelBounds;
            //select button
            if (state == 1)
                return new Bounds(panelBounds.getWidth() * 60 / 200,
                        panelBounds.getHeight() * 65 / 100, panelBounds.getWidth() * 80 / 200,
                        panelBounds.getHeight() * 14 / 100);
            //combo
            if (state == 2)
                return new Bounds(panelBounds.getWidth() * 40 / 200,
                        panelBounds.getHeight() * 35 / 100, panelBounds.getWidth() * 120 / 200,
                        panelBounds.getHeight() * 12 / 100);
            // title
            if (state == 3)
                return new Bounds(0, 10, panelBounds.getWidth(), panelBounds.getHeight() / 3);
            return null;
        }

        public static Bounds changeStartCardBound(int state, int index) {
            Bounds panelBounds = new Bounds(mainBounds.getWidth() * 50 / 200, mainBounds.getHeight() * 40 / 200,
                    mainBounds.getWidth() * 100 / 200, mainBounds.getHeight() * 120 / 200);
            if (state == 0)
                return panelBounds;
            if (state == 1)
                return new Bounds(0, 10, panelBounds.getWidth(), panelBounds.getHeight() / 3);
            //Finish button
            if (state == 2)
                return new Bounds(panelBounds.getWidth() * 80 / 200,
                        panelBounds.getHeight() * 75 / 100, panelBounds.getWidth() * 40 / 200,
                        panelBounds.getHeight() * 11 / 100);
            if (state == 3) {
                int aroundX = panelBounds.getWidth() * 14 / 100;
                int aroundY = panelBounds.getHeight() * 12 / 100;
                int width = panelBounds.getWidth() * 20 / 100;
                int distance = width + (panelBounds.getWidth() - 2 * aroundX - width * ModelDefault.gameDefaults.MAX_START_PLAYER_CARDS)
                        / (ModelDefault.gameDefaults.MAX_START_PLAYER_CARDS);
                return new Bounds(aroundX + index * distance,
                        aroundY + panelBounds.getHeight() * 10 / 100,
                        width, panelBounds.getHeight() * 50 / 100);
            }
            return null;
        }

        public static Bounds aroundDeckCardBound(int playerIndex, int state, int index) {
            Bounds aroundDeckIcon = new Bounds(mainBounds.getWidth() * 175 / 200,
                    (mainBounds.getHeight() * 50 / 100) - playerIndex * mainBounds.getHeight() * 22 / 100,
                    mainBounds.getWidth() * 8 / 200,
                    mainBounds.getHeight() * 23 / 200);
            //Around Deck Icon
            if (state == 0) {
                return aroundDeckIcon;
            }
            //back button
            if (state == 1) {
                return new Bounds(aroundDeckPanel.getWidth() * 2 / 5, aroundDeckPanel.getHeight() * 7 / 9,
                        aroundDeckPanel.getWidth() / 5, aroundDeckPanel.getHeight() / 10);
            }
            //card position
            if (state == 2) {
                int row;
                int aroundX = aroundDeckPanel.getWidth() * 10 / 100;
                int aroundY = aroundDeckPanel.getHeight() * 14 / 100;
                if (Math.floorMod(index, 12) < 6) {
                    row = 0;
                } else {
                    row = 1;
                }
                int column = Math.floorMod(index, 6);
                return new Bounds(aroundX + column * aroundDeckPanel.getWidth() * 13 / 100 + 10,
                        aroundY + row * aroundDeckPanel.getHeight() * 30 / 100,
                        aroundDeckPanel.getWidth() * 13 / 100, aroundDeckPanel.getHeight() * 30 / 100);
            }
            // set title
            if (state == 3)
                return new Bounds(0, 10, aroundDeckPanel.getWidth(), aroundDeckPanel.getHeight() / 3);


            return null;
        }

        public static Bounds manaBarBounds(int playerIndex) {
            if (playerIndex == 0)
                return new Bounds(mainBounds.getWidth() * 181 / 200, mainBounds.getHeight() * 70 / 100,
                        mainBounds.getWidth() * 18 / 200, mainBounds.getHeight() * 23 / 200);
            if (playerIndex == 1)
                return new Bounds(mainBounds.getWidth() * 181 / 200, mainBounds.getHeight() * 15 / 100,
                        mainBounds.getWidth() * 18 / 200, mainBounds.getHeight() * 23 / 200);
            return null;
        }

        public static Bounds handDeckCard(int playerIndex, int index, int size) {
            int width = mainBounds.getWidth() * 8 / 100;
            int height = mainBounds.getHeight() * 15 / 100;
            int xStart = (mainBounds.getWidth() - width * size) / 2;
            int xEnd = width * size + xStart;
            int y = mainBounds.getHeight() * 80 / 100 - playerIndex * mainBounds.getHeight() * 79 / 100;
            int xPart = (xEnd - xStart) / size;
            return new Bounds(xEnd - xPart * (index + 1), y, width, height);
        }

        public static Bounds questBounds(int playerIndex, int stat) {
            int x = mainBounds.getHeight() * 5 / 100;
            int y = mainBounds.getHeight() * 68 / 100 - playerIndex * mainBounds.getHeight() * 60 / 100;
            int width = mainBounds.getWidth() * 9 / 100;
            int height = mainBounds.getHeight() * 15 / 100;
            if (stat == 0) {
                return new Bounds(x, y, width, height);
            }
            if(stat == 1){
                return new Bounds(0, height*4/10, width,height*2/10);
            }
            return null;
        }

        public static Bounds groundDeckCard(int playerIndex, int cardIndex, int size) {
            int width = mainBounds.getWidth() * 7 / 100;
            int height = mainBounds.getHeight() * 13 / 100;
            int xStart = (mainBounds.getWidth() - width * size) / 2;
            int xEnd = width * size + xStart;
            int y = mainBounds.getHeight() * 47 / 100 - mainBounds.getHeight() * playerIndex * 27 / 200;
            int xPart = (xEnd - xStart) / size;
            return new Bounds(xEnd - xPart * (cardIndex + 1), y, width, height);
        }

        public static Bounds weaponGroundBounds(int playerIndex) {
            int y = mainBounds.getHeight() * 64 / 100 - mainBounds.getHeight() * playerIndex * 47 / 100;
            return new Bounds(mainBounds.getWidth() * 74 / 200, y, mainBounds.getWidth() * 8 / 100, mainBounds.getHeight() * 15 / 100);
        }

        public static Bounds menuButtons(int index) {
            return new Bounds(GraphicsDefault.GameBoard.menuMessage.getWidth() / 5,
                    GraphicsDefault.GameBoard.menuMessage.getHeight() * (2 * index + 1) / 12,
                    GraphicsDefault.GameBoard.menuMessage.getWidth() * 6 / 10,
                    GraphicsDefault.GameBoard.menuMessage.getHeight() / 7 - 10);
        }

        public static Bounds deleteAccountButtons(int index) {
            return new Bounds(GraphicsDefault.GameBoard.menuMessage.getWidth() / 5 +
                    index*( GraphicsDefault.GameBoard.menuMessage.getWidth() * 6 / 20+20)-10,
                    GraphicsDefault.GameBoard.menuMessage.getHeight() * 9 / 12,
                    GraphicsDefault.GameBoard.menuMessage.getWidth() * 6 / 20,
                    GraphicsDefault.GameBoard.menuMessage.getHeight() / 7 - 10);
        }

        public static Bounds heroBounds(int playerIndex, int state) {
            Bounds heroPanelBounds = new Bounds(mainBounds.getWidth() * 92 / 200,
                    mainBounds.getHeight() * 60 / 100 - playerIndex * mainBounds.getHeight() * 47 / 100,
                    mainBounds.getWidth() * 26 / 200, mainBounds.getHeight() * 42 / 200);

            // main Bound
            if (state == 1)
                return heroPanelBounds;
            // health Bound
            if (state == 2)
                return new Bounds(heroPanelBounds.getWidth() * 60 / 80, heroPanelBounds.getWidth() * 23 / 40,
                        heroPanelBounds.getWidth() / 5, heroPanelBounds.getHeight() / 6);

            return null;
        }

        public static Bounds heroPowerBounds(int playerIndex, int state) {
            int width = mainBounds.getWidth() * 20 / 200;
            int height = mainBounds.getHeight() * 23 / 200;
            if (state == 1) {
                if (playerIndex == 0)
                    return new Bounds(mainBounds.getWidth() * 120 / 200, mainBounds.getHeight() * 66 / 100,
                            width, height);
                if (playerIndex == 1)
                    return new Bounds(mainBounds.getWidth() * 120 / 200, mainBounds.getHeight() * 20 / 100,
                            width, height);
            }
            if (state == 2)
                return new Bounds(width * 21 / 50, height / 10,
                        width * 2 / 10, height * 2 / 10);
            return null;
        }

        public static Bounds eventBounds(int state) {
            Bounds eventBounds = new Bounds(mainBounds.getWidth() * 1 / 200, mainBounds.getHeight() * 24 / 100,
                    mainBounds.getWidth() * 33 / 200, mainBounds.getHeight() * 90 / 200);
            if (state == 1)
                return eventBounds;
            // title
            if (state == 2)
                return new Bounds(0, eventBounds.getHeight() / 10,
                        eventBounds.getWidth(), eventBounds.getHeight() / 10);
            // event message
            if (state == 3)
                return new Bounds(eventBounds.getWidth() / 10, eventBounds.getHeight() / 9,
                        eventBounds.getWidth() * 8 / 10, eventBounds.getHeight() * 4 / 5);
            return null;
        }

        public static Bounds cardTextBounds(int state, Bounds cardBound) {
            // mana Bounds
            if (state == 1)
                return new Bounds(cardBound.getWidth() / 40, 0, cardBound.getWidth() / 5, cardBound.getHeight() / 6);
            // left bottom Bound
            if (state == 2)
                return new Bounds(cardBound.getWidth() / 30, cardBound.getWidth() * 35 / 40, cardBound.getWidth() / 5, cardBound.getHeight() / 6);
            // right bottom Bound
            if (state == 3)
                return new Bounds(cardBound.getWidth() * 63 / 80, cardBound.getWidth() * 35 / 40, cardBound.getWidth() / 5, cardBound.getHeight() / 6);
            return null;

        }

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

    public static class StatusMenu {
        public static Bounds mainBounds = new Bounds(0, 0, screenSize.width, screenSize.height);
        static int yGap = mainBounds.getHeight() / 15;
        static int xGap = mainBounds.getWidth() / 25;
        public static Bounds backBounds = new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() / 8,
                GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                GraphicsDefault.UserMenu.mainBounds.getWidth() / 12,
                GraphicsDefault.UserMenu.mainBounds.getHeight() / 14);
        public static Bounds exitGameBounds = new Bounds(GraphicsDefault.UserMenu.mainBounds.getWidth() * 5 / 24 + 5,
                GraphicsDefault.UserMenu.mainBounds.getHeight() - GraphicsDefault.UserMenu.mainBounds.getHeight() / 12 - 50,
                GraphicsDefault.UserMenu.mainBounds.getWidth() / 9,
                GraphicsDefault.UserMenu.mainBounds.getHeight() / 14);
        public static Bounds titleBounds = new Bounds(0, 0, mainBounds.getWidth(), 2 * yGap);

        //message bounds
        public static Bounds messageBounds = new Bounds(mainBounds.getWidth() * 3 / 10, mainBounds.getHeight() / 6,
                mainBounds.getWidth() * 4 / 10, mainBounds.getHeight() * 2 / 3);
        public static Bounds messageTitleBounds = new Bounds(0, 0, messageBounds.getWidth(), mainBounds.getHeight() / 10);
        public static Bounds messageDetailBounds = new Bounds(messageBounds.getWidth() / 20, messageBounds.getHeight() / 10,
                messageBounds.getWidth(), messageBounds.getHeight() * 8 / 10 - messageBounds.getHeight() * 3 / 24);
        public static Bounds backMessageBounds = new Bounds(messageBounds.getWidth() * 3 / 8, messageBounds.getHeight() * 21 / 24,
                messageBounds.getWidth() / 4, messageBounds.getHeight() / 12);

        public static Bounds deckSection(int index, int state) {
            Bounds result = null;
            int width = (mainBounds.getWidth() - 5 * xGap) / 4;
            int height = mainBounds.getWidth() / 10;
            int row;
            if (Math.floorMod(index, ModelDefault.deckDefaults.topDeckNumber) < 4) {
                row = 0;
            } else {
                if (Math.floorMod(index, ModelDefault.deckDefaults.topDeckNumber) < 8) {
                    row = 1;
                } else {
                    row = 2;
                }
            }
            int column = Math.floorMod(index, 4);
            //deck location
            if (state == 1) {
                result = new Bounds(xGap + column * (width + xGap), 2 * yGap + row * (height + yGap), width, height);
            }
            //Deck title text
            if (state == 2) {
                result = new Bounds(xGap + column * (width + xGap), 2 * yGap + height + row * (height + yGap), width, height / 5);
            }
            return result;
        }


    }

    public static class message {
        public static Bounds messagePanel = new Bounds(screenSize.width / 6, screenSize.height / 5,
                screenSize.width * 2 / 4, screenSize.height / 2);
        public static Bounds mainMenuMessagePanel = new Bounds(screenSize.width / 4, screenSize.height / 4,
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
