package userInterfaces.graphicsActions;

import controller.FileManagement;
import controller.PlayerController;
import enums.LogsEnum;
import logs.PlayerLogs;


import javax.swing.*;
import java.awt.*;

public class MyAction {

    public void exitGame(JButton button, PlayerController playerController) {
        button.addActionListener(actionEvent -> {
            if (playerController != null) {
                PlayerLogs.addToLogBody(LogsEnum.valueOf("sign").getEvent()[2],
                        LogsEnum.valueOf("sign").getEvent_description()[3], playerController.getPlayer());
                FileManagement.savePlayerToFile(playerController.getPlayer());
            }
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

}
