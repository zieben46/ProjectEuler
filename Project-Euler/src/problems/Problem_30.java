//Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
//
//    1634 = 14 + 64 + 34 + 44
//    8208 = 84 + 24 + 04 + 84
//    9474 = 94 + 44 + 74 + 44
//
//As 1 = 14 is not a sum it is not included.
//
//The sum of these numbers is 1634 + 8208 + 9474 = 19316.
//
//Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.


package problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem_30 {

	public static void main(String[] args) {
		Integer[] number = new Integer[]{134234};
		
		List<Integer> number2 = Arrays.asList(number);
		
		System.out.println(
				IntStream.range(2, 10000000)
			          .parallel()
				      .filter(e -> parsedPoweredSumEqualsSelf(e))
				      .sum());  
	}
	
	private static double getSumFromOfFifths(List<Integer> list) {
		return list.stream()
				   .parallel()
				   .mapToDouble(e -> Math.pow(e, 5))
				   .sum();
	}
	
	private static List<Integer> parseNumber(int input) {
		return parseNumber(Arrays.asList(new Integer[]{}), Arrays.asList(input));
	}
	
	//...wow.  recursive very unnecessary here
		private static List<Integer> parseNumber(List<Integer> parsed, List<Integer> notParsed) {
			
			if (notParsed.get(0)==0) {
				return Arrays.asList(new Integer[]{});
			} else {
				List<Integer> tail = Arrays.asList(notParsed.get(0) % 10);
				List<Integer> allElse = Arrays.asList(notParsed.get(0) / 10);
				List<Integer> all = new ArrayList<Integer>();
				all.addAll(tail);
				all.addAll(parseNumber(tail, allElse));
				return all;
			}
		}
		
		private static boolean parsedPoweredSumEqualsSelf(int input) {
			return input == getSumFromOfFifths(parseNumber(input));
		}
}