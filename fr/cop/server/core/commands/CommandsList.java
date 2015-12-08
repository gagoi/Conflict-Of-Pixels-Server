package fr.cop.server.core.commands;

public class CommandsList {

	private static MainCommand[] commands = new MainCommand[]{
			
	};
	
	
	public static MainCommand getCommandFromName(String name){
		for (MainCommand c : commands) {
			if(c.getTotalName().equals(name)) return c;
		}
		return null;
	}
	
	
	
}
