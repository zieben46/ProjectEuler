//
//
//Pentagonal numbers are generated by the formula, Pn=n(3n-1)/2. The first ten pentagonal numbers are:
//
//1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
//
//It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference, 70 - 22 = 48, is not pentagonal.
//
//Find the pair of pentagonal numbers, Pj and Pk, for which their sum and difference are pentagonal and D = |Pk - Pj| is minimised; what is the value of D?
//

package problems;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Problem_44 {

private static ArrayList<String> pandigitals = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		System.out.println(run2());		
	}
	
	private static BigInteger run2() {
		return pentagonalStream(1)
				 .parallel()
				 .unordered()
				 .flatMap(e ->pentagonalStream(getNFromPent(e))
						                 .unordered()
						                 .limit(4000)					             
	                                     .filter(f -> isPentagonal(e.add(f))&&f.doubleValue() != e.doubleValue())
	                                     .map(f -> f.subtract(e).abs()))
				 .filter(g -> isPentagonal(g))
				 .findFirst()
				 .orElse(BigInteger.valueOf(1));
	}
	
	private static boolean isPentagonal(BigInteger input) {
		//solve 2n^2-n-2z=0 is an integer;
		input = input.multiply(BigInteger.valueOf(24)).add(BigInteger.valueOf(1));
		double c = (Math.sqrt(input.doubleValue())+1)/6;
		return isInteger(c);
	}
	
	private static boolean isInteger(double c) {
		return Math.round(c) == (c/c)*c;
	}
	
	private static Stream<BigInteger> pentagonalStream(int beginning) {
		return Stream.iterate(beginning, e-> e + 1)
				.parallel()
				.map(e -> BigInteger.valueOf(e))
				.map(Problem_44::getPent);
	}
	
	private static BigInteger getPent(BigInteger i) {
		BigInteger j = i.multiply(BigInteger.valueOf(3));
		j = j.subtract(BigInteger.valueOf(1));
		j = i.multiply(j);
		j = j.divide(BigInteger.valueOf(2));
		return j;
	}
	
	private static int getNFromPent(BigInteger i) {
		double toDouble = i.doubleValue();
		return (int) Math.sqrt(toDouble*24+1)/6+1;
	}
	
}