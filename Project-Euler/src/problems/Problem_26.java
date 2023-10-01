//for 1/2..1/999 find the denomenator with the longest repeated pattern

package problems;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem_26 {
	private static Pattern patt =  Pattern.compile("(.+?){2}\\1+$");
	public static void main(String[] args){
		loop();
	}	

	private static void loop() {
		int longest = 0;
		BigDecimal h= new BigDecimal(1);
		for (int i = 2; i <10; i++) {
			String s =h.divide(BigDecimal.valueOf(i), 10000, 1).toString();
			Matcher m = patt.matcher(s);
			if (m.find()){		
				if (m.group(1).length() > longest) {
					longest = m.group(1).length();
					System.out.println("ALL NUMBER: "+s);
					System.out.println("PATTERN: "+m.group(1));
					System.out.println("LENGTH: "+longest);
					System.out.println("DIVIDE BY: "+i);
				}
			}
		}	
	}
}