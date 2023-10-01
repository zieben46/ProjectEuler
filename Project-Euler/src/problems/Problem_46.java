//
//
//It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
//
//9 = 7 + 2×12
//15 = 7 + 2×22
//21 = 3 + 2×32
//25 = 7 + 2×32
//27 = 19 + 2×22
//33 = 31 + 2×12
//
//It turns out that the conjecture was false.
//
//What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?

package problems;

import java.io.IOException;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Problem_46 {

	public static void main(String[] args) throws IOException {
		System.out.println(breakGoldBochsTheory());
		
	}
	
	private static boolean solveGoldBochs(double input) {
		return primeStream(input).mapToDouble(e -> e)				
				                 .flatMap(e -> twiceASquareStream(4)
				                		 		  .limit(500)
				                                  .map(f -> e + f))
				                 .anyMatch(g -> g == input);
	}
	
	
	private static DoubleStream oddCompositeStream() {
		return DoubleStream.iterate(1, e -> e + 2)
				        .filter(Problem_46::isComposite);
	}
	
	private static boolean isComposite(double input) {
		return !isPrime(input);
	}
	
	private static boolean isPrime(double input) {
		return !IntStream.range(2, (int) Math.sqrt(input)+1)
				         .anyMatch(e -> input % e ==0);
	}
	
	private static IntStream primeStream(double input) {
		return IntStream.range(1, (int) input)
				        .filter(Problem_46::isPrime);
	}
	
	private static DoubleStream twiceASquareStream(int max) {
		return DoubleStream.iterate(1, e -> e+1)
				           .map(e -> 2*Math.pow(e, 2));
	}
	
	private static OptionalDouble breakGoldBochsTheory() {
		return oddCompositeStream().filter(e -> !solveGoldBochs(e))
				                   .findFirst();			           
	}
}
