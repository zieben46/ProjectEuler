//
//The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
//
//There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
//
//How many circular primes are there below one million?

package problems;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Problem_35 {

public static void main(String[] args) {
			
    System.out.println(
        IntStream.range(2, 1000000)
	         .parallel()
	         .filter(Problem_35::isCircularPrime)
	         .count()
	);
}
		
private  static boolean isCircularPrime(int input) {
	int[] combinations = getCircularCombinations(input);
	return Arrays.stream(combinations)
			     .allMatch(Problem_35::isPrime);
}
		
private static int[] getCircularCombinations(int input) {
	String inputStr = Integer.toString(input);
	int inputLength = inputStr.length();
	int[] combinations = new int[inputLength];
			
	for (int i = 0; i < inputStr.length(); i++) {
		combinations[i] = Integer.parseInt(inputStr.substring(i, inputLength)+inputStr.substring(0, i));
	}
	return combinations;			
}
			
private static boolean isPrime(int input) {
	return !IntStream.range(2, (int) Math.sqrt(input)+1)
			 .anyMatch(e -> input % e == 0);
}

}