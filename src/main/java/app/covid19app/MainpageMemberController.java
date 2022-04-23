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
    private AnchorPane infoPane;
    private AnchorPane changeInfoPane;
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
    @FXML private Button informationbtn;
    @FXML private Button newsbtn;
    @FXML private Button signoutbtn;
    @FXML private Button treatmentbtn;
    @FXML private VBox content;
    private FXMLLoader inforoot;
    private FXMLLoader changeInforoot;
    @FXML void informationclicked(MouseEvent event) {
        btnActive(informationbtn);
        content.getChildren().clear();
        content.getChildren().add(infoPane);
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
            inforoot = new FXMLLoader(Covid19App.class.getResource("InformationPane.fxml"));
            infoPane= inforoot.load();
            changeInforoot = new FXMLLoader(Covid19App.class.getResource("ChangeInfoPage.fxml"));
            changeInfoPane = changeInforoot.load();
            InformationPaneController informationPaneController = (InformationPaneController) inforoot.getController();
            informationPaneController.btn.setOnAction(e->{
                content.getChildren().clear();
                content.getChildren().add(changeInfoPane);
            });
            ChangeInfoPageController changeInfoPageController = (ChangeInfoPageController) changeInforoot.getController();
            changeInfoPageController.btn.setOnAction(e->{
                content.getChildren().clear();
                content.getChildren().add(infoPane);
            });
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
        informationbtn.setStyle(unhover);
        newsbtn.setStyle(unhover);
        signoutbtn.setStyle(unhover);
        treatmentbtn.setStyle(unhover);
        informationbtn.setOnMouseEntered(e->{
            informationbtn.setStyle(onhover);
        });
        informationbtn.setOnMouseExited(e->{
            informationbtn.setStyle(unhover);
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
