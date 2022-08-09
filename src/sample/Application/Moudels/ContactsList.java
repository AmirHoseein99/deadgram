package sample.Application.Moudels;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ContactsList implements Callback<ListView<User>, ListCell<User>> {
    @Override
    public ListCell<User> call(ListView<User> param) {
        ListCell<User> cell = new ListCell<User>() {

            @Override
            protected void updateItem(User user, boolean bln) {
                super.updateItem(user, bln);
                setGraphic(null);
                setText(null);
                if (user != null) {
                    HBox hBox = new HBox();
                    Text name = new Text(user.getUserName());
                    ImageView pictureImageView = new ImageView();
                    Image image = new Image(user.getUserProfilePic());
                    pictureImageView.setFitHeight(50);
                    pictureImageView.setFitWidth(50);
                    pictureImageView.setImage(image);

                    hBox.getChildren().addAll(pictureImageView, name);
                    hBox.setSpacing(10);
                    hBox.setAlignment(Pos.CENTER_LEFT);

                    setGraphic(hBox);
                }
            }
        };

        return cell;

    }
}

