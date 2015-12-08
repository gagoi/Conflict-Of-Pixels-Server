package fr.cop.server.core.commands;

import fr.cop.common.Param;

public class MainCommand {

	public static final String TYPE_CLIENT = "client:";
	public static final String TYPE_GAME = "game:";
	public static final String TYPE_SHOP = "shop:";
	public static final String TYPE_PROFIL = "profil:";

	private Param[] params;
	private String type, name;

	public MainCommand(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public MainCommand(String type, String name, Param param) {
		new MainCommand(type, name);
		this.params = new Param[] { param };
	}

	public MainCommand(String type, String name, Param... params) {
		new MainCommand(type, name);
		this.params = params;
	}

	public void action() {

	}

	public boolean verifyValidity() {
		return false;

	}

	public String getTotalName() {
		return null;
	}
}
