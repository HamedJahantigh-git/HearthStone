import initializer.Initializer;
import model.MyThread;
import userInterfaces.MyGraphics;

public class ClientMain extends MyThread {

    public static void main(String[] args) {
        new ClientMain().startNewClient();
    }

    private void startNewClient(){
        Initializer.getInstance().initializeClient();
        new MyGraphics().startAccountMenu();
    }
}
