package problems;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

//Consider quadratic Diophantine equations of the form:
//
//x2 – Dy2 = 1
//
//For example, when D=13, the minimal solution in x is 6492 – 13×1802 = 1.
//
//It can be assumed that there are no solutions in positive integers when D is square.
//
//By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:
//
//32 – 2×22 = 1
//22 – 3×12 = 1
//92 – 5×42 = 1
//52 – 6×22 = 1
//82 – 7×32 = 1
//
//Hence, by considering minimal solutions in x for D = 7, the largest x is obtained when D=5.
//
//Find the value of D = 1000 in minimal solutions of x for which the largest value of x is obtained.



public class Problem_66 {

	//  	v23—3          va-b     
	//	1 + -----  =  z + -----
	//	      7             c

	public static void main(String[] args) {
		
		BigInteger[] test = minimalSolution();
		System.out.println(test[0]);
		System.out.println(test[1]);
		System.out.println(test[2]);

	}

	
	private static BigInteger[] minimalSolution() {
		return IntStream.range(1, 1001)
				.filter(Problem_66::isNotSquare)
				.mapToObj(e -> DiophantineSolution(e))
				.max((a, b) -> a[0].compareTo(b[0]))
				.orElse(new BigInteger[2]);
	}
	

	private static BigInteger[] DiophantineSolution(int squareRoot) {
		return IntStream.iterate(1, e -> e + 1)
				.mapToObj(e ->seriesEstimate(squareRoot, e))
				.filter(e -> isDiophantine(e, squareRoot))
				.findFirst()
				.orElse(new BigInteger[2]);
	}
	
	private static BigInteger[] seriesEstimate(int squareRoot, int size) {
		LinkedList<Integer> series = fractionalSeries(squareRoot, size);
		BigInteger[] listSum = listSum(series);
		BigInteger startNumber = BigInteger.valueOf(startNumber(squareRoot));
		BigInteger completeNum = listSum[1].multiply(startNumber).add(listSum[0]);
		BigInteger completeDenom = listSum[1];
		return new BigInteger[]{completeNum, completeDenom, BigInteger.valueOf(squareRoot)};
	}
	
	private static LinkedList<Integer> fractionalSeries(int squareRoot, int size) {
		LinkedList<Integer> list = new LinkedList<>();
		List<Integer> An = Ao(squareRoot);

		for (int i = 1; i <= size; i++) {
			List<Integer> AnPlusOne = AnPlusOne(An);
			list.push(AnPlusOne.get(3));
			An = AnPlusOne;	
		}
		
		return list;	
	}

	private static List<Integer> Ao(int squareRoot) {
		int a = squareRoot;
		int b =(int) -Math.sqrt(a);
		int c = 1;
		return Arrays.asList(a, b, c);
	}
	
	private static List<Integer> AnPlusOne(List<Integer> An) {
		int a = An.get(0);
		int b = An.get(1);
		int c = An.get(2);

		double aSqareRoot = Math.sqrt(a);
		int z = (int) (c / (aSqareRoot + b));

		int bPrime = (int) Math.round(((-z*a - b*c + z*b*b)/c));
		int cPrime = (int) Math.round(((a - b*b)/c));
		return Arrays.asList(a, bPrime, Math.round(cPrime), z);
	}
	
	private static int startNumber(int squareRoot) {
		return (int) Math.sqrt(squareRoot);
	}
	
	private static BigInteger[] listSum(LinkedList<Integer> list) {
		BigInteger num = BigInteger.ONE;
		BigInteger denom = BigInteger.valueOf(list.pop());
		while (!list.isEmpty()) {
			num = denom.multiply(BigInteger.valueOf(list.pop())).add(num);
			BigInteger[] flipped = flip(new BigInteger[]{num, denom});
			num = flipped[0];
			denom = flipped[1];
		}
		return new BigInteger[]{num, denom};
	}

	private static boolean isNotSquare(int input) {
		return !isInteger(Math.sqrt(input));
	}

	public static boolean isInteger(double input) {
		return Math.floor(input) == input;
	}

	private static boolean isDiophantine(BigInteger[] number, int D) {
		//x*x+D*y*y==1;
		BigInteger x = number[0];
		BigInteger y = number[1];
		return x.multiply(x).subtract(BigInteger.valueOf(D).multiply(y).multiply(y)).equals(BigInteger.ONE);
	}


	private static BigInteger[] flip(BigInteger[] input) {
		BigInteger a = input[0];
		BigInteger b = input[1];
		return new BigInteger[]{b, a};
	}
}