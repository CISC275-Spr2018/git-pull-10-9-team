public enum Action {
	FORWARD("orc_forward_"),
	FIRE("orc_fire_"),
	JUMP("orc_jump_"),
	DIE("orc_die_");

	private String name = null;

	private Action(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
	public static Action[] octaDirectionalValues(){
		Action [] result = {FORWARD, FIRE,JUMP};
		return result;
	}

}