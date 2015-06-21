import processing.core.PApplet;

public class Enemy extends Entity {

	float attackRange;

	static Player player;

	public Enemy(int r, int c, int x, int y, float s, float range) {
		super(r, c, x, y, s, 20);
		attackRange = range;
		player = parent.player;
	}

	public Enemy(int r, int c, float s, float range) {
		super(r, c, PApplet.round(parent.random(2 * Tile.SIZE,
				(Room.SIZE * Tile.SIZE) - (2 * Tile.SIZE))), PApplet
				.round(parent.random(2 * Tile.SIZE, (Room.SIZE * Tile.SIZE)
						- (2 * Tile.SIZE))), s, 20);
		attackRange = range;
		player = parent.player;
		
		while (otherEntityInWay(location.xPos, location.yPos)) {
			location.xPos = PApplet.round(parent.random(2 * Tile.SIZE,
					(Room.SIZE * Tile.SIZE) - (2 * Tile.SIZE)));
			
			location.yPos = PApplet.round(parent.random(2 * Tile.SIZE,
					(Room.SIZE * Tile.SIZE) - (2 * Tile.SIZE)));
		}
	}

	public boolean otherEntityInWay(int x, int y) {
		for (Entity e : parent.world.getPlayerRoom().entities) {
			if (x >= e.location.xPos && x <= e.location.xPos + e.SIZE) {
				if (y >= e.location.yPos && y <= e.location.yPos + e.SIZE) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void move() {

		int newX = location.xPos, newY = location.yPos;

		if (PApplet.dist(location.xPos, location.yPos, player.location.xPos,
				player.location.yPos) > attackRange) {

			if (location.xPos - player.location.xPos >= speed) {
				left = true;
				newX += -speed;
			} else {
				left = false;
			}
			if (location.xPos - player.location.xPos <= -speed) {
				right = true;
				newX += speed;
			} else {
				right = false;
			}
			if (location.yPos - player.location.yPos <= -speed) {
				down = true;
				newY += speed;
			} else {
				down = false;
			}
			if (location.yPos - player.location.yPos >= speed) {
				up = true;
				newY += -speed;
			} else {
				up = false;
			}
		}

		if (!otherEntityInWay(newX, newY)) {
			location.xPos = newX;
			location.yPos = newY;
		}
	}

	@Override
	public void draw() {
		parent.fill(0, 200, 0);
		parent.ellipse(location.xPos, location.yPos, SIZE, SIZE);
	}

}
