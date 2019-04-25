package hospitalRegister;

import com.jfoenix.controls.*;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LoginCtrl {
    @FXML
    public GridPane gridPanes;
    @FXML
    JFXRadioButton DoctorLogin;
    @FXML
    JFXRadioButton PatientLogin;
    @FXML
    JFXTextField inputUsername;
    @FXML
    JFXPasswordField inputPassword;
    @FXML
    JFXButton buttonLogin;
    @FXML
    private final ToggleGroup group = new ToggleGroup();


    @FXML
    void initialize() {
        DoctorLogin.setToggleGroup(group);
        DoctorLogin.setUserData("Doctor");
        PatientLogin.setToggleGroup(group);
        PatientLogin.setSelected(true);
        PatientLogin.setUserData("Patient");

        // 按下enter登录
        buttonLogin.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                Login();
        });

        inputPassword.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                Login();
        });


    }

    @FXML
    void Login() {
        buttonLogin.setText("登录中...");
        if (!validateUserNameAndPassword())
            return;
        if (group.getSelectedToggle() != null) {
            try {
                if (group.getSelectedToggle().getUserData().toString().equals("Doctor")) {
                    doctorLogin();
                }
                if (group.getSelectedToggle().getUserData().toString().equals("Patient")) {
                    patientLogin();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private void doctorLogin() throws IOException {
        ResultSet result = DBConnector.getInstance().getDoctorInfo(inputUsername.getText().trim());
        if (result == null) {
            alertErrorDialog("读取数据库错误。");
            return;
        }

        try {
            if (!result.next()) {
                alertErrorDialog("用户不存在");
                return;
            } else if (!result.getString(Config.ColumnDoctorPassword).equals(inputPassword.getText())) {
                alertErrorDialog("密码错误");
                return;
            }

            DoctorCtrl.doctorName = result.getString(Config.ColumnDoctorName);
            DoctorCtrl.doctorNumber = result.getString(Config.ColumnDoctorNumber);
            DoctorCtrl.lastLogin = result.getString(Config.ColumnDoctorLastLogin);

            DBConnector.getInstance().updateDoctorLoginTime(
                    result.getString(Config.ColumnDoctorNumber),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Doctor.fxml")));
            scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
            FXRobotHelper.getStages().get(0).setScene(scene);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void patientLogin() throws IOException {
        ResultSet result = DBConnector.getInstance().getPatientInfo(inputUsername.getText().trim());
        if (result == null) {
            alertErrorDialog("读取数据库错误");
            return;
        }

        try {
            if (!result.next()) {
                alertErrorDialog("用户不存在");
                return;
            } else if (!result.getString(Config.ColumnPatientPassword).equals(inputPassword.getText())) {
                alertErrorDialog("密码错误");
                return;
            }

            // fill info and login to patient page
            PatientCtrl.patientName = result.getString(Config.ColumnPatientName);
            PatientCtrl.patientBalance = result.getDouble(Config.ColumnPatientBalance);
            PatientCtrl.patientNumber = result.getString(Config.ColumnPatientNumber);
            PatientCtrl.lastLogin = result.getString(Config.ColumnPatientLastLogin);

            DBConnector.getInstance().updatePatientLoginTime(
                    result.getString(Config.ColumnPatientNumber),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Patient.fxml")));
            scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
            FXRobotHelper.getStages().get(0).setScene(scene);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validateUserNameAndPassword() {
        if (inputUsername.getText().isEmpty()) {
            alertWarningDialog("请输入用户名");
            return false;
        }
        if (inputPassword.getText().isEmpty()) {
            alertWarningDialog("请输入密码");
            return false;
        }

        return true;
    }

    @FXML
    void onInputUsernameAction() {
        inputUsername.setStyle("");
    }

    @FXML
    void onInputPasswordAction() {
        inputPassword.setStyle("");
    }

    private void alertWarningDialog(String p_header) {
        Alert _alert = new Alert(Alert.AlertType.WARNING);
        _alert.setTitle("警告");
        _alert.setHeaderText(p_header);
        _alert.show();
    }

    private void alertErrorDialog(String p_header) {
        Alert _alert = new Alert(Alert.AlertType.ERROR);
        _alert.setTitle("错误");
        _alert.setHeaderText(p_header);
        _alert.showAndWait();
        buttonLogin.setText("登录");
    }

}
