package mvc.model.squares;

import mvc.model.*;

public class StartSquare extends Square {

	EndSlideSquare nextSq;
	COLOUR colour;
	
	public StartSquare(int num, EndSlideSquare sq, COLOUR c) {
		super(num);
		this.setNext(sq);
		this.setColour(c);
	}
	
	public void setColour(COLOUR c) {
		this.colour = c;
	}
	
	public COLOUR getColour() {
		return colour;
	}
	
	public void setNext(EndSlideSquare sq) {
		this.nextSq = sq;
	}
	
	public EndSlideSquare getNext() {
		return nextSq;
	}
}