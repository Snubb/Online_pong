package mvc;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerThread implements Runnable{
    private HostModel model;
    private int port;
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Stick> stickArrayList = new ArrayList<>();
    private ServerSocket serverSocket;
    private SocketThread socketThread;

    public ServerThread(HostModel model, int port) {
        this.model = model;
        this.port = port;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            socketThread = new SocketThread(serverSocket, this);
            Thread server = new Thread(socketThread);
            server.start();
            boolean run = true;
            Scanner tgb = new Scanner(System.in);
            System.out.println("Server started");
            while (run) {
                String msg = tgb.nextLine();
                if (msg.endsWith("quit")) {
                    run = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        try {
            socketThread.stop();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addConnection(Client client) {
        /*if (stickArrayList.size() == 0) {
            stickArrayList.add(new Stick(50, 50, 20, 100, client));
        } else if (stickArrayList.size() == 1) {
            stickArrayList.add(new Stick(250, 250, 20, 100, client));
        } else {
            System.out.println("Shit's full");
            return;
        }*/
        clientArrayList.add(client);
        if (clientArrayList.size() >= 2) {
            //Denna lösning för att slumpa höger/vänster kinda shit men jag fixar senare kanske
            if (Math.random() >= 0.5) {
                sendMessageToAll("beginGame:right");
            } else {
                sendMessageToAll("beginGame:left");
            }
        }
        /*StringBuilder newMsg = new StringBuilder();
        for (Stick stick : stickArrayList) {
            newMsg.append(stick.toString());
        }
        sendMessageToAll(newMsg.toString());*/
    }

    public void sendMessageToAll(String msg) {
        for (int i = 0; i < clientArrayList.size(); i++) {
            clientArrayList.get(i).out.println(msg);
        }
    }
}
