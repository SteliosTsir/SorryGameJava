package mvc.model;

import mvc.model.squares.*;

/**
 * 
 * @author Stylianos Tsirindanis
 */

public abstract class Card {
	String image;
		
	public void setImage(String im) {
		this.image = im;
	}
	
	public String getImage() {
		return image;
	}
	
	int num;
	
    public int getNum() {
        return num;
    }

    public void setNum(int n) {
        this.num = n;
    }
        
    public Card(int n, String im) {
    	this.setNum(n);
    	this.setImage(im);
    }

	public abstract boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue);
	
	public abstract boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice);
	
	public abstract boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue);

	public abstract boolean movePawn(Pawn p, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue);
}


abstract class NumberCard extends Card {
	public NumberCard(int n, String im) {
		super(n, im);
	}
}

class SorryCard extends Card {
	
	public SorryCard() {
		super(-1, "resourses/images/cards/cardSorry.png");
	}
		
	/**
	 * 
	 * Preconditions
	 * - Enemy pawn must not be in safety zone
	 * - Pawn must be in start position
	 * - Both pawns must not be NULL
	 * 
	 * Postconditions
	 * - If possible the pawn will switch places with enemy pawn
	 * - If switch happens enemy pawn will go back to start position
	 * - If switch not possible card is not played and player folds
	 * 
	 * @param p Pawn that will be moved
	 * @param p1 Enemy pawn
	 */
	
