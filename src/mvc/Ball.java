package mvc;

import mvc.Shapes.Circle;
import mvc.Shapes.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ball {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private Circle circle;
    private Rectangle rect;
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public Circle getCircle() {
        return circle;
    }

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.circle = new Circle(new Point(x, y), 10);
        this.rect = new Rectangle(x, y, 10, 10);
    }

    public void reset() {
        this.x = 400;
        int saveVx = this.vx;
        this.vx = 0;
        this.vy = 0;
        executorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        vx = -saveVx;
                    }
                }, 3, TimeUnit.SECONDS);
    }
    public void update(ArrayList<Stick> sticks) {
        this.x += vx;
        this.y += vy;
        this.rect = new Rectangle(x, y, 10, 10);
        if (this.y < 0) {
            this.y = 0;
            this.vy *= -1;
        } else if (this.y > 600) {
            this.y = 600;
            this.vy *= -1;
        }
        for (Stick stick: sticks) {
            if (this.rect.intersects(stick.getRect())) {
                //this.x = 400;
                this.vx *= -1;
                this.vy += stick.getVy();
                if (this.vy > 5) {
                    this.vy = 5;
                } else if(this.vy < -5) {
                    this.vy = -5;
                }

            }

        }
    }
}
