package Project2_GUI_v2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Developer extends Person {

    static ArrayList<Developer> developers = new ArrayList<Developer>();


    public Developer(String name, String surname, String id, int pesel, String email) throws nonUniquePeselException {
        super(name, surname, id, pesel, email);
        try {
            FileWriter fileWriter = new FileWriter("log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Developer " + name + " " + surname + " (" + id + ") " + "Pesel: " + pesel + " Email: " + email + " was created\n");
            bufferedWriter.close();
        } catch (IOException v) {
            System.out.println("Error: " + v);
        }
    }

    @Override
    public Object getName() {
        return super.getName();
    }

    @Override
    public void setName(Object name) {
        super.setName(name);
    }

    @Override
    public Object getSurname() {
        return super.getSurname();
    }

    @Override
    public void setSurname(Object surname) {
        super.setSurname(surname);
    }

    @Override
    public Object getId() {
        return super.getId();
    }

    @Override
    public void setId(Object id) {
        super.setId(id);
    }

    @Override
    public int getPesel() {
        return super.getPesel();
    }

    @Override
    public void setPesel(int pesel) {
        super.setPesel(pesel);
    }

    @Override
    public Object getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(Object email) {
        super.setEmail(email);
    }
}
