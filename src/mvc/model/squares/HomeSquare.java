package mvc.model.squares;

import mvc.model.*;

public class HomeSquare extends Square {

	COLOUR colour;
	
	public HomeSquare(int num, COLOUR c) {
		super(num);
		this.setColour(c);
	}
		
	public void setColour(COLOUR c) {
		this.colour = c;
	}
	
	public COLOUR getColour() {
		return colour;
	}
	
}