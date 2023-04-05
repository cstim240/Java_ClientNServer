package networking;
import java.io.*;
import java.net.Socket;

public class ConnectionHandlerThread extends Thread{
	private Socket socket; //socket representing TCP/IP connection to Client
	private DataInputStream in; //use bufferred reader to read client data
	private DataOutputStream out;
	private static int counter = 0;
	
	public ConnectionHandlerThread(Socket socket) {
		this.socket = socket;
		//establish assignments over socket's streams and how we can read/write from the data received
		try {
			in = new DataInputStream(socket.getInputStream()); //instead of BufferredRead, we use DataInputStream to transfer primitive data
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("ConnectionHandler: " + e.getMessage());
		} 
	}
	
	public void run() {
		++counter;
		System.out.printf("\t|Connection #%d established\n", counter);
		try {
			displayClientMessage(counter);
		} catch (Exception e) {
			System.out.printf("\t|ConnectionHandler.handleClientRequest: ");
			cleanup();
		}
	}

	private void cleanup() {
		System.out.println("Connection reset: ...\n\t|cleaning up and exiting ...\n");
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("\t|ConnectionHandler: cleanup went wrong\n");
		}
	}

	private void displayClientMessage(int counter) throws ClientDisconnectedException, IOException {
		
		while (true) {
			int grade = in.readInt();
			System.out.printf("\t|Message received from client #%d <<%d>>\n", counter, grade);
			
			if (grade > 90) { //A+
				out.writeInt(Configuration.APlus);
			} else if (grade > 85) {
				out.writeInt(Configuration.A);
			} else if (grade > 80) {
				out.writeInt(Configuration.AMinus);
			} else if (grade > 77) { //B+
				out.writeInt(Configuration.BPlus);
			} else if (grade > 73) {
				out.writeInt(Configuration.B);
			} else if (grade > 70) {
				out.writeInt(Configuration.BMinus);
			} else if (grade > 67) { //C+
				out.writeInt(Configuration.CPlus);
			} else if (grade > 63) {
				out.writeInt(Configuration.C);
			} else if (grade > 60) {
				out.writeInt(Configuration.CMinus);
			} else if (grade > 50) { //D
				out.writeInt(Configuration.D);
			} else { 
				out.writeInt(Configuration.F);
			}
			
			//if (line == null || line.equals("null") || line.equals("quit")) {
				//throw new ClientDisconnectedException("\n\t|Client has closed the connection ...\n ");
			//}
			
			out.flush();
		}
		
	}
}
