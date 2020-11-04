package sample.Models;

import javafx.beans.property.*;

public class Students_Info {
    private StringProperty First_Name;
    private StringProperty Last_Name;
    private IntegerProperty Id_Student;
    private StringProperty Birthday;
    private StringProperty City;
    private StringProperty No;
    private IntegerProperty Age;
    private StringProperty Avg;
    public String getNo() {
        return No.get();
    }
    public void setNo(String No) {
        this.No=new SimpleStringProperty(No);
    }
    public Integer getAge() {

        return Age.get();
    }
    public void setAge(int Age) {

        this.Age=new SimpleIntegerProperty(Age);
    }
    public String getAvg() {

        return Avg.get();
    }
    public void setAvg(String Avg) {

        this.Avg=new SimpleStringProperty(Avg);
    }
    public String getFirst_Name() {
        return First_Name.get();
    }
    public void setFirst_Name(String First_Name) {
        this.First_Name=new SimpleStringProperty(First_Name);
    }
    public String getLast_Name() {
        return Last_Name.get();
    }
    public void setLast_Name(String Last_Name) {
        this.Last_Name=new SimpleStringProperty(Last_Name);
    }
    public Integer getId_Student() {
        return Id_Student.get();
    }
    public void setId_Student(Integer Id_Student) {
        this.Id_Student=new SimpleIntegerProperty(Id_Student);
    }
    public String getBirthday() {
        return Birthday.get();
    }
    public void setBirthday(String Birthday) {
        this.Birthday=new SimpleStringProperty(Birthday);
    }
    public String getCity() {
        return City.get();
    }
    public void setCity(String City) {
        this.City=new SimpleStringProperty(City);
    }
    public Students_Info(){}
    public Students_Info(String First_Name,String Last_Name,Integer Id_Student,String Birthday,String City) {
        this.First_Name =  new SimpleStringProperty(First_Name);
        this.Last_Name =  new SimpleStringProperty(Last_Name);
        this.Id_Student =  new SimpleIntegerProperty(Id_Student);
        this.Birthday =  new SimpleStringProperty(Birthday);
        this.City =  new SimpleStringProperty(City);
    }
}
