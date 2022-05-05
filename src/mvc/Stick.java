package mvc;

public class Stick {
    private int x;
    private int y;
    private int width;
    private int height;
    private Client client;

    public Stick(int x, int y, int width, int height, Client client) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.client = client;
    }
}
