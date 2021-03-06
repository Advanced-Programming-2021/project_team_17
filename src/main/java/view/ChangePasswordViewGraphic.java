package view;

import controller.ChangePasswordControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ChangePasswordViewGraphic extends Application {
    private static Stage stage;
    static ChangePasswordViewGraphic instance = null;
    private static User user;
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField newPassword;

    public static ChangePasswordViewGraphic getInstance() {
        if (instance == null) instance = new ChangePasswordViewGraphic();
        return instance;
    }

    public static void showPasswordChanged(User user) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("Done");
        error.setContentText("Dear " + user.getNickname() + " your password was changed successfully!");
        error.showAndWait();
    }

    @Override
    public void start(Stage stage) throws Exception {
        ChangePasswordViewGraphic.stage = stage;
        URL url = getClass().getResource("/ChangePassword.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    public void setCurrentUser(User user) {
        ChangePasswordViewGraphic.user = user;
    }

    public void changePassword() {
        try {
            ChangePasswordControllerGraphic.changePassword(user, oldPassword.getText(), newPassword.getText(),stage);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setHeaderText("Error");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }

    public void goBack() throws Exception {
        ChangePasswordControllerGraphic.goBack(stage);
    }
}
