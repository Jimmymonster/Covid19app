package app.covid19app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Covid19App extends Application {
    static LoginPage login = new LoginPage();
    static SignUpPage signUpPage = new SignUpPage();
    static MainPage mainPage = new MainPage();
    static Scene scene = new Scene(login,1280,720);
    @Override
    public void start(Stage stage) throws IOException {


        FileInputStream input = new FileInputStream("src\\main\\Images\\medic.jpg");
        stage.getIcons().add(new Image(input));
        stage.setTitle("Covid19App!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void change_to_sign_up_page(){scene.setRoot(signUpPage);}
    public static void change_to_sign_in_page(){scene.setRoot(login);}
    public static void change_to_main_page(){scene.setRoot(mainPage);}
}