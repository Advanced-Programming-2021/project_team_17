package model;


import controller.DeckController;
import controller.DuelController;
import javafx.scene.image.Image;

import java.util.ArrayList;

public interface Card {
    String getName();
    String getNamePascalCase();
    String getDescription();
    int getPrice();
    @Override
    String toString();
    Image getImage();

    static Card getCardByImage(Image image) {
        ArrayList<Card> allCards = (ArrayList<Card>)DeckController.getInstance(null).getAllCardsOfGame();
        for (Card card : allCards) {
            if(card.getImage().equals(image))
                return card;
        }
        return null;
    }
}
