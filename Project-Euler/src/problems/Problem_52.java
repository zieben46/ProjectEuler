//
//
//It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.
//
//Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.

package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Problem_52 {

public static void main(String[] args) {
		double thenTime = System.nanoTime();

		System.out.println(go());

		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}

	private static String go() {
		return IntStream.iterate(1, e -> e + 1)
				        .parallel()
				        .mapToObj(Integer::toString)
				        .filter(Problem_52::passesAllTests)
				        .findFirst()
				        .orElse("NONE FOUND");	
	}

	public static boolean passesAllTests(String input) {
		int inputNumb = Integer.parseInt(input);
		String times6 = Integer.toString(inputNumb * 6);
		String times5 = Integer.toString(inputNumb * 5);
		String times4 = Integer.toString(inputNumb * 4);
		String times3 = Integer.toString(inputNumb * 3);
		String times2 = Integer.toString(inputNumb * 2);
		String times1 = Integer.toString(inputNumb * 1);
		List<String> permutations = permutations(input);

		return  permutations.contains(times6)&&
				permutations.contains(times5)&&
				permutations.contains(times4)&&
				permutations.contains(times3)&&
				permutations.contains(times2)&&
				permutations.contains(times1);
	}

	private static List<String> permutations(String number) {
		List<String> completedPermutations = new ArrayList<>();
		return permutations("", number, completedPermutations);
	}

	private static List<String> permutations(String preffix, String suffix, List<String> completedPermutations) {
		if (suffix.length() == 0) {
			completedPermutations.add(preffix);
		} else {
			for (int i = 0; i< suffix.length(); i++) {
				permutations(preffix+ suffix.charAt(i),
						suffix.substring(0, i)+ suffix.substring(i+1, suffix.length()), completedPermutations);
			}
		}
		return completedPermutations;
	}
}