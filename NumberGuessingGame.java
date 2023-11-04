import java.util.Random;
import java.util.Scanner;

    public class NumberGuessingGame {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            int targetNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
            int attempts = 0;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I've selected a random number between 1 and 100.");

            while (attempts < 10) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < targetNumber) {
                    System.out.println("Try a higher number.");
                } else if (userGuess > targetNumber) {
                    System.out.println("Try a lower number.");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    break;
                }

                if (attempts == 10) {
                    System.out.println("Sorry, you've run out of attempts. The number was " + targetNumber + ".");
                }
            }

            scanner.close();
        }
    }
