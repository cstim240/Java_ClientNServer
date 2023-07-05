# Java_ClientNServer
A basic Client, ConnectionHandler, and server setup using Java. 
Purpose of this exercise: To modify the networking classes to enable server to computer letter grade and have the server display 
its communication with clients. The Client should be able to send any grade between 0 and 100, disconnect using -1, and display 
its communication with the server.

To run the java files, ensure you have the latest version of Java installed in your computer: https://www.oracle.com/ca-en/java/technologies/downloads/

Open your terminal or powershell, compile all the files: https://www.tutorialspoint.com/How-to-run-a-java-program

To run the client type: java -cp networking.Client <<hostname/IP address>> <<port>>
  From the client, you can type: -1, to quit the connection to the server.
To run the Server type: java -cp networking.MultiThreadedServer <<port>>
  Cmnd + C to exit the Server in Mac's terminal. Windows key + R for windows.
