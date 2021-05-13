package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private static List<User> allUsers;
    private String username;
    private String password;
    private String nickname;
    private int score;
    private List<Card> allCards;
    private List<Deck> allDecks;
    private Deck currentActiveDeck;
    private int lifePoint;
    private Board board;
    private int money;

    public User(String username, String nickname, String password) {
        if (allUsers == null)
            allUsers = new ArrayList<>();
        this.allCards = new ArrayList<>();
        this.allDecks = new ArrayList<>();
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.currentActiveDeck = null;
        setScore(0);
        setMoney(100000);
        allUsers.add(this);
    }

    public static User getUserByUsername(String username) {
        if (allUsers != null) {
            for (User user : allUsers) {
                if (user.getUsername().equals(username))
                    return user;
            }
        }
        return null;
    }

    public static User getUserByNickname(String nickname) {
        for (User allUser : allUsers) {
            if (allUser.getNickname().equals(nickname))
                return allUser;
        }
        return null;
    }

    public void increaseScore(int amount) {
        this.score += amount;
    }

    public void decreaseScore(int amount) {
        this.score -= amount;
    }

    public void increaseLifePoint(int amount) {
        this.lifePoint += amount;
    }

    public void decreaseLifePoint(int amount) {
        this.lifePoint -= amount;
    }

    public void increaseMoney(int amount) {
        this.money -= amount;
    }

    public void decreaseMoney(int amount) {
        this.money += amount;
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public int getScore() {
        return this.score;
    }

    public Deck getActiveDeck() {
        return this.currentActiveDeck;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setNewBoard() {
        this.board = new Board();
    }

    public int getLifePoint() {
        return this.lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setActiveDeck(Deck deck) {
        this.currentActiveDeck = deck;
    }

    //TODO age lazem bood jaye pascalcase aadish kon
    public Card getCardByName(String name) {
        for (Card card : allCards) {
            if (card.getNamePascalCase().equals(name)) {
                return card;
            }
        }
        return null;
    }

    public List<Deck> getAllDecks() {
        return this.allDecks;
    }

    public List<Card> getAllCards() {
        return this.allCards;
    }

    public void deleteDeck(String name) {
        this.allDecks.remove(getDeckByName(name));
    }

    public void deleteCard(String name) {
        this.allCards.remove(getCardByName(name));
    }

    public void addDeck(Deck deck) {
        this.allDecks.add(deck);
    }

    public void addCardToUsersAllCards(Card card) {
        this.allCards.add(card);
    }

    public Deck getDeckByName(String name) {
        for (Deck deck : allDecks) {
            if (deck.getDeckName().equals(name)) {
                return deck;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(this.username, user.username);
    }
}