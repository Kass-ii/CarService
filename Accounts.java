import java.util.Scanner;
import java.io.*;



public class Accounts {



    public static void MakeAccounts(Scanner in,String username, String password){ //new account

        String user = username;
        String pass = password;

        //Scanner user = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String NewUsername = in.nextLine();

        //Scanner password = new Scanner(System.in);
        System.out.println("Enter your password: ");
        String NewPassword = in.nextLine();


        //file writting and creation



        public static final String ACCOUNTS_FILE = "input.txt";
        //this is a problem this is supposed to be the file need to be public so other methods can access it??
        //maybe it needs to be private but idk





        File UserAccounts = new File(ACCOUNTS_FILE);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(UserAccounts, true))){
            writer.write(NewUsername);
            writer.write(",");
            writer.write(NewPassword);
            writer.newLine();
            System.out.println("Account added!");
        } catch (IOException e){
            System.err.println("ERROR: " + e.getMessage());
        }

        //call verify here and change its value?
        VerifyAccount(user,pass); // new arg to not affect the strings???? maybe
    }






    public static boolean VerifyAccount(String username, String password){
        boolean check = false;
         try(BufferedReader UserRead = new BufferedReader(new FileReader(UserAccounts))){
            //pass username to file checker
            //if valid 
                //check password
                    //if yes 
                        //return true

            //if not valid
            //return false
            
         }
    }
   

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your Email or Username");
        String username = input.nextLine();

        System.out.println("Please enter your password: ");
        String password = input.nextLine();

        while(VerifyAccount(username,password)!=true){ //does this call the verify fn?
            System.out.println("Sorry, your account doesn't exist. Please make an account: ");
            MakeAccounts(input, username, password);
            continue;
        }

        //allow access to the appliaction here is where we add the actual aplicaition 
        input.close();
    }
    
}


