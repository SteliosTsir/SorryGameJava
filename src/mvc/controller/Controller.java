package mvc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import mvc.model.*;
import mvc.model.squares.*;
import mvc.view.*;

/**
 * Sorry! Board Game 					HY252 Project 2023-2024
 * 
 * @author Stylianos Tsirindanis 		csd5124 
 */


public class Controller {

	Deck deck;
	View view;
	Card currCard;
	ArrayList<Player> p;
	Square[] roundsquares;
	Square[] greensq;
	Square[] bluesq;
	Square[] yellowsq;
	Square[] redsq;
	StartSquare startRed;
	StartSquare startBlue;
	StartSquare startGreen;
	StartSquare startYellow;
	HomeSquare homeRed;
	HomeSquare homeBlue;
	HomeSquare homeGreen;
	HomeSquare homeYellow;
	StartSlideSquare ssqRed1;
	StartSlideSquare ssqBlue1;
	StartSlideSquare ssqGreen1;
	StartSlideSquare ssqYellow1;	
	StartSlideSquare ssqRed2;
	StartSlideSquare ssqBlue2;
	StartSlideSquare ssqGreen2;
	StartSlideSquare ssqYellow2;	
	EndSlideSquare esqRed1;
	EndSlideSquare esqBlue1;
	EndSlideSquare esqGreen1;
	EndSlideSquare esqYellow1;	
	EndSlideSquare esqRed2;
	EndSlideSquare esqBlue2;
	EndSlideSquare esqGreen2;
	EndSlideSquare esqYellow2;
	
	int cfold = 1;
	int max;
	int turn;
	
	public void setPlayer(ArrayList<Player> p) {
		this.p = p;
	}
	
	/**
	 * 
	 * Preconditions
	 * - There is an instance of Controller class
	 * Postconditions
	 * - The Squares, Players, View, Deck, and are initialized and the window is shown to the user
	 * 
	 */
	
