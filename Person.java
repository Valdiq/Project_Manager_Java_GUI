package Project2_GUI_v2;

import java.util.HashMap;
import java.util.Map;

public abstract class Person<String> {
    private String name;
    private String surname;
    private String id;
    private int pesel;
    private String email;

    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

    public Person(String name, String surname, String id, int pesel, String email) throws nonUniquePeselException {
        if (!persons.keySet().contains(pesel)) {
            this.name = name;
            this.surname = surname;
            this.id = id;
            this.pesel = pesel;
            this.email = email;
            persons.put(pesel, this);
        } else {
            throw new nonUniquePeselException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
