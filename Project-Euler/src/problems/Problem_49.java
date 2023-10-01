//
//
//The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
//
//There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.
//
//What 12-digit number do you form by concatenating the three terms in this sequence?

package problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_49 {

	public static void main(String[] args) {


		double thenTime = System.nanoTime();

		go();

		double nowTime = System.nanoTime();

		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");

	}


	private static List<String> permutations(int number) {
		List<String> completedPermutations = new ArrayList<>();
		return permutations("", Integer.toString(number), completedPermutations);
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

	private static boolean isPrime(int input) {
		return !IntStream.range(2, (int)Math.sqrt(input)+1)
				.anyMatch(e -> input % e ==0);
	}

	private static IntStream primeNumbersStream() {
		return IntStream.range(999, 10000)
				.filter(Problem_49::isPrime);
	}


	private static void go() {
		List<Integer> primeNumbers = primeNumbersStream().boxed()
				                                         .collect(Collectors.toList());
		
		for (Integer primeNumb: primeNumbers) {
			for (int j = 3330; j < 3331; j++) {
				List<Integer> numbs = primesCommonSpacing(primeNumb, j);
				ArrayList<ArrayList<String>> sequentialNumbs = filterToSequential(numbs, j);
				for (ArrayList<String> group: sequentialNumbs) {
					List<String> permutations = permutations(Integer.parseInt(group.get(0)));
					if (permutations.containsAll(group)) {
						System.out.println(group);
					}
				}	
			}
		}		
	}
	

	private static List<Integer> primesCommonSpacing(int startNumber, int spacing) {
		return IntStream.range(startNumber,10001 )
				.parallel()
				.filter(Problem_49::isPrime)
				.filter(e -> hasEvenSpacing(startNumber, e, spacing))
				.boxed()
				.collect(Collectors.toList());
	}

	private static ArrayList<ArrayList<String>> filterToSequential(List<Integer> primeCommonSpacing, int spacing) {
		ArrayList<ArrayList<String>> output = new ArrayList<>();
		for (int i=2; i < primeCommonSpacing.size(); i++) {
			Integer third = primeCommonSpacing.get(i);
			Integer second = primeCommonSpacing.get(i-1);
			Integer first = primeCommonSpacing.get(i-2);
			if (third-second==second-first) {
				ArrayList<String> group = new ArrayList();
				group.add(Integer.toString(first));
				group.add(Integer.toString(second));
				group.add(Integer.toString(third));
				output.add(group);

			}
		}
		return output;
	}


	private static boolean hasEvenSpacing(int startNumber, int number, int spacing) {
		return (number - startNumber) % spacing == 0;
	}
}
