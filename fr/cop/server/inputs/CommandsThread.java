package fr.cop.server.inputs;

import java.io.BufferedReader;
import java.io.IOException;

public class CommandsThread implements Runnable {

	private BufferedReader in;

	public CommandsThread(BufferedReader in) {
		this.in = in;
	}

	@Override
	public void run() {
		try {
			String commande = "";
			while ((commande = in.readLine()) != null) {
				for (Command command : Command.commands) {
					if(command.test(commande))
						command.use();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
