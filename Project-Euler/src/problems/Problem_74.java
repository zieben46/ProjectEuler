//
//
//The number 145 is well known for the property that the sum of the factorial of its digits is equal to 145:
//
//1! + 4! + 5! = 1 + 24 + 120 = 145
//
//Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to 169; it turns out that there are only three such loops that exist:
//
//169 ? 363601 ? 1454 ? 169
//871 ? 45361 ? 871
//872 ? 45362 ? 872
//
//It is not difficult to prove that EVERY starting number will eventually get stuck in a loop. For example,
//
//69 ? 363600 ? 1454 ? 169 ? 363601 (? 1454)
//78 ? 45360 ? 871 ? 45361 (? 871)
//540 ? 145 (? 145)
//
//Starting with 69 produces a chain of five non-repeating terms, but the longest non-repeating chain with a starting number below one million is sixty terms.
//
//How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?

package problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Problem_74 {

	public static void main(String[] args) {

		double thenTime = System.nanoTime();
		
		System.out.println(go());

		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}


	private static long go() {
		return IntStream.range(1 , 1000001)
				.parallel()
		         .map(Problem_74::loops)
		         .filter(e -> e == 60)
		         .count();
	}
	
	private static int loops(int input) {
		ArrayList<BigInteger> numberCollection = new ArrayList<>();
		BigInteger nextNumber = factorialSum(BigInteger.valueOf(input));
		numberCollection.add(BigInteger.valueOf(input));
		int counter = 1;
		while (!numberCollection.contains(nextNumber)) {
			numberCollection.add(nextNumber);
			nextNumber = factorialSum(nextNumber);
			
			counter++;
			if (counter > 60) {
				return counter;
			}
			
		}
		return numberCollection.size();
	}
	
	private static BigInteger factorialSum(BigInteger input) {
		return Arrays.stream(input.toString().split(""))
				     .map(BigInteger::new)
				     .map(Problem_74::factorial)
				     .reduce((a,  b) -> a.add(b))
				     .orElse(BigInteger.ONE);
	}
	
	private static BigInteger factorial(BigInteger input) {
		if (input.equals(BigInteger.ONE)||input.equals(BigInteger.ZERO)) {
			return BigInteger.ONE;
		}
		
		return input.multiply(factorial(input.subtract(BigInteger.ONE)));
	}
}