	public boolean movePawn(Pawn p, Pawn p1, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		Square currsq = p.getSq();
		if(p.getColour() == p1.getColour()) {
			return false;
		}
		if(currsq instanceof StartSquare) {
			if(!((p1.getSq() instanceof StartSquare) || (p1.getSq() instanceof SafetySquare) || (p1.getSq() instanceof HomeSquare))) {
				p.getSq().setPawn(null);
				p1.getSq().setPawn(p);
				p.setSq(p1.getSq());
			} else {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean movePawn(Pawn p, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}
	
	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}

	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

}


class NumberOneCard extends NumberCard {

	public NumberOneCard() {
		super(1, "resourses/images/cards/card1.png");
	}

	/**
	 * 
	 * Preconditions
	 * - Pawn must not be NULL
	 * - Pawn should be able to move
	 * 
	 * Postconditions
	 * - If pawn is not at start, it will move one step forward
	 * - If pawn is at start it will move to the square next to the start
	 * 
	 * @param p Pawn that will be moved
	 */
	
	public boolean movePawn(Pawn p, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		Square currsq = p.getSq();
		if(currsq instanceof StartSquare) {
			StartSquare ss = (StartSquare) currsq;
			if(ss.getNext().getPawn() != null) {
				if(ss.getNext().getPawn().getColour() == p.getColour()) {
					return false;
				}
			}
			p.getSq().setPawn(null);			
			p.setSq(ss.getNext());
			p.getSq().setPawn(p);
		} else {
			if(p.getColour() == COLOUR.Red && currsq.getNum() == 3){
				if(redsq[0].getPawn() != null && redsq[0].getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(redsq[0]);
				p.getSq().setPawn(p);			
			} else if(p.getColour() == COLOUR.Blue && currsq.getNum() == 18) {
				if(bluesq[0].getPawn() != null && bluesq[0].getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(bluesq[0]);
				p.getSq().setPawn(p);			
			}else if(p.getColour() == COLOUR.Yellow && currsq.getNum() == 33) {
				if(yellowsq[0].getPawn() != null && yellowsq[0].getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(yellowsq[0]);
				p.getSq().setPawn(p);			
			} else if(p.getColour() == COLOUR.Green && currsq.getNum() == 48) {
				if(greensq[0].getPawn() != null && greensq[0].getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(redsq[0]);
				p.getSq().setPawn(p);
			} else {
				if(currsq instanceof SafetySquare) {
					if(currsq.getNum() < 5) {
						if(p.getColour() == COLOUR.Red){
							p.setSq(redsq[currsq.getNum()]);
						} else if(p.getColour() == COLOUR.Blue) {
							p.setSq(bluesq[currsq.getNum()]);
						}else if(p.getColour() == COLOUR.Yellow) {
							p.setSq(yellowsq[currsq.getNum()]);
						} else  {
							p.setSq(greensq[currsq.getNum()]);
						}
					} else if(currsq.getNum() == 5) {
						if(p.getColour() == COLOUR.Red){
							p.setSq(homeRed);
						} else if(p.getColour() == COLOUR.Blue) {
							p.setSq(homeBlue);
						}else if(p.getColour() == COLOUR.Yellow) {
							p.setSq(homeYellow);
						} else  {
							p.setSq(homeGreen);
						}
					} else {
						return false;
					}
				} else {
					if(currsq.getNum() + 1 <= 60) {
						if(round[currsq.getNum()].getPawn() != null) {
							if(round[currsq.getNum()].getPawn().getColour() == p.getColour()) {
								return false;
							}
						}
						p.getSq().setPawn(null);			
						p.setSq(round[currsq.getNum()]);
						p.getSq().setPawn(p);
					} else {
						if(round[0].getPawn() != null) {
							if(round[0].getPawn().getColour() == p.getColour()) {
								return false;
							}
						}
						p.getSq().setPawn(null);
						p.setSq(round[0]);
						p.getSq().setPawn(p);
					}
				}
			}
		}
		return true;
	}

	public boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}
	
	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

}

class NumberTwoCard extends NumberCard {
	
	public NumberTwoCard() {
		super(2, "resourses/images/cards/card2.png");

	}
	
	/**
	 * 
	 * Preconditions
	 * - Pawn must not be NULL
	 * - Pawn should be able to move
	 * 
	 * Postconditions
	 * - If pawn is not at start, it will move two steps forward
	 * - If pawn is at start it will move to the square next to the start
	 *
	 * @param p Pawn that will be moved
	 */
	
	public boolean movePawn(Pawn p, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		Square currsq = p.getSq();
		if(currsq instanceof StartSquare) {
			StartSquare ss = (StartSquare) currsq;
			if(ss.getNext().getPawn() != null) {
				if(ss.getNext().getPawn().getColour() == p.getColour()) {
					return false;
				}
			}
			p.getSq().setPawn(null);			
			p.setSq(ss.getNext());
			p.getSq().setPawn(p);
		} else {
			Square ns = null;
			if(p.getColour() == COLOUR.Red && (currsq.getNum() == 2 || currsq.getNum() == 3)){
				if(currsq.getNum() == 2) {
					ns = redsq[0];
				} else if(currsq.getNum() == 3) {
					ns = redsq[1];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Blue && (currsq.getNum() == 17 || currsq.getNum() == 18)) {
				if(currsq.getNum() == 17) {
					ns = bluesq[0];
				} else if(currsq.getNum() == 18) {
					ns = bluesq[1];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			}else if(p.getColour() == COLOUR.Yellow && (currsq.getNum() == 32 || currsq.getNum() == 33)) {
				if(currsq.getNum() == 32) {
					ns = yellowsq[0];
				} else if(currsq.getNum() == 33) {
					ns = yellowsq[1];
				}			
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Green && (currsq.getNum() == 47 || currsq.getNum() == 48)) {
				if(currsq.getNum() == 47) {
					ns = greensq[0];
				} else if(currsq.getNum() == 48) {
					ns = greensq[1];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else {
				if(currsq instanceof SafetySquare) {
					if(currsq.getNum() < 4) {
						if(p.getColour() == COLOUR.Red){
							ns = redsq[currsq.getNum() + 1];
						} else if(p.getColour() == COLOUR.Blue) {
							ns = bluesq[currsq.getNum() + 1];
						}else if(p.getColour() == COLOUR.Yellow) {
							ns = yellowsq[currsq.getNum() + 1];
						} else {
							ns = greensq[currsq.getNum() + 1];
						}
					} else if(currsq.getNum() == 4) {
						if(p.getColour() == COLOUR.Red){
							ns = homeRed;
						} else if(p.getColour() == COLOUR.Blue) {
							ns = homeBlue;
						}else if(p.getColour() == COLOUR.Yellow) {
							ns = homeYellow;
						} else {
							ns = homeGreen;
						}
					} else {
						return false;
					}
					if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
					p.getSq().setPawn(null);			
					p.setSq(ns);
					p.getSq().setPawn(p);
				} else {
					if(currsq.getNum() + 2 <= 60) {
						if(round[currsq.getNum() + 1].getPawn() != null) {
							if(round[currsq.getNum() + 1].getPawn().getColour() == p.getColour()) {
								return false;
							}
						}
						p.getSq().setPawn(null);			
						p.setSq(round[currsq.getNum() + 1]);
						p.getSq().setPawn(p);
					} else {
						int count = 0;
						for(int i = currsq.getNum(); i < (currsq.getNum() + 1); i++) {
							count++;
						}
						if(round[count].getPawn() != null) {
							if(round[count].getPawn().getColour() == p.getColour()) {
								return false;
							}
						}
						p.getSq().setPawn(null);
						p.setSq(round[count]);						
						p.getSq().setPawn(p);
					}
				}
			}
		}
		return true;
	}
	
	public boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq,HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}
	
	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}

	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

}

class NumberThreeCard extends NumberCard {
	
	public NumberThreeCard() {
		super(3, "resourses/images/cards/card3.png");

	}
	
	/**
	 * 
	 * Preconditions
	 * - p1 and p2 must not be NULL
	 * 
	 * Postconditions
	 * - If possible one of the pawns or both will move three steps forward
	 * 
	 * @param p1 Pawn that will be moved
	 * @param p2 Pawn that will be moved
	 */
	
	public boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		int success = 0;
		if(p1.getSq() instanceof StartSquare) {
			success++;
		} else {
			Square ns = null;
			Square currsq = p1.getSq();
			if(p1.getColour() == COLOUR.Red && (currsq.getNum() == 1 || currsq.getNum() == 2 || currsq.getNum() == 3)){
				if(currsq.getNum() == 2) {
					ns = redsq[1];
				} else if(currsq.getNum() == 3) {
					ns = redsq[2];
				} else if(currsq.getNum() == 1) {
					ns = redsq[0];
				}
				if(ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p1.getSq().setPawn(null);			
					p1.setSq(ns);
					p1.getSq().setPawn(p2);
				}
			} else if(p1.getColour() == COLOUR.Blue && (currsq.getNum() == 17 || currsq.getNum() == 18 || currsq.getNum() == 19)) {
				if(currsq.getNum() == 17) {
					ns = bluesq[1];
				} else if(currsq.getNum() == 18) {
					ns = bluesq[2];
				} else if(currsq.getNum() == 16) {
					ns = bluesq[0];
				}
				if(ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p1.getSq().setPawn(null);			
					p1.setSq(ns);
					p1.getSq().setPawn(p2);
				}
			}else if(p1.getColour() == COLOUR.Yellow && (currsq.getNum() == 31 || currsq.getNum() == 32 || currsq.getNum() == 33)) {
				if(currsq.getNum() == 32) {
					ns = yellowsq[1];
				} else if(currsq.getNum() == 33) {
					ns = yellowsq[2];
				} else if(currsq.getNum() == 31) {
					ns = yellowsq[0];
				}
				if(ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p1.getSq().setPawn(null);			
					p1.setSq(ns);
					p1.getSq().setPawn(p2);
				}
			} else if(p1.getColour() == COLOUR.Green && (currsq.getNum() == 46 || currsq.getNum() == 47 || currsq.getNum() == 48)) {
				if(currsq.getNum() == 47) {
					ns = greensq[1];
				} else if(currsq.getNum() == 48) {
					ns = greensq[2];
				} else if(currsq.getNum() == 46) {
					ns = greensq[0];
				}
				if(ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p1.getSq().setPawn(null);			
					p1.setSq(ns);
					p1.getSq().setPawn(p2);
				}
			} else {
				if(currsq instanceof SafetySquare) {
					if(currsq.getNum() < 3) {
						if(p1.getColour() == COLOUR.Red){
							ns = redsq[currsq.getNum() + 2];
						} else if(p1.getColour() == COLOUR.Blue) {
							ns = bluesq[currsq.getNum() + 2];
						}else if(p1.getColour() == COLOUR.Yellow) {
							ns = yellowsq[currsq.getNum() + 2];
						} else  {
							ns = greensq[currsq.getNum() + 2];
						}
					} else if(currsq.getNum() == 3){
						if(p1.getColour() == COLOUR.Red){
							ns = homeRed;
						} else if(p1.getColour() == COLOUR.Blue) {
							ns = homeBlue;
						}else if(p1.getColour() == COLOUR.Yellow) {
							ns = homeYellow;
						} else {
							ns = homeGreen;
						}
					} else {
						success++;
					}
					if(ns == null || ns.getPawn().getColour() == p1.getColour()) {
						success++;
					} else {
						p1.getSq().setPawn(null);			
						p1.setSq(ns);
						p1.getSq().setPawn(p1);
					}
				} else {
					boolean skip = false;
					if(currsq.getNum() + 3 <= 60) {
						if(roundsquares[currsq.getNum() + 2].getPawn() != null) {
							if(roundsquares[currsq.getNum() + 2].getPawn().getColour() == p1.getColour()) {
								skip = true;
							}
						} 
						if(skip == false) {
							p1.getSq().setPawn(null);			
							p1.setSq(roundsquares[currsq.getNum() + 2]);
							p1.getSq().setPawn(p1);
						}
					} else {
						int count = 0;
						for(int i = currsq.getNum(); i < (currsq.getNum() + 3); i++) {
							count++;
						}
						if(roundsquares[count].getPawn() != null) {
							if(roundsquares[count].getPawn().getColour() == p1.getColour()) {
								skip = true;
							}
						}
						if(skip == false) {
							p1.getSq().setPawn(null);
							p1.setSq(roundsquares[count]);						
							p1.getSq().setPawn(p1);
						}
					}
					if(skip == true) {
						success++;
					}
				}
			}
		}

		if(p2.getSq() instanceof StartSquare) {
			success++;
		} else {
			Square ns = null;
			Square currsq = p2.getSq();
			if(p2.getColour() == COLOUR.Red && (currsq.getNum() == 1 || currsq.getNum() == 2 || currsq.getNum() == 3)){
				if(currsq.getNum() == 2) {
					ns = redsq[1];
				} else if(currsq.getNum() == 3) {
					ns = redsq[2];
				} else if(currsq.getNum() == 1) {
					ns = redsq[0];
				}
				if(ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p2.getSq().setPawn(null);			
					p2.setSq(ns);
					p2.getSq().setPawn(p2);
				}
			} else if(p2.getColour() == COLOUR.Blue && (currsq.getNum() == 17 || currsq.getNum() == 18 || currsq.getNum() == 19)) {
				if(currsq.getNum() == 17) {
					ns = bluesq[1];
				} else if(currsq.getNum() == 18) {
					ns = bluesq[2];
				} else if(currsq.getNum() == 16) {
					ns = bluesq[0];
				}
				if(ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p2.getSq().setPawn(null);			
					p2.setSq(ns);
					p2.getSq().setPawn(p2);
				}
			}else if(p2.getColour() == COLOUR.Yellow && (currsq.getNum() == 31 || currsq.getNum() == 32 || currsq.getNum() == 33)) {
				if(currsq.getNum() == 32) {
					ns = yellowsq[1];
				} else if(currsq.getNum() == 33) {
					ns = yellowsq[2];
				} else if(currsq.getNum() == 31) {
					ns = yellowsq[0];
				}
				if(ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p2.getSq().setPawn(null);			
					p2.setSq(ns);
					p2.getSq().setPawn(p2);
				}
			} else if(p2.getColour() == COLOUR.Green && (currsq.getNum() == 46 || currsq.getNum() == 47 || currsq.getNum() == 48)) {
				if(currsq.getNum() == 47) {
					ns = greensq[1];
				} else if(currsq.getNum() == 48) {
					ns = greensq[2];
				} else if(currsq.getNum() == 46) {
					ns = greensq[0];
				}
				if(ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p2.getSq().setPawn(null);			
					p2.setSq(ns);
					p2.getSq().setPawn(p2);
				}
			} else {
				if(currsq instanceof SafetySquare) {
					if(currsq.getNum() < 3) {
						if(p2.getColour() == COLOUR.Red){
							ns = redsq[currsq.getNum() + 2];
						} else if(p2.getColour() == COLOUR.Blue) {
							ns = bluesq[currsq.getNum() + 2];
						}else if(p2.getColour() == COLOUR.Yellow) {
							ns = yellowsq[currsq.getNum() + 2];
						} else  {
							ns = greensq[currsq.getNum() + 2];
						}
					} else if(currsq.getNum() == 3){
						if(p2.getColour() == COLOUR.Red){
							ns = homeRed;
						} else if(p2.getColour() == COLOUR.Blue) {
							ns = homeBlue;
						}else if(p2.getColour() == COLOUR.Yellow) {
							ns = homeYellow;
						} else {
							ns = homeGreen;
						}
					} else {
						success++;
					}
					if(ns == null || ns.getPawn().getColour() == p2.getColour()) {
						success++;
					} else {
						p2.getSq().setPawn(null);			
						p2.setSq(ns);
						p2.getSq().setPawn(p2);
					}
				} else {
					boolean skip = false;
					if(currsq.getNum() + 3 <= 60) {
						if(roundsquares[currsq.getNum() + 2].getPawn() != null) {
							if(roundsquares[currsq.getNum() + 2].getPawn().getColour() == p2.getColour()) {
								skip = true;
							}
						} 
						if(skip == false) {
							p2.getSq().setPawn(null);			
							p2.setSq(roundsquares[currsq.getNum() + 2]);
							p2.getSq().setPawn(p2);
						}
					} else {
						int count = 0;
						for(int i = currsq.getNum(); i < (currsq.getNum() + 3); i++) {
							count++;
						}
						if(roundsquares[count].getPawn() != null) {
							if(roundsquares[count].getPawn().getColour() == p2.getColour()) {
								skip = true;
							}
						}
						if(skip == false) {
							p2.getSq().setPawn(null);
							p2.setSq(roundsquares[count]);						
							p2.getSq().setPawn(p2);
						}
					}
					if(skip == true) {
						success++;
					}
				}
			}
		}
		
		if(success >= 2) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean movePawn(Pawn p1, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}
	
	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}

	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

}

class NumberFourCard extends NumberCard {
	
	public NumberFourCard() {
		super(4, "resourses/images/cards/card4.png");

	}
	
	/**
	 * 
	 * Preconditions
	 * - Pawn must not be NULL
	 * - Pawn should be able to move
	 * 
	 * Postconditions
	 * - If possible the pawn will move four steps back
	 * 
	 * @param p Pawn that will be moved
	 */
	
	public boolean movePawn(Pawn p, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		Square currsq = p.getSq();
		if(currsq instanceof StartSquare) {
			return false;
		} else if(currsq instanceof SafetySquare) {
			Square ns = null;
			if(p.getColour() == COLOUR.Red) {
				if(currsq.getNum() == 5) {
					ns = redsq[0];
				} else if(currsq.getNum() == 4) {
					ns = round[2];
				} else if(currsq.getNum() == 3) {
					ns = round[1];
				} else if(currsq.getNum() == 2) {
					ns = round[0];
				} else if(currsq.getNum() == 1) {
					ns = round[59];
				}
			} else if(p.getColour() == COLOUR.Blue) {
				if(currsq.getNum() == 5) {
					ns = bluesq[0];
				} else if(currsq.getNum() == 4) {
					ns = round[17];
				} else if(currsq.getNum() == 3) {
					ns = round[16];
				} else if(currsq.getNum() == 2) {
					ns = round[15];
				} else if(currsq.getNum() == 1) {
					ns = round[14];
				}
			}else if(p.getColour() == COLOUR.Yellow) {
				if(currsq.getNum() == 5) {
					ns = yellowsq[0];
				} else if(currsq.getNum() == 4) {
					ns = round[32];
				} else if(currsq.getNum() == 3) {
					ns = round[31];
				} else if(currsq.getNum() == 2) {
					ns = round[30];
				} else if(currsq.getNum() == 1) {
					ns = round[29];
				}
			} else if(p.getColour() == COLOUR.Green) {
				if(currsq.getNum() == 5) {
					ns = greensq[0];
				} else if(currsq.getNum() == 4) {
					ns = round[47];
				} else if(currsq.getNum() == 3) {
					ns = round[46];
				} else if(currsq.getNum() == 2) {
					ns = round[45];
				} else if(currsq.getNum() == 1) {
					ns = round[44];
				}
			}
			if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
		} else {
			Square ns = null;
			if(p.getSq().getNum() > 4) {
				ns = round[p.getSq().getNum() - 5];
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);			
			} else {
				ns = round[60 - (5 - p.getSq().getNum())];
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);			
			}
		}
		return true;
	}
	
	public boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}
	
	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}

	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

}

class NumberFiveCard extends NumberCard {
	
	public NumberFiveCard() {
		super(5, "resourses/images/cards/card5.png");

	}
	
	/**
	 * 
	 * Preconditions
	 * - p1 and p2 must not be NULL
	 * 
	 * Postconditions
	 * - If possible one of the pawns or both will move five steps forward
	 * 
	 * @param p1 Pawn that will be moved
	 * @param p2 Pawn that will be moved
	 */
	
	public boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		int success = 0;
		if(p1.getSq() instanceof StartSquare) {
			success++;
		} else {
			Square ns = null;
			Square currsq = p1.getSq();
			if(p1.getColour() == COLOUR.Red && (currsq.getNum() == 59 || currsq.getNum() == 60 || (currsq.getNum() >= 1 && currsq.getNum() <= 3))) {
				if(currsq.getNum() == 2) {
					ns = redsq[3];
				} else if(currsq.getNum() == 3) {
					ns = redsq[4];
				} else if(currsq.getNum() == 1) {
					ns = redsq[2];
				} else if(currsq.getNum() == 60) {
					ns = redsq[1];
				} else if(currsq.getNum() == 59) {
					ns = redsq[0];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p1.getColour()) {
					success++;
				} else {
					p1.getSq().setPawn(null);			
					p1.setSq(ns);
					p1.getSq().setPawn(p2);
				}
			} else if(p1.getColour() == COLOUR.Blue && (currsq.getNum() >= 14 && currsq.getNum() <= 18)) {
				if(currsq.getNum() == 17) {
					ns = bluesq[3];
				} else if(currsq.getNum() == 18) {
					ns = bluesq[4];
				} else if(currsq.getNum() == 16) {
					ns = bluesq[2];
				} else if(currsq.getNum() == 15) {
					ns = bluesq[1];
				} else if(currsq.getNum() == 14) {
					ns = bluesq[0];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p1.getColour()) {
					success++;
				} else {
					p1.getSq().setPawn(null);			
					p1.setSq(ns);
					p1.getSq().setPawn(p1);
				}
			}else if(p1.getColour() == COLOUR.Yellow && (currsq.getNum() >= 29 && currsq.getNum() <= 33)) {
				if(currsq.getNum() == 32) {
					ns = yellowsq[3];
				} else if(currsq.getNum() == 33) {
					ns = yellowsq[4];
				} else if(currsq.getNum() == 31) {
					ns = yellowsq[2];
				} else if(currsq.getNum() == 30) {
					ns = yellowsq[1];
				} else if(currsq.getNum() == 29) {
					ns = yellowsq[0];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p1.getColour()) {
					success++;
				} else {
					p1.getSq().setPawn(null);			
					p1.setSq(ns);
					p1.getSq().setPawn(p1);
				}
			} else if(p1.getColour() == COLOUR.Green && (currsq.getNum() >= 44 && currsq.getNum() <= 48)) {
				if(currsq.getNum() == 47) {
					ns = greensq[3];
				} else if(currsq.getNum() == 48) {
					ns = greensq[4];
				} else if(currsq.getNum() == 46) {
					ns = greensq[2];
				} else if(currsq.getNum() == 45) {
					ns = greensq[1];
				} else if(currsq.getNum() == 44) {
					ns = greensq[0];
				}
				if(ns.getPawn() != null && ns.getPawn() != null && ns.getPawn().getColour() == p1.getColour()) {
					success++;
				} else {
					p1.getSq().setPawn(null);			
					p1.setSq(ns);
					p1.getSq().setPawn(p1);
				}
			} else {
				if(currsq instanceof SafetySquare) {
					if(currsq.getNum() == 1) {
						if(p1.getColour() == COLOUR.Red){
							p1.setSq(homeRed);
						} else if(p1.getColour() == COLOUR.Blue) {
							p1.setSq(homeBlue);
						}else if(p1.getColour() == COLOUR.Yellow) {
							p1.setSq(homeYellow);
						} else  {
							p1.setSq(homeGreen);
						}
					} else {
						success++;
					}
				} else {
					if(p1.getSq().getNum() + 5 <= 60) {
						ns = roundsquares[p1.getSq().getNum() + 4];
						if(ns.getPawn() != null && ns.getPawn().getColour() == p1.getColour()) {
							success++;
						} else {
							p1.setSq(ns);
						}
					} else {
						int count = 0;
						for(int i = p1.getSq().getNum(); i < (p1.getSq().getNum() + 5); i++) {
							count++;
						}
						if(roundsquares[count].getPawn() != null && roundsquares[count].getPawn().getColour() == p1.getColour()) {
							success++;
						} else {
							p1.setSq(roundsquares[count]);
						}
					}
				}			
			}
		}

		if(p2.getSq() instanceof StartSquare) {
			success++;
		} else {
			Square ns = null;
			Square currsq = p2.getSq();
			if(p2.getColour() == COLOUR.Red && (currsq.getNum() == 59 || currsq.getNum() == 60 || (currsq.getNum() >= 1 && currsq.getNum() <= 3))) {
				if(currsq.getNum() == 2) {
					ns = redsq[3];
				} else if(currsq.getNum() == 3) {
					ns = redsq[4];
				} else if(currsq.getNum() == 1) {
					ns = redsq[2];
				} else if(currsq.getNum() == 60) {
					ns = redsq[1];
				} else if(currsq.getNum() == 59) {
					ns = redsq[0];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p2.getSq().setPawn(null);			
					p2.setSq(ns);
					p2.getSq().setPawn(p2);
				}
			} else if(p2.getColour() == COLOUR.Blue && (currsq.getNum() >= 14 && currsq.getNum() <= 18)) {
				if(currsq.getNum() == 17) {
					ns = bluesq[3];
				} else if(currsq.getNum() == 18) {
					ns = bluesq[4];
				} else if(currsq.getNum() == 16) {
					ns = bluesq[2];
				} else if(currsq.getNum() == 15) {
					ns = bluesq[1];
				} else if(currsq.getNum() == 14) {
					ns = bluesq[0];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p2.getSq().setPawn(null);			
					p2.setSq(ns);
					p2.getSq().setPawn(p2);
				}
			}else if(p2.getColour() == COLOUR.Yellow && (currsq.getNum() >= 29 && currsq.getNum() <= 33)) {
				if(currsq.getNum() == 32) {
					ns = yellowsq[3];
				} else if(currsq.getNum() == 33) {
					ns = yellowsq[4];
				} else if(currsq.getNum() == 31) {
					ns = yellowsq[2];
				} else if(currsq.getNum() == 30) {
					ns = yellowsq[1];
				} else if(currsq.getNum() == 29) {
					ns = yellowsq[0];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p2.getSq().setPawn(null);			
					p2.setSq(ns);
					p2.getSq().setPawn(p2);
				}
			} else if(p2.getColour() == COLOUR.Green && (currsq.getNum() >= 44 && currsq.getNum() <= 48)) {
				if(currsq.getNum() == 47) {
					ns = greensq[3];
				} else if(currsq.getNum() == 48) {
					ns = greensq[4];
				} else if(currsq.getNum() == 46) {
					ns = greensq[2];
				} else if(currsq.getNum() == 45) {
					ns = greensq[1];
				} else if(currsq.getNum() == 44) {
					ns = greensq[0];
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p2.getColour()) {
					success++;
				} else {
					p2.getSq().setPawn(null);			
					p2.setSq(ns);
					p2.getSq().setPawn(p2);
				}
			} else {
				if(currsq instanceof SafetySquare) {
					if(currsq.getNum() == 1) {
						if(p2.getColour() == COLOUR.Red){
							p2.setSq(homeRed);
						} else if(p2.getColour() == COLOUR.Blue) {
							p2.setSq(homeBlue);
						}else if(p2.getColour() == COLOUR.Yellow) {
							p2.setSq(homeYellow);
						} else  {
							p2.setSq(homeGreen);
						}
					} else {
						success++;
					}
				} else {
					if(p2.getSq().getNum() + 5 <= 60) {
						ns = roundsquares[p2.getSq().getNum() + 4];
						if(ns.getPawn() != null && ns.getPawn().getColour() == p2.getColour()) {
							success++;
						} else {
							p2.setSq(ns);
						}
					} else {
						int count = 0;
						for(int i = p2.getSq().getNum(); i < (p2.getSq().getNum() + 5); i++) {
							count++;
						}
						if(roundsquares[count].getPawn() != null && roundsquares[count].getPawn().getColour() == p2.getColour()) {
							success++;
						} else {
							p2.setSq(roundsquares[count]);
						}
					}
				}			
			}
		}
		
		if(success >= 2) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}
	
	public boolean movePawn(Pawn p1, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}
	
	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

}

class NumberSevenCard extends NumberCard {
	
