package data;

public enum Orientation {
	HORIZONTAL (1, 0),
	VERTICAL (0, 1);
	
	private final int x;
	private final int y;
	
	Orientation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position next(Position position) {
		return position.add(x, y);
	}
	
	public Position next(Position position, int times) {
		if (times < 0) {
			times = Math.abs(times);
			return this.previous(position, times);
		}
		
		while (times --> 0) {
			position = position.add(x, y);
		}
		
		return position;
	}
	
	public Position previous(Position position) {
		return position.subtract(x, y);
	}
	
	public Position previous(Position position, int times) {
		if (times < 0) {
			times = Math.abs(times);
			return this.next(position, times);
		}
		
		while (times --> 0) {
			position = position.subtract(x, y);
		}
		
		return position;
	}
	
	public Orientation getInverse() {
		return this == HORIZONTAL ? VERTICAL : HORIZONTAL;
	}
}
