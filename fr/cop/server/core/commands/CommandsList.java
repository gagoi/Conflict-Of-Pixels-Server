package fr.cop.server.core.commands;

import java.util.ArrayList;

public class CommandsList {

	
	/*
	 * Tableau contenant toutes les commandes que le serveur peut recevoir.
	 * */
	private static MainCommand[] commands = new MainCommand[] {
			new StopCommand(), // Commande d'arret du serveur.
			new RequestConnection(), // Commande de demande de connection de la part d'un client.
	};

	
	/*
	 * Fonction permettant de récupérer une commande depuis son nom.
	 */
	public static MainCommand getCommandFromName(String name) {
		for (MainCommand c : commands) {
			if (c.getTotalName().equals(name)) return c;
		}
		return null;
	}
	
	/*
	 * Fonction permmettant de récuperer toutes les commandes d'un type. 
	 */
	public static MainCommand[] getCommandsOf(String type) {
		ArrayList<MainCommand> typeCom = new ArrayList<MainCommand>();
		
		for (MainCommand c : commands) {
			if(c.getType().equals(type)) typeCom.add(c);
		}
		
		return (MainCommand[]) typeCom.toArray();
	}
	
	/*
	 * Fonction permettant de récupérer toutes les commandes.
	 */
	public static MainCommand[] getCommands(){
		return commands;
	}

}
