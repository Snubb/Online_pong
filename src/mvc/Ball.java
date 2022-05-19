package mvc;

import mvc.Shapes.Circle;
import mvc.Shapes.Point;

import java.awt.*;
import java.util.ArrayList;

public class Ball {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private Circle circle;
    private Rectangle rect;

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
        if (this.x < 0) {
            this.x = 400;
            this.vx *= -1;
        } else if (this.x > 800) {
            this.x = 400;
            this.vx *= -1;
        }
        for (Stick stick: sticks) {
            if (this.rect.intersects(stick.getRect())) {
                System.out.println("Bounced");
                //this.x = 400;
                this.vx *= -1;
            }

        }
    }
}