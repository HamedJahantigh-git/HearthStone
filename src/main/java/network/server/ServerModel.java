package network.server;

import controller.PlayerController;

import java.util.HashMap;
import java.util.Map;

public class ServerModel {

    private Map<String, PlayerController> onlinePlayerCont;


    private static ServerModel instance = null;

    private ServerModel(){
        onlinePlayerCont = new HashMap<>();
    }

    public static ServerModel getInstance(){
        if(instance == null)
            instance = new ServerModel();
        return instance;
    }

    public void addOnlinePlayer(String authToken, PlayerController playerController) {
        onlinePlayerCont.put(authToken, playerController);
    }


}
