package app.covid19app;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Covid19App extends Application {
    static HashMap<String, Parent> map = new HashMap();
    //all page
    static LoginPage login = new LoginPage();
    static SignUpPage signUpPage = new SignUpPage();
    static MainPage_Admin mainPageAdmin = new MainPage_Admin();
    static MainPage_Staff mainPageStaff = new MainPage_Staff();
    static MainPage_Member mainPageMember = new MainPage_Member();
    // Scene
    static Scene scene = new Scene(login,1280,720);
    @Override
    public void start(Stage stage) throws IOException {
        addScene();
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
    public static void changeScene(String name){
        scene.setRoot(map.get(name));
    }
    public static void changeScene(String name,String username){
        if(name=="mainpage_member"){mainPageMember.setUsername(username);}
        else if(name=="mainpage_staff"){mainPageStaff.setUsername(username);}
        else if(name=="mainpage_admin"){mainPageAdmin.setUsername(username);}
        scene.setRoot(map.get(name));
    }
    public void addScene(){
        map.put("login",login);
        map.put("signup",signUpPage);
        map.put("mainpage_admin", mainPageAdmin);
        map.put("mainpage_staff",mainPageStaff);
        map.put("mainpage_member",mainPageMember);
    }
}