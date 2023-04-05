package networking;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/** <h1> Lab 7 - Java Networking - Client and Server </h1>
 * <p>
 * Description: Modify the networking classes to enable server to computer letter grade 
 * and have the server display its communication with clients.
 * The Client should be able to send any grade between 0 and 100, disconnect using -1, 
 * and display its communication with the server.
 * </p>
 * <h2> CPSC 1181 - 002 </h2>
 * @author Tim Supan
 * @see <a href="https://docs.oracle.com/en/java/"> Java Documentation </a> 
 * @version 18.0.1.1
 * @since 2023-04-04
 */

public class Client {
	//--Instance vars----\\
	private Socket socket;
	private String host;
	private int port;
	private DataInputStream in; //data input stream that lets an application read primitive Java data types from underlying input stream
	private DataOutputStream out;
	
	
	Client(String host, int port){
		this.host = host;
		this.port = port;
		requestService();
	}
	
	private void requestService() { //connects client to server
		try {
			this.socket = new Socket(host, port); //we create a Socket using our instance vars as arguments, we need to surround it with a catch/try block
			System.out.printf("\t|Client connected to <<%s>> on port <<%d>>\n",host, port); //if connection is successful, this prints
			System.out.printf("\t|To quit, type <<%d>>\n\t",-1); //we see how we handle "quit" in displayClientRequest()
			in = new DataInputStream(socket.getInputStream()); 
			out = new DataOutputStream(socket.getOutputStream()); //printwriter writes text 
			displayClientRequest();
		} catch (ClientDisconnectedException e) { //when a clientDisconnectedException occurs, it prints this message
			System.out.printf("\t|Closing connection to server");
			System.out.println("\t|ConnectionHandler: ...\n\t|cleaning up and exiting ...\n ");
			System.exit(1);
			cleanup();
		} catch (Exception e) {
			System.exit(1);
			cleanup();
		} 
	}
	
	private void displayClientRequest() throws IOException, ClientDisconnectedException {
		Scanner scan = new Scanner(System.in);
		//while clientInput has something inside that's not "quit" or the end of inputstream has been reached
		while(true){
			int gradeInput = scan.nextInt();
			
			if (gradeInput == Configuration.QUIT) {
				throw new ClientDisconnectedException(" ... user has entered exit command ..."); 
			}
			out.writeInt(gradeInput);
			out.flush(); //usually used with outputstreams, the flush method ensures any bufferred text written to PrintWriter is sent over the network
			
			System.out.printf("\t|For grade <<%d>> server's response is <<%s>>\n\t", gradeInput, Configuration.RESPONSE[in.readInt()]); //Configuration.RESPONSE[in.readInt()]
		}
		
		
		//if (clientInput.equals("quit"))
			//throw new ClientDisconnectedException(" ... user has entered exit command ..."); 
			//ClientDisconnectedException is a custom exception defined in another java class file, 
			//by throwing this exception when the user enters "quit", it tells the system how to handle it
	}
	
	private void cleanup() {
		System.out.printf("\t|Client: ... \n\t|cleaning up and exiting");
		try {
			out.close();
			in.close();
			socket.close();
		} catch (IOException ioe) {
			System.out.printf("Oops, something went wrong!"); 
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.printf("\t Usage: java -cp networking.Client <<hostname>> <<port>>");
			System.exit(1); //if user doesn't provide proper hostname and port #, program closes
		}
		int port = Integer.parseInt(args[1]);
		Client c = new Client(args[0], port);
	}

}
