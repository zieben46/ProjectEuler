//All square roots are periodic when written as continued fractions and can be written in the form:
//vN = a0 + 	
//1
//  	a1 + 	
//1
//  	  	a2 + 	
//1
//  	  	  	a3 + ...
//
//For example, let us consider v23:
//v23 = 4 + v23 — 4 = 4 +  	
//1
//	 = 4 +  	
//1
//  	
//1
//v23—4
//	  	1 +  	
//v23 – 3
//7
//
//If we continue we would get the following expansion:
//v23 = 4 + 	
//1
//  	1 + 	
//1
//  	  	3 + 	
//1
//  	  	  	1 + 	
//1
//  	  	  	  	8 + ...
//
//The process can be summarised as follows:
//a0 = 4, 	  	
//1
//v23—4
//	 =  	
//v23+4
//7
//	 = 1 +  	
//v23—3
//7
//a1 = 1, 	  	
//7
//v23—3
//	 =  	
//7(v23+3)
//14
//	 = 3 +  	
//v23—3
//2
//a2 = 3, 	  	
//2
//v23—3
//	 =  	
//2(v23+3)
//14
//	 = 1 +  	
//v23—4
//7
//a3 = 1, 	  	
//7
//v23—4
//	 =  	
//7(v23+4)
//7
//	 = 8 +  	v23—4
//a4 = 8, 	  	
//1
//v23—4
//	 =  	
//v23+4
//7
//	 = 1 +  	
//v23—3
//7
//a5 = 1, 	  	
//7
//v23—3
//	 =  	
//7(v23+3)
//14
//	 = 3 +  	
//v23—3
//2
//a6 = 3, 	  	
//2
//v23—3
//	 =  	
//2(v23+3)
//14
//	 = 1 +  	
//v23—4
//7
//a7 = 1, 	  	
//7
//v23—4
//	 =  	
//7(v23+4)
//7
//	 = 8 +  	v23—4
//
//It can be seen that the sequence is repeating. For conciseness, we use the notation v23 = [4;(1,3,1,8)], to indicate that the block (1,3,1,8) repeats indefinitely.
//
//The first ten continued fraction representations of (irrational) square roots are:
//
//v2=[1;(2)], period=1
//v3=[1;(1,2)], period=2
//v5=[2;(4)], period=1
//v6=[2;(2,4)], period=2
//v7=[2;(1,1,1,4)], period=4
//v8=[2;(1,4)], period=2
//v10=[3;(6)], period=1
//v11=[3;(3,6)], period=2
//v12= [3;(2,6)], period=2
//v13=[3;(1,1,1,1,6)], period=5
//
//Exactly four continued fractions, for N = 13, have an odd period.
//
//How many continued fractions for N = 10000 have an odd period?


package problems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Problem_64 {

	//  	v23—3          va-b     
	//	1 + -----  =  z + -----
	//	      7             c

	public static void main(String[] args) {		
		System.out.println(go());
	}


	private static long go() {
		return IntStream.range(1, 10001)
				.parallel()
				.filter(Problem_64::isNotSquare)
				.filter(Problem_64::hasOddPeriods)
				.count();
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

	private static boolean hasOddPeriods(int squareRoot) {
		Set<List<Integer>> set = fractionalRepresentation(squareRoot);
		return (set.size() % 2 != 0);
	}

	private static Set<List<Integer>> fractionalRepresentation(int squareRoot) {
		Set<List<Integer>> set = new HashSet<>();
		List<Integer> An = Ao(squareRoot);
		while (true) {
			List<Integer> AnPlusOne = AnPlusOne(An);
			if (!set.add(AnPlusOne)) {
				break;
			}
			An = AnPlusOne;		
		}
		return set;	
	}

	private static boolean isNotSquare(int input) {
		return !isInteger(Math.sqrt(input));
	}

	public static boolean isInteger(double input) {
		return Math.floor(input) == input;
	}
}
