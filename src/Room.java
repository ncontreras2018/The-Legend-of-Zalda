import java.util.ArrayList;

public class Room {

	int row, col;

	public static final int SIZE = 15;

	Tile tiles[][];

	ArrayList<Entity> entities;

	World world;

	Main parent;

	public Room(int r, int c, World w, Main pa) {
		row = r;
		col = c;
		world = w;

		parent = pa;

		entities = new ArrayList<Entity>();

		tiles = new Tile[SIZE][SIZE];

		setUpTiles();
	}

	public Tile getTileAt(int x, int y) {

		if (x < 0 || x >= Room.SIZE*Tile.SIZE || y < 0 || y >= Room.SIZE*Tile.SIZE) {
			return new Tile(-1, -1, null, parent);
		}

		return world.getPlayerRoom().tiles[y / Tile.SIZE][x / Tile.SIZE];
	}

	public void update() {
		for (Tile[] r : tiles) {
			for (Tile t : r) {
				t.update();
			}
		}
		for (Entity e : entities) {
			e.update();
		}
	}

	private void setUpTiles() {
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				tiles[r][c] = new Tile(r, c, this, parent);
			}
		}
	}
}
