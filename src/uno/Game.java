package uno;

import java.util.ArrayList;

public class Game {

    // INSTANCE VARIABLES //
    private int currentPlayer; // who ever's turn it is.
    private String[] playerIds; // name and/or playernumber.

    private UnoDeck deck; // current deck players are playing with.
    private ArrayList<ArrayList<UnoCard>> playerHand;
    // we have to make an arraylist of an arraylist,
    // every players hand is an arraylist of unoCards.
    // we need to keep track of all the hands so we make an arraylist of an arraylist.
    // playerHand is actually all of the players hands.
    private ArrayList<UnoCard> stockPile;

    private UnoCard.Color validColor; // color from UnoCard
    private UnoCard.Value validValue; // value from UnoCard

    boolean gameDirection;

    // CONSTRUCTOR //
    public Game(String[] pids) {
        deck = new UnoDeck();
        deck.shuffle();
        stockPile = new ArrayList<UnoCard>();

        playerIds = pids;

    }
}
