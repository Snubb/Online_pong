package mvc;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class Client implements Serializable {
    ObjectOutputStream objOut;
    private ServerListenThread in;
    PrintWriter out;


    public Client(ServerListenThread in, PrintWriter out, ObjectOutputStream objOut) {
        this.in = in;
        this.out = out;
        this.objOut = objOut;
    }
}
