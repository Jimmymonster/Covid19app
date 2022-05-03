package app.covid19app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewsPaneController implements Initializable {
    @FXML private ImageView image;
    private int count=0;
    private Image[] images= new Image[5];
    private Timeline timeline;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        images[0]= new Image(new File("src\\main\\Images\\news1.jpg").toURI().toString());
        images[1]= new Image(new File("src\\main\\Images\\news2.jpg").toURI().toString());
        images[2]= new Image(new File("src\\main\\Images\\news3.jpg").toURI().toString());
        image.setImage(images[0]);
        count=1;
        timeline = new Timeline(new KeyFrame(Duration.seconds(5),e->{
            image.setImage(images[count++]);
            count%=3;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void resetimage(){
        count=1;
        image.setImage(images[0]);
        timeline.playFromStart();
    }
}
