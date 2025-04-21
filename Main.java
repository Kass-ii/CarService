import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException{ //main method that uses classes
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Cats & Cars!"); // intro to prompt user to register, login, or login as admin
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Login as admin");
        int choice = scanner.nextInt();

        while (choice != 1 && choice != 2 && choice !=3) { // make sure that loops til user chooses an option for login/register
            System.out.print("Choose an option (1, 2, or 3): ");
            System.out.println("You must pick either 1 to reigster, 2 to login, or 3 to login as admin");
        }
        scanner.nextLine();
        System.out.print("Enter username: "); // get the user information
        String username = scanner.nextLine().trim();
        

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean loggedIn = false;  //set these boolean variables to false that way we can know if the login is student or admin
        boolean adminLogin = false;;   

    if (choice == 1) { // if choice is one then register account
        loggedIn = CatsCarsMain.registerUser(username, password);
    } else if (choice == 2) { // if choice is two then login as student
        loggedIn = CatsCarsMain.loginUser(username, password);
    } else if (choice == 3){ // if choice is three then login as admin
        loggedIn = false;
        adminLogin = CatsCarsMain.loginAdmin(username, password);
    } else {
        System.out.println("Invalid option.");
    }
        if (loggedIn) {    //if logged in as student then you go through a loop with cat options   
            int option = -1;
            while (option!=0){ 
            System.out.println("Would you like to 1, update a cat status. 2, get a list of cat statuses. Enter 0 to exit");
            option = scanner.nextInt(); 
            scanner.nextLine();  
            if (option ==1){ // if the student wants to update the cat status
                System.out.println("Enter the name of the cat you would like to update!"); // get name of cat
                String name = scanner.nextLine();

                Cat catUpdate = new Cat(); // create cat class
                catUpdate.setName(name);

                System.out.println("Would you like to update 1, last location of the cat. or 2, the last time this cat was fed");
                int status = scanner.nextInt(); //figure out wether user wants to update location of fed time of cat

                File file = new File("cat.txt"); //open the file to write the information too
                ArrayList<String> lines = new ArrayList<>(); //make an array list to loop through file
                boolean catFound = false;
                try (Scanner fileScanner = new Scanner(file)) { //go through file until cat is found
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    lines.add(line);
                    if (line.startsWith(name + " - ")) {
                    catFound = true;
                    }
                }
                } catch (FileNotFoundException e) {} //handle exception with file handling
                if (!catFound) { //if cat isn't found then they can't update anything
                System.out.println("You can only update existing cats.");
                continue;
                }

                if (status==1){ //update the cats location
                    System.out.println("Enter the cats last known location");
                    scanner.nextLine();
                    String location = scanner.nextLine(); //get location

                    catUpdate.setStatus("Last seen at " + location);
                    catUpdate.setTime();

                    try (FileWriter writer = new FileWriter(file)) { //write to file formatted
                        for (String line : lines) {
                            if (line.startsWith(catUpdate.getName() + " - ")) {
                                writer.write(catUpdate.getName() + " - " + catUpdate.getStatus() + " at " + catUpdate.getFormattedTime() + "\n");
                            } else {
                                writer.write(line + "\n");  
                            }
                        }
                    } catch (IOException e) { //if error writing to file
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                }
                else if (status == 2) { // update the cats last feeding time
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now(); //use the local date time function
                
                    catUpdate.setStatus("Last fed"); //
                    catUpdate.setTime(); 
                    try (FileWriter writer = new FileWriter(file)) {
                        for (String line : lines) {
                            if (line.startsWith(catUpdate.getName() + " - ")) {
                                writer.write(catUpdate.getName() + " - " + catUpdate.getStatus() + ": " + catUpdate.getFormattedTime() + "\n");
                            } else {
                                writer.write(line + "\n");
                            }
                        }
                 } catch (IOException e) {
                        System.out.println("Error writing to file.");
                    }
                }
                
                else System.out.println("Invalid option"); //if the user doesn't pick one or two
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
            else if (option == 0){
                break;
            }
            else System.out.println("Invalid option, try again");                                
        }
    }
    
    else if (adminLogin){      
        int option = -1;
        while (option!=0){ 
        System.out.println("Would you like to 1, update a cat status. 2, get a list of cat statuses 3, add or remove a cat");
        System.out.println("Or, 4 add someone to the admin list. Enter 0 to exit");
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
            System.out.println("You can only update existing cats.");
            continue;
            }

            if (status==1){
                System.out.println("Enter the cats last known location");
                scanner.nextLine();
                String location = scanner.nextLine();

                catUpdate.setStatus("Last seen at " + location);
                catUpdate.setTime();

                try (FileWriter writer = new FileWriter(file)) {
                    for (String line : lines) {
                        if (line.startsWith(catUpdate.getName() + " - ")) {
                            writer.write(catUpdate.getName() + " - " + catUpdate.getStatus() + " at " + catUpdate.getFormattedTime() + "\n");
                        } else {
                            writer.write(line + "\n"); 
                        }
                    }
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
                try (FileWriter writer = new FileWriter(file)) {
                    for (String line : lines) {
                        if (line.startsWith(catUpdate.getName() + " - ")) {
                            // Update the status for the cat
                            writer.write(catUpdate.getName() + " - " + catUpdate.getStatus() + ": " + catUpdate.getFormattedTime() + "\n");
                        } else {
                            writer.write(line + "\n");
                        }
                    }
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
            System.out.println("Would you like to 1. add a cat, 2. or remove a cat?");
            int decision = scanner.nextInt();
            scanner.nextLine();
            Admin admin = new Admin();
            System.out.println("Enter the name of the cat");
            String catName = scanner.nextLine();

            if (decision == 1){
                admin.setAddCat(catName);
                admin.addCatToList();
            }
            else if (decision == 2){
                admin.setRemoveCat(catName);
                admin.removeCatFromList();
            }
            else System.out.println("Invalid option, you are being returned to the main menu");
            
        }
        else if (option == 4){
        System.out.print("Enter new admin username: ");
        String newAdmin = scanner.nextLine();
        System.out.print("Enter new admin password: ");
        String newPass = scanner.nextLine();

        String hashed = CatsCarsMain.hashPassword(newPass); 
        Admin admin = new Admin();
        admin.addAdminToList(newAdmin, hashed);
        }
        else System.out.println("Invalid option, try again");                                
    }
}
    scanner.close();
    }
}