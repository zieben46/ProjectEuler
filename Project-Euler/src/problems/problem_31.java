//In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
//
//    1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
//
//It is possible to make £2 in the following way:
//
//    1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
//
//How many different ways can £2 be made using any number of coins?
//
//This one took me like 5 hours to figure out.

package problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class problem_31 {
	private static final List<Integer> currency = Arrays.asList(1, 2, 5, 10, 20, 50, 100, 200);
	private static Set<int[]> allPossibilities = new HashSet<int[]>();

	public static void main(String[] args) {
		dividePounds(200);				
		System.out.println("ANSWER IS: "+allPossibilities.size());
	}

	private static void dividePounds(int pounds) {		
		dividePounds(new int[]{}, pounds);
	}

	private static void dividePounds(int[] usedCoins, int amountRemaining) {
		if (amountRemaining == 0) {
			allPossibilities.add(usedCoins);			
		} else if (amountRemaining > 0) {
			for (int nextCoin: remainingPossibleCoins(usedCoins)) {
				int newAmountRemaining = amountRemaining - nextCoin;
				int[] newCoins = new int[usedCoins.length+1];
				newCoins[0] = nextCoin;
				for (int i=1; i<newCoins.length; i++) {
					newCoins[i] = usedCoins[i-1];
				}
				
				dividePounds(newCoins, newAmountRemaining);
			}
		}
	}
	
	private static List<Integer> remainingPossibleCoins(int[] usedCoins) {
		if (usedCoins.length > 0) {
			int lastUsedCoin = usedCoins[0];
			return currency.stream()
					       .filter(coin -> coin <= lastUsedCoin)
					       .collect(Collectors.toList());
		}
		return currency;
	}
}