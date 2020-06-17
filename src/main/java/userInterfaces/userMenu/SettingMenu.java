package userInterfaces.userMenu;

import defaults.FilesPath;
import defaults.GraphicsDefault;
import enums.SettingLayer;
import userInterfaces.graphicsActions.SettingMenuAction;
import userInterfaces.myComponent.MyJPanel;

public class SettingMenu {
    private UserFrame userFrame;
    private SettingMenuAction action;
    private MyJPanel mainPanel;

    public SettingMenu(UserFrame userFrame) {
        this.userFrame = userFrame;
        mainPanel = new MyJPanel(FilesPath.graphicsPath.backgroundsPath + "/Main Shop.jpg",
                GraphicsDefault.UserMenu.mainBounds, userFrame.getPane(), false, SettingLayer.mainPanel.getLayer());
        action = new SettingMenuAction(userFrame.getPlayerController());
    }

}
