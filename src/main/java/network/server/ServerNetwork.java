package network.server;

import controller.PlayerController;
import model.MyThread;
import network.ConfigLoader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerNetwork extends MyThread {

    private ServerSocket serverSocket;

    public ServerNetwork(ConfigLoader configLoader) {
        try {
            this.serverSocket = new ServerSocket(configLoader.readPortNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                connectClientToServer(serverSocket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void connectClientToServer(Socket socket) {
        new Transceiver(new PlayerController(socket)).start();

    }
}
