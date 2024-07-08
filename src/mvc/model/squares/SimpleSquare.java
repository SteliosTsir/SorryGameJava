package mvc.model.squares;

import mvc.model.*;

public class SimpleSquare extends Square {
	
	public SimpleSquare(int num, Pawn p) {
		super(num);
		super.setPawn(p);
	}
	
}