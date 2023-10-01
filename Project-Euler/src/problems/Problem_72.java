//
//
//
//Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
//
//If we list the set of reduced proper fractions for d = 8 in ascending order of size, we get:
//
//1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
//
//It can be seen that there are 21 elements in this set.
//
//How many elements would be contained in the set of reduced proper fractions for d = 1,000,000?

package problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_72 {
	private static List<Integer> primes;

	public static void main(String[] args) {
		
		double thenTime = System.nanoTime();
		primes = primesList();
		System.out.println(go());
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}

	private static BigInteger go() {
		return IntStream.range(2, 1000001)
				.map(Problem_72::phi)
				.mapToObj(e -> BigInteger.valueOf(e))
				.reduce((a, b) -> a.add(b))
				.orElse(BigInteger.valueOf(1));				  
	}

	private static int numeratorPhi(Set<Integer> n) {
		return n.stream()
				.map(e -> (e - 1))
				.reduce((a, b) -> a*b)
				.orElse(1);
	}

	private static int denominatorPhi(Set<Integer> n) {
		return n.stream()
				.reduce((a, b) -> a*b)
				.orElse(1);
	}

	private static int phi(int n) {
		Set<Integer> uniquePrimes = new TreeSet<>();
		uniquePrimes.addAll(primeFactors(n));
		int numPhi = numeratorPhi(uniquePrimes);
		int denomPhi = denominatorPhi(uniquePrimes);
		int phiN =n/denomPhi*(numPhi);
		return phiN;
	}

	private static ArrayList<Integer> primeFactors(int number) {
		return primeFactors(new ArrayList<Integer>(), number);
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

	private static boolean isPrime(int input) {
		return !IntStream.range(2,(int) Math.sqrt(input)+1)
				.anyMatch(e -> input % e ==0);
	}

	private static List<Integer> primesList() {
		return IntStream.range(2, 5000)
				.filter(Problem_72::isPrime)
				.boxed()
				.collect(Collectors.toList());
	}
}