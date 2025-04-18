import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cat {
    private String catId;
    private String name;
    private String status;
    private int[][] votes;
    private LocalDateTime theTime;

    public void setCatId(String catId){
        this.catId=catId;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public void setVotes(int[][] votes){
        this.votes=votes;
    }
    public void setTime(){
        this.theTime=LocalDateTime.now();
    }

    public String getCatId(){
        return catId;
    }
    public String getName(){
        return name;
    }
    public String getStatus(){
        return status;
    }
    public int[][] getVotes(){
        return votes;
    }
    public LocalDateTime getTime(){
        return theTime;
    }
    public String getFormattedTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return theTime.format(formatter);
    }    
}