	public void initialize() {
		deck = new Deck();
		view = new View();
		int count = 0;
		
	    count = view.initView();		
		
	    String names[] = new String[count];
	    
	    names = view.getNames(count);
	    
		p = new ArrayList<Player>();

		deck.Shuffle();
	    
		redsq = new SafetySquare[5];
		yellowsq = new SafetySquare[5];
		for(int i = 0; i < 5; i++) {
			redsq[i] = new SafetySquare(i + 1, COLOUR.Red, null);
			yellowsq[i] = new SafetySquare(i + 1, COLOUR.Yellow, null);			
		}
		
		
		roundsquares = new Square[60];
		for(int i = 0; i < 60; i++) {
			if(!((i >= 1 && i <= 4) || (i >= 9 && i <= 13) || (i >= 16 && i <= 19) || (i >= 24 && i <= 28) || (i >= 31 && i <= 34) || (i >= 39 && i <= 43) || (i >= 46 && i <= 49) || (i >= 54 && i <= 58))) {
				roundsquares[i] = new SimpleSquare(i + 1, null);
			} else {
				if((i >= 1 && i <= 4) || (i >= 9 && i <= 13)) {
					if(i == 1 || i == 9) {
						roundsquares[i] = new StartSlideSquare(i + 1, COLOUR.Red, null);
					} else if(i == 4 || i == 13) {
						roundsquares[i] = new EndSlideSquare(i + 1, COLOUR.Red, null);
					} else {
						roundsquares[i] = new InternalSlideSquare(i + 1, COLOUR.Red, null);
					}
				} else if((i >= 16 && i <= 19) || (i >= 24 && i <= 28)) {
					if(i == 16 || i == 24) {
						roundsquares[i] = new StartSlideSquare(i + 1, COLOUR.Blue, null);
					} else if(i == 19 || i == 28) {
						roundsquares[i] = new EndSlideSquare(i + 1, COLOUR.Blue, null);
					} else {
						roundsquares[i] = new InternalSlideSquare(i + 1, COLOUR.Blue, null);
					}
				} else if((i >= 31 && i <= 34) || (i >= 39 && i <= 43)) {
					if(i == 31 || i == 39) {
						roundsquares[i] = new StartSlideSquare(i + 1, COLOUR.Yellow, null);
					} else if(i == 34 || i == 43) {
						roundsquares[i] = new EndSlideSquare(i + 1, COLOUR.Yellow, null);
					} else {
						roundsquares[i] = new InternalSlideSquare(i + 1, COLOUR.Yellow, null);
					}
				} else {
					if(i == 46 || i == 54) {
						roundsquares[i] = new StartSlideSquare(i + 1, COLOUR.Green, null);
					} else if(i == 49 || i == 58) {
						roundsquares[i] = new EndSlideSquare(i + 1, COLOUR.Green, null);
					} else {
						roundsquares[i] = new InternalSlideSquare(i + 1, COLOUR.Green, null);
					}
				}
			}
		}
		
		
		ssqRed1 = (StartSlideSquare) roundsquares[1];
		ssqRed2 = (StartSlideSquare) roundsquares[9];
		esqRed1 = (EndSlideSquare) roundsquares[4];
		esqRed2 = (EndSlideSquare) roundsquares[13];
		
		ssqYellow1 = (StartSlideSquare) roundsquares[31];
		ssqYellow2 = (StartSlideSquare) roundsquares[39];
		esqYellow1 = (EndSlideSquare) roundsquares[34];
		esqYellow2 = (EndSlideSquare) roundsquares[43];
		
		ssqGreen1 = (StartSlideSquare) roundsquares[46];
		ssqGreen2 = (StartSlideSquare) roundsquares[54];
		esqGreen1 = (EndSlideSquare) roundsquares[49];
		esqGreen2 = (EndSlideSquare) roundsquares[58];
		
		ssqBlue1 = (StartSlideSquare) roundsquares[16];
		ssqBlue2 = (StartSlideSquare) roundsquares[24];
		esqBlue1 = (EndSlideSquare) roundsquares[19];
		esqBlue2 = (EndSlideSquare) roundsquares[28];
		
		startRed = new StartSquare(-1, esqRed1 ,COLOUR.Red);		
		startYellow = new StartSquare(-1, esqYellow1 ,COLOUR.Yellow);
		
		homeRed = new HomeSquare(-2 ,COLOUR.Red);
		homeYellow = new HomeSquare(-2 ,COLOUR.Yellow);
		
		if(count == 2) {
			p.add(new Player(names[0], COLOUR.Red));
			p.get(0).setPawns(new Pawn(1, p.get(0), COLOUR.Red, startRed), new Pawn(2, p.get(0), COLOUR.Red, startRed));
			p.add(new Player(names[1], COLOUR.Yellow));
			p.get(1).setPawns(new Pawn(1, p.get(1), COLOUR.Yellow, startYellow), new Pawn(2, p.get(1), COLOUR.Yellow, startYellow));
		} else if(count == 3) {
			startBlue = new StartSquare(-1, esqBlue1 ,COLOUR.Blue);
			homeBlue = new HomeSquare(-2, COLOUR.Blue);
			p.add(new Player(names[0], COLOUR.Red));
			p.get(0).setPawns(new Pawn(1, p.get(0), COLOUR.Red, startRed), new Pawn(2, p.get(0), COLOUR.Red, startRed));
			p.add(new Player(names[1], COLOUR.Blue));
			p.get(1).setPawns(new Pawn(1, p.get(1), COLOUR.Blue, startBlue), new Pawn(2, p.get(1), COLOUR.Blue, startBlue));
			p.add(new Player(names[2], COLOUR.Yellow));
			p.get(2).setPawns(new Pawn(1, p.get(2), COLOUR.Yellow, startYellow), new Pawn(2, p.get(2), COLOUR.Yellow, startYellow));
			bluesq = new SafetySquare[5];
			for(int i = 0; i < 5; i++) {
				bluesq[i] = new SafetySquare(i + 1, COLOUR.Blue, null);			
			}
		} else {
			startGreen = new StartSquare(-1, esqGreen1 ,COLOUR.Green);
			homeGreen = new HomeSquare(-2, COLOUR.Green);
			startBlue = new StartSquare(-1, esqBlue1 ,COLOUR.Blue);
			homeBlue = new HomeSquare(-2, COLOUR.Blue);
			p.add(new Player(names[0], COLOUR.Red));
			p.get(0).setPawns(new Pawn(1, p.get(0), COLOUR.Red, startRed), new Pawn(2, p.get(0), COLOUR.Red, startRed));
			p.add(new Player(names[1], COLOUR.Blue));
			p.get(1).setPawns(new Pawn(1, p.get(1), COLOUR.Blue, startBlue), new Pawn(2, p.get(1), COLOUR.Blue, startBlue));
			p.add(new Player(names[2], COLOUR.Yellow));
			p.get(2).setPawns(new Pawn(1, p.get(2), COLOUR.Yellow, startYellow), new Pawn(2, p.get(2), COLOUR.Yellow, startYellow));
			p.add(new Player(names[3], COLOUR.Green));
			p.get(3).setPawns(new Pawn(1, p.get(3), COLOUR.Green, startGreen), new Pawn(2, p.get(3), COLOUR.Green, startGreen));
			greensq = new SafetySquare[5];
			bluesq = new SafetySquare[5];
			for(int i = 0; i < 5; i++) {
				greensq[i] = new SafetySquare(i + 1, COLOUR.Green, null);
				bluesq[i] = new SafetySquare(i + 1, COLOUR.Blue, null);			
			}
		}
				
		view.startGameView(p, roundsquares, bluesq, redsq, yellowsq, greensq, ssqRed1, ssqBlue1, ssqYellow1, ssqGreen1, ssqRed2, ssqBlue2, ssqYellow2, ssqGreen2, esqRed1, esqBlue1, esqYellow1, esqGreen1, esqRed2, esqBlue2, esqYellow2, esqGreen2, startRed, startYellow, startBlue, startGreen, homeRed, homeYellow, homeBlue, homeGreen);
		
		
		return;
		
	}
	
