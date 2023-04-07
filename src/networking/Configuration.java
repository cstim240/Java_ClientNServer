package networking;

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

public interface Configuration {
	int QUIT = -1;
	int FAIL = 0;
	int PASS = 1;
	int APlus = 2;
	int A = 3;
	int AMinus = 4;
	int BPlus = 5;
	int B = 6;
	int BMinus = 7;
	int CPlus = 8;
	int C = 9;
	int CMinus = 10;
	int D = 11;
	int F = 12;
	String [] RESPONSE = new String[] {"Fail","Pass", "A+", "A", "A-", "B+", "B", "B-", 
			"C+", "C", "C-", "D", "F"};
}
