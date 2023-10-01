//
//
//It is possible to write five as a sum in exactly six different ways:
//
//4 + 1
//3 + 2
//3 + 1 + 1
//2 + 2 + 1
//2 + 1 + 1 + 1
//1 + 1 + 1 + 1 + 1
//
//How many different ways can one hundred be written as a sum of at least two positive integers?


package problems;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_76 {
	
	//private static Set<int[]> allPossibilities = new HashSet<int[]>();
	//private static ArrayList<int[]> allPossibilities = new ArrayList<int[]>();

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		int sum = countPermutations(5);
					
//		System.out.println("PARTIAL ANSWER IS: "+(allPossibilities.size()));
//		System.out.println("ANSWER IS: "+(sum));
//		
//		for(int[] i: allPossibilities) {
//			for(int j:i) {
//				System.out.print(j+" ");
//			}
//			
//			System.out.println();
//		}
		
		go4();
		
		//System.out.println(allPossibilities);
		
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
	
	private static void go4() {
		BigInteger sum = BigInteger.ZERO;
		for (int i =3; i <=100; i++) {
			sum = sum.add(BigInteger.valueOf(countPermutations(i)));
		}
		System.out.println(sum.add(BigInteger.ONE));
	}

	private static int countPermutations(int pounds) {		
		return countPermutations(new int[]{}, pounds);
	}

	private static int countPermutations(int[] usedCoins, int amountRemaining) {
		//int sum = memorized[amountRemaining];	
		int sum = 0;
		List<Integer> currency = createList(amountRemaining);
		if (amountRemaining == 0) {
			//allPossibilities.add(usedCoins);	
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
		return IntStream.range(2, n+1)
				        .boxed()
				        .collect(Collectors.toList());
	}
}