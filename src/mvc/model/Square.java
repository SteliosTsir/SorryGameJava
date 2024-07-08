package mvc.model;

import javax.swing.JLabel;

public class Square {
	int num;
	JLabel sq;	
	Pawn pawn;
	
	public void setPawn(Pawn p) {
		this.pawn = p;
	}
	
	public Pawn getPawn() {
		return pawn;
	}
	
	public void setLabel(JLabel lb) {
		this.sq = lb;
	}
	
	public JLabel getLabel() {
		return sq;
	}
	
	public void setNum(int n) {
		this.num = n;
	}
	
	public int getNum() {
		return num;
	}
	
	public Square(int num) {
		this.setNum(num);
	}
	
}