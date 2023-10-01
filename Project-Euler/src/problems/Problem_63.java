//
//The 5-digit number, 16807=75, is also a fifth power. Similarly, the 9-digit number, 134217728=89, is a ninth power.
//
//How many n-digit positive integers exist which are also an nth power?
//


package problems;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem_63 {

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		
		System.out.println(go());

		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
	
	
	private static int startNumber(int pow) {
		return IntStream.range(1, 10)	
						.filter(n -> hasSameLength(n, pow))
			            .findFirst()
			            .orElse(10);            
	}
	
	
	private static boolean hasSameLength(int n, int pow) {
		BigInteger big = BigInteger.valueOf(n).pow(pow);
		return big.toString().length() == pow;
	}
	
	
	private static int go() {
		return Stream.iterate(1,e -> e + 1)
					 .map(e -> 10 - startNumber(e))					 
					 .mapToInt(e -> Integer.valueOf(e))
					 .limit(1000)
					 .sum();
					 
	}
}