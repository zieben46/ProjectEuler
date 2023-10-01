package problems;

import java.util.stream.IntStream;

public class Problem_01 {
	
	public static int answer;
	public static void main(String[] args) {

		answer = IntStream.range(1, 1000)
				          .filter(i -> i%3==0 || i%5==0)
				          .sum();
		System.out.println(answer);

	}
	
}




