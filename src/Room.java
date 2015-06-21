import java.util.ArrayList;

import processing.core.PApplet;

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

		if (x < 0 || x >= Room.SIZE * Tile.SIZE || y < 0
				|| y >= Room.SIZE * Tile.SIZE) {
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
		ArrayList<Entity> newList = (ArrayList<Entity>) entities.clone();

		while (newList.size() > 0) {

			Entity closest = newList.get(0);
			float closestDist = PApplet.dist(closest.location.xPos,
					closest.location.yPos, parent.player.location.xPos,
					parent.player.location.yPos);

			for (int i = 0; i < newList.size(); i++) {
				Entity e = newList.get(i);
				if (PApplet.dist(e.location.xPos, e.location.yPos,
						parent.player.location.xPos,
						parent.player.location.yPos) < closestDist) {
					closest = e;
					closestDist = PApplet.dist(e.location.xPos,
							e.location.yPos, parent.player.location.xPos,
							parent.player.location.yPos);
				}
				closest.update();
				newList.remove(closest);
			}
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
