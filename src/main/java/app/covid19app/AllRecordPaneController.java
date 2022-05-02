package app.covid19app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AllRecordPaneController implements Initializable {
    @FXML private TableColumn<Data, HBox> actioncol;
    @FXML private TableColumn<Data, String> namecol;
    @FXML private TableColumn<Data, String> statuscol;
    @FXML private TableColumn<Data, String> surnamecol;
    @FXML private VBox recordbox;
    @FXML private TextField searchbox;
    @FXML private TableView<Data> table;
    private final ObservableList<Data> observableList= FXCollections.observableArrayList();
    public String[] usernamelist = new String[10000];
    private Data[] datalist = new Data[10000];
    public Button[][] btn = new Button[10000][2];
    public int i=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update();
    }
    public void update(){
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnamecol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
        actioncol.setCellValueFactory(new PropertyValueFactory<>("button"));
        observableList.clear();
        i=0;
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserInfo order by Name ASC");
            while(resultSet.next()) {
                String username = resultSet.getString(1);
                //infomation
                usernamelist[i] = username;
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String status = resultSet.getString(13);
                if (status.equals("diagnosis")) status = "wait for diagnosis";
                btn[i][0]=new Button("User Info");
                btn[i][0].setStyle("-fx-background-radius: 100px;" +
                        "-fx-font-size: 14;");
                btn[i][1]=new Button("Edit Case");
                btn[i][1].setStyle("-fx-background-radius: 100px;" +
                        "-fx-font-size: 14;");
                HBox tmp = new HBox(10);
                tmp.getChildren().addAll(btn[i]);
                tmp.setAlignment(Pos.CENTER);
                datalist[i] = new Data(name,surname,status,tmp);
                observableList.add(datalist[i]);

                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(i==0) table.setPrefHeight(80);
        else table.setPrefHeight(i*40+40);
        FilteredList<Data> filteredList = new FilteredList<>(observableList,a->true);
        searchbox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Data -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Data.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (Data.getSurname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if(Data.getStatus().toLowerCase().contains(lowerCaseFilter)){
                    return true; // Filter matches status
                }
                return false; // Does not match.
            });
        });
        table.setItems(filteredList);
    }
}
