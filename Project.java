package Project2_GUI_v2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Project {
    private String title;
    private String info;
    private Developer developer;
    private Developer secondDeveloper;
    private String status = "Planned";

    static ArrayList<Project> projects = new ArrayList<Project>();

    public Project(String title, String info, Developer developer) {
        this.title = title;
        this.info = info;
        this.developer = developer;
        try {
            FileWriter fileWriter = new FileWriter("log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Project " + "'" + title + "'" + " was created. " + "Description: " + info + "." + " Developers: " + developer.getId() + '\n');
            bufferedWriter.close();
        } catch (IOException v) {
            System.out.println("Error: " + v);
        }
    }

    public Project(String title, String info, Developer developer, Developer secondDeveloper) {
        this.title = title;
        this.info = info;
        this.developer = developer;
        this.secondDeveloper = secondDeveloper;
        try {
            FileWriter fileWriter = new FileWriter("log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Project " + "'" + title + "'" + " was created. " + "Description: " + info + "." + " Developers: " + developer.getId() + ", " + secondDeveloper.getId() + '\n');
            bufferedWriter.close();
        } catch (IOException v) {
            System.out.println("Error: " + v);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Developer getSecondDeveloper() {
        return secondDeveloper;
    }

    public void setSecondDeveloper(Developer secondDeveloper) {
        this.secondDeveloper = secondDeveloper;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
