package Assignment2;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCipher {
    private int key;

    // Constructor: sets the secret numeric key (e.g., 3)
    public FileCipher(int key) {
        this.key = key;
    }

    // 1. ENCRYPTION: Reads plain file -> shifts forward (+) -> writes encrypted file
    public void encrypt(String inputFile, String outputFile) throws IOException {
        try (FileReader reader = new FileReader(inputFile);
             FileWriter writer = new FileWriter(outputFile)) {

            int character;
            // reader.read() reads one character at a time until End Of File (-1)
            while ((character = reader.read()) != -1) {
                char encryptedChar = (char) (character + key);
                writer.write(encryptedChar);
            }
        }
    }

    // 2. DECRYPTION: Reads encrypted file -> shifts backward (-) -> writes original file
    public void decrypt(String inputFile, String outputFile) throws IOException {
        try (FileReader reader = new FileReader(inputFile);
             FileWriter writer = new FileWriter(outputFile)) {

            int character;
            while ((character = reader.read()) != -1) {
                char decryptedChar = (char) (character - key);
                writer.write(decryptedChar);
            }
        }
    }
}