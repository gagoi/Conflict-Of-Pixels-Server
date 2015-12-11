package fr.cop.server.core.commands;

import fr.cop.server.core.Server;

public class StopCommand extends MainCommand{

	public StopCommand() {
		super("server", "stop");
	}
	@Override
	public void action() {
		Server.serverGame.logger.logTxt("<Server>", "Exit game, from command stop");
		System.exit(0);
	}
}
