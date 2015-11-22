package fr.cop.server.inputs;

public class Param {

	private String[] avaibleValues;

	public static final Param PLAYER_ID = new Param(0, 4);
	public static final Param ITEM_ID = new Param(0, 50);

	public Param(String[] avaibleValues) {
		this.avaibleValues = avaibleValues;
	}

	public Param(int min, int max) {
	}

	public boolean test(String value) {
		for (String val : avaibleValues) {
			if (value.equals(val)) return true;
		}
		return false;
	}

}
