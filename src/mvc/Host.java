package mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host {
    private HostModel model;
    private hostView view;
    public Host() {
        model = new HostModel();
        view = new hostView();

        JFrame frame = new JFrame("Server");
        frame.setContentPane(view.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        view.getServerStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = "Nothing";
                int port = Integer.parseInt(JOptionPane.showInputDialog("Port (ex: 8000): "));
                try {
                    ip = InetAddress.getLocalHost().toString();
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                }
                model.startServer(model, port);
                view.getTextArea1().setText("Server started at port " + port);
                view.getServerStartButton().setEnabled(false);
                view.getCloseServerButton().setEnabled(true);
            }
        });

        view.getCloseServerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.closeServer();
                view.getTextArea1().setText("Server closed");
                view.getCloseServerButton().setEnabled(false);
                view.getServerStartButton().setEnabled(true);
                view.getOpenPlayerButton().setEnabled(true);
                view.getOpenSecondPlayerButton().setEnabled(true);
            }
        });

        view.getOpenPlayerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player = new Player();
                player.start();
                view.getOpenPlayerButton().setEnabled(false);
            }
        });

        view.getOpenSecondPlayerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player player2 = new Player();
                player2.start();
                view.getOpenSecondPlayerButton().setEnabled(false);
            }
        });

    }
    public static void main(String[] args) {
        Host host = new Host();
    }
}
