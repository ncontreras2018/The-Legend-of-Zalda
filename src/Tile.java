

public class Tile {

	public static final int SIZE = 40;

	int row, col;

	Room room;

	Main parent;

	boolean wall, door;

	public Tile(int r, int c, Room ro, Main pa) {
		row = r;
		col = c;
		parent = pa;
		room = ro;

		if (row == 0 || row == Room.SIZE - 1 || col == 0
				|| col == Room.SIZE - 1) {
			wall = true;
		}

		setUpDoors();

	}

	private void setUpDoors() {

		if (wall == true) {

			if (row == Room.SIZE / 2 && col == 0) {
				if (room.col != 0) {
					wall = false;
					door = true;
				}
			}
			if (row == Room.SIZE / 2 && col == Room.SIZE - 1) {
				if (room.col != World.SIZE-1) {
					wall = false;
					door = true;
				}
			}
			if (row == 0 && col == Room.SIZE / 2) {
				if (room.row != 0) {
					wall = false;
					door = true;
				}
			}
			if (row == Room.SIZE - 1 && col == Room.SIZE / 2) {
				if (room.row != World.SIZE-1) {
					wall = false;
				}
			}
		}
	}

	public void draw() {
		parent.fill(255);

		if (wall) {
			parent.fill(100);
		}
		parent.rect(col * SIZE, row * SIZE, SIZE, SIZE);
	}

	public void update() {
		if (door) {
			checkForExit();
		}
		draw();
	}

	private void checkForExit() {

		switch (parent.player.movingOffEdge()) {

		case 1:
			parent.player.location = new Location(room.row - 1, room.col,
					parent.player.location.xPos, (Room.SIZE * Tile.SIZE)
							- Tile.SIZE);
			break;
		case 2:
			parent.player.location = new Location(room.row, room.col - 1,
					(Room.SIZE * Tile.SIZE) - Tile.SIZE,
					parent.player.location.yPos);
			break;
		case 3:
			parent.player.location = new Location(room.row + 1, room.col,
					parent.player.location.xPos, 0);
			break;
		case 4:
			parent.player.location = new Location(room.row, room.col + 1, 0,
					parent.player.location.yPos);
			break;
		}
	}
}
