/*package app.covid19app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainPage_Member extends AnchorPane {
    private String username="";
    private String name="";
    private String surname="";
    private String age="";
    private String description="";
    private String numberID="";
    private String tel="";

    private final StackPane st= new StackPane();
    private final HBox hb = new HBox(); //for crop vbox
    private final VBox[] box = {new VBox(10),new VBox()}; // 0 = left nev bar , 1 = content box
    private final Button[] btn= {new Button("News"),new Button("Information"),new Button("Treatment"),new Button("Sign out")};

    private final VBox[] leftbox = {new VBox(),new VBox(30)};
    private final Text toptxt = new Text();

    //btn0

    //btn1
    private final Text txtBTN1 = new Text("Your Information");
    private final HBox[] hbBTN1 = {new HBox(),new HBox(),new HBox()};
    private final Text[] info = new Text[5];
    private final HBox[] hbinfo = new HBox[5];
    //btn2

    //btn3

    private final String colorTheme = "#4F8EDB";
    private final String highlightedColor = "#376bab";
    private final String activeBTN = "-fx-background-color: " + highlightedColor + ";" +
            "-fx-background-radius: 50PX 0 0 50PX;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 20;";
    private final String non_activeBTN = "-fx-background-color: "+colorTheme+";" +
            "-fx-background-radius: 50PX 0 0 50PX;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 20;";
    public MainPage_Member(){
        setStyle("-fx-background-color: transparent;");
        AnchorPane.setRightAnchor(st,10.0);
        AnchorPane.setLeftAnchor(st,10.0);
        AnchorPane.setTopAnchor(st,10.0);
        AnchorPane.setBottomAnchor(st,10.0);
        st.setEffect(new DropShadow());

        box[0].setStyle("-fx-background-color: "+colorTheme+";");
        box[0].setPrefWidth(1280*0.2);
        box[0].setPadding(new Insets(200,0,0,0));
        box[0].setAlignment(Pos.TOP_CENTER);
        box[0].getChildren().addAll(btn);
        box[1].setStyle("-fx-background-color: white;");
        box[1].setVgrow(leftbox[1],Priority.ALWAYS);
        leftbox[1].setPadding(new Insets(30,30,30,30));

        btnAction(0);

        //btn 1 setup
        txtBTN1.setStyle("-fx-font-size: 40;" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: "+colorTheme+";");
        for(int i=0;i<5;i++){
            info[i] = new Text();
            hbinfo[i] = new HBox();
            info[i].setStyle("-fx-font-size: 40;" +
                    "-fx-font-family: QuickSand;" +
                    "-fx-font-weight: bold;" +
                    "-fx-fill: "+colorTheme+";");
            hbinfo[i].getChildren().add(info[i]);
        }
        hbinfo[0].getChildren().add(info[1]);
        //hbinfo[1] isn't use here don't get confuse

        for(int i=0;i<3;i++){
            hbBTN1[i].setAlignment(Pos.CENTER_LEFT);
        }
        hbBTN1[0].getChildren().addAll(hbinfo[0]);
        hbBTN1[0].setHgrow(hbinfo[0],Priority.ALWAYS);
        hbBTN1[1].getChildren().addAll(hbinfo[2],hbinfo[3]);
        hbBTN1[1].setHgrow(hbinfo[2],Priority.ALWAYS);
        hbBTN1[1].setHgrow(hbinfo[3],Priority.ALWAYS);
        hbBTN1[2].getChildren().addAll(hbinfo[4]);
        hbBTN1[2].setHgrow(hbinfo[4],Priority.ALWAYS);
        leftbox[1].setAlignment(Pos.TOP_CENTER);


        hb.setHgrow(box[1], Priority.ALWAYS);
        hb.getChildren().addAll(box[0],box[1]);

        st.getChildren().addAll(hb);

        getChildren().addAll(st);

        //action event
        for(int i=0;i<4;i++){
            int idx = i;
            btn[i].setOnAction(e->{btnAction(idx);});
        }

    }
    private void btnAction(int idx){
        box[1].getChildren().clear();
        leftbox[0].getChildren().clear();
        leftbox[1].getChildren().clear();
        box[1].getChildren().addAll(leftbox[0],leftbox[1]);
        leftbox[0].setStyle("-fx-background-color: #7cb3f7;");
        leftbox[0].setPrefHeight(720*0.2);
        leftbox[0].setAlignment(Pos.CENTER);
        toptxt.setText("Member\n"+"Hi! "+name+" "+surname);
        toptxt.setStyle("-fx-font-size: 40;" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: white;");
        leftbox[0].getChildren().add(toptxt);

        for(int i=0;i<4;i++){
            btn[i].setPrefWidth(1280*0.2);
            btn[i].setPrefHeight(50);
            if(i==idx) btn[i].setStyle(activeBTN);
            else btn[i].setStyle(non_activeBTN);
        }
        if(idx==0){

        }
        else if(idx==1){
            info[0].setText("Your name: " +name);
            info[1].setText("   "+surname);
            info[2].setText("Age: "+ age);
            info[3].setText("Number ID: "+numberID);
            info[4].setText("Tel. :"+ tel);
            leftbox[1].getChildren().addAll(txtBTN1,hbBTN1[0],hbBTN1[1],hbBTN1[2]);
        }
        else if(idx==2){

        }
        else if(idx==3){
            reset();
            //Covid19App.changeScene("login");
        }
    }
    private void reset(){
        username="";
        name="";
        surname="";
        age="";
        description="";
        numberID="";
        tel="";
        btnAction(0);
    }
    public void setUsername(String username){
        this.username=username;
        Connection connection = DbConnect.getInstance().getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserInfo where Username = '"+username+"'");
            if(resultSet.next()){
                name = resultSet.getString(2);
                surname = resultSet.getString(3);
                age = resultSet.getString(4);
                description = resultSet.getString(5);
                numberID = resultSet.getString(6);
                tel = resultSet.getString(7);
                btnAction(0);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}*/
