package data;

public class Position {
	
	private int x;	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	private int y;

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Position() {
		
	}
	
	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public boolean equals(Position obj) {
		if (obj == null) return false;
		if (x != obj.getX()) return false;
		if (y != obj.getY()) return false;
		return true;
	}
	
	public Position clone() {
		return new Position(this.x, this.y);
	}
	
	public Position add(int x, int y) {
		Position cloned = this.clone();
		cloned.setX(x + this.x);
		cloned.setY(y + this.y);
		return cloned;
	}
	
	public Position add(Position position) {
		return this.add(position.getX(), position.getY());
	}
	
	public Position subtract(int x, int y) {
		Position cloned = this.clone();
		cloned.setX(x - this.x);
		cloned.setY(y - this.y);
		return cloned;
	}
	
	public Position subtract(Position position) {
		return this.subtract(position.getX(), position.getY());
	} 
	
	public String toString() {
		return "(" + x + ";" + y + ")";
	}
}
