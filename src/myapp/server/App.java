package myapp.server;

import java.util.Random;
import java.util.Scanner;

public class App {
 
    public static void main(String[] args) {
        
        Random random = new Random(); // instantiate a Random object
        
        // instantiate a Scanner object
        Scanner scanner = new Scanner(System.in); // Scanner(System.in) scans from console input, Scanner(File object) scans from file, Scanner(InputStream object) scans from InputStream
        // eg. for client-server, Scanner(socket.getInputStream())
        
        int randomInteger = random.nextInt(100); // generates a random integer between 0 - 99

        // initialize a guess
        int guess = 0;

        // allow user to guess until they get the random number correctly
        while (guess != randomInteger) {
            System.out.println("Please guess a number: ");
            guess = scanner.nextInt();

            if (guess == randomInteger) {
                System.out.println("You guessed the correct number!");
                break;
            } else if (guess > randomInteger) {
                System.out.println("The number you guessed is too high.");
            } else if (guess < randomInteger) {
                System.out.println("The number you guessed is too low.");
            }
        }
        scanner.close();

    }
}
