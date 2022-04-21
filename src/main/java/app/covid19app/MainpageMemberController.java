package app.covid19app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainpageMemberController implements Initializable {
    private AnchorPane newsPane;
    private String username;
    private String onhover = "-fx-background-color: #376bab;" +
            "-fx-background-radius:  100 0 0 100;" +
            "-fx-font-family: Quicksand;" +
            "-fx-font-size: 26;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;";
    private String unhover = "-fx-background-color: transparent;" +
            "-fx-background-radius:  100 0 0 100;" +
            "-fx-font-family: Quicksand;" +
            "-fx-font-size: 26;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;";
    private int state=0; //0=news 1= info 2=treat 3=signout
    @FXML private Button infomationbtn;
    @FXML private Button newsbtn;
    @FXML private Button signoutbtn;
    @FXML private Button treatmentbtn;
    @FXML private VBox content;
    @FXML void informationclicked(MouseEvent event) {
        btnActive(infomationbtn);
        content.getChildren().clear();
    }
    @FXML void newsclicked(MouseEvent event) {
        btnActive(newsbtn);
        content.getChildren().clear();
        content.getChildren().add(newsPane);
    }
    @FXML void signoutclicked(MouseEvent event) {
        btnActive(signoutbtn);
        Covid19App.switchScene("SignInPage.fxml");
    }@FXML
    void treatmentclicked(MouseEvent event) {
        content.getChildren().clear();
        btnActive(treatmentbtn);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnActive(newsbtn);
        try {
            FXMLLoader root = new FXMLLoader(Covid19App.class.getResource("NewsPane.fxml"));
            newsPane=root.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        content.getChildren().add(newsPane);

        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        username = u.getUsername();
    }
    private void btnActive(Button btn){
        setbtn();
        btn.setOnMouseExited(e->{});
        btn.setStyle(onhover);
    }
    private void setbtn(){
        infomationbtn.setStyle(unhover);
        newsbtn.setStyle(unhover);
        signoutbtn.setStyle(unhover);
        treatmentbtn.setStyle(unhover);
        infomationbtn.setOnMouseEntered(e->{
            infomationbtn.setStyle(onhover);
        });
        infomationbtn.setOnMouseExited(e->{
            infomationbtn.setStyle(unhover);
        });
        newsbtn.setOnMouseEntered(e->{
            newsbtn.setStyle(onhover);
        });
        newsbtn.setOnMouseExited(e->{
            newsbtn.setStyle(unhover);
        });
        signoutbtn.setOnMouseEntered(e->{
            signoutbtn.setStyle(onhover);
        });
        signoutbtn.setOnMouseExited(e->{
            signoutbtn.setStyle(unhover);
        });
        treatmentbtn.setOnMouseEntered(e->{
            treatmentbtn.setStyle(onhover);
        });
        treatmentbtn.setOnMouseExited(e->{
            treatmentbtn.setStyle(unhover);
        });
    }
}
