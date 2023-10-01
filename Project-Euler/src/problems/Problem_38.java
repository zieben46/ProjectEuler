//
//
//Take the number 192 and multiply it by each of 1, 2, and 3:
//
//    192 × 1 = 192
//    192 × 2 = 384
//    192 × 3 = 576
//
//By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)
//
//The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).
//
//What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?

package problems;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem_38 {
	private static ArrayList<Integer> pandigitals = new ArrayList<>();

	public static void main(String[] args) {
		int highestPand = 0;
		createPandigitals();
		//System.out.println(isPandigital(123456789));
		
		for (int n = 2; n<10; n++) {
			for (int j = 5; j<10000;j++) {
				int concatProduct = concatProduct(j, n);
				//System.out.println(concatProduct);
				if (isPandigital(concatProduct)) {
					if (concatProduct > highestPand) {
						highestPand = concatProduct;
					}
					
					
				}
			}
		}
		System.out.println(highestPand);
	}
	
	private static void createPandigitals() {
		permutations("123456789");
	}

	private static void permutations(String input) {
		permutations("", input);
	}

	private static void permutations(String prefix, String suffix) {
		if (suffix.equals("")) {
			int pandigitalNumb = Integer.parseInt(prefix);
			pandigitals.add(pandigitalNumb);
		} else {
			for (int i = 0; i <suffix.length(); i++) {
				permutations(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i+ 1,suffix.length()));
			}
		}
	}
	
	private static int concatProduct(int input, int n) {
//		System.out.println(IntStream.range(2, n).map(e -> e*input)
//				.mapToObj(i -> ((Integer) i)
//					    .toString())
//						.collect(Collectors.joining()));
		try{
		
		return Integer.parseInt(IntStream.range(1, n+1).map(e -> e*input)
									.mapToObj(i -> ((Integer) i)
								    .toString())
									.collect(Collectors.joining()));
		} catch (NumberFormatException e) {
			return 5;
		}
		
		
	}
	
	private static boolean isPandigital(int input) {
		return pandigitals.contains(input);
	}
}






