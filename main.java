public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("1. Register");
    System.out.println("2. Login");
    System.out.print("Choose an option: ");
    int choice = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    boolean loggedIn = false;                  
    if (choice == 1) {
        loggedIn = registerUser(username, password);
    } else if (choice == 2) {
        loggedIn = loginUser(username, password);
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
