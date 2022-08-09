package sample.Application.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Application.Moudels.User;

import java.util.ArrayList;

public class Main extends Application {
    public static ArrayList<User> mainUsers = new ArrayList<>();
    public static Stage ps;

    public static ArrayList<User> getUsers() {
        return mainUsers;
    }

    public static void setUsers(ArrayList<User> users) {

        for (User u:users) {
            mainUsers.add( u );

        }
    }


    public static Stage getPs() {
        return ps;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ps = primaryStage;
        Parent root = FXMLLoader.load( getClass().getResource( "../FXMLs/WelComePage.fxml" ) );
//        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/plug.png").toString()));
        primaryStage.setTitle( "Telegram" );
        Scene mainScene = new Scene( root, 800, 600 );
        mainScene.setRoot( root );
        primaryStage.setScene( mainScene );
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch( args );
    }
}
