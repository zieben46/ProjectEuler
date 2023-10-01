//Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.

package problems;

import java.util.stream.IntStream;

public class Problem_23 {

public static void main(String[] args) {
		new Problem_23();
	}

	public Problem_23() {		
		System.out.println(IntStream.range(1, 28124)
				                    .parallel()
				                    .filter(e ->!hasAbundantBases(e))
				                    .sum());
	}
	
	private int properDivisorSum(int input) {
		return IntStream.range(1, input/2 + 1)
				        .filter(e -> input % e == 0)
				        .sum();
	}
	
	private boolean isAbundant(int input) {
		return properDivisorSum(input) > input;
	}
	
	private boolean hasAbundantBases(int input) {
		return IntStream.range(1, input/2 + 1)
				.anyMatch(e ->isAbundant(e)&&isAbundant(input-e));
	}
	
}