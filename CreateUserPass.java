public class CreateUserPass{
    private String username; //private variables
    private String password;

    public void account(String username, String password){ //account setters
        this.username=username;
        this.password=password;
    }

    public String getUser(){ //getters
        return username;
    }

    public String getPass(){
        return password;
    }
}