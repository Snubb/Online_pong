package mvc;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread implements Runnable {
    ServerSocket serverSocket;
    ServerThread serverThread;
    private int connections = 0;

    public SocketThread(ServerSocket serverSocket, ServerThread serverThread) {
        this.serverSocket = serverSocket;
        this.serverThread = serverThread;
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                ServerListenThread in =
                        new ServerListenThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), serverThread);
                Thread listener = new Thread(in);
                listener.start();
                System.out.println("New connection found");
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
                Client client = new Client(in, out, objOut);
                if (connections == 0) {
                    out.println("initSetUp: Player 1");
                    connections++;
                } else {
                    out.println("initSetUp: Player 2");
                }
                serverThread.addConnection(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void stop() {

    }
}

