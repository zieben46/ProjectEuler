//
//
//The square root of 2 can be written as an infinite continued fraction.
//v2 = 1 + 	
//1
//  	2 + 	
//1
//  	  	2 + 	
//1
//  	  	  	2 + 	
//1
//  	  	  	  	2 + ...
//
//The infinite continued fraction can be written, v2 = [1;(2)], (2) indicates that 2 repeats ad infinitum. In a similar way, v23 = [4;(1,3,1,8)].
//
//It turns out that the sequence of partial values of continued fractions for square roots provide the best rational approximations. Let us consider the convergents for v2.
//1 + 	
//1
//	= 3/2
//  	
//2
//	 
//1 + 	
//1
//	= 7/5
//  	2 + 	
//1
//  	  	
//2
//	 
//1 + 	
//1
//	= 17/12
//  	2 + 	
//1
//	 
//  	  	2 + 	
//1
//	 
//  	  	  	
//2
//	 
//1 + 	
//1
//	= 41/29
//  	2 + 	
//1
//  	  	2 + 	
//1
//	 
//  	  	  	2 + 	
//1
//	 
//  	  	  	  	
//2
//	 
//
//Hence the sequence of the first ten convergents for v2 are:
//1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, ...
//
//What is most surprising is that the important mathematical constant,
//e = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...].
//
//The first ten terms in the sequence of convergents for e are:
//2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...
//
//The sum of digits in the numerator of the 10th convergent is 1+4+5+7=17.
//
//Find the sum of digits in the numerator of the 100th convergent of the continued fraction for e.

package problems;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Problem_65 {



	public static void main(String[] args) {
		double thenTime = System.nanoTime();



		//go();
		
		
		BigInteger num100=sqrtTwoEstimate(100)[0];
		
		System.out.println(addDigits(num100));

		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
	
	private static int addDigits(BigInteger input) {
		return Arrays.stream(input.toString().split(""))
				     .mapToInt(Integer::valueOf)
				     .sum();
		
	}


	private static BigInteger[] sqrtTwoEstimate(int position) {
		BigInteger num = BigInteger.ONE;
		BigInteger denom = getBaseNumber(position);		
		return sqrtTwoEstimate(new BigInteger[]{num, denom}, position);
	}

	private static BigInteger[] sqrtTwoEstimate(BigInteger[] input, int position) {
		BigInteger num = input[0];
		BigInteger denom = input[1];
		
		if (position == 2) {
			BigInteger numComplete = a0(num, denom);
			BigInteger denomComplete = b0(num, denom);
			return new BigInteger[]{numComplete, denomComplete};
		} else {
			if ((position - 1) % 3 == 0) {
			BigInteger specialNumber =  specialNumber(position-1);
			BigInteger numPrime = aPrime(num, denom, specialNumber);
			BigInteger denomPrime =  bPrime(num, denom, specialNumber);
			return sqrtTwoEstimate(new BigInteger[]{denomPrime, numPrime}, position-1);
			} else {
				BigInteger numPrime = a1Prime(num, denom);
				BigInteger denomPrime =  b1Prime(num, denom);
				return sqrtTwoEstimate(new BigInteger[]{denomPrime, numPrime}, position - 1);	
			}
		}
	}

	private static BigInteger a1Prime(BigInteger x, BigInteger y) {
		return x.multiply(BigInteger.valueOf(1)).add(y);
	}
	
	
	private static BigInteger b1Prime(BigInteger x, BigInteger y) {
		return y;
	}
		
	private static BigInteger aPrime(BigInteger x, BigInteger y, BigInteger specialNumber) {
		return y.multiply(specialNumber).add(x);
	}

	private static BigInteger bPrime(BigInteger x, BigInteger y, BigInteger position) {
		return y;
	}
	
	private static BigInteger a0(BigInteger x, BigInteger y) {
		return y.multiply(BigInteger.valueOf(2)).add(x);
	}

	private static BigInteger b0(BigInteger x, BigInteger y) {
		return y;
	}

	private static BigInteger getBaseNumber(int input) {
		if ((input) % 3 == 0) {
			return specialNumber(input);
		} else {
			return BigInteger.ONE;
		}
	}
	
	private static BigInteger specialNumber(int input) {
		return BigInteger.valueOf((input+1)*2/3);
	}
}