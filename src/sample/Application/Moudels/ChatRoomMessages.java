package sample.Application.Moudels;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ChatRoomMessages {
//    public static Message message;
    public static HBox messageBox;
    public static Label messageText;
    public static ImageView profileImage;
    public static   User[] chatRoom;
//
//    public ChatRoomMessages(Message message) {
//        this.message = message;
//    }

    public static HBox showMessage(Message message){
        messageText = new Label(message.getMessage());

        messageBox = new HBox(messageText);
        return messageBox;
    }
}
