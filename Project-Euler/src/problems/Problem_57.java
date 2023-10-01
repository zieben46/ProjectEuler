//
//
//It is possible to show that the square root of two can be expressed as an infinite continued fraction.
//
//v 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
//
//By expanding this for the first four iterations, we get:
//
//1 + 1/2 = 3/2 = 1.5
//1 + 1/(2 + 1/2) = 7/5 = 1.4
//1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
//1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
//
//The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.
//
//In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?


package problems;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem_57 {
	
	/*Looking at sqrt 2 expansion, there are only 2 functions used to calculate nth term:
	 * 2 + 1/(x/y), and  1 + 1/(x/y). 
	 *  
	 * The first is used recursively, and second is used only once.
	 *  
	 *  We need to find an equation that spits out a numerator and a denominator for nth term
	 *  
	 * using algebra, 
	 * 2 + 1/(x/y) = (2x+y)/y
	 * 1 + 1/(x/y) = (x+y)/y
	 * 
	 * let:
	 * f'(x,y) = (2x+y)/y
	 * f(x,y) = (x+y)/y
	 * 
	 * we need numerator and denominator, so we must break down f'(x,y) and f(x,y) further:
	 * f'(x,y)=a'(x,y)/b'(x,y)
	 * where
	 * a'=2x+y
	 * b'=x
	 * 
	 * and f(x,y)=a(x,y)/b'(x,y)
	 * where
	 * a=x+y
	 * b=x
	 * 
	 * to find the nth term, this is the formula F(n) returns nth expansion
	 * 
	 * F(n) = f[ f'(f'...(5,2)    )),( f'(f'...(5,2)           ))]
	 * 
	 * where ... is a recursive step performed n times
	 * to get the numerator and denominator, simply substitute f(x,y) and f'(x,y) with their 
	 * respective a b, a' b' counterparts
	 * 
	 * numerator(n) = a[ a'(a'...(5,2)    )),( a'(a'...(5,2)           ))]
	 * numerator(n) = b[ b'(b'...(5,2)    )),( b'(b'...(5,2)           ))]
	 * 
	 * Now, it's just a matter a writing a program that accomplishes this.
	 * 
	 */

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		
		System.out.println(go());
		
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
	
	private static long go() {
		return Stream.iterate(2, e -> e + 1)
				.parallel()
				     .limit(999)
				     .map(e -> sqrtTwoEstimate(e))
				     .filter(e -> numHasMoreElements(e))
				     .count();	
	}
	
	private static BigInteger[] sqrtTwoEstimate(int position) {
		return sqrtTwoEstimate(new BigInteger[]{BigInteger.valueOf(5), BigInteger.valueOf(2)}, position);
	}
	
	private static BigInteger[] sqrtTwoEstimate(BigInteger[] input, int position) { 
		BigInteger num = input[0];
		BigInteger denom = input[1];
		if (position == 2) {
			BigInteger numComplete = a(num, denom);
			BigInteger denomComplete = b(num, denom);
			return new BigInteger[]{numComplete, denomComplete};
		} else {
			BigInteger numPrime = aPrime(num, denom);
			BigInteger denomPrime =  bPrime(num, denom);
			return sqrtTwoEstimate(new BigInteger[]{numPrime, denomPrime}, position-1);
		}
	}
	
	private static BigInteger aPrime(BigInteger x, BigInteger y) {
		//return 2*x + y;
		return x.multiply(BigInteger.valueOf(2)).add(y);
		
	}
	
	private static BigInteger bPrime(BigInteger x, BigInteger y) {
		return x;
	}
	
	private static BigInteger a(BigInteger x, BigInteger y) {
		return x.add(y);
	}
	
	private static BigInteger b(BigInteger x, BigInteger y) {
		return x;
	}
	
	private static boolean numHasMoreElements(BigInteger[] input) {
		String numString = input[0].toString();
		String denomString = input[1].toString();
		return numString.length() > denomString.length();
	}
}
