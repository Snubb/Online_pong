package mvc;

public class Host {
    private HostModel model;

    public Host() {
        model = new HostModel();



        model.startServer(model);
    }
    public static void main(String[] args) {
        Host host = new Host();
    }
}
