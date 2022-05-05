package mvc;

import java.io.PrintWriter;

public class Client {
    ServerListenThread in;
    PrintWriter out;

    public Client(ServerListenThread in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }
}
