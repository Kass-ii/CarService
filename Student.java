public class Student {
    private String studentId; // create private variables
    private String studentName;

    public void setStudentId(String studentId){ //setters
        this.studentId=studentId;
    }
    public void setStudentName(String studentName){
        this.studentName=studentName;
    }

    public String getStudentId(){ //getters
        return studentId;
    }
    public String getStudentName(){
        return studentName;
    }
}