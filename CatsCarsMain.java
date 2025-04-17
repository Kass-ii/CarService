
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CatsCarsMain {
    private static final String FILE_NAME = "users.txt";

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static boolean usernameExists(String username) throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(username)) return true;
            }
        }
        return false;
    }

    public static boolean registerUser(String username, String password)
            throws IOException, NoSuchAlgorithmException {
        if (usernameExists(username)) {
            System.out.println("Username already exists. Choose another one.");
            return false;
        }

        String hashedPassword = hashPassword(password);
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(username + ":" + hashedPassword);
            writer.newLine();
        }
        System.out.println("User registered successfully!");
        return true;
    }

