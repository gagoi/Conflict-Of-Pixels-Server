package fr.cop.server.core.commands;

import fr.cop.server.core.client_connection.ClientThread;
import fr.cop.server.core.commands.params.MainParam;

public class MainCommand {

	public static final String TYPE_CLIENT = "client:";
	public static final String TYPE_GAME = "game:";
	public static final String TYPE_SHOP = "shop:";
	public static final String TYPE_PROFIL = "profil:";
	public static final String TYPE_SERVER = "server:";

	private MainParam[] params;
	private String type, name;

	public MainCommand(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public MainCommand(String type, String name, MainParam param) {
		new MainCommand(type, name);
		this.params = new MainParam[] { param };
	}

	public MainCommand(String type, String name, MainParam... params) {
		new MainCommand(type, name);
		this.params = params;
	}

	public void action(ClientThread clientThread) {
		action();
	}
	
	public void action() {

	}

	public boolean verifyValidity(String input) {
		String[] splitedInput = input.split(" ");
		try {
			if (splitedInput[0].equals(getTotalName())) {
				for (int i = 1; i < splitedInput.length; i++) {
					if (!getParams()[i].test(splitedInput[i])) return false;
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public String getTotalName() {
		return type + ":" + name;
	}

	public String getType() {
		return this.type;
	}

	public MainParam[] getParams() {
		return params;
	}
}
