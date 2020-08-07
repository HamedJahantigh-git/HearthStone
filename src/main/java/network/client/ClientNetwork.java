package network.client;


import model.MyThread;
import userInterfaces.MyGraphics;

import java.io.IOException;
import java.net.Socket;

public class ClientNetwork extends MyThread {
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private String authToken;

    public ClientNetwork(MyGraphics myGraphics, String ip, int port) throws IOException {
        authToken = null;
        socket = new Socket(ip, port);
        sender = new Sender(this, socket);
        receiver = new Receiver(myGraphics, socket);
    }

    @Override
    public void run() {
        receiver.start();
    }

    public Sender getSender() {
        return sender;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
