//
//
//By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
//
//By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.
//
//Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.
//
//

package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_51 {

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		
		System.out.println(getSmallestPrime(go()));
		System.out.println(go());
		
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
	
	private static List<String> go() {
		return IntStream.range(100, 999)
				 .parallel()
				 .mapToObj(e -> e+"***")
				 .flatMap(e -> permutations(e).stream())
				 .map(Problem_51::zeroThruNineNumbs)
				 .filter(e -> numberOfPrimes(e)==8)
				 .findFirst()
				 .orElse(Arrays.asList("0000"));
	}	

	private static List<String> permutations(String number) {
		List<String> completedPermutations = new ArrayList<>();
		return permutations("", number, completedPermutations);
	}

	private static List<String> permutations(String preffix, String suffix, List<String> completedPermutations) {
		if (suffix.length() == 0) {
			completedPermutations.add(preffix);
		} else {
			for (int i = 0; i< suffix.length(); i++) {
				permutations(preffix+ suffix.charAt(i),
						suffix.substring(0, i)+ suffix.substring(i+1, suffix.length()), completedPermutations);
			}
		}
		return completedPermutations;
	}
	
	
	private static List<String> zeroThruNineNumbs(String input) {
		return IntStream.range(0, 10)
		                .mapToObj(i ->input.replaceAll("\\*", i+""))
		                .collect(Collectors.toList());
	}
	
	private static boolean isPrime(int input) {
		return !IntStream.range(2, (int)Math.sqrt(input)+1)
				         .anyMatch(e -> input % e ==0);
	}
	
	private static int numberOfPrimes(List<String> input) {
		return (int) input.stream()
				.filter(e -> e.charAt(0)!='0')
				          .mapToInt(Integer::parseInt)
				          .filter(Problem_51::isPrime)
				          .count();
	}

	private static String getSmallestPrime(List<String> input) {
		for (String str: input) {
			if (isPrime(Integer.parseInt(str))) {
				return str;
			}
		}
		return "";
	}
	
}