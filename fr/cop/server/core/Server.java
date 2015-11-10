package fr.cop.server.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Server {

	private ServerSocket serverSocket;
	private int port = 163;
	
	public Server() {
		printServInfo();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new Server();
	}

	public void printServInfo() {
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
}
