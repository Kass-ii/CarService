import java.io.*;
import java.util.*;

public class Admin {
    private String adminName;
    private String addCat;
    private String removeCat;
    private String addAdmin;

    public void setAdminName(String adminName){
        this.adminName=adminName;
    }
    public void setAddCat(String addCat){
        this.addCat=addCat;
    }
    public void setRemoveCat(String removeCat){
        this.removeCat=removeCat;
    }
    public void setAddAdmin(String addAdmin){
        this.addAdmin=addAdmin;
    }


    public String getAdminName(){
        return adminName;
    }
    public String getAddCat(){
        return addCat;
    }
    public String getRemoveCat(){
        return removeCat;
    }
    public String getAddAdmin(){
        return addAdmin;
    }

    public void addCatToList(){
        List<String> catList = new ArrayList<>();
        try {
            File file = new File("cat.txt");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        catList.add(line);
                    }
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            return;
        }

        catList.add(addCat);
        Collections.sort(catList, String.CASE_INSENSITIVE_ORDER);
        try {
            FileWriter writer = new FileWriter("cat.txt");
            for (String cat : catList) {
                writer.write(cat + "\n");
            }
            writer.close();
            System.out.println( addCat + " added to the list");
        } catch (IOException e) {
            System.out.println("Error writing to cat.txt");
            e.printStackTrace();
        
    }
    }
    public void removeCatFromList(){
        List<String> catList = new ArrayList<>();
        try{
            File file = new File("cat.txt");
            if (file.exists()){
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    String line = scanner.nextLine().trim();
                    if(!line.isEmpty()){
                        catList.add(line);
                    }
                }
                scanner.close();
            }
        } catch (FileNotFoundException e){ 
            return;
        }

        catList.removeIf(cat -> cat.equalsIgnoreCase(removeCat));

        try {
            FileWriter writer = new FileWriter("cat.txt");
            for (String cat : catList) {
                writer.write(cat + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to cat.txt");
            e.printStackTrace();
        }
    }
    public void listCats(){
        try{
            File file = new File ("cat.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e){
            return;
        }
    }
    public void addAdminToList(String username, String hashedPassword) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("admin.txt", true))) {
            writer.write(username + ":" + hashedPassword);
            writer.newLine();
            System.out.println(username + " added to admin list. they can still login as student if requested");
        } catch (IOException e) {
            System.out.println("Error adding admin.");
            e.printStackTrace();
        }
    }
    
}