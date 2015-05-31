package com.mtjwy.ChatApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class SimpleChatClient {
	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;
	
	String serverIP = "localhost";
	int serverPort = 4040;
	
	
	
	public void go() {
		//make gui and register a listener with the send button
		//call the setUpNetworking() method
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();
        
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        
        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setEditable(false);
        
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        mainPanel.add(outgoing);
        mainPanel.add(qScroller);
        mainPanel.add(sendButton);
        
        setUpNetwroking();
        
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
        
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(650, 500);
        frame.setVisible(true);
	}
	
	
	
	private void setUpNetwroking() {
		//make a Socket, then make a PrintWriter
		//assign the PrintWriter to writer instance variable
		try {
			sock = new Socket(serverIP, serverPort);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public class SendButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// get the text from the text field and
			//send it to the server using the writer
			try {
				writer.println(outgoing.getText());
				writer.flush();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
		
	}
	
	
	class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("client read " + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
	
	
	public static void main(String[] args) {
        new SimpleChatClient().go();
    }
	
}















