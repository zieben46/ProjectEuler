//
//
//It is well known that if the square root of a natural number is not an integer, then it is irrational. The decimal expansion of such square roots is infinite without any repeating pattern at all.
//
//The square root of two is 1.41421356237309504880..., and the digital sum of the first one hundred decimal digits is 475.
//
//For the first one hundred natural numbers, find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots.

package problems;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

public class Problem_80 {

	public static void main(String[] args) {	
		int answer = 0;
		for (int i = 1; i <= 100; i++) {
			if (isIrrational(i)) {
				answer+=digitalSum(estimate(i), 99);
			}
		}
		System.out.println(answer);
	}
	
	private static BigDecimal estimate(int input) {
		BigDecimal guess = BigDecimal.ONE;
		BigDecimal inputDecimal = BigDecimal.valueOf(input);
		BigDecimal avg = guess.add(BigDecimal.valueOf(input)).divide(BigDecimal.valueOf(2));
		
		int timer = 0;
		
		while(timer < 100) {
			timer++;		
			BigDecimal mid = inputDecimal.divide(avg, 500, RoundingMode.HALF_UP);
			avg = (avg.add(mid)).divide(BigDecimal.valueOf(2));
		}	
		return avg;
	}
	
	private static int digitalSum(BigDecimal input, int numbDigits) {
		String inputString = input.toString();
		int decimalPos = inputString.indexOf(".");
		int whole = Integer.parseInt(inputString.substring(0, decimalPos));
		String decimals = inputString.substring(decimalPos + 1, numbDigits + 2);
		return whole + Stream.of(decimals.split(""))
					.mapToInt(Integer::parseInt)
					.sum();	
	}
	
	private static boolean isIrrational(int input) {
		return !isInteger(Math.sqrt(input));
	}

	private static boolean isInteger(double input) {
		return Math.round(input) == input;
	}
}
