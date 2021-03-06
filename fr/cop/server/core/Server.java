package fr.cop.server.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.text.SimpleAttributeSet;

import fr.cop.common.logger.SimpleLog;
import fr.cop.server.core.client_connection.ClientThread;
import fr.cop.server.core.commands.CommandsList;
import fr.cop.server.core.commands.MainCommand;

public class Server implements Runnable {

	private ServerSocket serverSocket;
	private int port = 1630;
	
	public static Game serverGame;
	public static Server serverInstance;
	public static final File GAME_FOLDER = new File("C:\\Conflict Of Pixels_Server\\");
	public static final SimpleLog LOGGER = new SimpleLog("C:\\Conflict Of Pixels_Server\\");
	
	
	private static Vector<ClientThread> clients = new Vector<ClientThread>();
	private Thread internalCommandThread;
	private boolean isRunning;

	public Server() {
		printServInfo();
		serverInstance = this;
		serverGame = new Game();
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
					boolean isValid = false;
					for (MainCommand command : CommandsList.getCommands()) {
						if (command.verifyValidity(input)) {
							isValid = true;
							command.action();
						}
					}
					if (!isValid) System.out.println("Error, command doesn't exist, type \"server:help\" in order to have the list of the valid commands.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
