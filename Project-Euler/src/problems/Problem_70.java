//
//
//Euler's Totient function, f(n) [sometimes called the phi function], is used to determine the number of positive numbers less than or equal to n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, f(9)=6.
//The number 1 is considered to be relatively prime to every positive number, so f(1)=1.
//
//Interestingly, f(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
//
//Find the value of n, 1 < n < 107, for which f(n) is a permutation of n and the ratio n/f(n) produces a minimum.


package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_70 {
	private static List<Integer> primes;

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		primes = primesList();
		go();
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
	
	private static void go() {
		double min = 999999;
		for (int i = primes.size() - 1; i > 1; i--) {

			for (int j = i; j > 0; j--) {
				int number = primes.get(i)*primes.get(j);
				int phiN = phi(number);
				if (arePermutations(phiN, number)&&(double) number/phiN < min&&number<10000000) {
					min = (double) number/phiN;
					System.out.println(number+"   "+(double) number/phiN+"  "+phiN);
				}
			}
		}
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

	private static boolean arePermutations(int s1, int s2) {
		char[] s1List = String.valueOf(s1).toCharArray();
		char[] s2List = String.valueOf(s2).toCharArray();
		Arrays.sort(s1List);
		Arrays.sort(s2List);
		return Arrays.toString(s1List).equals((Arrays.toString(s2List)));
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
				.filter(Problem_70::isPrime)
				.boxed()
				.collect(Collectors.toList());
	}
}
	