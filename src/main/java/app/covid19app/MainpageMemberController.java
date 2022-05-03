package app.covid19app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainpageMemberController implements Initializable {
    private AnchorPane newsPane;
    private AnchorPane infoPane;
    private AnchorPane changeInfoPane;
    private AnchorPane treatmentPane;
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
    private FXMLLoader newsroot;
    private NewsPaneController newsPaneController;
    @FXML void informationclicked(MouseEvent event) {
        btnActive(informationbtn);
        content.getChildren().clear();
        content.getChildren().add(infoPane);
    }
    @FXML void newsclicked(MouseEvent event) {
        btnActive(newsbtn);
        newsPaneController.resetimage();
        content.getChildren().clear();
        content.getChildren().add(newsPane);
    }
    @FXML void signoutclicked(MouseEvent event) {
        btnActive(signoutbtn);
        Covid19App.switchScene("SignInPage.fxml");
    }@FXML
    void treatmentclicked(MouseEvent event) {
        content.getChildren().clear();
        content.getChildren().add(treatmentPane);
        btnActive(treatmentbtn);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnActive(newsbtn);
        try {
            newsroot = new FXMLLoader(Covid19App.class.getResource("NewsPane.fxml"));
            newsPane=newsroot.load();
            inforoot = new FXMLLoader(Covid19App.class.getResource("InformationPane.fxml"));
            infoPane= inforoot.load();
            changeInforoot = new FXMLLoader(Covid19App.class.getResource("ChangeInfoPage.fxml"));
            changeInfoPane = changeInforoot.load();
            FXMLLoader root = new FXMLLoader(Covid19App.class.getResource("TreatmentPane.fxml"));
            treatmentPane=root.load();
            newsPaneController=newsroot.getController();

            InformationPaneController informationPaneController = (InformationPaneController) inforoot.getController();
            informationPaneController.btn.setOnAction(e->{
                content.getChildren().clear();
                content.getChildren().add(changeInfoPane);
            });
            ChangeInfoPageController changeInfoPageController = (ChangeInfoPageController) changeInforoot.getController();
            changeInfoPageController.backbtn.setOnAction(e->{
                changeInfoPageController.reset();
                content.getChildren().clear();
                content.getChildren().add(infoPane);
            });
            changeInfoPageController.savebtn.setOnAction(e->{
                changeInfoPageController.error.setText("");
                //check
                if(changeInfoPageController.name.getText().isBlank()||changeInfoPageController.name.getText().isEmpty()||
                        changeInfoPageController.surname.getText().isBlank()||changeInfoPageController.surname.getText().isEmpty()||
                        changeInfoPageController.age.getText().isBlank()||changeInfoPageController.age.getText().isEmpty()||
                        changeInfoPageController.tel.getText().isEmpty()||changeInfoPageController.tel.getText().isBlank()||
                        changeInfoPageController.address.getText().isEmpty()||changeInfoPageController.address.getText().isBlank()){
                    changeInfoPageController.error.setText("Each field must be fill!!!");
                    return;
                }
                else if(!isAlpha(changeInfoPageController.name.getText())){
                    changeInfoPageController.error.setText("Name must not contain digits!!!");
                    return;
                }
                else if(!isAlpha(changeInfoPageController.surname.getText())){
                    changeInfoPageController.error.setText("Surname must not contain digits!!!");
                    return;
                }
                else if(!isInt(changeInfoPageController.age.getText())){
                    changeInfoPageController.error.setText("Age must be number!!!");
                    return;
                }
                else if(changeInfoPageController.age.getText().length()>3){
                    changeInfoPageController.error.setText("Your age is too high!!!");
                    return;
                }
                else if(!isInt(changeInfoPageController.tel.getText())){
                    changeInfoPageController.error.setText("Telephone number must be number!!!");
                    return;
                }
                else if(changeInfoPageController.tel.getText().length()>20){
                    changeInfoPageController.error.setText("Your telephone number is too long!!!");
                    return;
                }
                else if(changeInfoPageController.address.getText().length()>110){
                    changeInfoPageController.error.setText("Your address is too long!!!");
                    return;
                }
                if(changeInfoPageController.imgAddresstmp!=null){
                    File src = new File(changeInfoPageController.imgAddresstmp);
                    File dest = new File("src\\main\\userImages\\"+username+".jpg");
                    try {
                        Files.copy(src.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    changeInfoPageController.imgAddresstmp="src\\main\\userImages\\"+username+".jpg";
                }
                Connection connection = DbConnect.getInstance().getConnection();
                try {
                    Statement statement = connection.createStatement();
                    int status = statement.executeUpdate("update UserInfo set Name = '"+changeInfoPageController.name.getText()+"'," +
                            "Surname = '"+changeInfoPageController.surname.getText()+"'," +
                            "Age = '"+changeInfoPageController.age.getText()+"'," +
                            "Tel = '"+changeInfoPageController.tel.getText()+"'," +
                            "Address = '"+changeInfoPageController.address.getText()+"'," +
                            "ImgAddress = '"+changeInfoPageController.imgAddresstmp+"'" +
                            "where username = '"+username+"'");
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                content.getChildren().clear();
                informationPaneController.update();
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
    private boolean isInt(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    private boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
