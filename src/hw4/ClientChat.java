package hw4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientChat {
    private JTextArea chatText;
    private JPanel mainPanel;
    private JTextField messageTextField;
    private JButton sendButton;

    ClientChat(){
        createActionListeners();
    }

    private void createActionListeners() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        messageTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void sendMessage(){
        String message = messageTextField.getText();
        if(message.isEmpty()){
            return;
        }

        chatText.append(message + "\n");
        messageTextField.setText("");
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setTitle("Messenger");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new ClientChat().mainPanel);
        frame.setVisible(true);

    }
}
