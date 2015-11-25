package fr.cop.server.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Vector;

import fr.cop.common.Game;
import fr.cop.common.logger.SimpleLog;
import fr.cop.server.inputs.CommandsThread;

public class Server {

	private ServerSocket serverSocket;
	private int port = 163;
	public static Game serverGame;
	
	private static Vector<ClientThread> clients = new Vector<ClientThread>();

	public Server() {
		printServInfo();
		serverGame = new Game("C:\\Conflict Of Pixels_Server\\", Game.TYPE_SERVER);
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new CommandsThread(new BufferedReader(new InputStreamReader(System.in)));
		while (true) {
			try {
				clients.addElement(new ClientThread(serverSocket.accept()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	synchronized public void sendAll(String message, String sLast) {
		PrintWriter out;
		if (sLast.equals("")) sLast = "\n";
		for (int i = 0; i < clients.size(); i++) {
			out = (PrintWriter) clients.elementAt(i).getOut();
			if (out != null) {
				out.print(message + sLast);
				out.flush();
			}
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
}
