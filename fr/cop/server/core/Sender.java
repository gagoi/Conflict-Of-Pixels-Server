package fr.cop.server.core;

import java.io.IOException;
import java.io.OutputStreamWriter;

import fr.cop.common.Game;

public class Sender {
	private OutputStreamWriter out;

	public Sender(OutputStreamWriter out) {
		this.out = out;
	}

	public void send(String command) {

		try {
			out.write(command + "\n");
			out.flush();
			Game.logger.logTxt("<Sender:Send>", command);
		} catch (IOException e) {
			Game.logger.logTxt("<Sender:Error>", "Client non connecté....");
		}
	}
}
