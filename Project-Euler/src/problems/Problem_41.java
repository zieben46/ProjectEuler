//
//
//We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.
//
//What is the largest n-digit pandigital prime that exists?

package problems;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Problem_41 {
	private static ArrayList<Integer> pandigitals = new ArrayList<>();


	public static void main(String[] args) {

		String base = "";
		int maxPalindrome = 0;
		for (int i = 1; i<=9; i++) {
			base += i+"";
			createPandigitals(base);
			int localMax = getLocalMax();
			if (localMax > maxPalindrome) {
				maxPalindrome = localMax;
			}
			pandigitals.clear();
		}
		System.out.println(maxPalindrome);
	}


	private static void createPandigitals(String input) {
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

	private static boolean isPrime(int input) {
		return !IntStream.range(2, (int) Math.sqrt(input)-1)
				.anyMatch(e -> input % e == 0);
	}

	private static int getLocalMax() {
		return pandigitals.stream()
				.filter(Problem_41::isPrime)
				.max((a, b) -> a - b)
				.orElse(1);
	}
}