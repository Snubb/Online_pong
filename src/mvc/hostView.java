package mvc;

import javax.swing.*;

public class hostView {

    private JPanel panel;
    private JTextArea textArea1;
    private JButton serverStartButton;
    private JButton openPlayerButton;
    private JButton openSecondPlayerButton;

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public JButton getServerStartButton() {
        return serverStartButton;
    }

    public JButton getOpenPlayerButton() {
        return openPlayerButton;
    }

    public JButton getOpenSecondPlayerButton() {
        return openSecondPlayerButton;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JPanel getPanel() {
        return panel;
    }
}
