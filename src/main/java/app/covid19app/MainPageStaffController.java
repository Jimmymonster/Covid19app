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
    @FXML private Button diagnosisbtn;
    @FXML private VBox content;
    AnchorPane allrecordpane;
    private FXMLLoader allrecordroot;
    AnchorPane diagnosispane;
    private FXMLLoader diagnosisroot;
    AnchorPane recoveringpane;
    private FXMLLoader recoveringroot;
    AnchorPane curedpane;
    private FXMLLoader curedroot;
    @FXML void diagnosisclicked(MouseEvent event) {
        btnActive(diagnosisbtn);
        content.getChildren().clear();
        content.getChildren().add(diagnosispane);
    }
    @FXML void recoveringclicked(MouseEvent event) {
        btnActive(recoveringbtn);
        content.getChildren().clear();
        content.getChildren().add(recoveringpane);
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
        content.getChildren().add(curedpane);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnActive(allrecordbtn);
        try {
            allrecordroot = new FXMLLoader(Covid19App.class.getResource("AllRecordPane.fxml"));
            allrecordpane=allrecordroot.load();
            diagnosisroot = new FXMLLoader(Covid19App.class.getResource("DiagnosisRecordPane.fxml"));
            diagnosispane= diagnosisroot.load();
            recoveringroot = new FXMLLoader(Covid19App.class.getResource("RecoveringRecordPane.fxml"));
            recoveringpane= recoveringroot.load();
            curedroot =  new FXMLLoader(Covid19App.class.getResource("CuredRecordPane.fxml"));
            curedpane= curedroot.load();
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
        diagnosisbtn.setStyle(unhover);
        diagnosisbtn.setOnMouseEntered(e->{
            diagnosisbtn.setStyle(onhover);
        });
        diagnosisbtn.setOnMouseExited(e->{
            diagnosisbtn.setStyle(unhover);
        });
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
