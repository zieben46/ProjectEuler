package problems;

import java.util.stream.IntStream;

public class Problem_04 {
	
	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		
		Integer answer = IntStream.range(1, 1000)
                                  .boxed()
                                  .flatMap(x -> IntStream.range(0, x)
                                                         .mapToObj(y -> x*y)
                                                         .filter(numb -> is_Palindrome(numb+"")))
                                  .max(Integer::compareTo)
                                  .orElse(-1);
	
		double nowTime = System.nanoTime();
		
		System.out.println(answer);
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");

	}
		
    private static boolean is_Palindrome(String s) {
    	return new StringBuilder(s).reverse().toString().equals(s);
    }
}
