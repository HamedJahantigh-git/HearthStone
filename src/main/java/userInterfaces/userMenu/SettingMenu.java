package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.SettingLayer;
import userInterfaces.graphicsActions.SettingMenuAction;
import userInterfaces.graphicsActions.ShopMenuAction;
import userInterfaces.graphicsActions.StatusMenuAction;
import userInterfaces.myComponent.MyJPanel;

public class SettingMenu {
    private UserMenu userMenu;
    private SettingMenuAction action;
    private MyJPanel mainPanel;

    public SettingMenu(UserMenu userMenu) {
        this.userMenu = userMenu;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Main Shop.jpg",
                GraphicsDefault.UserMenu.mainBounds, userMenu.getPane(), false, SettingLayer.mainPanel.getLayer());
        action = new SettingMenuAction(userMenu.getPlayerController());
    }

}
