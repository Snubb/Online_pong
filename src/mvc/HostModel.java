package mvc;

public class HostModel {
    ServerThread serverThread;
    Thread server;

    public HostModel() {

    }
    public void startServer(HostModel model, int port) {
        serverThread = new ServerThread(model, port);
        server = new Thread(serverThread);
        server.start();
    }
    public void closeServer() {
        serverThread.stop();
        server.interrupt();
    }
}
