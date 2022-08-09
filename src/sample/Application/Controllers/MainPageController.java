package sample.Application.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Application.Client.Chat;
import sample.Application.Moudels.*;
import sample.Application.MessageStyle.*;
import sample.Server.Server;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public static Stage settingStage;
    @FXML
    public BorderPane borderPane;
    public ListView chatPane;
    public Button buttonSend;
    public ListView userList;
    public TextArea messageBox;
    public ImageView currentUserImageView;
    public Label currentUserUsername;
    public ImageView userImageView;
    public static MainPageController instance;
    public ImageView chatImageViwe;
    public Label lastSeenLable;
    public Label userNameLable;
    public ArrayList<Message> messages = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();
    public VBox chatPaneVBOX;

    public ArrayList<User> getUsers() {
        return users;
    }

    public static Stage sStage;

    public static Stage getSStage() {

        return sStage;
    }

    public static MainPageController getInstance() {
        return instance;
    }

    public MainPageController() {
        instance = this;
    }

    public void sendButtonAction() {
        String message = messageBox.getText();
        if (!messageBox.getText().isEmpty()) {
            try {
                Chat.send( message, currentUserUsername.getText(), userNameLable.getText() );

                Task<HBox> yourMessages = new Task<>() {
                    @Override
                    public HBox call() throws Exception {
//                Image image = currentUserImageView.getImage();
//                ImageView profileImage = new ImageView( image );
//                profileImage.setFitHeight( 32 );
//                profileImage.setFitWidth( 32 );
                        BubbledLabel bl6 = new BubbledLabel();
                        bl6.setText( message );
                        bl6.setBackground( new Background( new BackgroundFill( Color.LIGHTGREEN, null, null ) ) );
                        HBox x = new HBox();
                        x.setMaxWidth( chatPane.getWidth() - 20 );
                        x.setAlignment( Pos.TOP_RIGHT );
                        bl6.setBubbleSpec( BubbleSpec.FACE_RIGHT_CENTER );
                        x.getChildren().addAll( bl6 );
                        return x;
                    }
                };
                yourMessages.setOnSucceeded( event -> {
                    chatPane.getItems().add( yourMessages.getValue() );
                } );
                Thread t = new Thread( yourMessages );
                t.setDaemon( true );
                t.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
            messageBox.clear();
        }
    }

    public void sendMethod(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            sendButtonAction();
        }
    }

    public void SettingPane(MouseEvent mouseEvent) {
        settingStage = new Stage();
        sStage = settingStage;
        Parent root = null;
        try {
            root = FXMLLoader.load( getClass().getResource( "../FXMLs/SettingPage.fxml" ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        settingStage.initStyle( StageStyle.UNDECORATED );
        settingStage.setX( Main.ps.getX() );
        settingStage.setY( Main.ps.getY() );
        settingStage.setScene( new Scene( root ) );
        settingStage.show();

    }

//    public void addToChat(Message message) {
//        chatPane.getItems().add( ChatRoomMessages.showMessage( message ) );
//    }

    public void addToGroupChat(Message message) {
    }

    public void logoutScene() {
    }

    public void addToUsersList(Message message) {
        Platform.runLater( () -> {
            users = message.getUsers();
            ObservableList<User> users = FXCollections.observableList( message.getUsers() );
            userList.setItems( users );
            userList.setCellFactory( new ContactsList() );

        } );
    }

    public void setCurrentUserInformations(User user) {
        Image image = new Image( user.getUserProfilePic() );
        currentUserImageView.setImage( image );
        currentUserUsername.setText( user.getUserName() );

    }

    public void fileChooser(ActionEvent actionEvent) {
    }

    public void selectChat(MouseEvent mouseEvent) {
        String selectedUserImage;
        String selectedUserName;
        User selectedUser;
        selectedUser = (User) userList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUserImage = selectedUser.getUserProfilePic();
            selectedUserName = selectedUser.getUserName();
            updateChatPages( selectedUserImage, selectedUserName );
        }
    }

    public void updateChatPages(String chatImage, String chatName) {
        Platform.runLater( () -> {
            chatImageViwe.setImage( new Image( chatImage ) );
            userNameLable.setText( chatName );
            lastSeenLable.setText( null );
        } );
    }

    public void addMessageToChat(Message message) {
        Task<HBox> othersMessages = new Task<>() {
            @Override
            public HBox call() throws Exception {
//                ImageView profileImage = new ImageView( message.getSender().getUserProfilePic() );
//                profileImage.setFitHeight( 32 );
//                profileImage.setFitWidth( 32 );
                BubbledLabel bl6 = new BubbledLabel();
                bl6.setText( message.getMessage() );
                bl6.setBackground( new Background( new BackgroundFill( Color.WHITE, null, null ) ) );
                HBox x = new HBox();
                bl6.setBubbleSpec( BubbleSpec.FACE_LEFT_CENTER );
                x.getChildren().addAll( bl6 );
                return x;
            }
        };
        othersMessages.setOnSucceeded( event -> {
            chatPane.getItems().add( othersMessages.getValue() );
        } );
        if (!(message.getSender().getUserName().equals( message.getReceiver().getUserName() ))) {
            Thread t1 = new Thread( othersMessages );
            t1.setDaemon( true );
            t1.start();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}



