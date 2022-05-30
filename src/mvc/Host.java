package mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                model.startServer(model);
                view.getTextArea1().setText("Server started");
                view.getServerStartButton().setEnabled(false);
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
