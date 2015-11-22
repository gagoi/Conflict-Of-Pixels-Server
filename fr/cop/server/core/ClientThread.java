package fr.cop.server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import fr.cop.server.inputs.Command;

public class ClientThread implements Runnable {
	private Thread t;
	@SuppressWarnings("unused")
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private int idClient;
	private String ip;
	private boolean op = false;

	public ClientThread(Socket s) {
		this.s = s;
		setIp(s.getInetAddress().getHostAddress());
		try {
			out = new PrintWriter(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
		}
		System.out.println("Client : " + idClient + " is connected with ip : " + getIp());

		t = new Thread(this);
		t.start();
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
		} finally {
			System.out.println("Client " + idClient + " is deconnected.");
			Server.delClient(idClient);
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isOp() {
		return op;
	}

	public void setOp(boolean op) {
		this.op = op;
	}

	public PrintWriter getOut() {
		return out;
	}
}
