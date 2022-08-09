package sample.Application.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sample.Application.Client.Chat;
import sample.Application.Moudels.ContactsList;
import sample.Application.Moudels.Group;
import sample.Application.Moudels.User;
import sample.Server.Server;

import java.lang.reflect.GenericSignatureFormatError;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserListPageController implements Initializable {
    User selectedUser;
    public ListView usersListView;
    Group group = Group.getInstance();
    MainPageController mainPageController = MainPageController.getInstance();
    ArrayList<User> selectedMembers = new ArrayList<User>();


    public void createGroup(ActionEvent actionEvent) {
        group.setMembers( selectedMembers );
        Chat groupChat = new Chat( group, mainPageController );
        Thread userThread = new Thread( groupChat );
        userThread.start();

        Server.getUsers().add( group );
    }

    public void memberSelected(MouseEvent mouseEvent) {
        selectedUser = (User) usersListView.getSelectionModel().getSelectedItems();

    }

    public void addMember(ActionEvent actionEvent) {
        selectedMembers.add( selectedUser );
    }


    public void ClosePane(MouseEvent mouseEvent) {
        MainPageController.settingStage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater( () -> {
            ObservableList<User> user = FXCollections.observableList( mainPageController.getUsers() );
            usersListView.setItems( user );
            usersListView.setCellFactory( new ContactsList() );

        } );

    }
}
