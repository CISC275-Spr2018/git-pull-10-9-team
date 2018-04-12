

public enum Direction {

	NORTH("north"),
	NORTHEAST("northeast"),
	EAST("east"),
	SOUTHEAST("southeast"),
	SOUTH("south"),
	SOUTHWEST("southwest"),
	WEST("west"),
	NORTHWEST("northwest");

	private String name = null;

	private Direction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
	public static Direction[] nonDiagonalDirections(){
		Direction [] result = {NORTH,EAST,SOUTH,WEST};
		return result;
	}


}
