package app.covid19app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static javafx.scene.control.PopupControl.USE_PREF_SIZE;

public class UI {
    public static void TextFieldUI(TextField tf, HBox box, String promptText){
        tf.setPromptText(promptText);
        tf.setStyle("-fx-background-color: transparent;");
        box.getChildren().add(tf);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setHgrow(tf, Priority.ALWAYS);
        box.setStyle("-fx-background-color: white;" +
                "-fx-border-color: #a2a2a2;" +
                "-fx-border-width: 0px 0px 2px 0px");
        box.setPrefWidth(250);
        box.setMaxWidth(USE_PREF_SIZE);
        HBox.setMargin(box,new Insets(5,5,5,5));
    }
    public static void PasswordFieldUI(PasswordField pf, HBox box, String promptText){
        pf.setPromptText(promptText);
        pf.setStyle("-fx-background-color: transparent;");
        box.getChildren().add(pf);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setHgrow(pf,Priority.ALWAYS);
        box.setStyle("-fx-background-color: white;" +
                "-fx-border-color: #a2a2a2;" +
                "-fx-border-width: 0px 0px 2px 0px");
        box.setPrefWidth(250);
        box.setMaxWidth(USE_PREF_SIZE);
        HBox.setMargin(box,new Insets(5,5,5,5));
    }
}
