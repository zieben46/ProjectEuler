//The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
//
//Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
//
//NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

package problems;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem_37 {


	public static void main(String[] args) {

		System.out.println(
				Stream.iterate(11, e -> e+2)
				.parallel()
				.filter(Problem_37::isPrime)
				.filter(Problem_37::isPrimeLeftTruncate)
				.filter(Problem_37::isPrimeRightTruncate)
				.limit(11)
				.mapToInt(e -> e)
				.sum()
				);
	}

	private static boolean isPrime(int input) {
		if (input > 1) {
			return !IntStream.range(2, (int) Math.sqrt(input)+1)
					.anyMatch(e -> input % e == 0);
		} else {
			return false;
		}
	}

	private static boolean isPrime(String input) {
		return isPrime(Integer.valueOf(input));
	}

	private static boolean isPrimeLeftTruncate(int input) {
		return isPrimeLeftTruncate(Integer.toString(input));
	}

	private static boolean isPrimeRightTruncate(int input) {
		return isPrimeRightTruncate(Integer.toString(input));
	}

	private static boolean isPrimeLeftTruncate(String input) {
		int length = input.length();
		if (length == 0) {
			return true;
			} else if (!isPrime(input)) {
				return false;
			} else {
				String truncated = input.substring(0, length - 1);
				return isPrimeLeftTruncate(truncated);
			}
	}

	private static boolean isPrimeRightTruncate(String input) {
		int length = input.length();
		if (length == 0) {
			return true;
		} else if (!isPrime(input)) {
			return false;
		} else {
			String truncated = input.substring(1, length);
			return isPrimeRightTruncate(truncated);
		}
	}
}






