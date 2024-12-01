package com.lasalle.horseracinggui.model.cards;

import java.util.ArrayList;

public class CardsDeck {
    public final int CARDS_DECK_EMPTY = -1;
    static public final int KNIGHT_GOLD   = 40;
    static public final int KNIGHT_CUPS   = 41;
    static public final int KNIGHT_CLUBS  = 42;
    static public final int KNIGHT_SWORDS = 43;

    private ArrayList<Card> cardsDeck = new ArrayList<>();
    private int [] num = {1, 2, 3, 4, 5, 6, 7, 8 ,9};
    private CardSuit[] cardSuits = {CardSuit.GOLD, CardSuit.CUPS, CardSuit.CLUBS, CardSuit.SWORDS};

    private CardFace[] cardFaces = {CardFace.JACK, CardFace.KNIGHT};
    private Card card;
    private ArrayList<Integer> numCartes ;

    /**
     * Method to create the cards deck every time you want to play
     * Create Cards from two vectors (number and suit) and add them to the deck ArrayList
     */
    public CardsDeck () {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < cardSuits.length; j++) {
                card = new NumeredCard(num[i], cardSuits[j]);
                cardsDeck.add(card);

            }
        }
        for (CardFace face : cardFaces){
            for (int j = 0; j < cardSuits.length; j++) {
                card = new FacedCard(face, cardSuits[j]);
                cardsDeck.add(card);
            }
        }
        numCartes = new ArrayList<>();
    }

    public void shuffleDeck() {
        numCartes.clear();
        numCartes.add(KNIGHT_GOLD);
        numCartes.add(KNIGHT_CUPS);
        numCartes.add(KNIGHT_CLUBS);
        numCartes.add(KNIGHT_SWORDS);
    }

    /**
     * If the specified card has already been dealt, it returns null; otherwise,
     * it returns the card and removes it from the deck
     * @param numCarta
     * @return
     */
    public Card getSpecificCard(int numCarta) {
        if (numCartes.contains(numCarta))
            return null;
        numCartes.add(numCarta);
        return cardsDeck.get(numCarta);
    }

    /**
     * Metode per repartir un nova carta aleatoria a la ma
     * @return --> Carta donada
     */
    public Card getCardFromDeck(){
        Card cartaDonada;
        int numCarta = comprovarNumCartes();
        if (numCarta == CARDS_DECK_EMPTY)
            return null;
        cartaDonada = cardsDeck.get(numCarta) ;
        return cartaDonada;
    }

    /**
     * Helper method that returns a random card from Deck that has not been given before
     * @return --> Card not given before
     */
    private int comprovarNumCartes (){
        boolean trobada ;
        int numCarta;
        if (numCartes.size() == cardsDeck.size())
            return CARDS_DECK_EMPTY;
        do{
            trobada = false;
            numCarta = (int) (Math.random() * cardsDeck.size());
            if (numCartes.isEmpty()){
                trobada = false;
            } else if (numCartes.contains(numCarta))
                trobada = true;
        } while(trobada);
        numCartes.add(numCarta);
        return numCarta;
    }
}
