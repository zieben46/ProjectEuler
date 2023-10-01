//
//
//Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.
//
//37 36 35 34 33 32 31
//38 17 16 15 14 13 30
//39 18  5  4  3 12 29
//40 19  6  1  2 11 28
//41 20  7  8  9 10 27
//42 21 22 23 24 25 26
//43 44 45 46 47 48 49
//
//It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ˜ 62%.
//
//If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed. If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?

package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Problem_58 {

public static void main(String args[]) {
	go();
		
	}
	
	private static List<Integer> getDiagonals(int spacing) {
		int startNumb = getStartPointFromSpacing(spacing);
		ArrayList<Integer> x = new ArrayList<Integer>(Arrays.asList(startNumb, startNumb+spacing, startNumb+2*spacing, startNumb+3*spacing));
		return x;
	}
	
	private static int getStartPointFromSpacing(int spacing) {
		return spacing*spacing - spacing+1;
	}
	
	private static void go() {
		float numbPrimes = 0;
		int numbDiagonals = 1;
		for (int i = 2;; i+=2) {
			List<Integer> diagonals = getDiagonals(i);
			numbDiagonals +=4;
			numbPrimes += countPrimes(diagonals);
			double percentPrimes = (double) numbPrimes/numbDiagonals;
			if (percentPrimes < .10) {
				System.out.println(getBoxSizeFromSpacing(i)+"SIZE OF BOX");
				break;
			}
		}
	}
	
	private static boolean isPrime(int input) {
		return !IntStream.range(2, (int) Math.sqrt(input)+1)
				          .anyMatch(e -> input % e == 0);
	}
	
	private static long countPrimes(List<Integer> diagonals) {
		return diagonals.stream().filter(e -> isPrime(e))
		                 .count();
	}
	
	private static int getBoxSizeFromSpacing(int input) {
		return input + 1;	
	}
}