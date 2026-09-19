package Assignment2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== FILE ENCRYPTION / DECRYPTION ===");
            System.out.println("1. Encrypt a Text File");
            System.out.println("2. Decrypt a Text File");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleProcess(scanner, true);
                    break;
                case "2":
                    handleProcess(scanner, false);
                    break;
                case "3":
                    System.out.println("Program terminated.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    // Helper method to collect inputs and trigger encryption or decryption
    private static void handleProcess(Scanner scanner, boolean isEncrypt) {
        try {
            System.out.print("Enter source file name (e.g., plain.txt): ");
            String sourceFile = scanner.nextLine().trim();

            // Verify if file exists before proceeding
            File file = new File(sourceFile);
            if (!file.exists()) {
                System.out.println("Error: Source file does not exist.");
                return;
            }

            System.out.print("Enter destination file name (e.g., result.txt): ");
            String destFile = scanner.nextLine().trim();

            System.out.print("Enter secret shift key (an integer, e.g., 3): ");
            int key = Integer.parseInt(scanner.nextLine().trim());

            FileCipher cipher = new FileCipher(key);

            if (isEncrypt) {
                cipher.encrypt(sourceFile, destFile);
                System.out.println("File encrypted successfully into '" + destFile + "'.");
            } else {
                cipher.decrypt(sourceFile, destFile);
                System.out.println("File decrypted successfully into '" + destFile + "'.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: The key must be an integer.");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}