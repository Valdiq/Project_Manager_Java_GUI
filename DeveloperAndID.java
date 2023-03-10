package Project2_GUI_v2;

import java.util.HashMap;

public class DeveloperAndID {
    static HashMap<String, Developer> developersAndID = new HashMap<String, Developer>();

    public DeveloperAndID() {
        developersAndID.put((String) Developer.developers.get(0).getId(), Developer.developers.get(0));
        developersAndID.put((String) Developer.developers.get(1).getId(), Developer.developers.get(1));
        developersAndID.put((String) Developer.developers.get(2).getId(), Developer.developers.get(2));
        developersAndID.put((String) Developer.developers.get(3).getId(), Developer.developers.get(3));
        developersAndID.put((String) Developer.developers.get(4).getId(), Developer.developers.get(4));
    }
}
