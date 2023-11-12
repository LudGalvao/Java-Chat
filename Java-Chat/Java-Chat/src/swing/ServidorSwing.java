package swing;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorSwing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat Servidor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane);

        frame.setVisible(true);

        ServidorThread servidorThread = new ServidorThread(chatArea);
        servidorThread.start();
    }
}

class ServidorThread extends Thread {
    private final JTextArea chatArea;

    public ServidorThread(JTextArea chatArea) {
        this.chatArea = chatArea;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(10000);
            chatArea.append("Porta 10000 aberta, aguardando conexão...\n");

            while (true) {
                Socket client = server.accept();
                chatArea.append("Conexão de " + client.getInetAddress().getHostAddress() + "\n");

                ClienteThread clienteThread = new ClienteThread(client, chatArea);
                clienteThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClienteThread extends Thread {
    private final Socket client;
    private final JTextArea chatArea;

    public ClienteThread(Socket client, JTextArea chatArea) {
        this.client = client;
        this.chatArea = chatArea;
    }

    @Override
    public void run() {
        try {
            Scanner s = new Scanner(client.getInputStream());
            while (s.hasNextLine()) {
                chatArea.append(s.nextLine() + "\n");
            }
            s.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
