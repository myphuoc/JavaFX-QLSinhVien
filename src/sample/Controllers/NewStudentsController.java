package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import sample.Models.QuanLySv_DAO;
import sample.Models.formatCalender;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class NewStudentsController implements Initializable {
    @FXML
    private DatePicker birthdayPicker;
    @FXML
    private JFXTextField NewFirstName;
    @FXML
    private JFXTextField NewLastName;
    @FXML
    private JFXTextField NewCity;
    @FXML
    private JFXTextField NewMarkMath;
    @FXML
    private JFXTextField NewMarkChemistry;
    @FXML
    private JFXTextField NewMarkBiology;
    @FXML
    private JFXTextField NewMarkJavaFX;
    @FXML
    private JFXButton CancelFormNew;
    @FXML
    private JFXTextArea NoticeProgram;
    @FXML
    private void cancelForm(){
        // get a handle to the stage
        Stage stage = (Stage) CancelFormNew.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    public void Format_Show_Calender(){
        String pattern = "dd-MM-yyyy";
        formatCalender.format(pattern, birthdayPicker);
    }
    public void AddNewStudent() throws SQLException, ClassNotFoundException {
            String FirstName = NewFirstName.getText();
            String LastName = NewLastName.getText();
            String City = NewCity.getText();
            Double Math = Double.valueOf(NewMarkMath.getText());
            Double Biology = Double.valueOf(NewMarkBiology.getText());
            Double Chemistry = Double.valueOf(NewMarkChemistry.getText());
            Double JavaFx = Double.valueOf(NewMarkJavaFX.getText());
            LocalDate date = birthdayPicker.getValue();
            QuanLySv_DAO.AddNewStudents(FirstName,LastName,City,date,Math,Biology,Chemistry,JavaFx);
            NoticeProgram.setText("Add Student "+FirstName+" "+LastName+" Complete!!!");
            NoticeProgram.setStyle("-fx-text-fill:green;-fx-font-size: 20px");
            ObservableList list = QuanLySv_DAO.getAllStudents();
            MenuController menuController = ConnectControllers.getmenuController();
            menuController.tableview_Students.setItems(list);
            NewFirstName.setText("");
            NewLastName.setText("");
            NewCity.setText("");
            NewMarkBiology.setText("");
            NewMarkChemistry.setText("");
            NewMarkMath.setText("");
            NewMarkJavaFX.setText("");
            birthdayPicker.setValue(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
}
