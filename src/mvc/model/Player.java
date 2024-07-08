package mvc.model;

import mvc.model.squares.HomeSquare;

public class Player {
	Pawn[] pawns;
	String plname;
	COLOUR colour;
	
	public Player(String n, COLOUR c) {
		this.setName(n);
		this.setColour(c);
	}
	
	public void setPawns(Pawn p1, Pawn p2) {
		this.pawns = new Pawn[2];
		this.pawns[0] = p1;
		this.pawns[1] = p2;
	}
	
	public void setName(String n) {
		this.plname = n;
	}
	
	public void setColour(COLOUR c) {
		this.colour = c;
	}
	
	public Pawn[] getPawns() {
		return pawns;
	}
	
	public String getName() {
		return plname;
	}
	
	public COLOUR getColour() {
		return colour;
	}
	
	/**
	 * 
	 * Preconditions
	 * - There must be a working instance of the Player class
	 * 
	 * Postconditions
	 * - Checks if the player's pawns are both at Home Square and if they are returns true or false
	 * 
	 * @return If the Player has finished
	 */
	
	public boolean finished() {
		if((this.pawns[0].getSq() instanceof HomeSquare) && (this.pawns[1].getSq() instanceof HomeSquare)) {
			return true;
		}
		return false;
	}
}
