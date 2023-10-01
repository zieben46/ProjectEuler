//Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n=0.
//
//n2+an+b, where |a|<1000 and |b|=1000

package problems;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem_27 {

	public static void main(String[] args){
		int nAnswer = 0;
		int aAnswer = 0;
		int bAnswer = 0;

		for (int a = -999; a < 1000; a++) {
			for (int b = -1000; b <= 1000; b++) {
				int highestN = highestNforGivenCoefficients(a, b).get();
				if (highestN >= nAnswer) {
					nAnswer = highestN;
					aAnswer = a;
					bAnswer = b;
				}
			}
		}
		System.out.println(aAnswer+" THIS IS A");
		System.out.println(bAnswer+" THIS IS B");
		System.out.println(nAnswer+" HIGHEST N");
		System.out.println(aAnswer*bAnswer+" THIS IS COEFFICIENT");
	}	

	private static boolean isPrime(int input) {
		if (input < 0) {
			return false;
		} else {
			return !IntStream.range(2, (int) Math.sqrt(input) + 1)
					.anyMatch(e -> input % e == 0);
		}
	}

	private static int magicalFormula(int input, int a, int b) {
		return (int) Math.pow(input, 2) + input*a + b;
	}

	private static Optional<Integer> highestNforGivenCoefficients(int a, int b) {
		return Stream.iterate(0, n -> n + 1)
				.filter(n -> !mappedNisPrime(n, a, b))
				.findFirst();
	}


	private static boolean mappedNisPrime(int n, int a, int b) {
		return isPrime(magicalFormula(n, a, b));
	}
}