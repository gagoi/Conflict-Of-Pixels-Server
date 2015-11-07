package fr.cop.server.core;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {

	public Server() {
		printServInfo();
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
			System.out.println(" - IP : * Local : " + InetAddress.getLocalHost().getHostAddress() + "\r        * Online : " + InetAddress.getByName("www.google.com").getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println(" - Port : ");
		//		System.out.println(" - RAM : " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1000000) + "/" + Runtime.getRuntime().totalMemory()/1000000);
		System.out.println(" - Processor avaible : " + Runtime.getRuntime().availableProcessors());
		System.out.println("-----------------------------------");
	}
}
