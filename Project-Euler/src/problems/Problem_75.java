//
//
//
//It turns out that 12 cm is the smallest length of wire that can be bent to form an integer sided right angle triangle in exactly one way, but there are many more examples.
//
//12 cm: (3,4,5)
//24 cm: (6,8,10)
//30 cm: (5,12,13)
//36 cm: (9,12,15)
//40 cm: (8,15,17)
//48 cm: (12,16,20)
//
//In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle, and other lengths allow more than one solution to be found; for example, using 120 cm it is possible to form exactly three different integer sided right angle triangles.
//
//120 cm: (30,40,50), (20,48,52), (24,45,51)
//
//Given that L is the length of the wire, for how many values of L = 1,500,000 can exactly one integer sided right angle triangle be formed?
//

package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_75 {
	private static List<Integer> primes;
	private static List<Integer> parameters = new ArrayList<>();

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		
		primes = primesList();
		int counter = 0;
		createParamList();		
		for (int i = 2; i <= 1500000; i=i+2) {
			if (go(i)==1) {
				counter++;
			}
		}
		System.out.println(counter);

		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}


	private static void createParamList() {
		for (int m = 2; m < 1000; m++) {
			for (int n = 1; n < m; n++) {	
				if (HCFisOne(m, n)&&(m-n) % 2 != 0) {
				int a = m*m - n*n;
				int b = 2*m*n;
				int c = m*m+ n*n;
				parameters.add(a+b+c);
				}
			}
		}
		
		parameters.sort((a, b) -> a - b);
	}
	
	private static int go(int number) {
		int counter = 0;
		for (int i = 0; i <parameters.size(); i++ ) {
			if (number % parameters.get(i) == 0&&parameters.get(i)<=1500000) {
				//System.out.println(parameters.get(i));
				counter++;
			}
			
			if (parameters.get(i)> number || counter > 1) {
				return counter;
			}
		}	
		return counter;
	}

	private static ArrayList<Integer> primeFactors(int number) {
		return primeFactors(new ArrayList<Integer>(), number);
	}

	private static boolean HCFisOne(int a, int b) {
		ArrayList<Integer> numbFactors = primeFactors(a);
		ArrayList<Integer> denomFactors = primeFactors(b);
		return !numbFactors.stream()
				.anyMatch(e -> denomFactors.contains(e));
	}

	private static ArrayList<Integer> primeFactors(ArrayList<Integer> primeFactors, int number) {
		if (number == 1) {
			return primeFactors;
		}
		int newPrime=primeDividor(number);
		primeFactors.add(newPrime);
		return primeFactors(primeFactors, number/newPrime);
	}

	private static int primeDividor(int input) {
		return primes.stream()
				.filter(e -> input % e == 0)
				.findFirst()
				.orElse(input);
	}

	private static boolean isPrime(int input) {
		return !IntStream.range(2,(int) Math.sqrt(input)+1)
				.anyMatch(e -> input % e ==0);
	}

	private static List<Integer> primesList() {
		return IntStream.range(2, 1225)
				.filter(Problem_75::isPrime)
				.boxed()
				.collect(Collectors.toList());
	}
}