//find sum of parsed !100 (456 = 4+5+6)

package problems;

import java.math.BigInteger;


public class Problem_18 {

	public static void main(String[] args) {
		new Problem_18();
	}

	public Problem_18() {
		BigInteger myNumb = factorial(BigInteger.valueOf(100));
		System.out.println(myNumb);
		double sum = getSum(myNumb);
		System.out.println(sum);
	}
	
	
	private BigInteger factorial(BigInteger input) {
		if (input.intValue() == 1) {
			return BigInteger.valueOf(1);
		} else {
			return input.multiply(factorial(input.subtract(BigInteger.valueOf(1))));
		}
	}
	
	private long getSum(BigInteger bg) {
		return bg.toString().chars().map(e -> Character.getNumericValue(e)).sum();
	}
}
