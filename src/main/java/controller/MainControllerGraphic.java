package controller;

import controller.exeption.InvalidDeck;
import controller.exeption.NoActiveDeck;
import javafx.stage.Stage;
import model.User;
import view.*;

public class MainControllerGraphic {

    public static void logout(Stage stage) throws Exception {
        LogInViewGraphic.getInstance().start(stage);
    }

    public static void showScoreBoard(User user, Stage stage) throws Exception {
        ScoreBoardControllerGraphic.setPreviousMenu(MenuTypesGraphic.MAINCONTROLLERGRAPHIC);
        ScoreBoardViewGraphic.getInstance().setCurrentUser(user);
        ScoreBoardViewGraphic.getInstance().start(stage);
    }

    public static void showProfileMenu(User user, Stage stage) throws Exception {
        ProfileViewForGraphic.getInstance().setCurrentUser(user);
        ProfileViewForGraphic.getInstance().start(stage);
    }

    public static void showShopMenu(User user, Stage stage) throws Exception {
        ShopViewGraphic.getInstance().setCurrentUser(user);
        ShopViewGraphic.getInstance().start(stage);
    }

    public static void showDeckMenu(User user, Stage stage) throws Exception {
        AllDecksViewGraphic.getInstance().setCurrentUser(user);
        AllDecksViewGraphic.getInstance().start(stage);
    }

    public static void showNewDuelMenu(User user, Stage stage) throws Exception {
        if (user.getActiveDeck() == null)
            throw new NoActiveDeck(user.getUsername());
        if (!user.getActiveDeck().isValid())
            throw new InvalidDeck(user.getUsername());
        ChooseSecondPlayerViewGraphic.getInstance().setCurrentUser(user);
        ChooseSecondPlayerViewGraphic.getInstance().start(stage);
    }
}
