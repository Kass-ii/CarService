import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Cats & Cars!");
        System.out.println("1. Register");
        System.out.println("2. Login");

        int choice = -1;
        while (choice != 1 && choice != 2) {
            System.out.print("Choose an option (1 or 2): ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid option. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean loggedIn = false;                  
    if (choice == 1) {
        loggedIn = CatsCarsMain.registerUser(username, password);
    } else if (choice == 2) {
        loggedIn = CatsCarsMain.loginUser(username, password);
    } else {
        System.out.println("Invalid option.");
    }

        if (loggedIn) {                            
            System.out.print("please vote for your cat!: ");  
            String cat = scanner.nextLine();                    
            VotingSystem.vote(username, cat);                 
            VotingSystem.showVotes();                           
        }

        scanner.close();
    }
}
