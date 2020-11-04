package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import static com.sun.org.apache.xalan.internal.utils.SecuritySupport.getResourceAsStream;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception{
        this.stage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("Views/sample.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Students Management");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("Image/logo.png")));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
