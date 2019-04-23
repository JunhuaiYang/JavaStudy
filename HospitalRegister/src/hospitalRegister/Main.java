package hospitalRegister;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // intialize connector
        try {
            DBConnector.getInstance().connectDataBase("47.75.53.92", 3306, "hospital", "hospital", "yjh66778899");
        } catch (SQLException e) {
            System.err.println("failed to connect to sql database");
            System.exit(0);
        }

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("挂号管理系统");
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(190);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
