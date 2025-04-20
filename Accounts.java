import java.util.Scanner;
import java.io.*;



public class Accounts {



    public static void MakeAccounts(Scanner in,String username, String password){ //new account

        //Scanner user = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String NewUsername = in.nextLine();

        //Scanner password = new Scanner(System.in);
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

    public static boolean VerifyAccount(String username, String password){ //dont need to check password bc if one is wrong thats all we need
        boolean check = true;
         try(BufferedReader UserRead = new BufferedReader(new FileReader(UserAccounts))){

         }
         //if account isnt recognized prompt to make a new account
         //after account is made, call verify again and chck to make sure account exists
         //loop if still not verified


        if(check == true){
            return true;
        }
       else{
        return false;
       }
    }
   
    

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your Email or Username");
        String username = input.nextLine();

        System.out.println("Please enter your password: ");
        String password = input.nextLine();

        // if(VerifyAccount(username) == true){

        // }
        // else{
            
        //     MakeAccounts(input,username,password);
        // }

        while(VerifyAccount(username,password)!=true){
            System.out.println("Sorry, your account doesn't exist. Try again: ")

        }


        //if else statements
        //MakeAccounts(); //call to make new user account



        input.close();
    }
    


    
}


