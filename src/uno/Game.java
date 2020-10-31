package uno;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

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
    // in uno you either play clock- or counterclockwise.
    // var to keep track of direction the game go's when a player hits a reverse card.

    // CONSTRUCTOR //
    public Game(String[] pids) {
        deck = new UnoDeck();
        deck.shuffle();
        stockPile = new ArrayList<UnoCard>();
        // first initialise deck.
        // than shuffle the deck.
        // initialise stockpile with an arraylist of unoCards.

        playerIds = pids;
        currentPlayer = 0;
        gameDirection = false;

        playerHand = new ArrayList<ArrayList<UnoCard>>();

        for (int i = 0; i < pids.length; i++) {
            ArrayList<UnoCard> hand = new ArrayList<UnoCard>(Arrays.asList(deck.drawCard(7)));
            // creating a 'hand' of unoCards, wich is a new arrayList of UnoCards.
            // and filled it with from our UnoDeck (deck var).
            // from our deck var we draw seven cards, getting that as an array, than turn that into a list.
            // => creating a hand of 7 cards.

            playerHand.add(hand);
            // ones we have our hand of seven cards.
            // we are adding that hand to out playerHand var = every players hand.
        }
    }

    public void start(Game game) {
        UnoCard card = deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();

        if(card.getValue() == UnoCard.Value.Wild) {
            start(game);
        }

        if(card.getValue() == UnoCard.Value.WildFour || card.getValue() == UnoCard.Value.DrawTwo) {
            start(game);
        }

        if (card.getValue() == UnoCard.Value.Skip) {
            JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped");
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, message);

            if(!gameDirection) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            }

            else {
                currentPlayer = (currentPlayer +1) % playerIds.length;
                if (currentPlayer == -1) {
                    currentPlayer = playerIds.length - 1;
                }
            }
        }

        if (card.getValue() == UnoCard.Value.Reverse) {
            JLabel message = new JLabel(playerIds[currentPlayer] + " the game direction changed!");
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, message);
            gameDirection ^= true;
            currentPlayer = playerIds.length - 1;
        }

        stockPile.add(card);
    }
}
