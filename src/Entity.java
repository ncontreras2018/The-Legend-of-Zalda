import processing.core.PApplet;



public abstract class Entity {
	
	Location location;
	
	float speed;
	
	boolean up, down, left, right;
	
	static Main parent;
	
	public final int SIZE;
	
	public Entity(int r, int c, int x, int y, float s, int size) {
		location = new Location(r, c, x, y);
		speed = s;
		SIZE = size;
	}
	
	public void update() {
		move();
		draw();
	}
	@Override
	public String toString() {
		return location.xPos + " " + location.yPos;
	}
	
	public abstract void move();
	
	public abstract void draw();

}
