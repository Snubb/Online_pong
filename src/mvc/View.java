package mvc;
import mvc.Shapes.Circle;
import mvc.Shapes.Shape;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.concurrent.*;

public class View extends Canvas {
    private int WIDTH;
    private int HEIGTH;
    private int scale;

    private BufferedImage image;
    private Screen screen;

    private String counter = "";
    ScheduledFuture<?> t;

    Graphics g;

    private ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(1);

    public View(int width, int height, int scale) {
        // Screen data
        this.WIDTH = width;
        this.HEIGTH = height;
        this.scale = scale;
        image = new BufferedImage(WIDTH/scale, HEIGTH/scale, BufferedImage.TYPE_INT_RGB);
        screen = new Screen(((DataBufferInt) image.getRaster().getDataBuffer()).getData(),image.getWidth(),
                image.getHeight());
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
    }

    public void render(ArrayList<Stick> stickArrayList, Ball ball, int player1score, int player2score) {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();


        g.setColor(Color.black);
        g.drawImage(image, 0, 0, WIDTH, HEIGTH, null);
        String score = player1score + ":" + player2score;
        g.setColor(Color.white);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 50));
        g.drawString(score, WIDTH/2 - 40, 100);
        g.drawString(counter, WIDTH/2, HEIGTH/2);
        drawSticks(g, stickArrayList);
        g.fillOval(ball.getX(), ball.getY(), ball.getCircle().getRadius(), ball.getCircle().getRadius());
        //drawHitboxes(stickArrayList, ball, g);
        g.dispose();
        bs.show();
    }

    public void beginCountdown() {
        System.out.println("Began countdown");
        //final int[] count = {3};

        t = executorService.scheduleAtFixedRate(new Runnable() {
            int count = 3;
            @Override
            public void run() {
                System.out.println("counted");
                g.setColor(Color.white);
                counter = "" + count;
                count--;
                if (count <= -1) {
                    counter = "";
                    t.cancel(false);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void drawHitboxes(ArrayList<Stick> sticks, Ball ball, Graphics g) {
        g.setColor(Color.blue);
        for (Stick stick : sticks) {
            g.drawRect(stick.getRect().x, stick.getRect().y, stick.getRect().width, stick.getRect().height);
        }
        g.drawRect(ball.getRect().x, ball.getRect().y, ball.getRect().width, ball.getRect(). height);
    }

    public void drawSticks(Graphics g, ArrayList<Stick> stickArrayList) {
        g.setColor(Color.white);
        for (Stick stick : stickArrayList) {
            Rectangle rect = stick.getRect();
            //System.out.println("Drawing: " + rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    public Screen getScreen() {
        return screen;
    }

    public void draw() {

    }
}