	/**
	 * 
	 * Preconditions
	 * - There is a working instance of Controller class
	 * Postconditions
	 * - If any pawn is on a Start Slide Square it goes to the End Slide Square and any collision in between is checked
	 * 
	 * @param c An active instance of the controller
	 */
	
	public void checkSlide(Controller c) {
		Pawn[] pwn = c.p.get(c.turn).getPawns();
	
		if(pwn[0].getSq() instanceof StartSlideSquare) {
			if(pwn[0].getColour() != ((StartSlideSquare) pwn[0].getSq()).getColour()) {
				while(!(pwn[0].getSq() instanceof EndSlideSquare)) {
					pwn[0].setSq(c.roundsquares[pwn[0].getSq().getNum()]);
					checkCollision(c);
				}
			}
		}
	
		if(pwn[1].getSq() instanceof StartSlideSquare) {
			if(pwn[1].getColour() != ((StartSlideSquare) pwn[1].getSq()).getColour()) {
				while(!(pwn[1].getSq() instanceof EndSlideSquare)) {
					pwn[1].setSq(c.roundsquares[pwn[1].getSq().getNum()]);
					checkCollision(c);
				}
			}
		}
		
	}
	
	/**
	 * 
	 * Preconditions
	 * - There is a working instance of Controller class
	 * Postconditions
	 * - If any pawn lands on a square where is an enemy's pawn already there that pawn is send to Start and the player's pawn takes it's place
	 * 
	 * @param c An active instance of the controller
	 */
	
