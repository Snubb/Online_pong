package mvc;

import mvc.Shapes.Shape;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class PlayerModel {
    Socket socket = null;
    PrintWriter out;
    private ArrayList<Stick> stickArrayList = new ArrayList<>();
    int player;
    private boolean gameRunning;
    private Ball ball = new Ball(350, 350);
    private int player1score = 0;
    private int player2score = 0;
    private boolean scored = false;

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    public int getPlayer1score() {
        return player1score;
    }

    public int getPlayer2score() {
        return player2score;
    }

    public void setUp(int player) {
        Stick myStick = new Stick(0, 0, 0, 0);
        Stick enemyStick = new Stick(0, 0, 0, 0);
        switch (player) {
            case 1:
                System.out.println("you player 1");
                myStick = new Stick(50, 50, 10, 100);
                enemyStick = new Stick(750, 50, 10, 100);
                this.player = 1;
                break;
            case 2:
                System.out.println("you player 2");
                enemyStick = new Stick(750, 50, 10, 100);
                myStick = new Stick(50, 50, 10, 100);
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

    public PlayerModel(){

    }

    public void update() {
        for (Stick stick : stickArrayList) {
            stick.move();
        }
        ball.update(stickArrayList);
        checkForCollision();
    }

    private void checkForCollision() {
        if (ball.getX() < 0) {
            ball.reset();
            player2score++;
            scored = true;
        } else if (ball.getX() > 800) {
            ball.reset();
            player1score++;
            scored = true;
        }
    }



    public void startClient(View view) {
        //String ip = "localhost";
        //int port = 8000;
        String ip = JOptionPane.showInputDialog("IP: ");
        int port = Integer.parseInt(JOptionPane.showInputDialog("Port: "));

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

    public void begin(String direction) {
        this.gameRunning = true;
        if (direction.equals("right")) {
            this.ball.setVx(5);
        } else {
            this.ball.setVx(-5);
        }
        ball.reset();
    }
    public boolean isGameRunning() {
        return gameRunning;
    }
    public Ball getBall() {
        return ball;
    }
}
