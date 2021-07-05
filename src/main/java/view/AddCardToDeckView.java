package view;

import controller.DeckController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCardToDeckView extends Application implements Initializable {

    private static Deck deck;
    private static Stage stage;
    private static AddCardToDeckView instance = null;
    private static User user;
    private static ArrayList<Image> images = new ArrayList<>(4);
    private static Card card1, card2, card3, card4;
    public static ImageView image1, image2, image3, image4;
    private static int totalCardsNumber;
    private static int firstCardNumber = 0;
    private static AnchorPane root;
    /*@FXML
    private Label money, youHave1, youHave2, youHave3, youHave4;
    @FXML
    private Button addButton1Main, addButton2Main, addButton3Main, addButton4Main;*/


    public static AddCardToDeckView getInstance() {
        if (instance == null) instance = new AddCardToDeckView();
        return instance;
    }

    public void setCurrentUser(User user) {
        AddCardToDeckView.user = user;
    }

    public void setCurrentDeck(Deck deck) {
        AddCardToDeckView.deck = deck;
    }

    @Override
    public void start(Stage stage) throws Exception {
        AddCardToDeckView.stage = stage;
        URL url = getClass().getResource("/CreateNewDeck.fxml");
        root = FXMLLoader.load(url);
        DeckController.getInstance(user).setUsersCards();
        DeckController.initImages();
        setImagesAndCards();
        addImages();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    public void addCardToDeck(Card card, boolean isSide) {
        if (deck.getCountOfCardInDeck(card) > 2) {
            printTextError("you already have 3 cards of this type in this deck");
        } else {
            if (isSide) {
                user.removeCard(card);
                //user.getAllCards().remove(card);
                deck.addCardToSideDeck(card);
                printTextInformation("Card added to side deck successfully");
            } else {
                user.removeCard(card);
                //user.getAllCards().remove(card);
                deck.addCardToMainDeck(card);
                printTextInformation("Card added to main deck successfully");
            }
            resetImagesAndCards();
        }
    }


    public void goNextPage() {
        if (firstCardNumber + 1 >= totalCardsNumber) return;
        firstCardNumber += 1;
        resetImagesAndCards();
    }

    public void goPreviousPage() {
        if (firstCardNumber - 1 < 0) return;
        firstCardNumber -= 1;
        resetImagesAndCards();
    }

    private void setImagesAndCards() {
        images = DeckController.getInstance(user).getImages(firstCardNumber);
        if (images.size() > 0) image1 = setImageView(images.get(0), 83);
        if (images.size() > 1) image2 = setImageView(images.get(1), 283);
        if (images.size() > 2) image3 = setImageView(images.get(2), 483);
        if (images.size() > 3) image4 = setImageView(images.get(3), 683);
        ArrayList<Card> cards = DeckController.getInstance(user).getCards(firstCardNumber);
        if (cards.size() > 0) card1 = cards.get(0);
        if (cards.size() > 1) card2 = cards.get(1);
        if (cards.size() > 2) card3 = cards.get(2);
        if (cards.size() > 3) card4 = cards.get(3);
    }

    private ImageView setImageView(Image image, int x) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(140);
        imageView.setFitHeight(204);
        imageView.setX(x);
        imageView.setY(64);
        return imageView;
    }

    private void resetImagesAndCards() {
        DeckController.getInstance(user).setUsersCards();
        DeckController.initImages();
        images = DeckController.getInstance(user).getImages(firstCardNumber);
        ArrayList<Card> cards = DeckController.getInstance(user).getCards(firstCardNumber);
        removeImages();
        if (images.size() > 0) image1 = setImageView(images.get(0), 83);
        else if(image1!=null) image1.setImage(null);
        if (images.size() > 1) image2 = setImageView(images.get(1), 283);
        else if(image2!=null) image2.setImage(null);
        if (images.size() > 2) image3 = setImageView(images.get(2), 483);
        else if(image3!=null) image3.setImage(null);
        if (images.size() > 3) image4 = setImageView(images.get(3), 683);
        else if(image4!=null) image4.setImage(null);
        addImages();
        if (cards.size() > 0) card1 = cards.get(0);
        else card1 = null;
        if (cards.size() > 1) card2 = cards.get(1);
        else card2 = null;
        if (cards.size() > 2) card3 = cards.get(2);
        else card3 = null;
        if (cards.size() > 3) card4 = cards.get(3);
        else card4 = null;
    }

    private void removeImages() {
        if (root != null) {
            if (image1 != null) root.getChildren().remove(image1);
            if (image2 != null) root.getChildren().remove(image2);
            if (image3 != null) root.getChildren().remove(image3);
            if (image4 != null) root.getChildren().remove(image4);
        }
    }

    private void addImages() {
        if (root != null) {
            if (image1 != null) root.getChildren().add(image1);
            if (image2 != null) root.getChildren().add(image2);
            if (image3 != null) root.getChildren().add(image3);
            if (image4 != null) root.getChildren().add(image4);
        }
    }

    public void printTextInformation(String output) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, output, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DeckController.getInstance(user).setUsersCards();
        setImagesAndCards();
        addImages();
    }

    public void addToMainDeck1() {
        addCardToDeck(card1, false);
    }

    public void addToMainDeck2() {
        addCardToDeck(card2, false);
    }

    public void addToMainDeck3() {
        addCardToDeck(card3, false);
    }

    public void addToMainDeck4() {
        addCardToDeck(card4, false);
    }

    public void addToSideDeck1() {
        addCardToDeck(card1, true);
    }

    public void addToSideDeck2() {
        addCardToDeck(card2, true);
    }

    public void addToSideDeck3() {
        addCardToDeck(card3, true);
    }

    public void addToSideDeck4() {
        addCardToDeck(card4, true);
    }

    public void printTextError(String output) {
        Alert alert = new Alert(Alert.AlertType.ERROR, output, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

    public void back() throws Exception {
        AllDecksViewGraphic.getInstance().start(stage); //todo
    }


}