package sample.Application.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Application.Client.Chat;
import sample.Application.Moudels.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;

public class RegisterPageController {

    private Scene scene;
    public User newUser;
    public TextField loginUserName;
    public ImageView registerImage;
    public FileChooser fileChooser;
    public File selectedProfilePic;
    public PasswordField loginPassWord;
    public static MainPageController mainPageController;
    static ArrayList<User> users = new ArrayList<>();

    public void registerAndGotoMainPage(ActionEvent actionEvent) {

        newUser = new User( loginUserName.getText(), loginPassWord.getText(), selectedProfilePic.toURI().toString() );
        showMainPage();
        mainPageController.setCurrentUserInformations( newUser );
        Chat chat = new Chat( newUser, mainPageController );
        Thread userThread = new Thread( chat );
        userThread.start();
    }

    public void imageSelect(MouseEvent mouseEvent) {
    }

    public void openFileChooser(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle( "Select Image" );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter( "All Files", "*.*" ),
                new FileChooser.ExtensionFilter( "JPG", "*.jpg" ),
                new FileChooser.ExtensionFilter( "GIF", "*.gif" ),
                new FileChooser.ExtensionFilter( "BMP", "*.bmp" ),
                new FileChooser.ExtensionFilter( "PNG", "*.png" )
        );
        selectedProfilePic = fileChooser.showOpenDialog( new Stage() );
        if (!(selectedProfilePic == null)) {
            Image image = new Image( selectedProfilePic.toURI().toString() );
            registerImage.setImage( image );
        } else {
            registerImage.setImage( registerImage.getImage() );


        }
    }

    public void showMainPage() {
        Stage stage = Main.getPs();
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../FXMLs/MainPage.fxml" ) );
            root = fxmlLoader.load();
            mainPageController = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene( new Scene( root ) );
        stage.show();
    }
}
