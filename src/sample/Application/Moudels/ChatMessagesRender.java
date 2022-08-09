package sample.Application.Moudels;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import sample.Application.Controllers.MainPageController;

public class ChatMessagesRender implements Callback<ListView<Message>, ListCell<Message>> {
    MainPageController mainPageController = MainPageController.getInstance();

    @Override
    public ListCell<Message> call(ListView<Message> param) {
        ListCell<Message> cell = new ListCell<Message>() {
            @Override
            protected void updateItem(Message message, boolean bln) {
                super.updateItem( message, bln );
                setGraphic( null );
                setText( null );
                if (message != null) {
                    HBox hBox = new HBox();
                    VBox vBox = new VBox();
                    Text name = new Text(message.getSender().getUserName());
                    ImageView pictureImageView = new ImageView();
                    Image image = new Image( message.getSender().getUserProfilePic());
                    pictureImageView.setFitHeight( 20 );
                    pictureImageView.setFitWidth( 20 );
                    pictureImageView.setImage( image );
                    Text messageText = new Text( message.getMessage());
                    vBox.getChildren().addAll( name,messageText);
                    hBox.getChildren().addAll( pictureImageView,vBox);
                    hBox.setSpacing( 10 );
                    hBox.setAlignment( Pos.CENTER_LEFT );
                    if(message.getSender().getUserName().equals( mainPageController.currentUserUsername))
                        hBox.setStyle( "-fx-background-color = #a2f26d;" );
                    else
                        hBox.setStyle("-fx-background-color = #ffffff;");
                    setGraphic( hBox );
                }
            }
        };

        return cell;

    }
}
