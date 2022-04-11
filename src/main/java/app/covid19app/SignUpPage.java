package app.covid19app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class SignUpPage extends AnchorPane{
    private StackPane st = new StackPane();
    private HBox hb = new HBox();
    private VBox vb = new VBox();
    private VBox[] decorate = {new VBox(),new VBox()};

    private VBox vbin = new VBox(40);
    private Text txt = new Text("Sign up");

    private HBox usernameBox = new HBox();
    private TextField username = new TextField("username");

    private HBox passwordBox = new HBox();
    private PasswordField password = new PasswordField();

    private HBox confirmpasswordBox = new HBox();
    private PasswordField confirmpassword = new PasswordField();

    private Button SignUpBTN = new Button("Sign up");

    private HBox SignInBox = new HBox(10);
    private Text txt2 = new Text("Already have an Account?");
    private Text SignIn = new Text("Sign in");

    public SignUpPage(){
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

        SignInBox.setPadding(new Insets(10,10,10,10));
        SignIn.setStyle("-fx-fill: #fe4451;" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;");
        txt2.setStyle("-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;");
        SignInBox.setAlignment(Pos.BOTTOM_CENTER);
        SignInBox.getChildren().addAll(txt2,SignIn);

        vbin.getChildren().addAll(txt,usernameBox,passwordBox,confirmpasswordBox,SignUpBTN,SignInBox);
        vbin.setPadding(new Insets(10,10,10,10));

        vb.getChildren().add(vbin);
        st.getChildren().addAll(hb,vb);
        getChildren().add(st);
    }
}
