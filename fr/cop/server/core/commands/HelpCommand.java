package fr.cop.server.core.commands;

import fr.cop.server.core.Server;

public class HelpCommand extends MainCommand{

	public HelpCommand() {
		super(MainCommand.TYPE_SERVER, "help");
	}
	
	@Override
	public void action() {
		Server.serverGame.logger.logTxt("<SERVER>", "Help requested");
		System.out.println("Command List : ");
		for (MainCommand com : CommandsList.getCommandsInGoodOrganisation()) {
			com.printHelp();
		}
	}

}
