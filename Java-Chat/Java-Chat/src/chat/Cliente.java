package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket cliente = new Socket("192.168.0.19", 10000); // info sobre o servidor

        Scanner sc = new Scanner(System.in);
        PrintStream out = new PrintStream(cliente.getOutputStream());

        while (sc.hasNextLine()) {
            out.println(sc.nextLine());
        }

        out.close();
        sc.close();
        cliente.close();
    }
}