	public void checkCollision(Controller c) {
		boolean found = false;
		Square square = c.p.get(c.turn).getPawns()[0].getSq();
		int i = 0;
		for(i = 0; i < p.size(); i++) {
			if(i != c.turn) {
				if((c.p.get(i).getPawns()[0].getSq() == square) || (c.p.get(i).getPawns()[1].getSq() == square)) {
					found = true;
					break;
				}
			}
		}
		
		if(found) {
			if(c.p.get(i).getPawns()[0].getSq() == square) {
				c.p.get(i).getPawns()[0].getSq().setPawn(null);
				COLOUR col = c.p.get(i).getPawns()[0].getColour();
				if(col == COLOUR.Red) {
					c.p.get(i).getPawns()[0].setSq(startRed);
				} else if(col == COLOUR.Green) {
					c.p.get(i).getPawns()[0].setSq(startGreen);
				} else if(col == COLOUR.Yellow) {
					c.p.get(i).getPawns()[0].setSq(startYellow);
				} else {
					c.p.get(i).getPawns()[0].setSq(startBlue);
				}
				c.view.sendToStart(c.p.get(i).getPawns()[0], 0);
			} else {
				c.p.get(i).getPawns()[1].getSq().setPawn(null);
				COLOUR col = c.p.get(i).getPawns()[1].getColour();
				if(col == COLOUR.Red) {
					c.p.get(i).getPawns()[1].setSq(startRed);
				} else if(col == COLOUR.Green) {
					c.p.get(i).getPawns()[1].setSq(startGreen);
				} else if(col == COLOUR.Yellow) {
					c.p.get(i).getPawns()[1].setSq(startYellow);
				} else {
					c.p.get(i).getPawns()[1].setSq(startBlue);
				}
				c.view.sendToStart(c.p.get(i).getPawns()[1], 1);
			}
		}
		
		
		found = false;
		square = c.p.get(c.turn).getPawns()[1].getSq();
		i = 0;
		for(i = 0; i < p.size(); i++) {
			if(i != c.turn) {
				if((c.p.get(i).getPawns()[0].getSq() == square) || (c.p.get(i).getPawns()[1].getSq() == square)) {
					found = true;
					break;
				}
			}
		}
		
		if(found) {
			if(c.p.get(i).getPawns()[0].getSq() == square) {
				c.p.get(i).getPawns()[0].getSq().setPawn(null);
				COLOUR col = c.p.get(i).getPawns()[0].getColour();
				if(col == COLOUR.Red) {
					c.p.get(i).getPawns()[0].setSq(startRed);
				} else if(col == COLOUR.Green) {
					c.p.get(i).getPawns()[0].setSq(startGreen);
				} else if(col == COLOUR.Yellow) {
					c.p.get(i).getPawns()[0].setSq(startYellow);
				} else {
					c.p.get(i).getPawns()[0].setSq(startBlue);
				}
				c.view.sendToStart(c.p.get(i).getPawns()[0], 0);
			} else {
				c.p.get(i).getPawns()[0].getSq().setPawn(null);
				COLOUR col = c.p.get(i).getPawns()[1].getColour();
				if(col == COLOUR.Red) {
					c.p.get(i).getPawns()[1].setSq(startRed);
				} else if(col == COLOUR.Green) {
					c.p.get(i).getPawns()[1].setSq(startGreen);
				} else if(col == COLOUR.Yellow) {
					c.p.get(i).getPawns()[1].setSq(startYellow);
				} else {
					c.p.get(i).getPawns()[1].setSq(startBlue);
				}
				c.view.sendToStart(c.p.get(i).getPawns()[1], 1);
			}
		}
		
		
	}
	
	/**
	 * 
	 * Preconditions
	 * - Game has started
	 * Postconditions
	 * - The game is saved into a file
	 * 
	 */
	
	public void saveGame() {
		
	}
	
