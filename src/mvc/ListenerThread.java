package mvc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * This is a class
 * Created 2021-03-16
 *
 * @author Magnus Silverdal
 */
public class ListenerThread implements Runnable{
    private BufferedReader in;
    String msg;
    private View view;

    public ListenerThread(BufferedReader in, View view) {
        this.in = in;
        this.view = view;
    }

    @Override
    public void run() {
        msg = null;
        boolean running = true;
        while (running) {
            try {
                msg = in.readLine();
                view.setText(msg);
            } catch (IOException e) {
                running = false;
                //e.printStackTrace();
            }
            System.out.println(msg);

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