	public NumberSevenCard() {
		super(7, "resourses/images/cards/card7.png");

	}
	
	/**
	 * 
	 * Preconditions
	 * - p1, p2, s1 and s2 must not be NULL
	 * - Player pawns should both be able to move the steps they have to move
	 * 
	 * Postconditions
	 * - If possible the pawns will move to as many steps as the player wanted
	 * 
	 * @param p1 Pawn that will be moved
	 * @param p2 Pawn that will be moved
	 * @param move An array of integers containing the steps each pawn should move
	 */
	
	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		int movepOne = move[0];
		int movepTwo = move[1];
		
		int currsqOne = p1.getSq().getNum() - 1;
		int currsqTwo = p2.getSq().getNum() - 1;
		
		int ret = 0;
		Square ns = p1.getSq();
		
		if(currsqOne == -1 || currsqOne == -2) {
			ret++;
		} else {
			for(int i = 0; i < movepOne; i++) {
				if(!(ns instanceof SafetySquare)) {
					currsqOne++;
					if(currsqOne < 60 && currsqOne != 17 && currsqOne != 47 && currsqOne != 2 && currsqOne != 32) {
						ns = round[currsqOne];
					} else if(currsqOne == 60) {
						currsqOne = 0;
						ns = round[currsqOne];
					} else {
						currsqOne = 0;
						if(p1.getColour() == COLOUR.Red) {
							ns = redsq[0];
						} else if(p1.getColour() == COLOUR.Blue) {
							ns = bluesq[0];
						} else if(p1.getColour() == COLOUR.Yellow) {
							ns = yellowsq[0];
						} else {
							ns = greensq[0];
						}
					}
				} else {
					currsqOne++;
					if(currsqOne <= 4) {
						if(p1.getColour() == COLOUR.Red) {
							ns = redsq[currsqOne - 1];
						} else if(p1.getColour() == COLOUR.Blue) {
							ns = bluesq[currsqOne - 1];
						} else if(p1.getColour() == COLOUR.Yellow) {
							ns = yellowsq[currsqOne - 1];
						} else {
							ns = greensq[currsqOne - 1];
						}
					} else if(currsqOne == 5) {
						if(p1.getColour() == COLOUR.Red) {
							ns = homeRed;
						} else if(p1.getColour() == COLOUR.Blue) {
							ns = homeBlue;
						} else if(p1.getColour() == COLOUR.Yellow) {
							ns = homeYellow;
						} else {
							ns = homeGreen;
						}
					} else {
						ns = null;
					}
				}
			}
			if(ns == null) {
				ret++;
			} else {
				if(ns instanceof HomeSquare) {
					p1.getSq().setPawn(null);			
					p1.setSq(ns);
				} else {
					if(ns.getPawn() != null && ns.getPawn().getColour() == p1.getColour()) {
						ret++;
					} else {
						p1.getSq().setPawn(null);			
						p1.setSq(ns);
						p1.getSq().setPawn(p1);
					}
				}
			}
		}

