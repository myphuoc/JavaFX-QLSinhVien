package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.Models.QuanLySv_DAO;
import sample.Models.Students_Info;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;



public class MenuController implements Initializable {
    @FXML
    private JFXTextField searchName;
    @FXML
    public TableView<Students_Info> tableview_Students;
    @FXML
    private TableColumn<Students_Info, String> colFirst_Name;
    @FXML
    private TableColumn<Students_Info, String> colLast_Name;
    @FXML
    private TableColumn<Students_Info,String> colNo;
    @FXML
    private JFXTextField fullName;
    @FXML
    private JFXTextField id_student;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField birthday;
    @FXML
    private JFXTextField avg;
    @FXML
    private JFXTextField age;
    @FXML
    public JFXButton ShowEditBtn;

    public void Show_Info_Students(){
        ShowEditBtn.setDisable(false);
        fullName.setText(tableview_Students.getSelectionModel().getSelectedItem().getFirst_Name()+" "+tableview_Students.getSelectionModel().getSelectedItem().getLast_Name());
        city.setText(tableview_Students.getSelectionModel().getSelectedItem().getCity());
        id_student.setText(String.valueOf(tableview_Students.getSelectionModel().getSelectedItem().getId_Student()));
//        xử lý hiển thị ngày tháng năm sinh
        Format formatter;
        Date day = Date.valueOf(tableview_Students.getSelectionModel().getSelectedItem().getBirthday());
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        String s;
        s = formatter.format(day);
        birthday.setText(s);
//        xử lý tính tuổi và hiển thị
        age.setText(String.valueOf(tableview_Students.getSelectionModel().getSelectedItem().getAge()));
        avg.setText(String.valueOf(tableview_Students.getSelectionModel().getSelectedItem().getAvg()));

    }
    public void show_new_students() throws IOException {
        Stage stageAdd = new Stage();
        Parent rootAdd = FXMLLoader.load(getClass().getResource("/sample/Views/NewStudents.fxml"));
        Scene scene1;
        scene1 = new Scene(rootAdd);
        stageAdd.setTitle("Add new student");
        stageAdd.initModality(Modality.WINDOW_MODAL);
        stageAdd.initOwner(Main.stage);
        stageAdd.setScene(scene1);
        stageAdd.show();
    }
    public void displayTable(){
        ObservableList list = null;
        try {
            list = QuanLySv_DAO.getAllStudents();
            tableview_Students.setItems(list);
            colNo.setCellValueFactory(new PropertyValueFactory<Students_Info,String>("No"));
            colFirst_Name.setCellValueFactory(new PropertyValueFactory<Students_Info, String>("First_Name"));
            colLast_Name.setCellValueFactory(new PropertyValueFactory<Students_Info, String>("Last_Name"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void deleteSelected() throws SQLException, ClassNotFoundException {
        int id_Student = tableview_Students.getSelectionModel().getSelectedItem().getId_Student();
        QuanLySv_DAO.deleteIdStudent(id_Student);
        displayTable();
    }
    public void showEdit() throws IOException {
        Stage stageEdit = new Stage();
        Parent rootAdd = FXMLLoader.load(getClass().getResource("/sample/Views/EditStudents.fxml"));
        Scene scene1;
        scene1 = new Scene(rootAdd);
        stageEdit.setTitle("Edit student");
        stageEdit.initModality(Modality.WINDOW_MODAL);
        stageEdit.initOwner(Main.stage);
        stageEdit.setScene(scene1);
        stageEdit.show();
    }
    @FXML
    private void searchName(KeyEvent ke) throws SQLException, ClassNotFoundException {
        ObservableList data = null;
        data = QuanLySv_DAO.getAllStudents();
        FilteredList<Students_Info> filteredData = new FilteredList<>(data, p -> true);
            searchName.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(pers -> {
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(pers.getId_Student().toString().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }
                    else if(pers.getFirst_Name().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                        return true;
                    }else if(pers.getLast_Name().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                        return true;
                    }
                    return false;
                } );
                SortedList<Students_Info> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview_Students.comparatorProperty());
                tableview_Students.setItems(sortedData);
            });

    }
    @FXML
    public void deleteAll() throws SQLException, ClassNotFoundException {
        QuanLySv_DAO.deleteAll();
        displayTable();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ConnectControllers.setmenuController(this);
        displayTable();
    }
}
