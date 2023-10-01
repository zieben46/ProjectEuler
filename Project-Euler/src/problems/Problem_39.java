//
//
//If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.
//
//{20,48,52}, {24,45,51}, {30,40,50}
//
//For which value of p = 1000, is the number of solutions maximised?

package problems;

import java.util.stream.IntStream;

public class Problem_39 {

public static void main(String[] args) {
		System.out.println(
		IntStream.range(1, 1000).map(Problem_39::getSolutions).max());
		
		int maxSolutions=0;
		int bestParameter = 0;
		
		for (int i=1; i <=1000; i++) {
			int numbSolutions = getSolutions(i);
			if (numbSolutions > maxSolutions) {
				maxSolutions = numbSolutions;
				bestParameter = i;
			}
		}
		
		System.out.println(bestParameter+"  "+maxSolutions);
		
		
	}



	private static double getHypotenuse(double a, double b) {
		return Math.sqrt(a*a+b*b);
	}
	
	private static int getSolutions(int parameter) {
		int numbSolutions=0;
		for (double a = 1; a <parameter; a++) {
			for (double b = a; b< parameter; b++) {
				double c =getHypotenuse(a, b);
				if (hypotenuseIsIntegeral(a, b)&&(a+b+c==parameter)) {
					numbSolutions++;
					
				}
			}
		}
		return numbSolutions;
	}


	private static boolean isIntegral(double c) {
		return Math.round(c) == (c/c)*c;
	}

	private static boolean hypotenuseIsIntegeral(double a, double b) {
		return isIntegral(getHypotenuse(a, b));
	}
	
}