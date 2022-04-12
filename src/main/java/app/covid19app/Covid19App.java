package app.covid19app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Covid19App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginPage login = new LoginPage();
        SignUpPage sign = new SignUpPage();

        Scene scene = new Scene(login,1280,720);
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
}