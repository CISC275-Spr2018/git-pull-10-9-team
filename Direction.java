

public enum Direction {

	NORTH("north"),
	EAST("east"),
	SOUTH("south"),
	WEST("west"),
	NORTHEAST("northeast"),
	SOUTHEAST("southeast"),
	SOUTHWEST("southwest"),
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
