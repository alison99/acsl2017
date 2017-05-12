import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class ACSL3_LightsOut17_c17ac {
	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/LightsOut_TestData";
		Scanner scan;
		FileOutputStream fop = null;

		try{
			scan = new Scanner(new BufferedReader(new FileReader(filein)));
			fop = new FileOutputStream(new File(filein+"-out"));
		} catch(FileNotFoundException e) {
			//switch to regular terminal input
			scan = new Scanner(System.in);
		}

		//main loop:
		while(scan.hasNext()) {
			String[] data = new String[6]; //change to 6? how many input lines tho!!!
			for (int i = 0; i < data.length; i++) {
				data[i] = scan.nextLine();
			}
			String answer = solve(data);
			System.out.println(answer);
			if(fop!=null) fop.write((answer+"\n").getBytes());
		}

		fop.flush();
		fop.close();
		scan.close();
	}

	/**
	 * solve for a specific input
	 * @param s the input
	 * @return the output
	 */

	static String solve(String[] s) {
		//declarations
		String[] binary = new String[6];
		int[][] board1 = new int[12][12];
		int[][] board2 = new int[12][12];
		String result = "";

		//get rid of spaces
		for (int i = 0; i < s.length; i++) {
			s[i] = s[i].replaceAll("\\s","");
			//convert hex to binary
			binary[i] = new BigInteger(s[i], 16).toString(2);
			//add leading 0s
			binary[i] = String.format("%64s", binary[i]).replace(" ", "0");
		}

		for (int k = 0; k < s.length-1; k++) {

			//fill in the grids
			int[] tile1 = new int[64];
			String[] split = binary[k].split("");
			for (int i = 0; i < tile1.length; i++) {
				tile1[i] = Integer.parseInt(split[i]); //make this work for everyone
			}

			int index1 = 0;
			while(index1<64)	{
				for (int r = 9; r >=2; r--) {
					for (int c = 2; c < 10; c++) {
						board1[r][c] = Integer.parseInt(split[index1]);
						index1++;
					}
				}
			}

			int[] tile2 = new int[64];
			String[] split2 = binary[k+1].split("");
			for (int i = 0; i < tile2.length; i++) {
				tile2[i] = Integer.parseInt(split2[i]); //make this work for everyone
			}

			int index2 = 0;
			while(index2<64)	{
				for (int r = 9; r >=2; r--) {
					for (int c = 2; c < 10; c++) {
						board2[r][c] = Integer.parseInt(split2[index2]);
						index2++;
					}
				}
			}

			for (int c = 1; c < 12; c++) {
				if(board1[2][c]!=board2[2][c]) {
					if((board1[5][c]==board2[5][c])&&(board1[5][c-1]==board2[5][c-1])&&(board1[5][c+1]==board2[5][c+1])) board2[0][c]=1;
					else {
						if(board1[6][c]==board2[6][c]) board2[1][c]=1;
					}
				}

			}
			for (int r = 11; r >0; r--) {
				if(board1[r][9]!=board2[r][9]) {
					if((board1[r][6]==board2[r][6])&&(board1[r+1][6]==board2[r+1][6])&&(board1[r-1][6]==board2[r-1][6])) board2[r][11]=1;
					else {
						if(board1[r][5]==board2[r][5]) board2[r][10]=1;
					}
				}

			}
			//			//grid test
			//			for (int r = 0; r < board1.length; r++) {
			//				for (int c = 0; c < board1.length; c++) {
			//					System.out.print(board1[r][c] + " ");
			//				}
			//				System.out.println();
			//			}
			//			System.out.println();
			//
			//			//grid test
			//			for (int r = 0; r < board2.length; r++) {
			//				for (int c = 0; c < board2.length; c++) {
			//					System.out.print(board2[r][c] + " ");
			//				}
			//				System.out.println();
			//			}
			//			System.out.println();

			//loop through both boards and check which ones were inverted
			int pressc = 0;
			int pressr = 0;

			for (int r = 0; r <12; r++) {
				for (int c = 0; c < 12; c++) {
					if (board1[r][c]!=board2[r][c]) {
						if (r>pressr) pressr=r;
						if (c>pressc) pressc=c;
					}
				}
			}

			result += (12-pressr) + "" + (pressc-3) + "\n"; //fix this for if they're the same
		}

		return result;
	}
}
