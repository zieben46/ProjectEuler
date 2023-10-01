//2^1000 then sum of each part
package problems;

import java.math.BigInteger;
import java.util.ArrayList;



public class Problem_15 {

	public static void main(String[] args) {
		long answer=0;
		BigInteger n = BigInteger.valueOf(2);
		n = n.pow(1000);
		String str=n.toString();
		String[] secondstring=str.split("");
		for (String s:secondstring) {
			answer=answer+Long.parseLong(s);
			
		}
		System.out.println(answer);	
	}

}