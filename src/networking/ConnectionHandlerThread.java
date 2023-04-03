package networking;
import java.io.*;
import java.net.Socket;

public class ConnectionHandlerThread extends Thread{
	private Socket socket; //socket representing TCP/IP connection to Client
	private InputStream is; //get data from client on this input stream
	private OutputStream os; //can send data back to the client on this output stream
	private BufferedReader in; //use bufferred reader to read client data
	private PrintWriter out;
	
	public ConnectionHandlerThread(Socket socket) {
		this.socket = socket;
		//establish assignments over socket's streams and how we can read/write from the data received
		try {
			is = socket.getInputStream(); //get data from client on this input stream 
			os = socket.getOutputStream(); //send data back to client on this output stream
			in = new BufferedReader(new InputStreamReader(is)); //use BufferredReader to read data
			out = new PrintWriter(os, true);
		} catch (IOException e) {
			System.out.println("ConnectionHandler: " + e.getMessage());
		} 
	}
	
	public void run() {
		System.out.println("\tNew ConnectionHandler constructed ....\n");
		try {
			displayClientMessage();
		} catch (Exception e) {
			System.out.printf("\tconnectionHandler.handleClientRequest: " + e.getMessage());
			cleanup();
		}
	}

	private void cleanup() {
		System.out.println("\t|ConnectionHandler: ...\n\t|cleaning up and exiting ...\n");
		try {
			in.close();
			is.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("\\t|ConnectionHandler:cleanup went wrong\\n");
		}
	}

	private void displayClientMessage() throws ClientDisconnectedException, IOException {
		while (true) {
			String line = in.readLine();
			if (line == null || line.equals("null") || line.equals("quit")) {
				throw new ClientDisconnectedException("\\n\\t|Client has closed the connection ...\\n ");
			}
			
			System.out.printf("\tMessage received from client <<%s>>\n", line);
			out.println("Your message has been received!");
			out.flush();
		}
	}
}
