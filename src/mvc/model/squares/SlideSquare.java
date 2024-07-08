package mvc.model.squares;

import mvc.model.*;

public class SlideSquare extends Square {

	COLOUR colour;
	
	public SlideSquare(int num, COLOUR c, Pawn p) {
		super(num);
		this.setColour(c);
		super.setPawn(p);
	}
	
	public void setColour(COLOUR c) {
		this.colour = c;
	}
	
	public COLOUR getColour() {
		return colour;
	}
	
}
