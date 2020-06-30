package userInterfaces.userMenu;

import controller.PlayerController;
import defaults.GraphicsDefault;
import enums.*;
import model.card.Card;
import userInterfaces.Sounds;
import userInterfaces.myComponent.MyFrame;
import userInterfaces.userMenu.play.MineGameBoard;
import userInterfaces.userMenu.play.PlayerGraphicThread;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class UserFrame {

    private PlayerController playerController;
    private MyFrame userFrame;
    private JLayeredPane pane;
    private Sounds mainSounds;
    private MainMenu mainMenu;
    private CollectionMenu collectionMenu;
    private ShopMenu shopMenu;
    private StatusMenu statusMenu;
    private SettingMenu settingMenu;
    private MineGameBoard mineGameBoard;

    public UserFrame(PlayerController playerController) {
        this.playerController = playerController;
        userFrame = new MyFrame("User Menu",
                GraphicsDefault.UserMenu.mainBounds);
        pane = userFrame.getLayeredPane();
        mainSounds = new Sounds("MainMenu.wav");
        mainMenu = new MainMenu(this);
        shopMenu = new ShopMenu(this);
        collectionMenu = new CollectionMenu(this);
        statusMenu = new StatusMenu(this);
        settingMenu = new SettingMenu(this);
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public JLayeredPane getPane() {
        return pane;
    }

    public Sounds getMainSounds() {
        return mainSounds;
    }

    public ShopMenu getShopMenu() {
        return shopMenu;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void startCollection() {
        showPanel(AllMenuSwitch.collection);
        ArrayList<Card> cards = new ArrayList<>();
        collectionMenu.showDeckList();
        collectionMenu.startShowCardPanelContent("Pleas Select Hero To Show Cards", cards);
    }

    public void startShopMenu() {
        showPanel(AllMenuSwitch.shop);
        shopMenu.getCardPanel().removeAll();
    }

    public void startMineGame() {
        mainSounds.stopAudio();
        deleteLayer(MineGameLayer.mainPanel.getLayer(), MineGameLayer.endLayer.getLayer());
        mineGameBoard = new MineGameBoard(this, playerController.getPlayer(), "Battle Ground 1");
        showPanel(AllMenuSwitch.playMine);
    }

    public void startSettingMenu() {
        showPanel(AllMenuSwitch.setting);
    }

    public void startStatus() {
        statusMenu.showTopDecks();
        showPanel(AllMenuSwitch.status);
    }

    public void startMainMenu() {
        showPanel(AllMenuSwitch.mainMenu);
    }

    void showPanel(AllMenuSwitch s) {
        switch (s) {
            case mainMenu:
                offAllLayer();
                onLayer(MainLayer.mainPanel.getLayer(), MainLayer.endLayer.getLayer());
                break;
            case playMine:
                offAllLayer();
                onLayer(MineGameLayer.mainPanel.getLayer(), MineGameLayer.endLayer.getLayer());
                break;
            case shop:
                offAllLayer();
                onLayer(ShopLayer.mainPanel.getLayer(), ShopLayer.endLayer.getLayer());
                break;
            case collection:
                offAllLayer();
                onLayer(CollectionLayer.mainPanel.getLayer(), CollectionLayer.endLayer.getLayer());
                break;
            case setting:
                offAllLayer();
                onLayer(SettingLayer.mainPanel.getLayer(), SettingLayer.endLayer.getLayer());
                break;
            case status:
                offAllLayer();
                onLayer(StatusLayer.mainPanel.getLayer(), StatusLayer.endLayer.getLayer());
                break;
        }
    }

    private void offAllLayer() {
        for (Component component : pane.getComponents()) {
            component.setVisible(false);
            component.setEnabled(false);
        }
    }

    private void onLayer(int start, int end) {
        for (int i = start; i < end; i++) {
            for (Component component : pane.getComponentsInLayer(i)) {
                component.setVisible(true);
                component.setEnabled(true);
            }
        }
    }

    private void deleteLayer(int start, int end) {
        for (int i = start; i < end; i++) {
            for (Component component : pane.getComponentsInLayer(i)) {
                pane.remove(component);
            }
        }
    }

}
