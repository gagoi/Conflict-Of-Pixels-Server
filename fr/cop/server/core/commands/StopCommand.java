package fr.cop.server.core.commands;

import fr.cop.server.core.Server;

public class StopCommand extends MainCommand{

	public StopCommand() {
		super(MainCommand.TYPE_SERVER, "stop");
	}
	@Override
	public void action() {
		Server.serverGame.logger.logTxt("<Server>", "Exit game, from command stop");
		System.exit(0);
	}

	@Override
	public void printHelp() {
		System.out.println("  - [" + getTotalName() + "] : This command stop the server without prevent anyone.");
	}}
