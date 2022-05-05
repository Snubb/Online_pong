package mvc;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * This is a class
 * Created 2021-03-16
 *
 * @author Magnus Silverdal
 */
public class ServerListenThread implements Runnable{
    private BufferedReader in;
    String msg;
    private ServerThread serverThread;

    public ServerListenThread(BufferedReader in, ServerThread serverThread) {
        this.in = in;
        this.serverThread = serverThread;
    }

    @Override
    public void run() {
        msg = null;
        boolean running = true;
        while (running) {
            try {
                msg = in.readLine();
            } catch (IOException e) {
                running = false;
                //e.printStackTrace();
            }
            System.out.println(msg);
            serverThread.sendMessageToAll(msg);
        }
    }

    public void stop()  {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
