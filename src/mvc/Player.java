package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player implements Runnable {
    private Thread thread;
    private boolean running = false;
    private int fps = 60;
    private int ups = 60;
    private PlayerModel model;
    private View view;
    private int width = 800;
    private int height = 600;
    //I have not taken scale into consideration so changing it will likely crash the whole thing
    private int scale = 1;
    private boolean randomSpawn = false;
    private boolean blackWhite = false;
    private final Rectangle mouse = new Rectangle();
    private JFrame frame;
    private String title = "";



    public Player() {
        model = new PlayerModel();
        view = new View(width, height, scale);

        frame = new JFrame(title);
        //frame.setUndecorated(true);
        //frame.setOpacity(0.5f);
        frame.setResizable(false);
        frame.add(view);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.addKeyListener(new KL());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();

        model.startClient();
    }

    private class ML implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) { }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }
        @Override
        public void mouseReleased(MouseEvent mouseEvent) { }
        @Override
        public void mouseEntered(MouseEvent mouseEvent) { }
        @Override
        public void mouseExited(MouseEvent mouseEvent) { }
    }

    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == 'k') {
                model.sendMessage("mah balls");
            }
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
        }
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double nsFPS = 1000000000.0 / fps;
        double nsUPS = 1000000000.0 / ups;
        double deltaFPS = 0;
        double deltaUPS = 0;
        int frames = 0;
        int updates = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            deltaFPS += (now - lastTime) / nsFPS;
            deltaUPS += (now - lastTime) / nsUPS;
            lastTime = now;

            while(deltaUPS >= 1) {
                model.update();
                view.draw();
                updates++;
                deltaUPS--;
            }

            while (deltaFPS >= 1) {
                view.render();
                frames++;
                deltaFPS--;
            }

            if(System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle("WEE");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    public static void main(String[] args) {
        Player c = new Player();
        c.start();
    }

}
