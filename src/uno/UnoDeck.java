package uno;

//    The deck consists of 108 cards: 4 "Wild" cards (player gets to choose color with this)
//    and 4 cards of "Wild Draw Four (player gets to choose color and next person needs to draw four cards)."
//    25 cards each of four different colors (red, yellow, green, blue).
//    Each color consists of one zero, two each of 1 through 9, and two each of "Skip," "Draw Two," and "Reverse."
//    (1*0, 2*1, 2*2, 2*3, 2*4, 2*5, 2*6, 2*7, 2*8, 2*9, 2*skip, 2*drawTwo, 2*reverse) => of each color!

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class UnoDeck {

    // INSTANCE VARIABLES //
    private UnoCard[] cards;
    private int cardsInUnoDeck;
    // var is in dit een array van ons aangemaakt object UnoCard genaamt cards.
    // om bij te houden hoeveel kaarten we toevoegen aan ons deck,
    // later zullen we deze cardsInDeck laten itereren met ++ in onze array per keer we een nieuwe kaart aannmaken,
    // ! om ervoor te zorgen dat telkens er een nieuwe kaart wordt toegevoegd we naar de volgende 'cel' in de array springen!

    // CONSTRUCTOR //
    public UnoDeck() {
        cards = new UnoCard[108];
        // array aanmaken voor het volledig deck van 108 kaarten.
        // op dit moment is ons deck leeg, we hebben nog geen waarden meegeven per UnoCard.
    }

    // METHOD: TO ASSEMBLE DECK //
    public void reset() {
        // we noemen deze methode reset omdat ons deck telkens we restten op
        // deze manier gestructureerd moet worden.

        UnoCard.Color[] colors = UnoCard.Color.values();
        cardsInUnoDeck = 0;
        // onze kaarten zijn leeg op dit moment,
        // we maken dus een nieuwe array van de ENUMERATION: COLOR van ons OBJECT: UNOCARD.
        // deze array noemen we Colors want we vullen deze met de waaarde van de enumeration van onze kleuren.

        // MAKING '0' CARD:
        for (int i = 0; i < colors.length - 1; i++) {
            UnoCard.Color color = colors[i];
            // we beginnen de loop met 0 zodat hij start bij rood,
            // min 1 => om ervoor te zorgen dat we 'wild' color niet meenemen in de loop.
            // we vullen dus de colors van de '0' kaarten op met kleur op index i (rood - blauw - geel - groen).

            // aanvullen van onze cards array met de '0' kaarten van elke kleur die we net hebben aangemaakt.
            cards[cardsInUnoDeck++] = new UnoCard(color, UnoCard.Value.getValue(0));

            // MAKING '1' TO '9' CARDS
            for (int j = 1; j < 10; j++) {
                cards[cardsInUnoDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));
                cards[cardsInUnoDeck++] = new UnoCard(color, UnoCard.Value.getValue(j));
                // aanvullen van cards array met 2 KEER(vandaar de dubbele code), de kaarten 1 tem 9 in elke kleur.
                // we gebruiken hier 1 tot 10 omdat we getallen 1 tot 9 willen,
                // en deze dan te laten verwijzen naar de waarden in onze Value enumeration.
            }

            // MAKING 'DRAW-SKIP-REV' CARDS
            UnoCard.Value[] drawSkipRevCards = new UnoCard.Value[]{UnoCard.Value.DrawTwo, UnoCard.Value.Skip, UnoCard.Value.Reverse};
            // nu moeten we ook voor elke kleur nog de speciale kaarten waarden geven, hiervoor maken we weer een nieuwe array.

            // ADDING CREATED CARDS TO DECK
            for (UnoCard.Value sValue : drawSkipRevCards) {
                cards[cardsInUnoDeck++] = new UnoCard(color, sValue);
                cards[cardsInUnoDeck++] = new UnoCard(color, sValue);
            }
            // omdat we onze value (uit enum) al toegekent hebben aan onze speciale kaarten moeten we deze enkel nog door
            // onze color forloop laten gaan om ze daarna als nieuwe uno kaarten toe te voegen aan ons deck.
        }

        // MAKING WILD AND WILDFOUR CARDS
        UnoCard.Value[] wildCards = new UnoCard.Value[]{UnoCard.Value.Wild, UnoCard.Value.WildFour};

        for (UnoCard.Value wValue : wildCards) {
            for (int k = 0; k < 4; k++) {
                cards[cardsInUnoDeck++] = new UnoCard(UnoCard.Color.Wild, wValue);
            }
        }
    }

    // METHOD: SWITCH CURRENT DECK (!WHEN EMPTY!) WITH STOCKPILE OF PLAYED CARDS //
    public void replaceDeckWith(ArrayList<UnoCard> cards) {
        this.cards = cards.toArray(new UnoCard[cards.size()]);
        // turning the arraylist of uno cards into an Array the size of the Arraylist.
        this.cardsInUnoDeck = this.cards.length;
        // parameter = onze cards (stockpile)
        // vervang het deck door een ArrayList van UnoCard's (the stockpile)
    }

    // METHOD: CHECK IF THE CARDS IN THE DECK ARE EMPTY //
    public boolean isEmpty() {
        return cardsInUnoDeck == 0;
    }

    // METHOD: SHUFFLE THE CARDS IN STOCKPILE //
    public void shuffle() {
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {
            // get a random index of the array past the current index.
            // ... the argument is an exclusive bound
            // swap the random element with the present element
            int randomValue = i + random.nextInt(cards.length - i);
            UnoCard randomCard = cards[randomValue];
            // SWAP RANDOM WITH CURRENT INDEX
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
            // getting random value
            // drawing a random card from our deck at the index of our random value
        }
    }

    // EXCEPTION METHODS //
    public UnoCard drawCard() throws IllegalArgumentException {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot draw card since there are no cards in the current deck");
        }
        return cards[--cardsInUnoDeck];
    }

    // GUI METHOD //
    public ImageIcon drawCardImage() throws IllegalArgumentException {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot draw card, current deck is empty");
        }
        return new ImageIcon(cards[--cardsInUnoDeck].toString() + ".png");
    }

    //  METHOD: DRAW MULTIPLE CARDS => FOR DRAW-TWO AND WILD_FOUR. //
    public UnoCard[] drawCard(int a) {
        if (a < 0) {
            throw new IllegalArgumentException("Must draw positive cards but tried to draw " + a + " cards");
        }

        if (a > cardsInUnoDeck) {
            throw new IllegalArgumentException("Cannot draw " + a + " cards since there are only " + cardsInUnoDeck + "cards.");
        }

        UnoCard[] ret = new UnoCard[a];

        for (int i = 0; i < a; i++) {
            ret[i] = cards[--cardsInUnoDeck];
        }
        return ret;
    }

}

