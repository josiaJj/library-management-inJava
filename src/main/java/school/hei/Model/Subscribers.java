package school.hei.Model;

import school.hei.Model.User;

public class Subscribers extends User {
    public Subscribers() {}
    @Override
    public String toString() {
        return "Username= " + username +
                ", Password= " + password +
                ", Sex= " + sex +
                "} \n";
    }
}
