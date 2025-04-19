import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

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
            int option = -1;
            while (option!=0){ 
            System.out.println("Would you like to 1, update a cat status. 2, get a list of cat statuses, or 3, vote for your cat. Enter 0 to exit");
            option = scanner.nextInt(); 
            scanner.nextLine();  
            if (option ==1){
                System.out.println("Enter the name of the cat you would like to update!");
                String name = scanner.nextLine();
                Cat catUpdate = new Cat();
                catUpdate.setName(name);
                System.out.println("Would you like to update 1, last location of the cat. or 2, the last time this cat was fed");
                int status = scanner.nextInt();
                File file = new File("cat.txt");
                ArrayList<String> lines = new ArrayList<>();
                boolean catFound = false;
                
                try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    lines.add(line);
                    if (line.startsWith(name + " - ")) {
                    catFound = true;
                    }
                }
                } catch (FileNotFoundException e) {}

                if (!catFound) {
                System.out.println("Cat not found. You can only update existing cats.");
                continue;
                }

                if (status==1){
                    System.out.println("Enter the cats last known location");
                    scanner.nextLine();
                    String location = scanner.nextLine();

                    catUpdate.setStatus("Last seen at " + location);
                    catUpdate.setTime();

                    try (FileWriter writer = new FileWriter("cat.txt", true)) {
                        writer.write(catUpdate.getName() + " - " + catUpdate.getStatus());
                        writer.write(" at " + catUpdate.getFormattedTime());
                        writer.write("\n");
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                }
                else if (status == 2) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String formattedTime = now.format(formatter);
                
                    System.out.println("Last fed at: " + formattedTime);
                
                    catUpdate.setStatus("Last fed"); 
                    catUpdate.setTime(); 
                
                    try (FileWriter writer = new FileWriter("cat.txt", true)) {
                        writer.write(catUpdate.getName() + " - " + catUpdate.getStatus() + ": " + catUpdate.getFormattedTime() + "\n");
                    } catch (IOException e) {
                        System.out.println("Error writing to file.");
                    }
                }
                
                else System.out.println("Invalid option");
            }   
            else if (option == 2){
                try{
                    File file = new File ("cat.txt");
                    Scanner fileScanner = new Scanner(file);
                    while (fileScanner.hasNextLine()){
                        String line = fileScanner.nextLine();
                        System.out.println(line);
                    }
                    fileScanner.close();
                } catch (FileNotFoundException e){
                    return;
                }
            }   
            else if (option == 3){
                System.out.print("please vote for your cat!: ");  
                String cat = scanner.nextLine();                    
                VotingSystem.vote(username, cat);                 
                VotingSystem.showVotes();
            }
            else System.out.println("Invalid option, try again");                                
        }
    }

        scanner.close();
    }
}