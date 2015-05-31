package com.mtjwy.ChatApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleChatServer {

	ArrayList<PrintWriter> clientOutputStreams;
	int serverPort = 4040;

	public class ClientHandler implements Runnable {
		BufferedReader reader;
		Socket sock;

		public ClientHandler(Socket clientSOcket) {
			try {
				sock = clientSOcket;
				InputStreamReader isReader = new InputStreamReader(
						sock.getInputStream());
				reader = new BufferedReader(isReader);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void run() {
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					System.out.println("read: " + message);
					tellEveryone(message);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	

	public void go() {
		clientOutputStreams = new ArrayList<PrintWriter>();
		try {
			ServerSocket serverSock = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(
						clientSocket.getOutputStream());
				clientOutputStreams.add(writer);

				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println("got a connection");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void tellEveryone(String message) {
		Iterator<PrintWriter> it = clientOutputStreams.iterator();
		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new SimpleChatServer().go();
	}

}
