package Project2_GUI_v2;

import java.util.HashMap;

public class ManagerDataBase {
    static HashMap<String, String> managerLoginData = new HashMap<String, String>();

    public ManagerDataBase() {
        managerLoginData.put("m12345", "admin1");
        managerLoginData.put("m54321", "admin2");
        managerLoginData.put("m09099", "admin3");
    }

    public HashMap<String, String> getManagerLoginData() {
        return managerLoginData;
    }

}
