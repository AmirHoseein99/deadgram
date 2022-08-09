package sample.Application.Controllers;
import sample.Application.Controllers.Main;
import sample.Application.Controllers.MainPageController;
import sample.Server.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Application.Moudels.User;
import sample.Server.Server;

import java.util.ArrayList;
import java.util.logging.Handler;

public class LoginPageController {

    public TextField loginUserName;
    public PasswordField loginPassWord;
    public static MainPageController mainPageController;

    public void loginAndGotoMainPage(ActionEvent actionEvent) {
        ArrayList names = new ArrayList();
        ArrayList password = new ArrayList();
        for (User user: mainPageController.getUsers()) {
            names.add( user.getUserName() );
            password.add( user.getPassword() );
        }
        if (names.contains( loginUserName.getText() ) && password.contains( loginPassWord.getText() )) {
            showMainPage();
        } else {
            Alert alert = new Alert( Alert.AlertType.ERROR, "Wrong Informations" );
            alert.show();
            return;
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

