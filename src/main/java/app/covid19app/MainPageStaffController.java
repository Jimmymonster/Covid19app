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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
    private AnchorPane allrecordpane;
    private FXMLLoader allrecordroot;
    private AnchorPane diagnosispane;
    private FXMLLoader diagnosisroot;
    private AnchorPane recoveringpane;
    private FXMLLoader recoveringroot;
    private AnchorPane curedpane;
    private FXMLLoader curedroot;
    private AnchorPane edittreatmentpane;
    private FXMLLoader edittreatmentroot;
    private AnchorPane informationpane;
    private FXMLLoader informationroot;
    private EditTreatmentPaneController editTreatmentPaneController;
    private AllRecordPaneController allRecordPaneController;
    private DiagnosisRecordPaneController diagnosisRecordPaneController;
    private RecoveringRecordPaneController recoveringRecordPaneController;
    private CuredRecordPaneController curedRecordPaneController;
    private InformationPane2Controller informationPaneController;
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
            allRecordPaneController = allrecordroot.getController();
            diagnosisroot = new FXMLLoader(Covid19App.class.getResource("DiagnosisRecordPane.fxml"));
            diagnosispane= diagnosisroot.load();
            diagnosisRecordPaneController = diagnosisroot.getController();
            recoveringroot = new FXMLLoader(Covid19App.class.getResource("RecoveringRecordPane.fxml"));
            recoveringpane= recoveringroot.load();
            recoveringRecordPaneController = recoveringroot.getController();
            curedroot =  new FXMLLoader(Covid19App.class.getResource("CuredRecordPane.fxml"));
            curedpane= curedroot.load();
            curedRecordPaneController = curedroot.getController();
            edittreatmentroot = new FXMLLoader(Covid19App.class.getResource("EditTreatmentPane.fxml"));
            edittreatmentpane = edittreatmentroot.load();
            editTreatmentPaneController = edittreatmentroot.getController();
            informationroot = new FXMLLoader(Covid19App.class.getResource("InformationPane2.fxml"));
            informationpane = informationroot.load();
            informationPaneController = informationroot.getController();
            //btn action (hard code!!!)
            updatebtn();

        } catch (IOException e) {
            e.printStackTrace();
        }
        content.getChildren().add(allrecordpane);
        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        username = u.getUsername();

    }
    private void insertdata(String username){
        String location= editTreatmentPaneController.locationfield.getText();
        String firstday= editTreatmentPaneController.firstdayfield.getText();
        String quarantine= editTreatmentPaneController.quarantinefield.getText();
        String medicine= editTreatmentPaneController.medicinefield.getText();
        String description= editTreatmentPaneController.descriptionfield.getText();
        Object patientstatus = editTreatmentPaneController.combobox.getSelectionModel().getSelectedItem();
        if(patientstatus.equals("wait for diagnosis")) patientstatus="diagnosis";
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("update UserTreatment set Location = '"+location+"'," +
                    "Firstday = '"+firstday+"'," +
                    "Quarantine = '"+quarantine+"'," +
                    "Medicine = '"+medicine+"'," +
                    "Description = '"+description+"'" +
                    "where Username = '"+username+"'");
            int status2 = statement.executeUpdate("update UserInfo set status ='"+patientstatus+"' where Username = '"+username+"'");
            if(status>0) System.out.println("Update data success");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        allRecordPaneController.update();
        recoveringRecordPaneController.update();
        diagnosisRecordPaneController.update();
        curedRecordPaneController.update();
        updatebtn();
    }
    private void updatebtn(){
        for(int i=0;i<allRecordPaneController.i;i++){
            int finalI = i;
            allRecordPaneController.btn[i][0].setOnAction(e->{
                content.getChildren().clear();
                informationPaneController.update(allRecordPaneController.usernamelist[finalI]);
                content.getChildren().add(informationpane);
                informationPaneController.btn.setOnAction(e2->{
                    btnActive(allrecordbtn);
                    content.getChildren().clear();
                    content.getChildren().add(allrecordpane);
                });
            });
            allRecordPaneController.btn[i][1].setOnAction(e->{
                content.getChildren().clear();
                editTreatmentPaneController.update(allRecordPaneController.usernamelist[finalI]);
                content.getChildren().add(edittreatmentpane);
                editTreatmentPaneController.backbtn.setOnAction(e2->{
                    btnActive(allrecordbtn);
                    content.getChildren().clear();
                    content.getChildren().add(allrecordpane);
                });
                editTreatmentPaneController.savebtn.setOnAction(e2->{
                    insertdata(allRecordPaneController.usernamelist[finalI]);
                    btnActive(allrecordbtn);
                    content.getChildren().clear();
                    content.getChildren().add(allrecordpane);
                });
            });
        }
        for(int i=0;i<diagnosisRecordPaneController.i;i++){
            int finalI = i;
            diagnosisRecordPaneController.btn[i][0].setOnAction(e->{
                content.getChildren().clear();
                informationPaneController.update(diagnosisRecordPaneController.usernamelist[finalI]);
                content.getChildren().add(informationpane);
                informationPaneController.btn.setOnAction(e2->{
                    btnActive(diagnosisbtn);
                    content.getChildren().clear();
                    content.getChildren().add(diagnosispane);
                });
            });
            diagnosisRecordPaneController.btn[i][1].setOnAction(e->{
                content.getChildren().clear();
                editTreatmentPaneController.update(diagnosisRecordPaneController.usernamelist[finalI]);
                content.getChildren().add(edittreatmentpane);
                editTreatmentPaneController.backbtn.setOnAction(e2->{
                    btnActive(diagnosisbtn);
                    content.getChildren().clear();
                    content.getChildren().add(diagnosispane);
                });
                editTreatmentPaneController.savebtn.setOnAction(e2->{
                    insertdata(diagnosisRecordPaneController.usernamelist[finalI]);
                    btnActive(diagnosisbtn);
                    content.getChildren().clear();
                    content.getChildren().add(diagnosispane);
                });
            });
        }
        for(int i=0;i<curedRecordPaneController.i;i++){
            int finalI = i;
            curedRecordPaneController.btn[i][0].setOnAction(e->{
                content.getChildren().clear();
                informationPaneController.update(curedRecordPaneController.usernamelist[finalI]);
                content.getChildren().add(informationpane);
                informationPaneController.btn.setOnAction(e2->{
                    content.getChildren().clear();
                    btnActive(curedbtn);
                    content.getChildren().add(curedpane);
                });
            });
            curedRecordPaneController.btn[i][1].setOnAction(e->{
                content.getChildren().clear();
                editTreatmentPaneController.update(curedRecordPaneController.usernamelist[finalI]);
                content.getChildren().add(edittreatmentpane);
                editTreatmentPaneController.backbtn.setOnAction(e2->{
                    btnActive(curedbtn);
                    content.getChildren().clear();
                    content.getChildren().add(curedpane);
                });
                editTreatmentPaneController.savebtn.setOnAction(e2->{
                    insertdata(curedRecordPaneController.usernamelist[finalI]);
                    btnActive(curedbtn);
                    content.getChildren().clear();
                    content.getChildren().add(curedpane);
                });
            });
        }
        for(int i=0;i<recoveringRecordPaneController.i;i++){
            int finalI = i;
            recoveringRecordPaneController.btn[i][0].setOnAction(e->{
                content.getChildren().clear();
                informationPaneController.update(recoveringRecordPaneController.usernamelist[finalI]);
                content.getChildren().add(informationpane);
                informationPaneController.btn.setOnAction(e2->{
                    btnActive(recoveringbtn);
                    content.getChildren().clear();
                    content.getChildren().add(recoveringpane);
                });
            });
            recoveringRecordPaneController.btn[i][1].setOnAction(e->{
                content.getChildren().clear();
                editTreatmentPaneController.update(recoveringRecordPaneController.usernamelist[finalI]);
                content.getChildren().add(edittreatmentpane);
                editTreatmentPaneController.backbtn.setOnAction(e2->{
                    btnActive(recoveringbtn);
                    content.getChildren().clear();
                    content.getChildren().add(recoveringpane);
                });
                editTreatmentPaneController.savebtn.setOnAction(e2->{
                    insertdata(recoveringRecordPaneController.usernamelist[finalI]);
                    btnActive(recoveringbtn);
                    content.getChildren().clear();
                    content.getChildren().add(recoveringpane);
                });
            });
        }
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