		if(ret != 0) {
			return false;
		}
		
		ns = p2.getSq();
		
		
		if(currsqTwo == -1 || currsqTwo == -2) {
			ret++;
		} else {
			for(int i = 0; i < movepTwo; i++) {
				currsqTwo++;
				if(!(ns instanceof SafetySquare)) {
					if(currsqTwo < 60 && currsqTwo != 17 && currsqTwo != 47&& currsqTwo != 2 && currsqTwo != 32) {
						ns = round[currsqTwo];
					} else if(currsqTwo == 60) {
						currsqTwo = 0;
						ns = round[currsqTwo];
					} else {
						currsqTwo = 0;
						if(p2.getColour() == COLOUR.Red) {
							ns = redsq[0];
						} else if(p2.getColour() == COLOUR.Blue) {
							ns = bluesq[0];
						} else if(p2.getColour() == COLOUR.Yellow) {
							ns = yellowsq[0];
						} else {
							ns = greensq[0];
						}
					}
				} else {
					currsqTwo++;
					if(currsqTwo <= 4) {
						if(p2.getColour() == COLOUR.Red) {
							ns = redsq[currsqTwo - 1];
						} else if(p2.getColour() == COLOUR.Blue) {
							ns = bluesq[currsqTwo - 1];
						} else if(p2.getColour() == COLOUR.Yellow) {
							ns = yellowsq[currsqTwo - 1];
						} else {
							ns = greensq[currsqTwo - 1];
						}
					} else if(currsqTwo == 5) {
						if(p2.getColour() == COLOUR.Red) {
							ns = homeRed;
						} else if(p2.getColour() == COLOUR.Blue) {
							ns = homeBlue;
						} else if(p2.getColour() == COLOUR.Yellow) {
							ns = homeYellow;
						} else {
							ns = homeGreen;
						}
					} else {
						ns = null;
					}
				}
			}
			if(ns == null) {
				ret++;
			} else {
				if(ns instanceof HomeSquare) {
					p2.getSq().setPawn(null);			
					p2.setSq(ns);
				} else {
					if(ns.getPawn() != null && ns.getPawn().getColour() == p2.getColour()) {
						ret++;
					} else {
						p2.getSq().setPawn(null);			
						p2.setSq(ns);
						p2.getSq().setPawn(p2);
					}
				}
			}
		}
		
