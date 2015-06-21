


public class World {
	
	public static final int SIZE = 15;
	
	Room[][] rooms;
	
	public Main parent;
	
	public Player player;
	
	public World(Main pa) {
		parent = pa;
		
		rooms = new Room[SIZE][SIZE];
		
		setUpRooms();
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	private void setUpRooms() {
		for (int r = 0; r < rooms.length; r++) {
			for (int c = 0; c < rooms[r].length; c++) {
				rooms[r][c] = new Room(r, c, this, parent);
			}
		}
		
	}
	
	public Room getPlayerRoom() {
		return rooms[player.location.roomR][player.location.roomC];
	}

	public void update() {
		player.update();
		
		Room curRoom = getPlayerRoom();
		
		curRoom.update();
		
		player.draw();
	}

}
