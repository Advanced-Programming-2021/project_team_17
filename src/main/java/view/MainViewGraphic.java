package view;

import client.Main;
import controller.MainControllerGraphic;
import controller.SoundController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;


public class MainViewGraphic extends Application {

    private static Stage stage;
    static MainViewGraphic instance = null;
    private static User user;
    public static Button button = new Button();
    public static TextField textField = new TextField();
    public static AnchorPane root;
    public boolean isCTRLPressed = false;
    public boolean isSHIFTPressed = false;
    public boolean isMPressed = false;
    static Thread thread;


    public static MainViewGraphic getInstance() {
        if (instance == null) instance = new MainViewGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        MainViewGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainViewGraphic.stage = stage;
        URL url = getClass().getResource("/MainMenu.fxml");
        root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        preCheat();
        checkForInvitation();
    }

    public void logout() throws Exception {
        MainControllerGraphic.logout(user, stage);
    }

    public void showScoreboard() throws Exception {
        MainControllerGraphic.showScoreBoard(user, stage);
    }

    public void profileMenu() throws Exception {
        MainControllerGraphic.showProfileMenu(user, stage);
    }

    public void shopMenu() throws Exception {
        MainControllerGraphic.showShopMenu(user, stage);
    }

    public void goToDeckMenu() throws Exception {
        MainControllerGraphic.showDeckMenu(user, stage);
    }

    public void importExport() throws Exception {
        ImportExportGraphic.getInstance().start(stage);
    }

    public void newDuel() {
        try {
            MainControllerGraphic.showNewDuelMenu(user, stage, thread);
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    public void muteUnmute() {
        SoundController.muteAndUnmute();
    }

    private void printError(String command) {
        Alert alert = new Alert(Alert.AlertType.ERROR, command);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

    private void preCheat() {
        button.setLayoutX(19);
        button.setLayoutY(124);
        button.setText("cheat!");
        textField.setLayoutX(19);
        textField.setLayoutY(95);
        stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().getName().equals("M"))
                    isMPressed = true;
                if (event.getCode().getName().equals("Ctrl"))
                    isCTRLPressed = true;
                if (event.getCode().getName().equals("Shift"))
                    isSHIFTPressed = true;

                if (isSHIFTPressed && isCTRLPressed && isMPressed) {
                    root.getChildren().add(button);
                    root.getChildren().add(textField);
                    button.setOnAction(this::createCheatEnvironment);
                }
            }

            private void createCheatEnvironment(ActionEvent actionEvent) {
                if (textField.getText().matches("\\d+")) {
                    int amountOfMoney = Integer.parseInt(textField.getText());
                    user.increaseMoney(amountOfMoney);
                    alertForCheat();
                }
                root.getChildren().remove(button);
                root.getChildren().remove(textField);
            }
        });
        stage.getScene().setOnKeyReleased(event -> {
            if (event.getCode().getName().equals("M"))
                isMPressed = false;
            if (event.getCode().getName().equals("Ctrl"))
                isCTRLPressed = false;
            if (event.getCode().getName().equals("Shift"))
                isSHIFTPressed = false;
        });
    }

    public void alertForCheat() {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("shame on you!");
        error.setContentText("you successfully cheated!");
        error.showAndWait();
    }

    public void openChat() throws Exception {
        ChatView.getInstance().setCurrentUser(user);
        ChatView.getInstance().start(stage);
    }

    public void goToTVRoom() throws Exception {
        TVRoomView.getInstance().start(stage);
    }

    public void checkForInvitation() {
        thread = new Thread(() -> {
            while (true) {
                try {
                    Main.dataOutputStream.writeUTF("getRequest!@#" + user.getUsername());
                    Main.dataOutputStream.flush();
                    String result = Main.dataInputStream.readUTF();
                    if (!result.equals("")) {
                        Platform.runLater(() ->
                                showAlert(result));
                    }
                    Thread.sleep(2000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void showAlert(String senderUsername) {
        Alert info = new Alert(Alert.AlertType.CONFIRMATION, "You have just been invited to a play a game with " + senderUsername + " \n" + "Want to join?", ButtonType.YES, ButtonType.NO);
        info.setHeaderText("Invitation");
        info.showAndWait();
        if (info.getResult().equals(ButtonType.YES)) {
            try {
                RockPaperScissorView.getInstance().setCurrentUser(user);
                RockPaperScissorView.getInstance().setNumberOfRounds(1);
                RockPaperScissorView.getInstance().setPlayerTwoName(senderUsername);
                RockPaperScissorView.getInstance().start(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }
}
