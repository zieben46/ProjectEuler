
//
//A googol (10100) is a massive number: one followed by one-hundred zeros; 100100 is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.
//
//Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?


package problems;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

public class Problem_56 {

public static void main(String[] args) {
		double thenTime = System.nanoTime();
		
		System.out.println(go());

		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
		
	}
	
	
	private static BigInteger powerOf(int base, int exponent) {
		return BigInteger.valueOf((long) base).pow(exponent);
	}
	
	private static double sumofDigits(BigInteger input) {
		return Arrays.stream(input.toString().split(""))
				     .mapToInt(e -> Integer.parseInt(e))
				     .sum();
	}
	
	private static double go() {
		return Stream.iterate(1, e -> e + 1)
				     .limit(100)
				     .flatMap(e -> Stream.iterate(1, f -> f+1)
				                         .limit(100)
				                         .map(f -> powerOf(e, f)))
				     .mapToDouble(g -> sumofDigits(g))
				     .max()
				     .orElse(0);
	}
}