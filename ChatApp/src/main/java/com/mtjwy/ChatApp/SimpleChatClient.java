package com.mtjwy.ChatApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleChatClient {
	JTextField outgoing;
	PrintWriter writer;
	Socket sock;
	
	public static void main(String[] args) {
        new SimpleChatClient().go();
    }
	
	public void go() {
		//make gui and register a listener with the send button
		//call the setUpNetworking() method
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();
        
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        //setUpNetworking();
        
        frame.setSize(650, 500);
        frame.setVisible(true);
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















