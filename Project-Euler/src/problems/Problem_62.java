//
//
//The cube, 41063625 (3453), can be permuted to produce two other cubes: 56623104 (3843) and 66430125 (4053). In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.
//
//Find the smallest cube for which exactly five permutations of its digits are cube.


package problems;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_62 {
	
	
	private static List<BigInteger> bigIntegers;
	
	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		bigIntegers = createBigIntegers();
		
		System.out.println(go());
		
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
		
	private static List<BigInteger> go() {
		
		return bigIntegers.stream()
				           .parallel()
				          .map(wow -> bigIntegers.stream()
				                  .filter(wow2 -> arePermutations(wow, wow2))
				                  .collect(Collectors.toList()))
				          .filter(e -> e.size()==5)
				          .findFirst()
				          .orElse(null);
	}
	
	private static boolean arePermutations(BigInteger s1, BigInteger s2) {
		char[] s1List = s1.toString().toCharArray();
		char[] s2List = s2.toString().toCharArray();
		Arrays.sort(s1List);
		Arrays.sort(s2List);
		return Arrays.toString(s1List).equals((Arrays.toString(s2List)));
	}
	
	private static List<BigInteger> createBigIntegers() {
		return IntStream.range(1, 10000)
				        .mapToObj(e -> BigInteger.valueOf(e)
				        		           .multiply(BigInteger.valueOf(e)
				        		    		   .multiply(BigInteger.valueOf(e))))
				        .collect(Collectors.toList());
	}
}