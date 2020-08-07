package userInterfaces.graphicsActions;

import controller.FileManagement;
import controller.PlayerController;
import enums.LogsEnum;
import logs.PlayerLogs;
import network.client.ClientNetwork;
import userInterfaces.MyGraphics;


import javax.swing.*;
import java.awt.*;

public class MyAction {

    //todo delete in final
    protected PlayerController playerController;

    protected ClientNetwork clientNetwork;

    protected MyGraphics myGraphics;

    protected MyAction(MyGraphics myGraphics){
        this.myGraphics = myGraphics;
    }

    protected void setGraphics(MyGraphics myGraphics){
        this.myGraphics = myGraphics;
    }

    public void exitGame(JButton button) {
        button.addActionListener(actionEvent -> {
            clientNetwork.getSender().exitGame();
            System.exit(0);
        });
    }

    public void okMessage(JPanel messagePanel, JPanel[] onPanels, JButton button) {
        button.addActionListener(actionEvent -> {
            for (JPanel panel : onPanels) {
                for (Component c : panel.getComponents()) c.setEnabled(true);
            }
            messagePanel.setVisible(false);
            messagePanel.removeAll();
        });
    }

    public PlayerController getPlayerController() {
        return playerController;
    }
}
