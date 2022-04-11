package app.covid19app;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginPage extends Pane {
    private TextField Username = new TextField("Enter Username");
    private PasswordField Password = new PasswordField();
    private Button SignInBTN = new Button("Sign in");
    private Button SignUpBTN = new Button("Sign up");
    private Label txt = new Label("Don't have an account?");
    private Rectangle bg= new Rectangle(400,400);
    private double paneHeight = 720;
    private double paneWidth = 1280;
    public LoginPage(){
        try {
            FileInputStream input = new FileInputStream("src\\main\\Images\\login-bg.jpg");
            Image img= new Image(input);
            BackgroundImage bgimg= new BackgroundImage(img,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, false));
            Background background = new Background(bgimg);
            setBackground(background);
        }catch (FileNotFoundException e){
            System.out.println("File not found in login page: MISSING BACKGROUND");
        }

        setHeight(paneWidth);
        setWidth(paneHeight);

        Username.relocate((paneWidth-400)/2.0+130,(paneHeight-400)/2.0+130);
        Password.relocate((paneWidth-400)/2.0+130,(paneHeight-400)/2.0+170);

        SignInBTN.relocate((paneWidth-400)/2.0+150,(paneHeight-400)/2.0+250);
        txt.relocate((paneWidth-400)/2.0+50,(paneHeight-400)/2.0+300);
        SignUpBTN.relocate((paneWidth-400)/2.0+215,(paneHeight-400)/2.0+300);

        bg.setFill(Color.WHITE);
        bg.setArcHeight(50.0);
        bg.setArcWidth(50.0);
        bg.relocate((paneWidth-400)/2.0,(paneHeight-400)/2.0);
        getChildren().addAll(bg,SignInBTN,SignUpBTN,txt,Username,Password);
    }
}
