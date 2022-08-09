package sample.Application.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Application.Client.Chat;
import sample.Application.Moudels.User;

import java.io.File;
import java.io.IOException;

public class SettingPageController {
    public static Stage createGroup;
    User user;
    File selectedPic;

    public static Stage getCreateGroup() {
        return createGroup;
    }

    public void ClosePane(MouseEvent mouseEvent) {
        MainPageController.settingStage.close();
    }

    public void editProfilePic(MouseEvent mouseEvent) throws IOException {
        user = User.getInstance();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle( "Select Image" );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter( "All Files", "*.*" ),
                new FileChooser.ExtensionFilter( "JPG", "*.jpg" ),
                new FileChooser.ExtensionFilter( "GIF", "*.gif" ),
                new FileChooser.ExtensionFilter( "BMP", "*.bmp" ),
                new FileChooser.ExtensionFilter( "PNG", "*.png" )
        );
        selectedPic = fileChooser.showOpenDialog( new Stage() );
        if (!(selectedPic == null && user != null)) {
            user.setUserProfilePic( selectedPic.toURI().toString() );
        }
        Chat.changeUserPic( user );
    }

    public void editUsername(MouseEvent mouseEvent) {
    }

    public void editPassword(MouseEvent mouseEvent) {
    }

    public void CreateGroup(MouseEvent mouseEvent) {
        createGroup = MainPageController.getSStage();
        Parent root = null;
        try {
            root = FXMLLoader.load( getClass().getResource( "../FXMLs/CreateGroupPage.fxml" ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        createGroup.setX( Main.ps.getX() + 100 );
        createGroup.setY( Main.ps.getY() + 200 );

        createGroup.setScene( new Scene( root ) );
        createGroup.show();

    }

    public void deleteProfilePic(MouseEvent mouseEvent) throws IOException {
        user.setUserProfilePic( selectedPic.toURI().toString() );
        Chat.changeUserPic( user );
    }

    public void logOut(MouseEvent mouseEvent) {
    }
}
