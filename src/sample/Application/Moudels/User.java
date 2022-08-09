package sample.Application.Moudels;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public String userName;
    public String password;
    public String userProfilePic;
    public ListView chatPaneList;
    static User instance;

    public static User getInstance() {
        return instance;
    }

    public User(String userName, String password, String userProfilePic) {
        this.userName = userName;
        this.password = password;
        this.userProfilePic = userProfilePic;
        instance = this;
    }
    public User() {

    }

    public User(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }
}