		if(ret >= 2) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * Preconditions
	 * - Pawn must not be NULL
	 * - Pawn should be able to move
	 * 
	 * Postconditions
	 * - If possible the pawn will move seven steps forward
	 * 
	 * @param p Pawn that will be moved
	 */
	
	public boolean movePawn(Pawn p, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		Square currsq = p.getSq();
		if(currsq instanceof StartSquare) {
			return false;
		} else {
			Square ns = null;
			if(p.getColour() == COLOUR.Red && currsq.getNum() >= 57 && currsq.getNum() <= 61){
				if(currsq.getNum() == 1 && currsq instanceof SimpleSquare) {
					ns = redsq[4];
				} else if(currsq.getNum() == 60) {
					ns = redsq[3];
				} else if(currsq.getNum() == 59) {
					ns = redsq[2];
				} else if(currsq.getNum() == 58) {
					ns = redsq[1];
				} else if(currsq.getNum() == 57) {
					ns = redsq[0];
				} else if(currsq.getNum() == 2 && currsq instanceof StartSlideSquare) {
					ns = homeRed;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Blue && currsq.getNum() >= 12 && currsq.getNum() <= 16) {
				if(currsq.getNum() == 16) {
					ns = bluesq[4];
				} else if(currsq.getNum() == 15) {
					ns = bluesq[3];
				} else if(currsq.getNum() == 14) {
					ns = bluesq[2];
				} else if(currsq.getNum() == 13) {
					ns = bluesq[1];
				} else if(currsq.getNum() == 12) {
					ns = bluesq[0];
				} else if(currsq.getNum() == 17) {
					ns = homeBlue;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			}else if(p.getColour() == COLOUR.Yellow && currsq.getNum() >= 27 && currsq.getNum() <= 31) {
				if(currsq.getNum() == 31) {
					ns = yellowsq[4];
				} else if(currsq.getNum() == 30) {
					ns = yellowsq[3];
				} else if(currsq.getNum() == 29) {
					ns = yellowsq[2];
				} else if(currsq.getNum() == 28) {
					ns = yellowsq[1];
				} else if(currsq.getNum() == 27) {
					ns = yellowsq[0];
				} else if(currsq.getNum() == 32) {
					ns = homeYellow;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Green && (currsq.getNum() >= 42 && currsq.getNum() <= 46)) {
				if(currsq.getNum() == 46) {
					ns = greensq[4];
				} else if(currsq.getNum() == 45) {
					ns = greensq[3];
				} else if(currsq.getNum() == 44) {
					ns = greensq[2];
				} else if(currsq.getNum() == 43) {
					ns = greensq[1];
				} else if(currsq.getNum() == 42) {
					ns = greensq[0];
				} else if(currsq.getNum() == 47) {
					ns = homeGreen;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else {
				if(currsq instanceof SafetySquare) {
				} else {
					if(currsq.getNum() + 7 <= 60) {
						if(round[currsq.getNum() + 6].getPawn() != null && round[currsq.getNum() + 6].getPawn().getColour() == p.getColour()) return false;
						p.getSq().setPawn(null);			
						p.setSq(round[currsq.getNum() + 6]);
						p.getSq().setPawn(p);
					} else {
						int count = 6 - (60 - p.getSq().getNum());
						if(round[count].getPawn() != null && round[count].getPawn().getColour() == p.getColour()) return false;
						p.getSq().setPawn(null);			
						p.setSq(round[count]);
						p.getSq().setPawn(p);
					}
				}
			}
		}
		return true;
	}
		
	public boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}

}

class NumberEightCard extends NumberCard {
	
	public NumberEightCard() {
		super(8, "resourses/images/cards/card8.png");

	}
	
	/**
	 * 
	 * Preconditions
	 * - Pawn must not be NULL
	 * - Pawn should be able to move
	 * 
	 * Postconditions
	 * - If possible the pawn will move eight steps forward
	 * 
	 * @param p Pawn that will be moved
	 */
	
	public boolean movePawn(Pawn p, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		Square currsq = p.getSq();
		if(currsq instanceof StartSquare) {
			return false;
		} else {
			Square ns = null;
			if(p.getColour() == COLOUR.Red && currsq.getNum() >= 56 && currsq.getNum() <= 60 || (currsq.getNum() == 5 && currsq instanceof SimpleSquare)){
				if(currsq.getNum() == 60) {
					ns = redsq[4];
				} else if(currsq.getNum() == 59) {
					ns = redsq[3];
				} else if(currsq.getNum() == 58) {
					ns = redsq[2];
				} else if(currsq.getNum() == 57) {
					ns = redsq[1];
				} else if(currsq.getNum() == 56) {
					ns = redsq[0];
				} else if(currsq.getNum() == 5 && currsq instanceof SimpleSquare) {
					ns = homeRed;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Blue && currsq.getNum() >= 11 && currsq.getNum() <= 16) {
				if(currsq.getNum() == 15) {
					ns = bluesq[4];
				} else if(currsq.getNum() == 14) {
					ns = bluesq[3];
				} else if(currsq.getNum() == 13) {
					ns = bluesq[2];
				} else if(currsq.getNum() == 12) {
					ns = bluesq[1];
				} else if(currsq.getNum() == 11) {
					ns = bluesq[0];
				} else if(currsq.getNum() == 16) {
					ns = homeBlue;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			}else if(p.getColour() == COLOUR.Yellow && currsq.getNum() >= 26 && currsq.getNum() <= 31) {
				if(currsq.getNum() == 30) {
					ns = yellowsq[4];
				} else if(currsq.getNum() == 29) {
					ns = yellowsq[3];
				} else if(currsq.getNum() == 28) {
					ns = yellowsq[2];
				} else if(currsq.getNum() == 27) {
					ns = yellowsq[1];
				} else if(currsq.getNum() == 26) {
					ns = yellowsq[0];
				} else if(currsq.getNum() == 31) {
					ns = homeYellow;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Green && (currsq.getNum() >= 41 && currsq.getNum() <= 46)) {
				if(currsq.getNum() == 45) {
					ns = greensq[4];
				} else if(currsq.getNum() == 44) {
					ns = greensq[3];
				} else if(currsq.getNum() == 43) {
					ns = greensq[2];
				} else if(currsq.getNum() == 42) {
					ns = greensq[1];
				} else if(currsq.getNum() == 41) {
					ns = greensq[0];
				} else if(currsq.getNum() == 46) {
					ns = homeGreen;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else {
				if(currsq instanceof SafetySquare) {
				} else {
					if(currsq.getNum() + 8 <= 60) {
						if(round[currsq.getNum() + 7].getPawn() != null && round[currsq.getNum() + 7].getPawn().getColour() == p.getColour()) return false;
						p.getSq().setPawn(null);			
						p.setSq(round[currsq.getNum() + 7]);
						p.getSq().setPawn(p);
					} else {
						int count = 0;
						for(int i = p.getSq().getNum(); i < (p.getSq().getNum() + 8); i++) {
							count++;
						}
						if(round[count].getPawn() != null && round[count].getPawn().getColour() == p.getColour()) return false;
						p.getSq().setPawn(null);			
						p.setSq(round[count]);
						p.getSq().setPawn(p);
					}
				}
			}
		}
		return true;
	}
	
	public boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}
	
	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}

	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

}

class NumberTenCard extends NumberCard {
	
	public NumberTenCard() {
		super(10, "resourses/images/cards/card10.png");

	}
	
	/**
	 * 
	 * Preconditions
	 * - Pawn and Square must not be NULL
	 * - Square should be 10 steps in front of pawn or one step back depending on the player's choice
	 * - Pawn should be able to move to the Square
	 * 
	 * Postconditions
	 * - If possible the pawn will move to the given square
	 * 
	 * @param p Pawn that will be moved
	 * @param choice An integer representing the player choice ( 0 or 1 ) 
	 */

	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {
		Square currsq = p.getSq();
		boolean check = false;
		if(choice == 0) {
			if(currsq instanceof StartSquare) {
				return false;
			} else {
				Square ns = null;
				if(p.getColour() == COLOUR.Red && currsq.getNum() >= 54 && currsq.getNum() <= 59){
					if(currsq.getNum() == 58) {
						check = true;
						ns = redsq[4];
					} else if(currsq.getNum() == 57) {
						check = true;
						ns = redsq[3];
					} else if(currsq.getNum() == 56) {
						check = true;
						ns = redsq[2];
					} else if(currsq.getNum() == 55) {
						check = true;
						ns = redsq[1];
					} else if(currsq.getNum() == 54) {
						check = true;
						ns = redsq[0];
					} else if(currsq.getNum() == 59) {
						ns = homeRed;
					}
					if(ns instanceof HomeSquare) {
						p.getSq().setPawn(null);			
						p.setSq(ns);
					}
					if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
					p.getSq().setPawn(null);			
					p.setSq(ns);
					p.getSq().setPawn(p);
				} else if(p.getColour() == COLOUR.Blue && currsq.getNum() >= 9 && currsq.getNum() <= 14) {
					if(currsq.getNum() == 13) {
						check = true;
						ns = bluesq[4];
					} else if(currsq.getNum() == 12) {
						check = true;
						ns = bluesq[3];
					} else if(currsq.getNum() == 11) {
						check = true;
						ns = bluesq[2];
					} else if(currsq.getNum() == 10) {
						check = true;
						ns = bluesq[1];
					} else if(currsq.getNum() == 9) {
						check = true;
						ns = bluesq[0];
					} else if(currsq.getNum() == 14) {
						ns = homeBlue;
					}
					if(ns instanceof HomeSquare) {
						p.getSq().setPawn(null);			
						p.setSq(ns);
					}
					if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
					p.getSq().setPawn(null);			
					p.setSq(ns);
					p.getSq().setPawn(p);
				}else if(p.getColour() == COLOUR.Yellow && currsq.getNum() >= 24 && currsq.getNum() <= 29) {
					if(currsq.getNum() == 28) {
						check = true;
						ns = yellowsq[4];
					} else if(currsq.getNum() == 27) {
						check = true;
						ns = yellowsq[3];
					} else if(currsq.getNum() == 26) {
						check = true;
						ns = yellowsq[2];
					} else if(currsq.getNum() == 25) {
						check = true;
						ns = yellowsq[1];
					} else if(currsq.getNum() == 24) {
						check = true;
						ns = yellowsq[0];
					}else if(currsq.getNum() == 29) {
						ns = homeYellow;
					}
					if(ns instanceof HomeSquare) {
						p.getSq().setPawn(null);			
						p.setSq(ns);
					}
					if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
					p.getSq().setPawn(null);			
					p.setSq(ns);
					p.getSq().setPawn(p);
				} else if(p.getColour() == COLOUR.Green && (currsq.getNum() >= 39 && currsq.getNum() <= 44)) {
					if(currsq.getNum() == 43) {
						check = true;
						ns = greensq[4];
					} else if(currsq.getNum() == 42) {
						check = true;
						ns = greensq[3];
					} else if(currsq.getNum() == 41) {
						check = true;
						ns = greensq[2];
					} else if(currsq.getNum() == 40) {
						check = true;
						ns = greensq[1];
					} else if(currsq.getNum() == 39) {
						check = true;
						ns = greensq[0];
					} else if(currsq.getNum() == 44) {
						ns = homeGreen;
					}
					if(ns instanceof HomeSquare) {
						p.getSq().setPawn(null);			
						p.setSq(ns);
					}
					if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
					p.getSq().setPawn(null);			
					p.setSq(ns);
					p.getSq().setPawn(p);
				} else {
					if(currsq instanceof SafetySquare) {
					} else {
						check = true;
						if(currsq.getNum() + 10 <= 60) {
							if(roundsquares[currsq.getNum() + 9].getPawn() != null && roundsquares[currsq.getNum() + 9].getPawn().getColour() == p.getColour()) return false;
							p.setSq(roundsquares[currsq.getNum() + 9]);
						} else {
							int count = 0;
							for(int i = p.getSq().getNum(); i < (p.getSq().getNum() + 10); i++) {
								count++;
							}
							if(roundsquares[count].getPawn() != null && roundsquares[count].getPawn().getColour() == p.getColour()) return false;
							p.setSq(roundsquares[count]);
						}
					}
				}
			}
			return check;
		} else {
			if(currsq instanceof StartSquare) {
				return false;
			} else if(currsq instanceof SafetySquare) {
				Square ns = null;
				if(p.getSq().getNum() == 1) {
					if(p.getColour() == COLOUR.Red) {
						ns = roundsquares[59];
					} else if(p.getColour() == COLOUR.Blue) {
						ns = roundsquares[14];
					}else if(p.getColour() == COLOUR.Yellow) {
						ns = roundsquares[29];
					} else if(p.getColour() == COLOUR.Green) {
						ns = roundsquares[44];
					}
				} else {
					if(p.getColour() == COLOUR.Red) {
						ns = redsq[p.getSq().getNum() - 2];
					} else if(p.getColour() == COLOUR.Blue) {
						ns = bluesq[p.getSq().getNum() - 2];
					}else if(p.getColour() == COLOUR.Yellow) {
						ns = yellowsq[p.getSq().getNum() - 2];
					} else if(p.getColour() == COLOUR.Green) {
						ns = greensq[p.getSq().getNum() - 2];
					}
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
					p.getSq().setPawn(null);			
					p.setSq(ns);
					p.getSq().setPawn(p);
			} else {
				Square ns = null;
				if(p.getSq().getNum() > 1) {
					ns = roundsquares[p.getSq().getNum() - 2];
					if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
					p.getSq().setPawn(null);			
					p.setSq(ns);
					p.getSq().setPawn(p);
				} else {
					ns = roundsquares[60 - (2 - p.getSq().getNum())];
					if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
					p.getSq().setPawn(null);			
					p.setSq(ns);
					p.getSq().setPawn(p);
				}
			}
		}
		return true;
	}
		
	public boolean movePawn(Pawn p, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}
	
	public boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

}

class NumberElevenCard extends NumberCard {
	
	public NumberElevenCard() {
		super(11, "resourses/images/cards/card11.png");

	}
	
	/**
	 * 
	 * Preconditions
	 * - Pawn and enemyPawn must not be NULL
	 * - Pawn should be able to move
	 * - Enemy pawn must not be at Start, Home or Safety Zone
	 * - Pawn must not be at Home or Start 
	 * 
	 * Postconditions
	 * - If possible the pawn will move to enemy pawn's square
	 * - Enemy pawn will go to start
	 * 
	 * @param p Pawn that will be moved
	 * @param enemyPawn The enemy pawn that p will be moved to
	 */
	
	public boolean movePawn(Pawn p, Pawn enemyPawn, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq ,HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		if(!((enemyPawn.getSq() instanceof SafetySquare) || (enemyPawn.getSq() instanceof StartSquare) || (enemyPawn.getSq() instanceof HomeSquare) || (p.getSq() instanceof HomeSquare) || (p.getSq() instanceof StartSquare) || (p.getSq() instanceof SafetySquare))) {
			Square temp = p.getSq();
			temp.setPawn(null);
			p.setSq(enemyPawn.getSq());
			enemyPawn.getSq().setPawn(p);
			enemyPawn.setSq(temp);
			temp.setPawn(enemyPawn);
			return true;
		}
		return false;
	}
	
	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		Square currsq = p.getSq();
		if(currsq instanceof StartSquare) {
			return false;
		} else {
			Square ns = null;
			if(p.getColour() == COLOUR.Red && currsq.getNum() >= 53 && currsq.getNum() <= 58){
				if(currsq.getNum() == 57) {
					ns = redsq[4];
				} else if(currsq.getNum() == 56) {
					ns = redsq[3];
				} else if(currsq.getNum() == 55) {
					ns = redsq[2];
				} else if(currsq.getNum() == 54) {
					ns = redsq[1];
				} else if(currsq.getNum() == 53) {
					ns = redsq[0];
				} else if(currsq.getNum() == 58) {
					ns = homeRed;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Blue && currsq.getNum() >= 8 && currsq.getNum() <= 13) {
				if(currsq.getNum() == 12) {
					ns = bluesq[4];
				} else if(currsq.getNum() == 11) {
					ns = bluesq[3];
				} else if(currsq.getNum() == 10) {
					ns = bluesq[2];
				} else if(currsq.getNum() == 9) {
					ns = bluesq[1];
				} else if(currsq.getNum() == 8) {
					ns = bluesq[0];
				} else if(currsq.getNum() == 13) {
					ns = homeBlue;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			}else if(p.getColour() == COLOUR.Yellow && currsq.getNum() >= 23 && currsq.getNum() <= 28) {
				if(currsq.getNum() == 27) {
					ns = yellowsq[4];
				} else if(currsq.getNum() == 26) {
					ns = yellowsq[3];
				} else if(currsq.getNum() == 25) {
					ns = yellowsq[2];
				} else if(currsq.getNum() == 24) {
					ns = yellowsq[1];
				} else if(currsq.getNum() == 23) {
					ns = yellowsq[0];
				} else if(currsq.getNum() == 28) {
					ns = homeYellow;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Green && (currsq.getNum() >= 38 && currsq.getNum() <= 43)) {
				if(currsq.getNum() == 42) {
					ns = greensq[4];
				} else if(currsq.getNum() == 41) {
					ns = greensq[3];
				} else if(currsq.getNum() == 40) {
					ns = greensq[2];
				} else if(currsq.getNum() == 39) {
					ns = greensq[1];
				} else if(currsq.getNum() == 38) {
					ns = greensq[0];
				} else if(currsq.getNum() == 43) {
					ns = homeGreen;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else {
				if(!(currsq instanceof SafetySquare)) {
					if(currsq.getNum() + 11 <= 60) {
						if(roundsquares[currsq.getNum() + 10].getPawn() != null && roundsquares[currsq.getNum() + 10].getPawn().getColour() == p.getColour()) return false;
						p.getSq().setPawn(null);			
						p.setSq(roundsquares[currsq.getNum() + 10]);
						p.getSq().setPawn(p);
					} else {
						int count = 0;
						for(int i = p.getSq().getNum(); i < (p.getSq().getNum() + 11); i++) {
							count++;
						}
						if(roundsquares[count].getPawn() != null && roundsquares[count].getPawn().getColour() == p.getColour()) return false;
						p.getSq().setPawn(null);			
						p.setSq(roundsquares[count]);
						p.getSq().setPawn(p);
					}
				}
			}
		}
		return true;
	}

	
	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return false;}

	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}
	
}

class NumberTwelveCard extends NumberCard {
	
	public NumberTwelveCard() {
		super(12, "resourses/images/cards/card12.png");

	}
	
	/**
	 * 
	 * Preconditions
	 * - Pawn must not be NULL
	 * - Pawn should be able to move
	 * 
	 * Postconditions
	 * - If possible the pawn will move twelve steps forward
	 * 
	 * @param p Pawn that will be moved
	 */
		
	public boolean movePawn(Pawn p, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {
		Square currsq = p.getSq();
		if(currsq instanceof StartSquare) {
			return false;
		} else {
			Square ns = null;
			if(p.getColour() == COLOUR.Red && currsq.getNum() >= 52 && currsq.getNum() <= 57){
				if(currsq.getNum() == 56) {
					ns = redsq[4];
				} else if(currsq.getNum() == 55) {
					ns = redsq[3];
				} else if(currsq.getNum() == 54) {
					ns = redsq[2];
				} else if(currsq.getNum() == 53) {
					ns = redsq[1];
				} else if(currsq.getNum() == 52) {
					ns = redsq[0];
				} else if(currsq.getNum() == 57) {
					ns = homeRed;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Blue && currsq.getNum() >= 7 && currsq.getNum() <= 12) {
				if(currsq.getNum() == 11) {
					ns = bluesq[4];
				} else if(currsq.getNum() == 10) {
					ns = bluesq[3];
				} else if(currsq.getNum() == 9) {
					ns = bluesq[2];
				} else if(currsq.getNum() == 8) {
					ns = bluesq[1];
				} else if(currsq.getNum() == 7) {
					ns = bluesq[0];
				} else if(currsq.getNum() == 12) {
					ns = homeBlue;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			}else if(p.getColour() == COLOUR.Yellow && currsq.getNum() >= 22 && currsq.getNum() <= 27) {
				if(currsq.getNum() == 26) {
					ns = yellowsq[4];
				} else if(currsq.getNum() == 25) {
					ns = yellowsq[3];
				} else if(currsq.getNum() == 24) {
					ns = yellowsq[2];
				} else if(currsq.getNum() == 23) {
					ns = yellowsq[1];
				} else if(currsq.getNum() == 22) {
					ns = yellowsq[0];
				} else if(currsq.getNum() == 27) {
					ns = homeYellow;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else if(p.getColour() == COLOUR.Green && (currsq.getNum() >= 37 && currsq.getNum() <= 42)) {
				if(currsq.getNum() == 41) {
					ns = greensq[4];
				} else if(currsq.getNum() == 40) {
					ns = greensq[3];
				} else if(currsq.getNum() == 39) {
					ns = greensq[2];
				} else if(currsq.getNum() == 38) {
					ns = greensq[1];
				} else if(currsq.getNum() == 37) {
					ns = greensq[0];
				} else if(currsq.getNum() == 42) {
					ns = homeGreen;
				}
				if(ns instanceof HomeSquare) {
					p.getSq().setPawn(null);			
					p.setSq(ns);
				}
				if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
				p.getSq().setPawn(null);			
				p.setSq(ns);
				p.getSq().setPawn(p);
			} else {
				if(currsq instanceof SafetySquare) {
				} else {
					if(currsq.getNum() + 12 <= 60) {
						ns = round[currsq.getNum() + 11];
						if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
						p.getSq().setPawn(null);			
						p.setSq(ns);
						p.getSq().setPawn(p);
					} else {
						int count = 0;
						for(int i = p.getSq().getNum(); i < (p.getSq().getNum() + 12); i++) {
							count++;
						}
						ns = round[count];
						if(ns.getPawn() != null && ns.getPawn().getColour() == p.getColour()) return false;
						p.getSq().setPawn(null);			
						p.setSq(ns);
						p.getSq().setPawn(p);
					}
				}
			}
		}
		return true;
	}
	
	public boolean movePawn(Pawn p1, Pawn p2, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}
	
	public boolean movePawn(Pawn p, Square[] roundsquares, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue, int choice) {return true;}

	public boolean movePawn(Pawn p1, Pawn p2, int[] move, Square[] round, Square[] greensq, Square[] redsq, Square[] yellowsq, Square[] bluesq, HomeSquare homeRed, HomeSquare homeYellow, HomeSquare homeGreen, HomeSquare homeBlue) {return true;}

}

