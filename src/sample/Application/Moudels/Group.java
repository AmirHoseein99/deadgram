package sample.Application.Moudels;

import java.util.ArrayList;

public class Group extends User {
    static Group instance;
    public String groupImage;
    public ArrayList<User> members;
    public String groupName;

    public Group(String groupImage, String groumName) {
        super();
        this.groupImage = groupImage;
        this.groupName = groumName;
        instance =this;

    }

    public static Group getInstance() {
        return instance;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }
}
