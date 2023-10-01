//We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.
//
//The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.
//
//Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.
//HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.

package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_32 {
	private static ArrayList<Integer> pandigitals = new ArrayList<>();
	
	public static void main(String[] args) {
		createPandigitals();
		HashSet<Integer> answers = stream2();
		int answerSum = 0;
		for (int answer: answers) {
			System.out.println(answer);
			answerSum+=answer;
		}
		
		System.out.println("ANSWER IS: "+answerSum );
		
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


	private static int joinThreeNumbers(int a, int b, int c) {
		try {
			return Integer.parseInt((Integer.toString(a)+Integer.toString(b)+Integer.toString(c)));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	
	private static boolean isPandigital(int a, int b) {
		return pandigitals.contains(joinThreeNumbers(a, b, a*b));
	}
	
	private static HashSet<Integer> stream2() {
		return IntStream.range(1, 4000).parallel().flatMap(x -> (IntStream.range(x, 4000)
			                                      .parallel()
				                                  .filter(y -> isPandigital(x, y))
				                                  .map(y -> x*y)))
				        .boxed()
				        .collect(Collectors.toCollection(HashSet::new));
	}
}