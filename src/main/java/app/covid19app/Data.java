package app.covid19app;

import javafx.scene.layout.HBox;

//Data set for table!!!
public class Data{
    private String name;
    private String surname;
    private String status;
    private HBox button;
    public Data(String name,String surname,String status,HBox button){
        this.name=name;
        this.surname=surname;
        this.status=status;
        this.button=button;
    }
    //setter
    public void setName(String name){this.name=name;}
    public void setSurname(String surname){this.surname=surname;}
    public void setStatus(String status){this.status=status;}
    public void setButton(HBox button){this.button=button;}
    //getter
    public String getName(){return this.name;}
    public String getSurname(){return this.surname;}
    public String getStatus(){return this.status;}
    public HBox getButton(){return this.button;}
    @Override
    public String toString(){
        return this.name+" "+this.surname+" "+this.status;
    }
}