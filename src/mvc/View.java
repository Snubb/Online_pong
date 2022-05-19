package mvc;
import mvc.Shapes.Circle;
import mvc.Shapes.Shape;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class View extends Canvas {
    private int WIDTH;
    private int HEIGTH;
    private int scale;
    private String message = "Nothing yet";

    private BufferedImage image;
    private Screen screen;

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

    public void render(ArrayList<Stick> stickArrayList, Ball ball) {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();


        g.setColor(Color.black);
        g.drawImage(image, 0, 0, WIDTH, HEIGTH, null);
        g.drawString(message, 50, 150);
        drawSticks(g, stickArrayList);
        g.fillOval(ball.getX(), ball.getY(), ball.getCircle().getRadius(), ball.getCircle().getRadius());
        drawHitboxes(stickArrayList, ball, g);
        g.dispose();
        bs.show();
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

    public void setText(String message) {
        this.message = message;
    }

    public Screen getScreen() {
        return screen;
    }

    public void draw() {

    }
}