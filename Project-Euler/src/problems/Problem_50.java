//
//
//The prime 41, can be written as the sum of six consecutive primes:
//41 = 2 + 3 + 5 + 7 + 11 + 13
//
//This is the longest sum of consecutive primes that adds to a prime below one-hundred.
//
//The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.
//
//Which prime, below one-million, can be written as the sum of the most consecutive primes?

package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_50 {
	
    private static List<Integer> primes = new ArrayList<>();

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		primes = primeNumbersStream(2).boxed()
				.collect(Collectors.toList());
		
		go();
		
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}

	private static boolean isPrime(int input) {
		return !IntStream.range(2, (int)Math.sqrt(input)+1)
				.anyMatch(e -> input % e ==0);
	}

	private static IntStream primeNumbersStream(int startNumber) {
		return IntStream.range(startNumber, 10000)
				.filter(Problem_50::isPrime);
	}	

private static int[] largestPrimeInSegment(int startNumber) {
		int sumOfPrimes = 0;
		int numbOfPrimes=0;
		int maxPrime = 0;
		int maxNumbOfPrimes=0;
		for (int i = primes.indexOf((Object) startNumber); i < primes.size(); i++) {
			sumOfPrimes+=primes.get(i);
			numbOfPrimes++;		
			if (sumOfPrimes>1000000) {
				break;
			}
				if (isPrime(sumOfPrimes)) {
					maxPrime = sumOfPrimes;
					maxNumbOfPrimes=numbOfPrimes;
				}
		}
		return new int[]{maxNumbOfPrimes, maxPrime};
	}

	private static void go() {
		int maxPrimeSum = 0;
		int numbOfRanks = 0;
		int startNumber = 0;
		
		for (Integer i: primes) {
			int[] pair = largestPrimeInSegment(i);
			int currPrime = pair[1];
			int currRank = pair[0];
			if (currRank > numbOfRanks) {
				numbOfRanks = currRank;
				maxPrimeSum = currPrime;
				startNumber = i;
		}

		}
		System.out.println(numbOfRanks);
		System.out.println(maxPrimeSum);
		System.out.println(startNumber);
	}
}