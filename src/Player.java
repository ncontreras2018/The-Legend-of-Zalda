import processing.core.*;

public class Player extends Entity {

	World world;

	PApplet parent;

	boolean up, down, left, right;

	public Player(World w, PApplet pa) {
		super(World.SIZE /2 , World.SIZE / 2, 100, 100, 2, 20);
		
		location.xPos = ((Room.SIZE * Tile.SIZE) / 2) - SIZE/2;
		location.yPos = ((Room.SIZE * Tile.SIZE) / 2) - SIZE/2;
				
		world = w;
		parent = pa;
	}

	public void moveTo(int rR, int rC, int tR, int tC) {
		location.roomR = rR;
		location.roomC = rC;

		location.xPos = tC * Tile.SIZE;
		location.yPos = tR * Tile.SIZE;
	}

	@Override
	public void move() {
		if (up && allowedToMove("up")) {
			location.yPos += -speed;
		}
		if (left && allowedToMove("left")) {
			location.xPos += -speed;
		}
		if (down && allowedToMove("down")) {
			location.yPos += speed;
		}
		if (right && allowedToMove("right")) {
			location.xPos += speed;
		}
	}

	public Tile tileLocation() {
		return world.getPlayerRoom().tiles[(location.yPos + (Tile.SIZE / 2))
				/ Tile.SIZE][(location.xPos + (Tile.SIZE / 2)) / Tile.SIZE];
	}

	public int movingOffEdge() {
		if (location.xPos <= -1) {
			return 2;
		}
		if (location.xPos > (Room.SIZE * Tile.SIZE) - SIZE) {
			return 4;
		}
		if (location.yPos <= -1) {
			return 1;
		}
		if (location.yPos > (Room.SIZE * Tile.SIZE) - SIZE) {
			return 3;
		}
		return 0;
	}

	@Override
	public void draw() {
		parent.fill(0, 200, 0);
		parent.rect(location.xPos, location.yPos, SIZE, SIZE);
	}
	private boolean allowedToMove(String dir) {

		if (dir.equals("up")) {
			if (world.getPlayerRoom().getTileAt(location.xPos,
					PApplet.round(location.yPos - speed)).wall == true) {
				return false;
			}
			if (world.getPlayerRoom().getTileAt(location.xPos + SIZE,
					PApplet.round(location.yPos - speed)).wall == true) {
				return false;
			}
			if (world.getPlayerRoom().getTileAt(location.xPos,
					PApplet.round(location.yPos + SIZE - speed)).wall == true) {
				return false;
			}
		}
		if (dir.equals("left")) {
			if (world.getPlayerRoom().getTileAt(
					PApplet.round(location.xPos - speed), location.yPos).wall == true) {
				return false;
			}
			if (world.getPlayerRoom().getTileAt(
					PApplet.round(location.xPos - speed), location.yPos + SIZE).wall == true) {
				return false;
			}
		}
		if (dir.equals("down")) {
			if (world.getPlayerRoom().getTileAt(location.xPos,
					PApplet.round(location.yPos + SIZE)).wall == true) {
				return false;
			}
			if (world.getPlayerRoom().getTileAt(location.xPos + SIZE,
					PApplet.round(location.yPos + SIZE)).wall == true) {
				return false;
			}
		}
		if (dir.equals("right")) {

			if (world.getPlayerRoom().getTileAt(
					PApplet.round(location.xPos + SIZE), location.yPos).wall == true) {
				return false;
			}
			if (world.getPlayerRoom().getTileAt(
					PApplet.round(location.xPos + SIZE), location.yPos + SIZE).wall == true) {
				return false;
			}
		}
		return true;
	}
}
