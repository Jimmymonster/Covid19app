package app.covid19app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUpPage extends AnchorPane{
    private final StackPane st = new StackPane();
    private final HBox hb = new HBox();
    private final VBox vb = new VBox();
    private final VBox[] decorate = {new VBox(),new VBox()};

    private final VBox vbin = new VBox(35);
    private final Text txt = new Text("Sign up");

    private final HBox usernameBox = new HBox();
    private final TextField username = new TextField("username");

    private final HBox passwordBox = new HBox();
    private final PasswordField password = new PasswordField();

    private final HBox confirmpasswordBox = new HBox();
    private final PasswordField confirmpassword = new PasswordField();

    private final Button SignUpBTN = new Button("Sign up");

    private final HBox SignInBox = new HBox(10);
    private final Text txt2 = new Text("Already have an Account?");
    private final Button SignInBTN = new Button("Sign in");

    private final Text error = new Text();

    public SignUpPage(){
        //UI things
        setStyle("-fx-background-color: transparent;");
        AnchorPane.setRightAnchor(st,10.0);
        AnchorPane.setLeftAnchor(st,10.0);
        AnchorPane.setTopAnchor(st,10.0);
        AnchorPane.setBottomAnchor(st,10.0);
        st.setEffect(new DropShadow());

        decorate[0].setStyle("-fx-background-color: #fe4451;");
        decorate[0].setPrefWidth(USE_COMPUTED_SIZE);
        decorate[0].setPrefHeight(USE_COMPUTED_SIZE);
        decorate[1].setStyle("-fx-background-color: white;");
        decorate[1].setPrefWidth(USE_COMPUTED_SIZE);
        decorate[1].setPrefHeight(USE_COMPUTED_SIZE);
        hb.getChildren().addAll(decorate[0],decorate[1]);
        hb.setHgrow(decorate[0],Priority.ALWAYS);
        hb.setHgrow(decorate[1],Priority.ALWAYS);
        hb.setPrefWidth(-1);
        hb.setPrefHeight(-1);
        hb.setStyle("-fx-background-color: transparent;");

        VBox.setMargin(vb, new Insets(45,45,45,45));
        vbin.setPrefHeight(350);
        vbin.setPrefWidth(350);
        vbin.setMaxWidth(USE_PREF_SIZE);
        vbin.setMaxHeight(USE_PREF_SIZE);
        vb.setAlignment(Pos.CENTER);
        vb.setEffect(new DropShadow());
        vbin.setStyle("-fx-background-color: white;");
        vbin.setAlignment(Pos.TOP_CENTER);

        txt.setStyle("-fx-font-size: 24;" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: black;");

        username.setStyle("-fx-background-color: transparent;");
        usernameBox.getChildren().add(username);
        usernameBox.setAlignment(Pos.CENTER_RIGHT);
        usernameBox.setHgrow(username,Priority.ALWAYS);
        usernameBox.setStyle("-fx-background-color: white;" +
                "-fx-border-color: #a2a2a2;" +
                "-fx-border-width: 0px 0px 2px 0px");
        usernameBox.setPrefWidth(250);
        usernameBox.setMaxWidth(USE_PREF_SIZE);
        HBox.setMargin(usernameBox,new Insets(5,5,5,5));

        password.setStyle("-fx-background-color: transparent;");
        passwordBox.getChildren().add(password);
        passwordBox.setAlignment(Pos.CENTER_RIGHT);
        passwordBox.setHgrow(password,Priority.ALWAYS);
        passwordBox.setStyle("-fx-background-color: white;" +
                "-fx-border-color: #a2a2a2;" +
                "-fx-border-width: 0px 0px 2px 0px");
        passwordBox.setPrefWidth(250);
        passwordBox.setMaxWidth(USE_PREF_SIZE);

        confirmpassword.setStyle("-fx-background-color: transparent;");
        confirmpasswordBox.getChildren().add(confirmpassword);
        confirmpasswordBox.setAlignment(Pos.CENTER_RIGHT);
        confirmpasswordBox.setHgrow(confirmpassword,Priority.ALWAYS);
        confirmpasswordBox.setStyle("-fx-background-color: white;" +
                "-fx-border-color: #a2a2a2;" +
                "-fx-border-width: 0px 0px 2px 0px");
        confirmpasswordBox.setPrefWidth(250);
        confirmpasswordBox.setMaxWidth(USE_PREF_SIZE);

        SignUpBTN.setPrefWidth(180);
        SignUpBTN.setStyle("-fx-background-color: #fe4451;" +
                "-fx-background-radius: 100PX;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14;");

        error.setStyle("-fx-fill: red;" +
                "-fx-font-size: 14;" +
                "-fx-font-weight: bold;");

        SignInBox.setPadding(new Insets(10,10,10,10));
        SignInBTN.setStyle("-fx-text-fill: #fe4451;" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: transparent;");
        txt2.setStyle("-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;");
        SignInBox.setAlignment(Pos.BOTTOM_CENTER);
        SignInBox.getChildren().addAll(txt2,SignInBTN);

        vbin.getChildren().addAll(txt,usernameBox,passwordBox,confirmpasswordBox,SignUpBTN,error,SignInBox);
        vbin.setPadding(new Insets(10,10,10,10));

        vb.getChildren().add(vbin);
        st.getChildren().addAll(hb,vb);
        getChildren().add(st);

        //Action event
        SignUpBTN.setOnAction(e->{SignUpBTNAction();});
        SignInBTN.setOnAction(e->{SignInBTNAction();});
    }

    private void SignUpBTNAction(){
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            String username = this.username.getText();
            String password = this.password.getText();
            String confirmpassword = this.confirmpassword.getText();
            //check error
            if(username.isBlank()||username.isEmpty()||password.isBlank()||password.isEmpty()||confirmpassword.isBlank()||confirmpassword.isEmpty()){
                error.setText("Each field must be fill!!!");
                connection.close();
                return;
            }
            else if(!confirmpassword.equals(password)){
                error.setText("Password isn't match!!!");
                connection.close();
                return;
            }
            //create statement
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserAccount where Username = '"+username+"'");
            if(resultSet.next() ){
                error.setText("Username is already used!!!");
                connection.close();
                return;
            }
            int status = statement.executeUpdate("insert into UserAccount (Username,Password,Rank) values('"+username+"','"+password+"','Member')");
            if(status>0){
                System.out.println("User registered");
                //go to sign in
                clearField();
                Covid19App.change_to_sign_in_page();
                connection.close();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void SignInBTNAction() {
        //go to sign in
        clearField();
        Covid19App.change_to_sign_in_page();
    }
    private void clearField(){
        error.setText("");
        username.setText("username");
        password.setText("");
        confirmpassword.setText("");
    }
}
