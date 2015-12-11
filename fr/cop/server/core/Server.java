package fr.cop.server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Vector;

import fr.cop.common.Game;
import fr.cop.server.core.client_connection.ClientThread;
import fr.cop.server.core.commands.CommandsList;
import fr.cop.server.core.commands.MainCommand;

public class Server implements Runnable {

	private ServerSocket serverSocket;
	private int port = 163;
	public static Game serverGame;
	public static Server serverInstance;
	private static Vector<ClientThread> clients = new Vector<ClientThread>();
	private Thread internalCommandThread;
	private boolean isRunning;

	public Server() {
		printServInfo();
		serverInstance = this;
		serverGame = new Game("C:\\Conflict Of Pixels_Server\\", Game.TYPE_SERVER);
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		isRunning = true;
		internalCommandThread = new Thread(this, "Internal Command");
		internalCommandThread.start();
		while (true) {
			try {
				clients.addElement(new ClientThread(serverSocket.accept()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	synchronized public void sendAll(String message, String sLast) {
		if (sLast.equals("")) sLast = "\n";
		for (int i = 0; i < clients.size(); i++) {
			clients.elementAt(i).send(message);
		}
		System.out.println("Envoye : " + message + sLast);
	}

	public static void main(String args[]) {
		new Server();
	}

	private void printServInfo() {
		System.out.println("-----------------------------------");
		System.out.println("Conflict Of Pixels - Server Opening");
		System.out.println("-----------------------------------");
		System.out.println("Running Information :");
		try {
			System.out.println(" - IP Local : " + InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println(" - Port : " + port);
		//		System.out.println(" - RAM : " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000) + "/" + Runtime.getRuntime().totalMemory()/1000000);
		System.out.println(" - Processor avaible : " + Runtime.getRuntime().availableProcessors());
		System.out.println("-----------------------------------");
	}

	public static void delClient(int idClient) {
		clients.remove(idClient);
	}

	@Override
	public void run() {
		while (isRunning) {
			String input = "";
			try {
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				while ((input = bf.readLine()) != null) {
					input = input.toLowerCase().replace(" ", "");
					for (MainCommand command : CommandsList.getCommands()) {
						if (command.verifyValidity(input)) command.action();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
