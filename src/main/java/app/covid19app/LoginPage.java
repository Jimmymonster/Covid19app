package app.covid19app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPage extends AnchorPane {

    private final StackPane st = new StackPane();
    //decoration
    private final HBox hb = new HBox();
    private final VBox[] decorate = {new VBox(),new VBox()};
    //vb at center
    private final VBox vb = new VBox();
    //vbox in vb center
    private final VBox vbin = new VBox(40);
    private final Text txt = new Text("Sign in");
    //username box
    private final HBox usernameBox = new HBox();
    private final TextField username = new TextField();
    //password box
    private final HBox passwordBox = new HBox();
    private final PasswordField password = new PasswordField();

    private final Button SignInBTN = new Button("Sign in");
    //sign up box
    private final HBox SignUpBox = new HBox(10);
    private final Text txt2 = new Text("Need an Account?");
    private final Button SignUpBTN = new Button("Sign Up");

    private final Text error = new Text();

    private final String colorTheme = "#4F8EDB";

    public LoginPage(){
        //UI things
        setStyle("-fx-background-color: transparent;");
        AnchorPane.setRightAnchor(st,10.0);
        AnchorPane.setLeftAnchor(st,10.0);
        AnchorPane.setTopAnchor(st,10.0);
        AnchorPane.setBottomAnchor(st,10.0);
        st.setEffect(new DropShadow());

        decorate[0].setStyle("-fx-background-color: "+colorTheme+";");
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

        UI.TextFieldUI(username,usernameBox,"username");
        UI.PasswordFieldUI(password,passwordBox,"password");
        UI.ButtonUI(SignInBTN,colorTheme);

        error.setStyle("-fx-fill: red;" +
                "-fx-font-size: 14;" +
                "-fx-font-weight: bold;");

        SignUpBox.setPadding(new Insets(10,10,10,10));
        SignUpBTN.setStyle("-fx-text-fill: "+colorTheme+";" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: transparent;");
        txt2.setStyle("-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;");
        SignUpBox.setAlignment(Pos.BOTTOM_CENTER);
        SignUpBox.getChildren().addAll(txt2,SignUpBTN);


        vbin.getChildren().addAll(txt,usernameBox,passwordBox,SignInBTN,error,SignUpBox);
        vbin.setPadding(new Insets(10,10,10,10));

        vb.getChildren().add(vbin);
        st.getChildren().addAll(hb,vb);
        getChildren().add(st);

        //Action event
        SignInBTN.setOnAction(e->{SignInpBTNAction();});
        SignUpBTN.setOnAction(e->{SignUpBTNAction();});
    }
    private void SignInpBTNAction(){
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            String username = this.username.getText();
            String password = this.password.getText();
            //check error
            if (username.isBlank() || username.isEmpty() || password.isBlank() || password.isEmpty()) {
                error.setText("Each field must be fill!!!");
                connection.close();
                return;
            }
            //create statement
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserAccount where Username = '"+username+"' and Password = '"+password+"'");
            if(resultSet.next()){
                clearField();
                String rank=resultSet.getString(3);
                //System.out.println(rank);
                if(rank.equals("Admin")){
                    //go to Admin page
                    Covid19App.changeScene("mainpage_admin",username);
                }
                else if(rank.equals("Staff")){
                    //go to Staff page
                    Covid19App.changeScene("mainpage_staff",username);
                }
                else if(rank.equals("Member")){
                    //go to Member page
                    Covid19App.changeScene("mainpage_member",username);
                }
                System.out.println("login complete!!!");
            }
            else{
                error.setText("Username or password is wrong!!!");
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void SignUpBTNAction(){
        //go to sign up page
        clearField();
        Covid19App.changeScene("signup");
    }
    private void clearField(){
        error.setText("");
        username.setText("");
        password.setText("");
    }
}
