import defaults.FilesPath;
import initializer.Initializer;
import network.ConfigLoader;
import network.server.ServerNetwork;

public class ServerMain {
    public static void main(String[] args) {
        getInstance().startServer();
    }

    private static ServerMain instance = null;
    private ServerNetwork serverNetwork;
    private ConfigLoader configLoader;

    private ServerMain() {
        this.configLoader = new ConfigLoader(FilesPath.CONFIGURATION_FILE);
        this.serverNetwork = new ServerNetwork(configLoader);
    }

    public static ServerMain getInstance() {
        if (instance == null)
            instance = new ServerMain();
        return instance;
    }

    private void startServer() {
        Initializer.getInstance().initializeServer();
        serverNetwork.start();
    }
}
