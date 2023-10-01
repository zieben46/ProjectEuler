//
//
//
//There are exactly ten ways of selecting three from five, 12345:
//
//123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
//
//In combinatorics, we use the notation, 5C3 = 10.
//
//In general,
//nCr = 	
//n!
//r!(n-r)!
//	,where r = n, n! = n×(n-1)×...×3×2×1, and 0! = 1.
//
//It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
//
//How many, not necessarily distinct, values of  nCr, for 1 = n = 100, are greater than one-million?

package problems;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Problem_53 {

public static void main(String[] args) {
		double thenTime = System.nanoTime();	

		System.out.println(go());
		
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}

	private static BigInteger factor(BigInteger input) {
		if (input.equals(BigInteger.valueOf(0))) {
			return BigInteger.valueOf(1);
		} else {
			return input.multiply(factor(input.subtract(BigInteger.ONE)));
		}
	}
	
	private static BigInteger combinatoricsOf(int n, int r) {
		BigInteger nBig = BigInteger.valueOf((long) n);
		BigInteger rBig = BigInteger.valueOf((long) r);
		BigInteger nMinusR =  BigInteger.valueOf((long) (n-r));
		
		return factor(nBig).divide(factor(nMinusR).multiply(factor(rBig)));
		
	}
	
	private static float go() {
		return Stream.iterate(1, e -> e + 1)
				.parallel()
			  .limit(100)
		      .flatMap( e -> Stream.iterate(1, f -> f + 1)
		                           .limit(e)
		                           .map( f -> combinatoricsOf(e, f)))
		      .mapToInt(g -> g.compareTo(BigInteger.valueOf(1000000)))
		      .filter(e -> e == 1)
		      .count();
	}
}