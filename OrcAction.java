public enum OrcAction {
	FORWARD("orc_forward_"),
	FIRE("orc_fire_"),
	JUMP("orc_jump_"),
	DIE("orc_die_");

	private String name = null;

	private OrcAction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
	public  Direction[] getDirections(){
		switch (this){
			case DIE: return Direction.nonDiagonalDirections();
			default: return Direction.values();
		}
	}

}