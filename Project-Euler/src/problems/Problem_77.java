//
//
//
//It is possible to write ten as the sum of primes in exactly five different ways:
//
//7 + 3
//5 + 5
//5 + 3 + 2
//3 + 3 + 2 + 2
//2 + 2 + 2 + 2 + 2
//
//What is the first value which can be written as the sum of primes in over five thousand different ways?
//
//

package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_77 {

	//private static Set<int[]> allPossibilities = new HashSet<int[]>();
	private static ArrayList<int[]> allPossibilities = new ArrayList<int[]>();

	private static List<Integer> primes;

	public static void main(String[] args) {
		
		double thenTime = System.nanoTime();
		primes = createPrimes();
		go4();

		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}

	private static void go4() {
		int sum = 0;
		int counter = 4;
		
		do {
			counter++;
			sum = countPermutations(counter);
			
			
		} while (sum <= 5000);
		System.out.println(counter);
		System.out.println(sum);
		
	}

	private static int countPermutations(int pounds) {		
		return countPermutations(new int[]{}, pounds);
	}

	private static int countPermutations(int[] usedCoins, int amountRemaining) {
		//int sum = memorized[amountRemaining];	
		int sum = 0;
		List<Integer> currency = createList(amountRemaining);
		if (amountRemaining == 0) {
			allPossibilities.add(usedCoins);	
			return 1;

		} else if (amountRemaining > 0) {
			List<Integer> remaining = remainingPossibleCoins(usedCoins, currency);
			for (int nextCoin: remaining) {
				int newAmountRemaining = amountRemaining - nextCoin;
				int[] newCoins = new int[usedCoins.length+1];
				newCoins[0] = nextCoin;
				for (int i=1; i<newCoins.length; i++) {
					newCoins[i] = usedCoins[i-1];
				}

				sum+= countPermutations(newCoins, newAmountRemaining);
			}
		}
		return sum;
	}

	private static List<Integer> remainingPossibleCoins(int[] usedCoins, List<Integer> currency) {
		if (usedCoins.length > 0) {
			int lastUsedCoin = usedCoins[0];
			return currency.stream()
					.filter(coin -> coin <= lastUsedCoin)
					.collect(Collectors.toList());
		}
		return currency;
	}

	private static List<Integer> createList(int n) {
		return primes.stream()
				     .filter(e -> e <= n)
				     .collect(Collectors.toList());
	}

	private static List<Integer> createPrimes() {
		return IntStream.range(2, 1000)
				        .filter(Problem_77::isPrime)
				        .boxed()
				        .collect(Collectors.toList());
	}

	private static boolean isPrime(int input) {
		return !IntStream.range(2, (int)Math.sqrt(input)+1)
				         .anyMatch(e -> input % e ==0);
	}
}
