package fr.cop.server.core.commands;

import fr.cop.common.network.params.MainParam;
import fr.cop.server.core.client_connection.ClientThread;

public class MainCommand {

	/*
	 * Type de commandes permettant d'organiser ces dernieres.
	 */
	public static final String TYPE_CLIENT = "client:";
	public static final String TYPE_GAME = "game:";
	public static final String TYPE_SHOP = "shop:";
	public static final String TYPE_PROFIL = "profil:";
	public static final String TYPE_SERVER = "server:";

	private MainParam[] params; // Tableau contenant les paramètres nécessaire de cette commande.
	private String type, name; // Type et nom de la commande.

	/*
	 * Créer une commande basique n'ayant aucun parametre.
	 * @param type, doit etre une des variables statiques finales de la classe.
	 */
	public MainCommand(String type, String name) {
		this.type = type; 
		this.name = name;
	}
	
	/*
	 * Créer une commande basique n'ayant qu'un parametre.
	 * @param type, doit etre une des variables statiques finales de la classe.
	 */
	public MainCommand(String type, String name, MainParam param) {
		this.type = type;
		this.name = name;
		this.params = new MainParam[] { param };
	}
	
	/*
	 * Créer une commande basique ayant plusieurs parametres.
	 * @param type, doit etre une des variables statiques finales de la classe.
	 */
	public MainCommand(String type, String name, MainParam... params) {
		this.type = type;
		this.name = name;
		this.params = params;
	}

	/*
	 * Méthode devant être réécrite dans le cas d'une commande visany d'un client.
	 * Elle sera appelée lorsque la commande sera utilisée.
	 */
	public void action(ClientThread clientThread) {
	}
	/*
	 * Méthode devant être réécrite dans le cas d'une commande ne visant pas d'un client.
	 * Elle sera appelée lorsque la commande sera utilisée.
	 */
	public void action() {
	}
	
	/*
	 * Méthode vérifiant que la commande correspond à l'input.
	 */
	public boolean verifyValidity(String input) {
		String[] splitedInput = input.split(" "); // On separe les parametres.
		try {
			if (splitedInput[0].equals(getTotalName())) { // Si le nom de la ommande correspond.
//				for (int i = 1; i < splitedInput.length; i++) { // On verifie tous les parametres.
//					if (!getParams()[i].test(splitedInput[i])) return false; // Si un difere on renvoie que la commande n'est pas bonne.
//				} 
				return true; // Sinon elle est bonne.
			}
			return false; // Si le nom n'est pas bon, on renvoie false:
		} catch (Exception e) { // Si une erreur est levee.
			return false; // On renvoie false.
		}
	}

	/*
	 * Fonction permettant de renvoyer le nom complet d'une commande.
	 */
	public String getTotalName() {
		return this.type + this.name;
	}
	
	/*
	 * Fonction permettant de renvoyer le type d'une commande.
	 */
	public String getType() {
		return this.type;
	}

	/*
	 * Fonction permettant de renvoyer les parametres d'une commande.
	 */
	public MainParam[] getParams() {
		return params;
	}

	/*
	 * Fonction permettant d'afficher l'aide de cette commande.
	 */
	public void printHelp() {
		System.out.println("  - ["+ getTotalName()+ "] : Help not found. You should contact the developpers in order to request an update.");
	}
}
