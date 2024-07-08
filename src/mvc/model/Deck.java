package mvc.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
    ArrayList<Card> gameCards;
    ArrayList<Card> playedGameCards;
	
    
    public ArrayList<Card> getGameCards() {
        return gameCards;
    }

    public void setGameCards(ArrayList<Card> gameCards) {
        this.gameCards = gameCards;
    }
    
    public ArrayList<Card> getPlayedGameCards() {
        return playedGameCards;
    }

    public void setPlayedGameCards(ArrayList<Card> pgc) {
        this.playedGameCards = pgc;
    }
    
    public Deck() {
    	gameCards = new ArrayList<Card>();
    	playedGameCards = new ArrayList<Card>();

    	for(int i = 0; i < 4; i++) {
    		gameCards.add(new NumberOneCard());
    	}

    	for(int i = 0; i < 4; i++) {
    		gameCards.add(new NumberTwoCard());
    	}
	
//    	for(int i = 0; i < 4; i++) {
//    		gameCards.add(new NumberThreeCard());
//    	}
//    	
//    	for(int i = 0; i < 4; i++) {
//    		gameCards.add(new NumberFourCard());
//    	}
//    	
//    	for(int i = 0; i < 4; i++) {
//    		gameCards.add(new NumberFiveCard());
//    	}
    	
    	for(int i = 0; i < 4; i++) {
    		gameCards.add(new NumberSevenCard());
    	}
    	
    	for(int i = 0; i < 4; i++) {
    		gameCards.add(new NumberEightCard());
    	}
    	
//    	for(int i = 0; i < 4; i++) {
//    		gameCards.add(new NumberTenCard());
//    	}
//    	
//    	for(int i = 0; i < 4; i++) {
//    		gameCards.add(new NumberElevenCard());
//    	}
//    	
//    	for(int i = 0; i < 4; i++) {
//    		gameCards.add(new NumberTwelveCard());
//    	}
//    	
//    	for(int i = 0; i < 4; i++) {
//    		gameCards.add(new SorryCard());
//    	}
    }
        
    
    
    
    /*
     * 
     * Functions to handle the card Decks ( may change )
     * 
     */
    
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the Deck class
	 * 
	 * Postconditions
	 * - Discards the current top card and adds it to the discarded cards deck
	 * 
	 */
    
    public void foldCard() {
    	Card top = gameCards.get(0);
    	gameCards.remove(0);
    	playedGameCards.add(top);
    }
    
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the Deck class
	 * 
	 * Postconditions
	 * - Returns the top card of the deck
	 * 
	 * @return The top card in the Deck
	 */
    
    public Card getTopCard() {
    	Card top = gameCards.get(0);
    	return top;
    }
    
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the Deck class
	 * 
	 * Postconditions
	 * - Shuffles the deck cards
	 * 
	 */
    
    public void Shuffle() {
    	Collections.shuffle(this.gameCards);
    }
    
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the Deck class
	 * 
	 * Postconditions
	 * - Adds all the discarded cards back to the deck and empties the discarded deck
	 * 
	 */
    
    public void refillCards() {
    	gameCards.addAll(playedGameCards);
    	for(int i = 0; i < playedGameCards.size(); i++) {
    		playedGameCards.remove(i);
    	}
    }
    
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the Deck class
	 * 
	 * Postconditions
	 * - Checks if the deck is empty and returns true or false
	 * 
	 * @return Weather the deck is empty or not
	 */
    
    public boolean isEmptyDeck() {
    	if(gameCards.size() == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
}
