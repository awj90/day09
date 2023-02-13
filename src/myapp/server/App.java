package myapp.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class App {
 
    public static void main(String[] args) throws IOException {
        
        Random random = new Random(); // instantiate a Random object
        
        // instantiate a Scanner object
        // Scanner scanner = new Scanner(System.in); // Scanner(System.in) scans from console input, Scanner(File object) scans from file, Scanner(InputStream object) scans from InputStream
        // eg. for client-server, Scanner(socket.getInputStream())
        
        int randomInteger = random.nextInt(100); // generates a random integer between 0 - 99

        // initialize a guess
        int guess = 0;

        // Instantiate a ServerSocket and Socket object listening on port 1234 for input
        ServerSocket server = new ServerSocket(1234);
        System.out.println("Server started...");
        Socket socket = server.accept();

        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        String messageReceived = "";

        while (!messageReceived.equalsIgnoreCase("quit")) {
        
            messageReceived = dis.readUTF(); // user inputs: "guess X", where X is an integer 
            
            if (messageReceived.contains("guess")) {
                guess = Integer.parseInt(messageReceived.substring(6));
            }

            if (guess == randomInteger) {
                dos.writeUTF("You guessed the correct number!");
                dos.flush();
                break;
            } else if (guess > randomInteger) {
                dos.writeUTF("The number you guessed is too high.");
                dos.flush();
            } else if (guess < randomInteger) {
                dos.writeUTF("The number you guessed is too low.");
                dos.flush();
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
        server.close();
    }
}
