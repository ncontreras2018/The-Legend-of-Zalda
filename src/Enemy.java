import processing.core.PApplet;

public class Enemy extends Entity {

	float attackRange;

	public Enemy(int r, int c, int x, int y, float s, float range) {
		super(r, c, x, y, s, 20);
		attackRange = range;
	}

	@Override
	public void move() {
		if (PApplet.dist(location.xPos, location.yPos,
				parent.player.location.xPos, parent.player.location.yPos) > attackRange) {

			if (Math.abs(parent.player.location.xPos - location.xPos) >= speed) {
				location.xPos += ((parent.player.location.xPos - location.xPos) / Math
						.abs(parent.player.location.xPos - location.xPos))
						* speed;
			}

			if (Math.abs(parent.player.location.yPos - location.yPos) >= speed) {
				location.yPos += ((parent.player.location.yPos - location.yPos) / Math
						.abs(parent.player.location.yPos - location.yPos))
						* speed;
			}
		}
	}

	@Override
	public void draw() {
		parent.fill(0, 200, 0);
		parent.ellipse(location.xPos, location.yPos, SIZE, SIZE);
	}

}
