package mvc;

public class HostModel {
    int port = 8000;

    public HostModel() {

    }
    public void startServer(HostModel model) {
        ServerThread serverThread = new ServerThread(model, port);
        Thread server = new Thread(serverThread);
        server.start();
    }
}
