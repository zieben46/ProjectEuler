package problems;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//I cheated
//
//Euler's Totient function, f(n) [sometimes called the phi function], is used to determine the number of numbers less than n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, f(9)=6.
//n 	Relatively Prime 	f(n) 	n/f(n)
//2 	1 	1 	2
//3 	1,2 	2 	1.5
//4 	1,3 	2 	2
//5 	1,2,3,4 	4 	1.25
//6 	1,5 	2 	3
//7 	1,2,3,4,5,6 	6 	1.1666...
//8 	1,3,5,7 	4 	2
//9 	1,2,4,5,7,8 	6 	1.5
//10 	1,3,7,9 	4 	2.5
//
//It can be seen that n=6 produces a maximum n/f(n) for n = 10.
//
//Find the value of n = 1,000,000 for which n/f(n) is a maximum.

public class Problem_69 {
	
		private static List<Integer> primes;

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		int result = 1;
		primes = primeList();
		int i = 0;
		int limit = 1000000;
		 
		while(result* primes.get(i) < limit){
		    result *= primes.get(i);
		    i++;
		}
		
		System.out.println(result);
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}


	
	private static List<Integer> primeList() {
		return IntStream.range(2, 100)
				        .filter(Problem_69::isPrime)
				        .boxed()
				        .collect(Collectors.toList());
	}

	private static boolean isPrime(int input) {
		return !IntStream.range(2,(int) Math.sqrt(input)+1)
				.anyMatch(e -> input % e ==0);
	}
	}
