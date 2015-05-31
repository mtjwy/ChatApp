package com.mtjwy.ChatApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextField;

public class SimpleChatClient {
	JTextField outgoing;
	PrintWriter writer;
	Socket sock;
	
	public void go() {
		//make gui and register a listener with the send button
		//call the setUpNetworking() method
	}
	
	private void setUpNextWroking() {
		//make a Socket, then make a PrintWriter
		//assign the PrintWriter to writer instance variable
	}
	
	public class SendButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// get the text from the text field and
			//send it to the server using the writer
			
		}
		
	}
}















