//145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
//
//Find the sum of all numbers which are equal to the sum of the factorial of their digits.
//
//Note: as 1! = 1 and 2! = 2 are not sums they are not included.

package problems;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Problem_34 {

public static void main(String[] args) {
			
		    System.out.println(
		    		IntStream.range(3, 10000000)
					         .parallel()
					         .filter(Problem_34::isCuriousNumber)
					         .sum());
		}
		
		private static boolean isCuriousNumber(int input) {
			int[] inputArray = createArray(input);
			return sumFactorOf(inputArray)==input;
		}
		
		private static int[] createArray(int input) {
			String[] s = Integer.toString(input).split("");
			return Arrays.stream(s)
					     .mapToInt(e -> Integer.parseInt(e)).toArray();
		}
	
		private static int sumFactorOf(int[] input) {
			return Arrays.stream(input)
					     .map(Problem_34::factor)
					     .sum();
		}
		
		private static int factor(int input) {
			return IntStream.range(1, input+1)
					        .reduce(1, (b, c) -> b*c);
		}
		
}