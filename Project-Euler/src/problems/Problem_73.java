//
//
//Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.
//
//If we list the set of reduced proper fractions for d = 8 in ascending order of size, we get:
//
//1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
//
//It can be seen that there are 3 fractions between 1/3 and 1/2.
//
//How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d = 12,000?


package problems;

import java.util.List;

public class Problem_73 {
	private static List<Integer> primes;

	public static void main(String[] args) {

		double thenTime = System.nanoTime();

		int[] a = new int[]{1,3};
		int[] b = new int[]{1,2};

		System.out.println(go(a, b));


		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}



	private static int go(int[] a, int[] b) {
		int halfWayNum= a[0]+b[0];
		int halfWayDenom= a[1]+b[1];
		int[] halfWay = new int[]{halfWayNum, halfWayDenom};
		
		if (halfWayDenom > 12000) {
			return 0;
		}
		
		return 1 + go(a, halfWay) + go(halfWay, b);
	}
}