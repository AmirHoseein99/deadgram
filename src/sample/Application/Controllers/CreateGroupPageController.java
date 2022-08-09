package sample.Application.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Application.Client.Chat;
import sample.Application.Moudels.Group;

import java.io.File;

public class CreateGroupPageController {
    public ImageView groupPic;
    public File selectedGroupPic;
    public TextField groupNameField;
    public Stage userList;
    MainPageController mainPageController = MainPageController.getInstance();

    public void searchGroupPic(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle( "Select Image" );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter( "All Files", "*.*" ),
                new FileChooser.ExtensionFilter( "JPG", "*.jpg" ),
                new FileChooser.ExtensionFilter( "GIF", "*.gif" ),
                new FileChooser.ExtensionFilter( "BMP", "*.bmp" ),
                new FileChooser.ExtensionFilter( "PNG", "*.png" )
        );
        selectedGroupPic = fileChooser.showOpenDialog( new Stage() );
        if (!(selectedGroupPic == null)) {
            Image image = new Image( selectedGroupPic.toURI().toString() );
            groupPic.setImage( image );
        } else {
            groupPic.setImage( groupPic.getImage() );
        }

    }

    public void showUserList(ActionEvent actionEvent) {
        Group group = new Group( selectedGroupPic.toURI().toString(), groupNameField.getText() );
        userList = MainPageController.getSStage();

        Parent root = null;
        try {
            root = FXMLLoader.load( getClass().getResource( "../FXMLs/UserListPage.fxml" ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        userList.setX( Main.ps.getX() + 100 );
        userList.setY( Main.ps.getY() + 200 );
        userList.setScene( new Scene( root ) );
        userList.show();

    }

    public void ClosePane(MouseEvent mouseEvent) {
    }
}
