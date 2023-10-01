//
//Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
//
//If we list the set of reduced proper fractions for d = 8 in ascending order of size, we get:
//
//1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
//
//It can be seen that 2/5 is the fraction immediately to the left of 3/7.
//
//By listing the set of reduced proper fractions for d = 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the left of 3/7.


package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_71 {
	
	private static List<Integer> primes;

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		primes = primesList();
		int[] test = go();
		System.out.println(test[0]+"/"+test[1]);
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
	
	private static int[] go() {
		return IntStream.range(1000, 1000000)
				.parallel()
		.mapToObj(e -> new int[]{ closestNumerator(e), e})
		.filter(Problem_71::HCFisOne)
		.max((a, b) -> compareDoubles(toDouble(a), toDouble(b)))
		.orElse(new int[]{1});
	}

	private static ArrayList<Integer> primeFactors(int number) {
		return primeFactors(new ArrayList<Integer>(), number);
	}

	private static boolean HCFisOne(int[] number) {
		int numb = number[0];
		int denom = number[1];
		ArrayList<Integer> numbFactors = primeFactors(numb);
		ArrayList<Integer> denomFactors = primeFactors(denom);
		return !numbFactors.stream()
				.anyMatch(e -> denomFactors.contains(e));
	}

	private static ArrayList<Integer> primeFactors(ArrayList<Integer> primeFactors, int number) {
		if (number == 1) {
			return primeFactors;
		}
		int newPrime=primeDividor(number);
		primeFactors.add(newPrime);
		return primeFactors(primeFactors, number/newPrime);
	}

	private static int primeDividor(int input) {
		    return primes.stream()
				.filter(e -> input % e == 0)
				.findFirst()
				.orElse(input);
	}
	
	private static int closestNumerator(int denomenator) {
		return denomenator*3/7;
	}

	private static double toDouble(int[] number) {
		return (double) number[0]/number[1];
	}
	
	private static int compareDoubles(double d1, double d2) {
		if (d1 > d2) {
			return 1;
		} else {
			return -1;
		}
	}
	
	
	private static boolean isPrime(int input) {
		return !IntStream.range(2,(int) Math.sqrt(input)+1)
				.anyMatch(e -> input % e ==0);
	}

	private static List<Integer> primesList() {
		return IntStream.range(2, 1000)
				.filter(Problem_71::isPrime)
				.boxed()
				.collect(Collectors.toList());
	}
}