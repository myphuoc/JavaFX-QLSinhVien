package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.Models.QuanLySv_DAO;
import sample.Models.Students_Info;
import sample.Models.formatCalender;
import sample.Utils.ConnectionUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class EditStudentsController implements Initializable {
    @FXML
    private JFXTextField EditFirstName;
    @FXML
    private JFXTextField EditLastName;
    @FXML
    private JFXTextField EditCity;
    @FXML
    private DatePicker EditbirthdayPicker;
    @FXML
    private JFXTextField EditMarkMath;
    @FXML
    private JFXTextField EditMarkChemistry;
    @FXML
    private JFXTextField EditMarkBiology;
    @FXML
    private JFXTextField EditMarkJavaFX;
    @FXML
    private JFXButton CancelFormEdit;
    @FXML
    private JFXButton CompleteFormEdit;
    public void Format_Show_Calender(){
        String pattern = "dd-MM-yyyy";
        formatCalender.format(pattern, EditbirthdayPicker);
    }
    public void editStudent() throws SQLException, ClassNotFoundException {
        MenuController menuController = ConnectControllers.getmenuController();
        int id_Student = menuController.tableview_Students.getSelectionModel().getSelectedItem().getId_Student();
        QuanLySv_DAO.updateStudent(id_Student,EditFirstName.getText(),EditLastName.getText(),EditCity.getText(),EditbirthdayPicker.getValue(),Double.valueOf(EditMarkMath.getText()),Double.valueOf(EditMarkBiology.getText()),Double.valueOf(EditMarkChemistry.getText()),Double.valueOf(EditMarkJavaFX.getText()));
        ObservableList list = QuanLySv_DAO.getAllStudents();
        menuController.tableview_Students.setItems(list);
        Stage stage = (Stage) CompleteFormEdit.getScene().getWindow();
        // do what you have to do
        stage.close();
        menuController.ShowEditBtn.setDisable(true);
    }
    @FXML
    private void cancelFormEdit(){
        // get a handle to the stage
        Stage stage = (Stage) CancelFormEdit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
//        ConnectControllers.setmenuController(this);
        MenuController menuController = ConnectControllers.getmenuController();
        EditFirstName.setText(menuController.tableview_Students.getSelectionModel().getSelectedItem().getFirst_Name());
        EditLastName.setText(menuController.tableview_Students.getSelectionModel().getSelectedItem().getLast_Name());
        EditbirthdayPicker.setValue(LocalDate.parse(menuController.tableview_Students.getSelectionModel().getSelectedItem().getBirthday()));
        EditCity.setText(menuController.tableview_Students.getSelectionModel().getSelectedItem().getCity());

    }
}
