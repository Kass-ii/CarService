public class CreateUserPass{
    private String username;
    private String password;

    public void account(String username, String password){
        this.username=username;
        this.password=password;
    }

    public String getUser(){
        return username;
    }

    public String getPass(){
        return password;
    }
}