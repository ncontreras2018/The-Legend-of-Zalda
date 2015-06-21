import processing.core.*;

@SuppressWarnings("serial")
public class Main extends PApplet {

	public World world;

	public Player player;

	@Override
	public void setup() {
		size((Tile.SIZE * Room.SIZE) + 1, (Tile.SIZE * Room.SIZE) + 1);

		frameRate(60);

		Entity.parent = this;

		world = new World(this);
		player = new Player(this);

		world.setPlayer(player);
		
		generateEnemys(999);
		

	}

	@Override
	public void draw() {
		world.update();
	}

	private void generateEnemys(int num) {
		for (int i = 0; i < num; i++) {
			int row = player.location.roomR, col = player.location.roomC;

			while (row == player.location.roomR && col == player.location.roomC) {
				row = round(random(0, World.SIZE-1));
				col = round(random(0, World.SIZE-1));
			}

			int roomSize = Room.SIZE * Tile.SIZE;

			world.rooms[row][col].entities.add(new Enemy(row, col,
					round(random(2 * Tile.SIZE, roomSize - (2 * Tile.SIZE))),
					round(random(2 * Tile.SIZE, roomSize - (2 * Tile.SIZE))),
					1.5f, 10));
		}
	}

	@Override
	public void keyPressed() {
		if (key == 'w') {
			player.up = true;
		}
		if (key == 'a') {
			player.left = true;
		}
		if (key == 's') {
			player.down = true;
		}
		if (key == 'd') {
			player.right = true;
		}
	}

	@Override
	public void keyReleased() {
		if (key == 'w') {
			player.up = false;
		}
		if (key == 'a') {
			player.left = false;
		}
		if (key == 's') {
			player.down = false;
		}
		if (key == 'd') {
			player.right = false;
		}
	}
}
