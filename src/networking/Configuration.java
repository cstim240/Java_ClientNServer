package networking;

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
