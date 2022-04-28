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

public class MainPageStaffController implements Initializable {
    AnchorPane allrecordpane;
    private String username;
    private String onhover = "-fx-background-color: #376bab;" +
            //  "-fx-background-radius:  100 0 0 100;" +
            "-fx-font-family: Quicksand;" +
            "-fx-font-size: 26;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;";
    private String unhover = "-fx-background-color: transparent;" +
            //  "-fx-background-radius:  100 0 0 100;" +
            "-fx-font-family: Quicksand;" +
            "-fx-font-size: 26;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;";
    @FXML private Button recoveringbtn;
    @FXML private Button allrecordbtn;
    @FXML private Button signoutbtn;
    @FXML private Button curedbtn;
    @FXML private VBox content;
    private FXMLLoader inforoot;
    private FXMLLoader changeInforoot;
    @FXML void recoveringclicked(MouseEvent event) {
        btnActive(recoveringbtn);
        content.getChildren().clear();
    }
    @FXML void allrecordclicked(MouseEvent event) {
        btnActive(allrecordbtn);
        content.getChildren().clear();
        content.getChildren().add(allrecordpane);
    }
    @FXML void signoutclicked(MouseEvent event) {
        btnActive(signoutbtn);
        Covid19App.switchScene("SignInPage.fxml");
    }@FXML void curedclicked(MouseEvent event) {
        content.getChildren().clear();
        btnActive(curedbtn);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnActive(allrecordbtn);
        try {
            FXMLLoader root = new FXMLLoader(Covid19App.class.getResource("AllRecordPane.fxml"));
            allrecordpane=root.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        content.getChildren().add(allrecordpane);
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
        recoveringbtn.setStyle(unhover);
        allrecordbtn.setStyle(unhover);
        signoutbtn.setStyle(unhover);
        curedbtn.setStyle(unhover);
        recoveringbtn.setOnMouseEntered(e->{
            recoveringbtn.setStyle(onhover);
        });
        recoveringbtn.setOnMouseExited(e->{
            recoveringbtn.setStyle(unhover);
        });
        allrecordbtn.setOnMouseEntered(e->{
            allrecordbtn.setStyle(onhover);
        });
        allrecordbtn.setOnMouseExited(e->{
            allrecordbtn.setStyle(unhover);
        });
        signoutbtn.setOnMouseEntered(e->{
            signoutbtn.setStyle(onhover);
        });
        signoutbtn.setOnMouseExited(e->{
            signoutbtn.setStyle(unhover);
        });
        curedbtn.setOnMouseEntered(e->{
            curedbtn.setStyle(onhover);
        });
        curedbtn.setOnMouseExited(e->{
            curedbtn.setStyle(unhover);
        });
    }
}
