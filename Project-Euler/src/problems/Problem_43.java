//
//
//The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.
//
//Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
//
//    d2d3d4=406 is divisible by 2
//    d3d4d5=063 is divisible by 3
//    d4d5d6=635 is divisible by 5
//    d5d6d7=357 is divisible by 7
//    d6d7d8=572 is divisible by 11
//    d7d8d9=728 is divisible by 13
//    d8d9d10=289 is divisible by 17
//
//Find the sum of all 0 to 9 pandigital numbers with this property.

package problems;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

public class Problem_43 {

private static ArrayList<String> pandigitals = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		permutations("0123456789");
		System.out.println(start());
	}

	private static void permutations(String input) {
		permutations("", input);
	}

	private static void permutations(String prefix, String suffix) {
		int suffixLength = suffix.length();
		if (suffixLength == 0) {
			pandigitals.add(prefix);
		} else {
			for (int i = 0; i< suffixLength;i++) {
				permutations(prefix+suffix.charAt(i), suffix.substring(0, i)+ suffix.substring(i+1, suffixLength));
			}

		}
	}

	private static int parsePandigitalBy3(String pandigital, int startSpot) {
		return Integer.parseInt(pandigital.substring(startSpot-1, startSpot+2));

	}

	private static boolean isDivisibleBy2(String pandigital) {
		int toInt = parsePandigitalBy3(pandigital, 2);
		return toInt % 2 ==0;
	}

	private static boolean isDivisibleBy3(String pandigital) {
		int toInt = parsePandigitalBy3(pandigital, 3);
		return toInt % 3 ==0;
	}

	private static boolean isDivisibleBy5(String pandigital) {
		int toInt = parsePandigitalBy3(pandigital, 4);
		return toInt % 5 ==0;
	}
	private static boolean isDivisibleBy7(String pandigital) {
		int toInt = parsePandigitalBy3(pandigital, 5);
		return toInt % 7 ==0;
	}
	private static boolean isDivisibleBy11(String pandigital) {
		int toInt = parsePandigitalBy3(pandigital, 6);
		return toInt % 11 ==0;
	}

	private static boolean isDivisibleBy13(String pandigital) {
		int toInt = parsePandigitalBy3(pandigital, 7);
		return toInt % 13 ==0;
	}

	private static boolean isDivisibleBy17(String pandigital) {
		int toInt = parsePandigitalBy3(pandigital, 8);
		return toInt % 17 ==0;
	}

	private static Optional<BigInteger> start() {
		return pandigitals.stream()
				.filter(Problem_43::passesAllTests)
				.mapToLong(e ->Long.parseLong(e))
				.mapToObj(e -> BigInteger.valueOf(e))
				.reduce((a, b)->a.add(b));
	}

	private static boolean passesAllTests(String input ) {
		return isDivisibleBy2(input)&&
				isDivisibleBy3(input)&&
				isDivisibleBy5(input)&&
				isDivisibleBy7(input)&&
				isDivisibleBy11(input)&&
				isDivisibleBy13(input)&&
				isDivisibleBy17(input);
	}
	
}