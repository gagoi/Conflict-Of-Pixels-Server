package fr.cop.server.core.client_connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import fr.cop.server.core.Game;
import fr.cop.server.core.Server;
import fr.cop.server.core.commands.CommandsList;
import fr.cop.server.core.commands.MainCommand;

/**
 * Un clientThread, est oomme son nom l'indique, un processus s�par� du reste du serveur.
 * Cela permet de l'ex�cuter en paral�lle � tout le reste (client et invit� de commande).
 * <p>
 * Il poss�de plusieurs attributs :
 * <ul>
 * <li>@param socket Permet d'�couter et d'envoyer des informations depuis/vers la machine du client.</li>
 * <li>@param id Permet au serveur de savoir qui est qui dans la liste des clients conn�ct�s. Ce nombre change � chauqe connexion/d�connexion/</li>
 * </ul>
 */
public class ClientThread implements Runnable {

	private int id; // Id du client non fixe, se refere a son placement dans la liste des clients.
	private Thread t; // Thread dans lequel ce client sera trait�.
	private Socket socket; // Connection "materiel" avec le client.

	public ClientThread(Socket clientSocket) {
		this.socket = clientSocket;

		Server.LOGGER.logTxt("<ClientThread:INFO>", "Client : " + id + " is connected with ip : " + socket.getInetAddress().getHostAddress());

		t = new Thread(this, "Client_Thread");
		t.start();
	}

	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input = "";
			while ((input = bf.readLine()) != null) {
				Server.LOGGER.logTxt("<INPUT>", input);

				System.out.println("false");
				for (MainCommand command : CommandsList.getCommands()) {
					if (command.verifyValidity(input)) {
						System.out.println("true");
						command.action(this);
					}
				}
			}
		} catch (IOException e) {
			Server.LOGGER.logErr("<ClientThread:ERROR>", "Client : " + id + " -- ip : " + socket.getInetAddress().getHostAddress());
		} finally {
			Server.LOGGER.logTxt("<SERVER>", "Client " + id + " is deconnected.");
			Server.delClient(id);
		}

	}

	public void send(String command) {
		System.out.println("Try Sending to " + id + " ; " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(command + "\n");
			bw.flush();
			Server.LOGGER.logTxt("<Sender:Send>", command);
		} catch (IOException e) {
			Server.LOGGER.logTxt("<Sender:Error>", "Client non connect�....");
		}
	}
}
