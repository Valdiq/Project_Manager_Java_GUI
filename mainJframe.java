package Project2_GUI_v2;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class mainJframe {
    public static void main(String[] args) throws nonUniquePeselException {

        Developer dev1 = new Developer("Vlad", "Deswid", "s12345", 123, "qwerty@gmail.com");
        Developer dev2 = new Developer("Bob", "Richerson", "s54321", 321, "qazxsw@fsd.sf");
        Developer dev3 = new Developer("John", "Karlson", "s11223", 132, "karlson@snt.yu");
        Developer dev4 = new Developer("Elon", "Musk", "s12390", 666, "emusk@tsla.mrs");
        Developer dev5 = new Developer("Quardis", "Erikson", "s01020", 111, "quardis@grm.ua");
        Developer.developers.add(dev1);
        Developer.developers.add(dev2);
        Developer.developers.add(dev3);
        Developer.developers.add(dev4);
        Developer.developers.add(dev5);

        Manager m1 = new Manager("Bill", "Gates", "m12345", 333, "mcrsft@me.mc");
        Manager m2 = new Manager("Warren", "Buffet", "m54321", 213, "wbuffet@wbinc.wb");
        Manager m3 = new Manager("Robert", "Lewandowski", "m09099", 999, "lewa09@lewa.lw");
        Manager.managers.add(m1);
        Manager.managers.add(m2);
        Manager.managers.add(m3);

        Project p1 = new Project("Calculator", "You must create a simple calculator for scientists", dev1);
        Project p2 = new Project("Building", "Just create a GUI for building company`s application", dev2, dev3);
        Project p3 = new Project("Snake", "You must create a popular old game called Snake", dev2, dev5);
        Project p4 = new Project("Stereografy", "You should create a code that provide hiding code or message into a picture", dev3, dev4);
        Project p5 = new Project("Password Manager", "You must create a simple applecation to save, changing and review user`s passwords", dev1, dev5);
        Project p6 = new Project("Packman", "Just create a simple console game named Packman", dev1, dev4);
        Project p7 = new Project("Parser", "Create a application that make possible to parse info from websites and post it in Facebook", dev4);
        Project p8 = new Project("Weather Bot", "Just create a simple bot for Telegram witch gives a weather forecast in chosen city", dev2);
        Project.projects.add(p1);
        Project.projects.add(p2);
        Project.projects.add(p3);
        Project.projects.add(p4);
        Project.projects.add(p5);
        Project.projects.add(p6);
        Project.projects.add(p7);
        Project.projects.add(p8);


        DeveloperAndID developerAndID = new DeveloperAndID();
        ManagerAndID managerAndID = new ManagerAndID();

        DeveloperDataBase developerDataBase = new DeveloperDataBase();
        ManagerDataBase managerDataBase = new ManagerDataBase();


        SwingUtilities.invokeLater(
                () -> {
                    LoginPage loginPage = new LoginPage(developerDataBase.getDeveloperLoginData(), managerDataBase.getManagerLoginData());
                }
        );
        try {
            File file = new File("log.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter("log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Login page was started\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}

//Vladyslav Stasyshyn
