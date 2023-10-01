//Find index of first Fibb # with 1,000 digits

package problems;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Problem_25 {
	public static void main(String[] args) {

		LinkedList<BigInteger> hello = new LinkedList<BigInteger>();
		hello.add(BigInteger.valueOf(1));
		addFib(hello);
	}

	private static void addFib(LinkedList<BigInteger> input) {
		List<BigInteger> tail = input.subList(0, input.size()-1);
		LinkedList<BigInteger> output = new LinkedList<BigInteger>();
		
		if (input.getLast().toString().length() == 1000) {
			System.out.println(input.size());
		} else {

			for (int i = 0; i < tail.size(); i++) {
				output.add(i, (input.get(i + 1)).add(tail.get(i)));
			}
			
			output.add(0, BigInteger.valueOf(1));
			output.add(0, BigInteger.valueOf(1));
			addFib(output);
		}
	}
}