package mvc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a class
 * Created 2021-03-16
 *
 * @author Magnus Silverdal
 */
public class ListenerThread implements Runnable{
    private BufferedReader in;
    private ObjectInputStream objIn;
    private String msg;
    private ArrayList<String> stickArrayList = new ArrayList<>();
    private View view;
    private PlayerModel model;

    public ListenerThread(BufferedReader in, View view, PlayerModel model, ObjectInputStream objIn) {

        this.in = in;
        this.view = view;
        this.model = model;
        this.objIn = objIn;
    }

    @Override
    public void run() {
        msg = null;

        boolean running = true;
        while (running) {
            try {
                msg = in.readLine();
                if (msg.startsWith("initSetUp")) {
                    if (msg.contains("Player 1")) {
                        System.out.println("I am player 1");
                        model.setUp(1);
                    } else if(msg.contains("Player 2")){
                        System.out.println("I am player 2");
                        model.setUp(2);
                    }
                }
                if (msg.startsWith("beginGame")) {
                    String direction = msg.split("beginGame")[1];
                    model.begin(direction);
                }
                if (msg.startsWith("moveAction")) {
                    int player = Integer.parseInt(msg.substring(msg.indexOf('{')+1, msg.indexOf('}')));
                    int speed = Integer.parseInt(msg.substring(msg.indexOf('[')+1, msg.indexOf(']')));
                    model.setStickSpeed(player, speed);
                }
                /*String[] strings = in.readLine().split("String");
                System.out.println("RESULT: \n" + Arrays.toString(strings));
                stickArrayList.clear();
                stickArrayList.add(msg.substring(0, msg.length()/2));
                stickArrayList.add(msg.substring(msg.length()/2));*/

                //model.setStickArrayList(stickArrayList);
            } catch (IOException e) {
                running = false;
                //e.printStackTrace();
            }
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
