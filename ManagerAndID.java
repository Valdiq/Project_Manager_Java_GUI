package Project2_GUI_v2;

import java.util.HashMap;

public class ManagerAndID {

    static HashMap<String, Manager> managersAndID = new HashMap<String, Manager>();

    public ManagerAndID() {
        managersAndID.put((String) Manager.managers.get(0).getId(), Manager.managers.get(0));
        managersAndID.put((String) Manager.managers.get(1).getId(), Manager.managers.get(1));
        managersAndID.put((String) Manager.managers.get(2).getId(), Manager.managers.get(2));
    }

}
