package networking;

import java.io.IOException;
import java.net.*;

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
 * @since 2023-07-04
 */

public class Server {
	private int port;
	/*a ServerSocket is used to listen for incoming connections from clients. When a client attempts
	* to connection to a server, the ServerSocket creates a new Socket obj to handle the communication
	* between the server and the client
	*/
	
	public Server (int port) { //this is a Multi-Threaded Server!
		this.port = port;
		try {
			ServerSocket ss = new ServerSocket(port);  //for all the connections, we use the same socket
			System.out.printf("\t|Server with IP address <<%s>> started ...\n\t|listening on port <<%d>> for client requests\n", ss.getInetAddress(), this.port);
			//getInetaddress of the Socket class returns the address which the socket is connected to
			while (true) {
				// waits until client requests a connection, then returns connection (socket)
				Socket connection = ss.accept();
				System.out.printf("\t|Server got new connection request from <<%s>>\n", connection.getInetAddress());
				
				//create new handler for the connection
				ConnectionHandlerThread handler = new ConnectionHandlerThread(connection);
				handler.start(); //handles the client request for every ConnectionHandlerThread instance
			}
		} catch (IOException e) {
			System.out.println("\t|Ooops something went wrong! :(\n");
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.printf("\t|Usage: java -cp networking.MultiThreadedServer <<port>>");
			System.exit(1);
		}
		int port = Integer.parseInt(args[0]);
		Server server = new Server(port);
	}
}
