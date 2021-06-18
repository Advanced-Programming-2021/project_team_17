package model;

import controller.ImportExportUserController;

import java.util.*;

public class Deck {
    private String deckName;
    private List<Cardable> mainDeck;
    private List<Cardable> sideDeck;

    public Deck(String deckName) {
        this.deckName = deckName;
        this.mainDeck = new ArrayList<>();
        this.sideDeck = new ArrayList<>();
    }

    public String getDeckName() {
        return this.deckName;
    }

    public List<Cardable> getMainDeck() {
        return this.mainDeck;
    }

    public List<Cardable> getSideDeck() {
        return this.sideDeck;
    }

    public void setMainDeck(List<Cardable> mainDeck) {
        this.mainDeck = mainDeck;
    }

    public void setSideDeck(List<Cardable> sideDeck) {
        this.sideDeck = sideDeck;
    }

    public int getSideSize() {
        return this.sideDeck.size();
    }

    public int getMainSize() {
        return this.mainDeck.size();
    }

    public void addCardToMainDeck(Cardable card) {
        this.mainDeck.add(card);
    }

    public void addCardToSideDeck(Cardable card) {
        this.sideDeck.add(card);
    }

    public void setDeck(ArrayList<Cardable> mainCards, ArrayList<Cardable> sideCards){
        this.mainDeck = mainCards;
        this.sideDeck = sideCards;
    }

    public void removeCardFromMainDeck(Cardable card) {
        this.mainDeck.remove(card);
    }

    public void removeCardFromSideDeck(Cardable card) {
        this.sideDeck.remove(card);
    }

    public int numberOfWantedCard(Cardable wantedCard) {
        int number = 0;
        for (Cardable eachCard : this.sideDeck) {
            if (eachCard.equals(wantedCard))
                number++;
        }
        for (Cardable eachCard : this.mainDeck) {
            if (eachCard.equals(wantedCard))
                number++;
        }
        return number;
    }

    public boolean isValid() {
        return ((this.mainDeck.size() >= 40) &&
                (this.mainDeck.size() <= 60) &&
                (this.sideDeck.size() <= 15));
    }

    public boolean cardExistsInDeck(Cardable card, boolean isSide) {
        if (isSide) {
            return this.sideDeck.contains(card);
        } else {
            return this.mainDeck.contains(card);
        }
    }

    public String toStringForMainDeck() {
        String toBeReturned = "Deck: " + this.deckName + "\nMain deck:\nMonsters:\n";
        ArrayList<String> monsters = new ArrayList<>();
        ArrayList<String> spellAndTraps = new ArrayList<>();
        for (Cardable card : this.mainDeck) {
            if (card instanceof MonsterCard)
                monsters.add(card.getNamePascalCase() + ": " + card.getDescription());
            else if ((card instanceof SpellCard) || (card instanceof TrapCard))
                spellAndTraps.add(card.getNamePascalCase() + ": " + card.getDescription());
        }
        Collections.sort(monsters);
        Collections.sort(spellAndTraps);
        for (String eachMonster : monsters) {
            toBeReturned += eachMonster + "\n";
        }
        toBeReturned += "Spell and Traps:\n";
        for (String eachSpellAndTrap : spellAndTraps) {
            toBeReturned += eachSpellAndTrap + "\n";
        }
        return toBeReturned;
    }

    public String toStringForSideDeck() {
        String toBeReturned = "Deck: " + this.deckName + "\nSide deck:\nMonsters:\n";
        ArrayList<String> monsters = new ArrayList<>();
        ArrayList<String> spellAndTraps = new ArrayList<>();
        for (Cardable card : this.sideDeck) {
            if (card instanceof MonsterCard)
                monsters.add(card.getNamePascalCase() + ": " + card.getDescription());
            else if ((card instanceof SpellCard) || (card instanceof TrapCard))
                spellAndTraps.add(card.getNamePascalCase() + ": " + card.getDescription());
        }
        Collections.sort(monsters);
        Collections.sort(spellAndTraps);
        for (String eachMonster : monsters) {
            toBeReturned += eachMonster + "\n";
        }
        toBeReturned += "Spell and Traps:\n";
        for (String eachSpellAndTrap : spellAndTraps) {
            toBeReturned += eachSpellAndTrap + "\n";
        }
        return toBeReturned;
    }

    @Override
    public String toString() {
        return this.deckName + ": main deck " + this.mainDeck.size() + ", side deck" + this.sideDeck.size() + ", " + this.isValid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(this.deckName, deck.deckName);
    }

}
