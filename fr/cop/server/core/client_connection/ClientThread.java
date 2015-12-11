package fr.cop.server.core.client_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import fr.cop.common.Game;
import fr.cop.server.core.Server;
import fr.cop.server.core.commands.CommandsList;
import fr.cop.server.core.commands.MainCommand;

/**
 * Un clientThread, est oomme son nom l'indique, un processus séparé du reste du serveur.
 * Cela permet de l'exécuter en paralèlle à tout le reste (client et invité de commande).
 * <p>
 * Il possède plusieurs attributs :
 * <ul>
 * <li>@param socket Permet d'écouter et d'envoyer des informations depuis/vers la machine du client.</li>
 * <li>@param id Permet au serveur de savoir qui est qui dans la liste des clients connéctés. Ce nombre change à chauqe connexion/déconnexion/</li>
 * </ul>
 */
public class ClientThread implements Runnable {

	private int id; // Id du client non fixe, se refere a son placement dans la liste des clients.
	private Thread t; // Thread dans lequel ce client sera traité.
	private Socket socket; // Connection "materiel" avec le client.

	public ClientThread(Socket clientSocket) {
		this.socket = clientSocket;

		Game.logger.logTxt("<ClientThread:INFO>", "Client : " + id + " is connected with ip : " + socket.getInetAddress().getHostAddress());

		t = new Thread(this, "Client_Thread");
		t.start();
	}

	@Override
	public void run() {
		try {
			String input = "";
			while ((input = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine()) != null) {
				Server.serverGame.logger.logTxt("<INPUT>", input);

				for (MainCommand command : CommandsList.getCommands()) {
					if (command.verifyValidity(input)) command.action(this);
				}
			}
		} catch (IOException e) {
			Game.logger.logErr("<ClientThread:ERROR>", "Client : " + id + " -- ip : " + socket.getInetAddress().getHostAddress());
		} finally {
			Game.logger.logTxt("<SERVER>", "Client " + id + " is deconnected.");
			Server.delClient(id);
		}

	}

	public void send(String command) {
		try {
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
			out.write(command);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
