//The series, 11 + 22 + 33 + ... + 1010 = 10405071317.
//
//Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.

package problems;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Problem_48 {

public static void main(String[] args) {
		System.out.println(go(1000));
	}
	
	private static String go(int max) {
		BigInteger bigNumber = getValue(max);
		return parseBigInteger(bigNumber);
	}
	
	private static BigInteger getValue(int max) {
		return IntStream.range(1, max + 1)
		                .mapToObj(e -> BigInteger.valueOf(e))
		                .map(e -> e.pow(e.intValue()))
		                .reduce((a, b) -> a.add(b))
		                .orElse(BigInteger.valueOf(1));
	}
	
	private static String parseBigInteger(BigInteger number) {
		String bigIntString = number.toString();
		int stringLength = bigIntString.length();
		return bigIntString.substring(stringLength-10,stringLength);
	}
	
}