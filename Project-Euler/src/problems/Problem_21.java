//find sum of amicable numbers below 10,000

package problems;

import java.util.stream.*;

public class Problem_21 {

	public static void main(String[] args) {
		new Problem_21();
	}

	public Problem_21() {
		System.out.println(
		);
		
		int answer = 0;
		for (int i = 1; i < 10000; i ++) {
			int thePairOfi = compositeSum(i);
			
			if (isAmicable(i, thePairOfi) && i != thePairOfi) {
				System.out.println(i+" "+ thePairOfi);
				answer += i;
			}
		}
		System.out.println(answer);
	}
	
	private int compositeSum(int input) {
		return IntStream.range(1, input - 1)
				        .filter(e -> input % e == 0)
				        .sum();
	}
	
	private boolean isAmicable(int a, int b) {
		return (compositeSum(a) == b && compositeSum(b) == a);
	}
}
