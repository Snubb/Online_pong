package mvc;

import java.awt.*;
import java.io.Serializable;

public class Stick implements Serializable {
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle rect;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int vy;
    private Client client;

    @Override
    public String toString() {
        return "Stick{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public Stick(int x, int y, int width, int height, Client client) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.client = client;
    }
    public Stick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(x, y, width, height);
    }
    public Stick(String string) {
        this.x = Integer.parseInt(string.substring(string.indexOf('x') + 2, string.indexOf(',', string.indexOf('x'))));
        this.y = Integer.parseInt(string.substring(string.indexOf('y') + 2, string.indexOf(',', string.indexOf('y'))));
        this.width = Integer.parseInt(string.substring(string.indexOf("width") + 6, string.indexOf(',', string.indexOf("width"))));
        this.height = Integer.parseInt(string.substring(string.indexOf("height"), string.indexOf(',', string.indexOf("heigth"))));
    }
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void setSpeed(int speed) {
        //System.out.println("Set speed to: " + speed);
        this.vy = speed;
    }
    public void move() {
        this.y += this.vy;
    }
}
