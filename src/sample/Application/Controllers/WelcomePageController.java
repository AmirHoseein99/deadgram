package sample.Application.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomePageController {
    public void gotoLoginPage(ActionEvent actionEvent) {
        Stage stage = Main.getPs();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../FXMLs/LoginPage.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void gotoRegisterPage(ActionEvent actionEvent) {
        Stage stage = Main.getPs();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../FXMLs/RegisterPage.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();
    }
}
