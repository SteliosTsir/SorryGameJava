package mvc.model;

public class Pawn {
	COLOUR colour;
	int pawnID;
	Square sq;
	Player owner;
	
	public Pawn(int id, Player p, COLOUR c, Square sq) {
		this.setId(id);
		this.setOwner(p);
		this.setColour(c);
		this.setSq(sq);
	}
	
	public void setColour(COLOUR c) {
		this.colour = c;
	}
	
	public COLOUR getColour() {
		return colour;
	}
	
	public void setId(int id) {
		this.pawnID = id;
	}
	
	public void setSq(Square sq) {
		this.sq = sq;
	}
	
	public void setOwner(Player pl) {
		this.owner = pl;
	}
	
	public int getId() {
		return pawnID;
	}
	
	public Square getSq() {
		return sq;
	}
	
	public Player getOwner() {
		return owner;
	}
}
