package myapp.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientApp {
    
    public static void main(String[] args) throws IOException {
        
        Socket socket = new Socket("localhost", 1234);
        
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        Console console = System.console();
        String responseToServer = "";
        String responseFromServer = "";

        while (true) {
            printMenu();
            responseToServer = console.readLine();
            dos.writeUTF(responseToServer);
            dos.flush();
            if (responseToServer.equalsIgnoreCase("quit")) {
                System.out.println("Ending program... Goodbye!");
                break;
            }
            responseFromServer = dis.readUTF();
            System.out.println(responseFromServer);
            if (responseToServer.contains("correct")) {
                break;
            }
        }

        bos.flush();
        os.flush();
        dos.close();
        bos.close();
        os.close();
        dis.close();
        bis.close();
        is.close();
        socket.close();
    }

    public static void printMenu() {
        System.out.println("""
            Enter "quit" to quit the program, or
            Enter "guess X" to guess a number X between 0 to 99: """);
    }
}
