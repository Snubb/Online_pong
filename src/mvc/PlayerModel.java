package mvc;

import mvc.Shapes.Shape;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class PlayerModel {
    private int numBalls;
    private double timeFrame = 0.1;
    Socket socket = null;
    PrintWriter out;
    private ArrayList<Stick> stickArrayList = new ArrayList<>();
    int player;

    public void setUp(int player) {
        Stick myStick = new Stick(0, 0, 0, 0);
        Stick enemyStick = new Stick(0, 0, 0, 0);
        switch (player) {
            case 1:
                myStick = new Stick(50, 50, 10, 100);
                enemyStick = new Stick(750, 50, 10, 100);
                this.player = 1;
                break;
            case 2:
                myStick = new Stick(750, 50, 10, 100);
                enemyStick = new Stick(50, 50, 10, 100);
                this.player = 2;
                break;
        }
        stickArrayList.add(myStick);
        stickArrayList.add(enemyStick);
    }

    public void setStickSpeed(int player, int speed) {
        stickArrayList.get(player-1).setSpeed(speed);
        /*switch (player) {
            case 1:
                stickArrayList.get(0).setSpeed(speed);
                break;
            case 2:
                stickArrayList.get(1).setSpeed(speed);
                break;
        }*/
    }

    public ArrayList<Stick> getStickArrayList() {
        return stickArrayList;
    }

    public String getAllSticks() {
        StringBuilder msg = new StringBuilder();
        for (Stick s : stickArrayList) {
            msg.append(s);
        }

        return msg.toString();
    }

    public void setTimeFrame(double timeFrame) {
        this.timeFrame = timeFrame;
    }

    public PlayerModel(){

    }

    public void update() {
        for (Stick stick : stickArrayList) {
            stick.move();
        }
    }

    public void startClient(View view) {
        String ip = "localhost";
        int port = 8000;

        try {
            socket = new Socket(ip,port);
        } catch (IOException e) {
            System.out.println("Client failed to connect");
            System.exit(0);
        }

        // GO
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream())), view, this, new ObjectInputStream(socket.getInputStream()));
            Thread listener = new Thread(in);
            listener.start();
            // OLD NOT NEEDED
            /*boolean run = false;
            while (run) {
                String msg = tgb.nextLine();
                if (msg.equals("quit")) {
                    run = false;
                    out.println(msg);
                } else {
                    out.println(msg);
                }
            }*/

            //out.close();
            //socket.close();
            System.out.println("Connected!");
        } catch (Exception e) {
            System.out.println("Client failed to communicate");
        }
    }

    public void sendMessage(String message) {
        out.println(message);

    }

    public void sendMoveAction(int speed) {
        out.println("moveAction{" + player + "}speed[" + speed + "]");
    }

    public void setStickArrayList(ArrayList<String> newArrayList) {
        stickArrayList.clear();
        for (String s : newArrayList) {
            stickArrayList.add(new Stick(s));
        }
    }



    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        /*for (int i = 0; i < balls.size(); i++) {
            shapes.add(balls.get(i).getShape());
        }*/
        return shapes;
    }


}
