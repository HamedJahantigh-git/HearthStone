package userInterfaces.graphicsActions;

import controller.PlayerController;
import defaults.GraphicsDefault;
import enums.LogsEnum;
import logs.PlayerLogs;
import userInterfaces.myComponent.MyCardButton;
import userInterfaces.userMenu.CollectionMenu;

import javax.swing.*;
import java.util.ArrayList;

public class CollectionMenuAction extends UserMenuAction {


    public CollectionMenuAction(PlayerController playerController) {
        super(playerController);
    }

    public void showHeroCards(ArrayList<MyCardButton> heroes, CollectionMenu collectionMenu) {
        for (MyCardButton button : heroes) {
            button.addActionListener(actionEvent -> {
                for (MyCardButton object : heroes) {
                    if (object.getY() != GraphicsDefault.Collection.heroesButtonBounds(1).getY())
                        object.setLocation(object.getX(), GraphicsDefault.Collection.heroesButtonBounds(1).getY());
                }
                button.setLocation(button.getX(), GraphicsDefault.Collection.heroesButtonBounds(1).getY() + 30);
                collectionMenu.setCurrentHeroSelected(button.getButtonName());
                PlayerLogs.addToLogBody(LogsEnum.valueOf("collection").getEvent()[1],
                        button.getButtonName() + LogsEnum.valueOf("collection").getEvent_description()[1], playerController.getPlayer());
                collectionMenu.startShowCardPanelContent(button.getButtonName(),
                        playerController.getCollectionController().getCollectionCardsShow(
                                button.getButtonName(), 0, "", true, true));
            });
        }

    }

    public void filterAction(JButton button, CollectionMenu collectionMenu, JTextField searchCardName,
                             JComboBox<Integer> manaFilter, JComboBox<String> userHaveFilter) {
        button.addActionListener(actionEvent -> {
            boolean userCards = true;
            boolean closedCards = true;
            if (userHaveFilter.getSelectedItem().toString().equals("User Cards"))
                closedCards = false;
            if (userHaveFilter.getSelectedItem().toString().equals("Close Cards"))
                userCards = false;
            if (collectionMenu.getCurrentHeroSelected() != null)
                collectionMenu.startShowCardPanelContent(collectionMenu.getCurrentHeroSelected(),
                        playerController.getCollectionController().getCollectionCardsShow(
                                collectionMenu.getCurrentHeroSelected(), (Integer) manaFilter.getSelectedItem(),
                                searchCardName.getText(), userCards, closedCards));
        });

    }

}