	/**
	 * 
	 * Preconditions
	 * - File is not NULL
	 * Postconditions
	 * - The game is loaded from a file
	 * 
	 * @param f File from which a save is loaded
	 */
	
	public void loadGame(File f) {
		
	}

	/**
	 * 
	 * Preconditions
	 * - Controller is not null
	 * Postconditions
	 * - Simuates a player round
	 * 	
	 * @param c An active instance of the controller
	 * @param turn Which player has a turn currently
	 */
	
	public void playerRound(Controller c, int turn) {
		c.cfold = 1;
		c.view.clickCard();
		Card card = c.updateCards(c);
		boolean res = false;
		
			int mp = 0;
			if(card.getNum() != 3 && card.getNum() != 5 && card.getNum() != 7 && card.getNum() != 12 && card.getNum() != -1 && card.getNum() != 8) {
				if(c.p.get(c.turn).getPawns()[0].getSq().getNum() == -1 && c.p.get(c.turn).getPawns()[1].getSq().getNum() == -1) {
					if(card.getNum() == 1 || card.getNum() == 2 || card.getNum() == -1) {
						mp = c.view.askForPawn(c.p, c.turn);
					}
				} else {
					mp = c.view.askForPawn(c.p, c.turn);
				}
			}
			
			if(c.p.get(c.turn).getPawns()[mp].getSq().getNum() == -2) {
				mp = 1 - mp;
			}
			
			res = false;
				if(card.getNum() == 3 || card.getNum() == 5) {
					res = card.movePawn(c.p.get(c.turn).getPawns()[0], c.p.get(c.turn).getPawns()[1], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
				} else if(card.getNum() == 8){
					int ch = 0;
					if(c.p.get(c.turn).getPawns()[0].getSq() instanceof StartSquare && c.p.get(c.turn).getPawns()[1].getSq() instanceof StartSquare) {
						ch = 1;
					} else {
						ch = c.view.askEight(c.p, c.turn);
					}
					if(ch == 0) {
						mp = c.view.askForPawn(c.p, c.turn);
						res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
						if(res == false && c.p.get(c.turn).getPawns()[1 - mp].getSq().getNum() != -2) {
							res = card.movePawn(c.p.get(c.turn).getPawns()[1 - mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
						}
						c.cfold = 1;
					} else {
						c.cfold = 0;
					}
				} else if(card.getNum() == 12){
					int ch = 0;
					if(c.p.get(c.turn).getPawns()[0].getSq() instanceof StartSquare && c.p.get(c.turn).getPawns()[1].getSq() instanceof StartSquare) {
						ch = 1;
					} else {
						ch = c.view.askTwelve(c.p, c.turn);
					}
					if(ch == 0) {
						mp = c.view.askForPawn(c.p, c.turn);
						res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
						if(res == false && c.p.get(c.turn).getPawns()[1 - mp].getSq().getNum() != -2) {
							res = card.movePawn(c.p.get(c.turn).getPawns()[1 - mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);	
						}
						c.cfold = 1;
					} else {
						c.cfold = 0;
					}
				} else if(card.getNum() == 10){
					if(!(c.p.get(c.turn).getPawns()[0].getSq() instanceof StartSquare && c.p.get(c.turn).getPawns()[1].getSq() instanceof StartSquare)) {
						int ch = c.view.askTen(c.p, c.turn);
						if(ch == 0) {
							res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue, ch);
							if(res == false && c.p.get(c.turn).getPawns()[1 - mp].getSq().getNum() != -2) {
								res = card.movePawn(c.p.get(c.turn).getPawns()[1 - mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue, ch);
							}
						}
						if(ch == 1 || res == false) {
							res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue, 1);
							if(res == false && c.p.get(c.turn).getPawns()[1 - mp].getSq().getNum() != -2) {
								res = card.movePawn(c.p.get(c.turn).getPawns()[1 - mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue, 1);
							}
						}
					}
				} else if(card.getNum() == 11){
					if(c.p.get(c.turn).getPawns()[0].getSq().getNum() != -1 || c.p.get(c.turn).getPawns()[1].getSq().getNum() != -1){
						int ch = c.view.askEleven(c.p, c.turn);
						if(ch == 0) {
							res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
						} else {
							int[] pawnselect = c.view.askElevenPawn(c.p, c.turn);
							res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.p.get(pawnselect[0]).getPawns()[pawnselect[1]], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
						}
					}
				} else if(card.getNum() == -1){
					boolean play = false;
					COLOUR cl = c.p.get(c.turn).getColour();
					for(int i = 0; i < c.roundsquares.length; i++) {
						if(c.roundsquares[i].getPawn() != null) {
							if(c.roundsquares[i].getPawn().getColour() != cl) {
								play = true;
								break;
							}
						}
					}
					if(play == true) {
						mp = c.view.askForPawn(c.p, c.turn);
						int[] pawnselect = c.view.askElevenPawn(c.p, c.turn);
						res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.p.get(pawnselect[0]).getPawns()[pawnselect[1]], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
						if(res == false) {
							res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
							if(res == false && c.p.get(c.turn).getPawns()[1 - mp].getSq().getNum() != -2) {
								res = card.movePawn(c.p.get(c.turn).getPawns()[1 - mp], c.p.get(pawnselect[0]).getPawns()[pawnselect[1]], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
								if(res == false) {
									res = card.movePawn(c.p.get(c.turn).getPawns()[1 - mp], c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
									if(res == true) {
										COLOUR col = c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].getColour();
										if(col == COLOUR.Red) {
											c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].setSq(startRed);
										} else if(col == COLOUR.Green) {
											c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].setSq(startGreen);
										} else if(col == COLOUR.Yellow) {
											c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].setSq(startYellow);
										} else {
											c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].setSq(startBlue);
										}
										c.view.sendToStart(c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]], (1 - pawnselect[1]));
									}
								} else {
									COLOUR col = c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].getColour();
									if(col == COLOUR.Red) {
										c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].setSq(startRed);
									} else if(col == COLOUR.Green) {
										c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].setSq(startGreen);
									} else if(col == COLOUR.Yellow) {
										c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].setSq(startYellow);
									} else {
										c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].setSq(startBlue);
									}
									c.view.sendToStart(c.p.get(pawnselect[0]).getPawns()[pawnselect[1]], pawnselect[1]);				
								}
							} else {
								COLOUR col = c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].getColour();
								if(col == COLOUR.Red) {
									c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].setSq(startRed);
								} else if(col == COLOUR.Green) {
									c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].setSq(startGreen);
								} else if(col == COLOUR.Yellow) {
									c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].setSq(startYellow);
								} else {
									c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]].setSq(startBlue);
								}
								c.view.sendToStart(c.p.get(pawnselect[0]).getPawns()[1 - pawnselect[1]], (1 - pawnselect[1]));
							}
						} else {
							COLOUR col = c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].getColour();
							if(col == COLOUR.Red) {
								c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].setSq(startRed);
							} else if(col == COLOUR.Green) {
								c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].setSq(startGreen);
							} else if(col == COLOUR.Yellow) {
								c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].setSq(startYellow);
							} else {
								c.p.get(pawnselect[0]).getPawns()[pawnselect[1]].setSq(startBlue);
							}
							c.view.sendToStart(c.p.get(pawnselect[0]).getPawns()[pawnselect[1]], pawnselect[1]);	
						}
					}
				} else if(card.getNum() == 2) {
					res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
					if(res == false && c.p.get(c.turn).getPawns()[1 - mp].getSq().getNum() != -2) {
						res = card.movePawn(c.p.get(c.turn).getPawns()[1 - mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);	
					}
				} else if(card.getNum() == 7) {
					if(!(c.p.get(c.turn).getPawns()[0].getSq() instanceof StartSquare && c.p.get(c.turn).getPawns()[1].getSq() instanceof StartSquare)){
						boolean skip = true;
						int ch = 0;
						if(!(c.p.get(c.turn).getPawns()[0].getSq() instanceof StartSquare || c.p.get(c.turn).getPawns()[1].getSq() instanceof StartSquare || c.p.get(c.turn).getPawns()[0].getSq() instanceof HomeSquare || c.p.get(c.turn).getPawns()[1].getSq() instanceof HomeSquare)) {
							ch = c.view.askSeven(c.p, c.turn);
							skip = false;
						}
						if(ch == 0) {
							if(skip == false) {
								mp = c.view.askForPawn(c.p, c.turn);
							}
							res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
							if(res == false && c.p.get(c.turn).getPawns()[1 - mp].getSq().getNum() != -2) {
								res = card.movePawn(c.p.get(c.turn).getPawns()[1 - mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
							}
						} else {
							do {
								int move[] = c.view.AskSevenSplit(c.p, c.turn);
								res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.p.get(c.turn).getPawns()[1 - mp], move, c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
							} while (res == false);
						}
					}
				} else {
					res = card.movePawn(c.p.get(c.turn).getPawns()[mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
					if(res == false && c.p.get(c.turn).getPawns()[1 - mp].getSq().getNum() != -2) {
						res = card.movePawn(c.p.get(c.turn).getPawns()[1 - mp], c.roundsquares, c.greensq, c.redsq, c.yellowsq, c.bluesq, c.homeRed, c.homeYellow, c.homeGreen, c.homeBlue);
					}
				}
			
			c.checkCollision(c);
			c.checkSlide(c);
			c.checkCollision(c);
			for(int i = 0; i < c.p.size(); i++) {
				c.view.updatePawn(c.p.get(i));
			}
	}
	
	/**
	 * 
	 * Preconditions
	 * - Controller is not null
	 * Postconditions
	 * - Updates the card deck and calls view to update the window graphics 
	 * 	
	 * @param c An active instance of the controller
	 */
	
	public Card updateCards(Controller c) {
		Card card = c.deck.getTopCard();
		c.view.updateCards(card);
		return card;
	}
	
	
	/**
	 * 
	 * Preconditions
	 * - Controller is not null
	 * Postconditions
	 * - Discards the current card and refills the deck if its empty
	 * 	
	 * @param c An active instance of the controller
	 */
	
	public void fold(Controller c) {
		c.deck.foldCard();
		if(c.deck.isEmptyDeck()) {
			c.deck.refillCards();
			c.deck.Shuffle();
		}
	}
	
	
	/**
	 * 
	 * Preconditions
	 * - Controller is not null
	 * Postconditions
	 * - Updates the card deck and calls view to update the window graphics 
	 * 	
	 * @param c An active instance of the controller
	 */
	
	public boolean checkIfGameFinished(Controller c) {
		boolean ended = false;
		for (int i = 0; i < c.p.size(); i++) {
			if (c.p.get(i).finished() == true) {
				ended = true;
			}
		}

		return ended;
	}
		
	
	public static void main(String[] args) {

		Controller c = new Controller();
		c.initialize();
		c.max = c.p.size();
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(c.max);
		c.turn = randomInt;
		
		while(true) {
			c.view.updateInfo(c.p.get(c.turn), c.deck.getGameCards().size());
			c.playerRound(c, c.turn);

			if(c.checkIfGameFinished(c)) {
				break;
			}
			if(c.cfold == 1 && c.deck.getTopCard().getNum() != 2) {
				c.turn++;
				if(c.turn >= c.max) {
					c.turn = 0;
				}
				c.view.clickFold();
				c.fold(c);
			} else {
				c.fold(c);
			}
			c.view.updateInfo(c.p.get(c.turn), c.deck.getGameCards().size());
		}
		
		c.view.showEnd(c.p.get(c.turn));
		
		return;
	}

}
