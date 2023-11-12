package swing;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSwing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane);

        JTextField messageField = new JTextField();
        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    Socket cliente = new Socket("10.136.64.46", 10000);
                    PrintStream out = new PrintStream(cliente.getOutputStream());
                    chatArea.append("Eu: " + messageField.getText() + "\n");
                    out.println(messageField.getText());
                    out.close();
                    cliente.close();
                    messageField.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        frame.add(messageField, "South");

        frame.setVisible(true);
    }
}


