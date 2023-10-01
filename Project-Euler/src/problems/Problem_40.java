//
//
//An irrational decimal fraction is created by concatenating the positive integers:
//
//0.123456789101112131415161718192021...
//
//It can be seen that the 12th digit of the fractional part is 1.
//
//If dn represents the nth digit of the fractional part, find the value of the following expression.
//
//d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000

package problems;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem_40 {

	public static void main(String[] args) {
		String str = buildString();
		char s1 = str.charAt(0);
		char s10 = str.charAt(9);
		char s100 = str.charAt(99);
		char s1000 = str.charAt(999);
		char s10000 = str.charAt(9999);
		char s100000 = str.charAt(99999);
		char s1000000 = str.charAt(999999);
		
		System.out.println(
				toInt(s1)*toInt(s10)*toInt(s100)*toInt(s1000)*toInt(s10000)*toInt(s100000)*toInt(s1000000)
				);
	}
	
	
	private static String buildString() {
		return Stream.iterate(1,e -> e+1)
				.limit(1000000)
				     .map(e -> Integer.toString(e))
				     .collect(Collectors.joining());
	}
	
	private static int toInt(char e) {
		return Character.getNumericValue(e);
	}
	
}