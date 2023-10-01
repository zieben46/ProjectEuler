//
//
//Let p(n) represent the number of different ways in which n coins can be separated into piles. For example, five coins can be separated into piles in exactly seven different ways, so p(5)=7.
//OOOOO
//OOOO   O
//OOO   OO
//OOO   O   O
//OO   OO   O
//OO   O   O   O
//O   O   O   O   O
//
//Find the least value of n for which p(n) is divisible by one million.
//
//
//Uses partician function:
//p(n)=p(n-1)+p(n-2)-p(n-5)-p(n-7)+p(n-12)+p(n-15)-p(n-22)-... (pentagonal #s: gk = k(3k-1)/2 for k = 1, -1, 2, -2, 3,)
//
//https://en.wikipedia.org/wiki/Partition_(number_theory)

package problems;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Problem_78 {
	private static int[] pentaNumbs;
	private static BigInteger[] memoizedParticians = new BigInteger[100001];
	
	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		pentaNumbs = createPentaList(100000);
		
		System.out.println(Stream.iterate(2, n -> n + 1)
		.filter(n -> (partician(n)).mod(BigInteger.valueOf(1000000)).equals(BigInteger.ZERO))
		.findFirst()
		.orElse(0)
		);

		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
	
	private static int mthGenPenta(int m) {
		int correctedM = correctedM(m);
		return (correctedM*(3*correctedM-1)) / 2;
	}
	
	private static int correctedM(int m) {
		int delta = (int) Math.ceil(m /2.0);
		int sign = m % 2 == 0 ? -1 : 1;
		return sign * delta;			
	}
	
	private static BigInteger partician(int n) {
		if (n == 0) {
			return BigInteger.ONE;
		}
		if (memoizedParticians[n]!= null) {
			return memoizedParticians[n];
		}

		int numbPartTerms = numbPartTerms(n);
		BigInteger numbParticians = BigInteger.ZERO;
		for (int i = 1; i <= numbPartTerms; i++) {
			BigInteger sign = coefficSign(i);
			numbParticians = numbParticians.add(sign.multiply(partician(n - pentaNumbs[i])));
		}
		
		memoizedParticians[n] = numbParticians;
		return memoizedParticians[n];
	}
	
	private static BigInteger coefficSign(int coefficPos) {
		return (int) Math.ceil(coefficPos / 2.0) % 2 == 0 ? BigInteger.ONE.negate(): BigInteger.ONE;
	}
	
	private static int numbPartTerms(int n) {
		return Stream.iterate(1, e -> e + 1)
                .filter(e -> n - pentaNumbs[e] < 0)
                .findFirst().orElse(1) - 1;
	}
	
	private static int[] createPentaList(int size) {
		int[] list = new int[size];
		for (int i = 1; i < size; i++) {
			list[i] = mthGenPenta(i);
		}
		
		return list;
	}
}