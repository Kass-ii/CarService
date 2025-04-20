import java.util.Scanner;
import java.io.*;



public class Accounts {



    public static void MakeAccounts(Scanner in){ //new account

        //Scanner user = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String NewUsername = in.nextLine();

        Scanner password = new Scanner(System.in);
        System.out.println("Enter your password: ");
        String NewPassword = in.nextLine();


        //file writting and creation
        File UserAccounts = new File("input.txt");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("input.text", true))){
            writer.write(NewUsername);
            writer.write(",");
            writer.write(NewPassword);
            writer.newLine();
            System.out.println("Account added!");
        } catch (IOException e){
            System.err.println("ERROR: " + e.getMessage());
        }
    }
   
    

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your Email or Username");
        String username = input.nextLine();

        //if else statements
        MakeAccounts(); //call to make new user account



        input.close();
    }
    


    
}


