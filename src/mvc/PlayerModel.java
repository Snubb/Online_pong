package mvc;

import mvc.Shapes.Shape;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class PlayerModel {
    private int numBalls;
    private double timeFrame = 0.1;
    Socket socket = null;
    PrintWriter out;


    public void setTimeFrame(double timeFrame) {
        this.timeFrame = timeFrame;
    }

    public PlayerModel(){

    }

    public void update() {

    }

    public void startClient() {
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
            Scanner tgb = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream(),true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream())));
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



    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        /*for (int i = 0; i < balls.size(); i++) {
            shapes.add(balls.get(i).getShape());
        }*/
        return shapes;
    }


}
