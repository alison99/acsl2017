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
 * ACSL Contest 2: Ascending
 * @author Alison Chen
 */

public class ACSL2_Ascending17_c17ac {

	public static void main(String[] args) throws IOException {
		//declarations:
		String filein = "testdata/Ascending_TestData";
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
		//declarations
		StringBuffer original = new StringBuffer(input);
		String result = "";
		int prior;
		boolean done = false;

		//scan from left, don't show if 0
		if(Integer.parseInt(original.substring(0,1))!=0) {
			result = original.substring(0,1) + " ";
		}
		prior = Integer.parseInt(original.substring(0,1));
		original.delete(0, 1);
		original.reverse();


		//loop reversing right/left
		while(done==false) {
			//check until it's bigger than the previous
			//result contains the final string, but we only want to compare to the previous number
			//so we need to have another string-turned-it that contains that previous number ONLY

				for (int i = 1; i <= original.length(); i++) {
					if(Integer.parseInt(original.substring(0, i))>prior) {
						//don't show 0 if starts with 0
						if(Integer.parseInt(original.substring(0, 1))!=0) {
						result = result + original.substring(0, i) + " ";
						}
						else {
							result = result + original.substring(1, i) + " ";
						}

						prior = Integer.parseInt(original.substring(0, i));
						original.delete(0, i);
						original.reverse();

						break;
					}
					//when there are no more greater, the loop ends
					else if(Integer.parseInt(original.toString())<prior) {
						done = true;
					}
				}
				//if nothing in string, ends
				if(original.length()==0) {
					done = true;
				}
			}
		
		return result;
	}

}
