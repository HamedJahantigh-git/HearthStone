package userInterfaces.graphicsActions;

import controller.PlayerController;
import defaults.GraphicsDefault;
import enums.LogsEnum;
import logs.PlayerLogs;
import userInterfaces.myComponent.MyCard;
import userInterfaces.userMenu.CollectionMenu;

import javax.swing.*;
import java.util.ArrayList;

public class CollectionMenuAction extends UserMenuAction {


    public CollectionMenuAction(PlayerController playerController) {
        super(playerController);
    }

    public void showCards(JButton button, String type, ArrayList<MyCard> heroes) {
        button.addActionListener(actionEvent -> {
            for (int i = 0; i < heroes.size(); i++) {
                if (heroes.get(i).getY() != GraphicsDefault.Collection.heroesUpper.getY())
                    heroes.get(i).setLocation(heroes.get(i).getX(), GraphicsDefault.Collection.heroesUpper.getY());
            }
            button.setLocation(button.getX(), GraphicsDefault.Collection.heroesUpper.getY() + 30);
            switch (type) {
                case "Neutral":

                    break;
                case "Hunter":
                    break;
                case "Mage":
                    break;
                case "Priest":
                    break;
                case "Rogue":
                    break;
                case "Warlock":
                    break;
            }
        });
    }
}
