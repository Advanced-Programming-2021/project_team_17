package view;

import controller.ShopController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import model.User;

import java.net.URL;
import java.util.ArrayList;

public class CreateNewDeckView extends Application  {

    private static Stage stage;
    private static Deck deck;
    private static CreateNewDeckView instance = null;
    private static User user;
    private static ArrayList<Image> images = new ArrayList<>(4);
    private static Card card1, card2, card3, card4;
    public static ImageView image1, image2, image3, image4;
    private static int totalCardsNumber;
    private static int firstCardNumber = 0;
    private static AnchorPane root;
    @FXML
    private Label money, youHave1, youHave2, youHave3, youHave4;
    @FXML
    private Button addButton1Main, addButton2Main, addButton3Main, addButton4Main;



    public static CreateNewDeckView getInstance() {
        if (instance == null) instance = new CreateNewDeckView();
        return instance;
    }

    public void setCurrentUser(User user) {
        CreateNewDeckView.user = user;
    }

    public void setCurrentDeck(Deck deck) {
        CreateNewDeckView.deck = deck;
    }

    @Override
    public void start(Stage stage) throws Exception {
        CreateNewDeckView.stage = stage;
        URL url = getClass().getResource("/CreateNewDeck.fxml");
        root = FXMLLoader.load(url);
        setImagesAndCards();
        addImages();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void addToMainDeck1() {
        addCardToDeck(card1,false);
    }

    public void addToMainDeck2() {
        addCardToDeck(card2,false);
    }

    public void addToMainDeck3() {
        addCardToDeck(card3,false);
    }

    public void addToMainDeck4() {
        addCardToDeck(card4,false);
    }

    public void addToSideDeck1() {
        addCardToDeck(card1,true);
    }

    public void addToSideDeck2() {
        addCardToDeck(card2,true);
    }

    public void addToSideDeck3() {
        addCardToDeck(card3,true);
    }

    public void addToSideDeck4() {
        addCardToDeck(card4,true);
    }


//    public void buy2() {
//        user.getAllCards().add(card2);
//        //setAll();
//        printText("Card added successfully");
//    }
//
//    public void buy3() {
//        user.getAllCards().add(card3);
//        //setAll();
//        printText("Card added successfully");
//    }
//
//    public void buy4() {
//        user.getAllCards().add(card4);
//        //setAll();
//        printText("Card added successfully");
//    }

    public void addCardToDeck(Card card, boolean isSide){
        if (deck.getCountOfCardInDeck(card) > 2) {
            printText("you already have 3 cards of this type in this deck");
        } else {
            if (isSide) {
                user.getAllCards().remove(card);
                deck.addCardToSideDeck(card);
                printText("Card added to side deck successfully");
            } else {
                user.getAllCards().remove(card);
                deck.addCardToMainDeck(card);
                printText("Card added to main deck successfully");
            }
            setOnlyImagesAndCards();
        }
    }


    public void goNextPage() {
        if (firstCardNumber + 4 >= totalCardsNumber) return;
        firstCardNumber += 4;
        setOnlyImagesAndCards();
        //setInStocks();
    }

    public void goPreviousPage() {
        if (firstCardNumber - 4 < 0) return;
        firstCardNumber -= 4;
        setOnlyImagesAndCards();
        //setInStocks();
    }

    private void setImagesAndCards() {
        images = ShopController.getInstance(user).getImages(firstCardNumber); //todo
        image1 = setImageView(images.get(0), 83);
        image2 = setImageView(images.get(1), 283);
        image3 = setImageView(images.get(2), 483);
        image4 = setImageView(images.get(3), 683);
        card1 = ShopController.getInstance(user).getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance(user).getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance(user).getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance(user).getCards(firstCardNumber).get(3);
    }

    private ImageView setImageView(Image image, int x) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(140);
        imageView.setFitHeight(204);
        imageView.setX(x);
        imageView.setY(64);
        return imageView;
    }

    private void setOnlyImagesAndCards() {
        images = ShopController.getInstance(user).getImages(firstCardNumber);
        removeImages();
        image1.setImage(images.get(0));
        image2.setImage(images.get(1));
        image3.setImage(images.get(2));
        image4.setImage(images.get(3));
        addImages();
        card1 = ShopController.getInstance(user).getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance(user).getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance(user).getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance(user).getCards(firstCardNumber).get(3);
    }

    private void removeImages() {
        root.getChildren().remove(image1);
        root.getChildren().remove(image2);
        root.getChildren().remove(image3);
        root.getChildren().remove(image4);
    }

    private void addImages() {
        root.getChildren().add(image1);
        root.getChildren().add(image2);
        root.getChildren().add(image3);
        root.getChildren().add(image4);
    }

    public void printText(String output) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, output, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

}
