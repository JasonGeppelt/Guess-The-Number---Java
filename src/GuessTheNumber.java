import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int gamesWon = 0;
        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess;
            int maxNumber;

            // Display difficulty levels and prompt user to choose
            System.out.println("Welcome to Guess the Number game!");
            System.out.println("Select the difficulty level:");
            System.out.println("1. Easy (1-100)");
            System.out.println("2. Medium (1-150)");
            System.out.println("3. Hard (1-200)");

            int choice = 0;
            boolean validChoice = false;

            // Validate user input for difficulty choice
            while (!validChoice) {
                System.out.print("Enter your choice (1-3): ");
                try {
                    choice = scanner.nextInt();

                    if (choice >= 1 && choice <= 3) {
                        validChoice = true;
                    } else {
                        System.out.println("Please enter a valid choice between 1 and 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Set max number range based on user's choice
            maxNumber = switch (choice) {
                case 1 -> 100;
                case 2 -> 150;
                case 3 -> 200;
                default -> 100; // Set default to Easy mode
            };

            // Generate a random number based on the selected difficulty
            numberToGuess = random.nextInt(maxNumber) + 1;
            int attempts = 10;

            int guess;
            boolean guessedCorrectly = false;

            // Game instructions
            System.out.println("I've picked a number between 1 and " + maxNumber + ". You have " + attempts + " attempts to guess it.");

            // Main game loop with improved error handling
            while (attempts > 0) {
                System.out.print("Enter your guess: ");
                try {
                    guess = scanner.nextInt();
                    attempts--;

                    if (guess == numberToGuess) {
                        guessedCorrectly = true;
                        break;
                    } else if (guess < numberToGuess) {
                        System.out.println("Too low! Try again.");
                    } else {
                        System.out.println("Too high! Try again.");
                    }

                    System.out.println("Attempts left: " + attempts);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Display result based on the user's guess
            if (guessedCorrectly) {
                System.out.println("Congratulations! You guessed the number " + numberToGuess + " correctly!");
                gamesWon++; // Increment the games won for each correct guess
            } else {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + numberToGuess);
            }

            // Ask the user if they want to play again
            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = scanner.next().toLowerCase();
            if (!playChoice.equals("yes")) {
                playAgain = false;
            }
        }

        // Display the final score and close the scanner
        System.out.println("Thank you for playing! Your score (games won): " + gamesWon);
        scanner.close(); // Close the scanner
    }
}
