package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import sample.Utils.ConnectionUtils;

import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;

public class QuanLySv_DAO {
    private static ObservableList<Students_Info> getListStudents(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Students_Info> List = FXCollections.observableArrayList();
        int i = 1;
        while (rs.next()) {
            Students_Info students_info = new Students_Info();
            students_info.setFirst_Name(rs.getString("First_Name_Student"));
            students_info.setLast_Name(rs.getString("Last_Name_Student"));
            students_info.setId_Student(rs.getInt("Id_Student"));
            students_info.setBirthday(rs.getString("Birthday"));
            students_info.setAvg(rs.getString("Average"));
            students_info.setCity(rs.getString("Name_City"));
            String tam = String.valueOf(i);
            students_info.setNo(tam);
//            xu ly tuoi
            Format formatter;
            formatter = new SimpleDateFormat("yyyy");
            String birth=formatter.format(Date.valueOf(students_info.getBirthday()));
            LocalDate today = LocalDate.now();
            String now = formatter.format(Date.valueOf(today));
            students_info.setAge(Integer.valueOf(now) - Integer.valueOf(birth));
            List.add(students_info);
            i++;
        }
        // Đóng kết nối
        return List;
    }

    public static ObservableList<Students_Info> getAllStudents() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection();
        // Tạo đối tượng Statement.
        Statement statement = connection.createStatement();

        String sql = "select Student_Info.*,City.Name_Role \n" +
                "from Student_info,City \n" +
                "where City.Id_Student = Student_info.Id_Student";

        // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
        ResultSet rs = statement.executeQuery(sql);
        ObservableList<Students_Info> list_Students = getListStudents(rs);
        connection.close();
        return list_Students;
    }
    public static void AddNewStudents(String FirstName,String LastName,String City,LocalDate Birthday,double Math,double biology,double chemistry,double javafx){
        String sql = "INSERT INTO Students_Info VALUES(?,?,?,?,?)";

        try{
            ObservableList list = getAllStudents();
            int tam=list.size();
            if(tam==0) {
                tam++;
                double avg = (Math + chemistry + biology + javafx) / 4;
                Connection connection = ConnectionUtils.getMyConnection();
                PreparedStatement pst = connection.prepareStatement(sql);
                int id_student = tam;
                pst.setInt(1, id_student);
                pst.setString(2, FirstName);
                pst.setString(3, LastName);
                Date date = Date.valueOf(Birthday);
                pst.setDate(4, date);
                pst.setFloat(5, (float) avg);
                pst.execute();
                pst.close();
            }
            else{
                double avg = (Math + chemistry + biology + javafx) / 4;
                Connection connection = ConnectionUtils.getMyConnection();
                PreparedStatement pst = connection.prepareStatement(sql);
                String eq="Select Max(Id_Student) as 'max' from Students_Info";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(eq);
                int idTam = 0;
                while (rs.next()){
                    idTam=rs.getInt("max");
                };
                int id_student = idTam+1;
                pst.setInt(1, id_student);
                pst.setString(2, FirstName);
                pst.setString(3, LastName);
                Date date = Date.valueOf(Birthday);
                pst.setDate(4, date);
                pst.setFloat(5, (float) avg);
                pst.execute();
                pst.close();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        String exp="Select Max(Id_City) as 'max' from City";
        String ex = "INSERT INTO City VALUES(?,?,?)";
        try{
            ObservableList list = getAllStudents();
            int tam=list.size();
            if(tam==0) {
                Connection connection = ConnectionUtils.getMyConnection();
                PreparedStatement pst = connection.prepareStatement(ex);
                tam++;
                int id_City = 1;
                int id_student = tam;
                pst.setInt(1, id_City);
                pst.setInt(2, id_student);
                pst.setString(3, City);
                pst.execute();
                pst.close();
            }
            else{
                Connection connection = ConnectionUtils.getMyConnection();
                PreparedStatement pst = connection.prepareStatement(ex);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(exp);
                int idTam = 0;
                while (rs.next()) {
                    idTam = rs.getInt("max");
                }
                tam++;
                int id_City = idTam+1;
                int id_student=idTam+1;
                pst.setInt(1, id_City);
                pst.setInt(2, id_student);
                pst.setString(3, City);
                pst.execute();
                pst.close();
                rs.close();
            }
        } catch (SQLException | ClassNotFoundException error) {
            error.printStackTrace();
        }
    }
    public static void deleteIdStudent(int id_Student) throws SQLException, ClassNotFoundException {
        String ex = "Delete From City where Id_Student = ?";
        Connection connection = ConnectionUtils.getMyConnection();
        PreparedStatement pst = connection.prepareStatement(ex);
        String exp = "Delete From Students_Info where Id_Student = ?";
        PreparedStatement ps = connection.prepareStatement(exp);
        pst.setInt(1,id_Student);
        pst.execute();
        ps.setInt(1,id_Student);
        ps.execute();
        pst.close();
        ps.close();
    }
    public static void updateStudent(int id_Student, String FirstName, String LastName, String City, LocalDate birthday,Double Math,Double Biology,Double Chemistry, Double JavaFx) throws SQLException, ClassNotFoundException {
        String ex = "UPDATE Students_Info set First_Name_Student = ?,Last_Name_Student = ?, Birthday = ?, Average = ? where Id_Student = ?";
        Connection connection = ConnectionUtils.getMyConnection();
        PreparedStatement pst = connection.prepareStatement(ex);
        float avg = (float) ((Biology + Chemistry + JavaFx + Math)/4);
        pst.setString(1,FirstName);
        pst.setString(2,LastName);
        Date date = Date.valueOf(birthday);
        pst.setDate(3,date);
        pst.setFloat(4,avg);
        pst.setInt(5,id_Student);
        pst.execute();
        String ep = "UPDATE  City set Name_City = ? where Id_Student = ?";
        PreparedStatement ps = connection.prepareStatement(ep);
        ps.setString(1,City);
        ps.setInt(2,id_Student);
        ps.execute();
        ps.close();
        pst.close();
    }
    public static void deleteAll() throws SQLException, ClassNotFoundException {
        String deleteCity = "Delete from City";
        String deleteStudents = "Delete from Students_Info";
        Connection connection = ConnectionUtils.getMyConnection();
        PreparedStatement dltCity = connection.prepareStatement(deleteCity);
        PreparedStatement dltStudent = connection.prepareStatement(deleteStudents);
        dltCity.execute();
        dltStudent.execute();
        dltCity.close();
        dltStudent.close();
    }
}
