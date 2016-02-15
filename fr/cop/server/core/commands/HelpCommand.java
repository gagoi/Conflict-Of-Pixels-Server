package fr.cop.server.core.commands;

import fr.cop.server.core.Game;
import fr.cop.server.core.Server;

public class HelpCommand extends MainCommand{

	public HelpCommand() {
		super(MainCommand.TYPE_SERVER, "help");
	}
	
	@Override
	public void action() {
		Server.LOGGER.logTxt("<SERVER>", "Help requested");
		System.out.println("Command List : ");
		for (MainCommand com : CommandsList.getCommandsInGoodOrganisation()) {
			com.printHelp();
		}
	}
	
	@Override
	public void printHelp() {
		System.out.println("  - [" + getTotalName() + "] : Display the help of the server, with the commands.");
	}

}
