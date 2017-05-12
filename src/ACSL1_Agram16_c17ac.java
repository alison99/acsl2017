import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;
/**
 * ACSL Contest 1: Agram
 * @author Alison Chen
 * 
 */

public class ACSL1_Agram16_c17ac {

	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/Agram_TestData";
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
			String answer = solve(scan.nextLine());
			System.out.println(answer);
			if(fop!=null) fop.write((answer+"\n").getBytes());
		}
		
		fop.flush();
		fop.close();
		scan.close();
	}
	
	//type all code here
	static String solve(String input) {
		//take input and parse
		String[] data = input.split(",[ ]*");
		String leading = data[0];
		
		//declarations
		int[] rank = new int[6];
		int lowestabove = 20;
		int lowestsuit = 20;
		int lowest = 20;
		char[] suit = new char[6];
		int ans1 = 0;
		int ans2 = 0;
		int ans3 = 0;
		int ans4 = 0;
		String result = "";
		
		//loop through cards and set rank
		for (int i = 0; i < data.length; i++) {
			if(data[i].startsWith("A")) rank[i]=1;
			else if(data[i].startsWith("T")) rank[i]=10;
			else if(data[i].startsWith("J")) rank[i]=11;
			else if(data[i].startsWith("Q")) rank[i]=12;
			else if(data[i].startsWith("K")) rank[i]=13;
			else {
				rank[i] = Character.getNumericValue(data[i].charAt(0));
			}
		}
		
		//loop through cards and pick result
		for (int i = 1; i < data.length; i++) {
			//check if same suit
			if(leading.charAt(1)==data[i].charAt(1)){
				//check rank
				if(rank[i]>rank[0]) {
					if(rank[i]<lowestabove) {
						lowestabove = rank[i];
						ans1 = i;
						}
				}
				else {
					if(rank[i]<lowestsuit) {
						lowestsuit = rank[i];
						ans2 = i;
					}
				}
			}
			else {
				if(rank[i]==lowest) {
					if(data[i].charAt(1)=='C') ans1 = i;
					else {
						suit[i] = data[i].charAt(1);
					}
				}
				if(rank[i]<lowest) {
					suit[ans3] = 'W';
					lowest = rank[i];
					suit[i] = data[i].charAt(1);
					ans3 = i;
				}
			}
		}
		
		if(new String(suit).contains("D")) {
			for (int j = 1; j < suit.length; j++) {
				if(suit[j]=='D') {
					ans4 = j;
				}
			}
		}
		else if(new String(suit).contains("H")) {
			for (int j = 1; j < suit.length; j++) {
				if(suit[j]=='H'){
					ans4 = j;
				}
			}
		}
		else if(new String(suit).contains("S")) {
			for (int j = 1; j < suit.length; j++) {
				if(suit[j]=='S'){
					ans4 = j;
				}
			}	
		}
		
		if(ans1>0) result = data[ans1];
		else if(ans2>0) result = data[ans2];
		else if(ans4>0)	result = data[ans4];
		else if(ans3>0)	result = data[ans3];
		
		
		return result;
}

}

