package Project2_GUI_v2;

import java.util.HashMap;

public class DeveloperDataBase {
    static HashMap<String, String> developerLoginData = new HashMap<String, String>();

    public DeveloperDataBase() {
        developerLoginData.put("s12345", "qwerty");
        developerLoginData.put("s54321", "ytrewq");
        developerLoginData.put("s11223", "qywter");
        developerLoginData.put("s12390", "yqtwre");
        developerLoginData.put("s01020", "ewqrty");
    }
    public HashMap<String, String> getDeveloperLoginData() {
        return developerLoginData;
    }

}
