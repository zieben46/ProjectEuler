package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Consider the following "magic" 3-gon ring, filled with the numbers 1 to 6, and each line adding to nine.
//
//Working clockwise, and starting from the group of three with the numerically lowest external node (4,3,2 in this example), each solution can be described uniquely. For example, the above solution can be described by the set: 4,3,2; 6,2,1; 5,1,3.
//
//It is possible to complete the ring with four different totals: 9, 10, 11, and 12. There are eight solutions in total.
//Total	Solution Set
//9	4,2,3; 5,3,1; 6,1,2
//9	4,3,2; 6,2,1; 5,1,3
//10	2,3,5; 4,5,1; 6,1,3
//10	2,5,3; 6,3,1; 4,1,5
//11	1,4,6; 3,6,2; 5,2,4
//11	1,6,4; 5,4,2; 3,2,6
//12	1,5,6; 2,6,4; 3,4,5
//12	1,6,5; 3,5,4; 2,4,6
//
//By concatenating each group it is possible to form 9-digit strings; the maximum string for a 3-gon ring is 432621513.
//
//Using the numbers 1 to 10, and depending on arrangements, it is possible to form 16- and 17-digit strings. What is the maximum 16-digit string for a "magic" 5-gon ring?
//




public class Problem_68 {
	
	//10 x1 x2 == k
	//x6 x2 x3 == k
	//x7 x3 x4 == k
	//x8 x4 x5 == k
	//x9 x5 x1 == k
	
	//List<Integer> = {x1, x2, x3, x4, x5, x6, x7, x8, x9} <--- tests all permutations of 123456789

	public static void main(String[] args) {
		List<String> perms = permutations("123456789");
		System.out.println(perms.size());
		
		
		for (String perm: perms) {
		List<Integer> testPerm = Arrays.stream(perm.split(""))
		                     .map(e -> Integer.parseInt(e))
		                     .collect(Collectors.toList());
		      if (isMagical(testPerm)) {
		    	  System.out.println(testPerm+ "  "+go(testPerm));
		      }
	
		}
	}
	
	private static int one(int x1, int x2) {
		return 10+x1+x2;
	}
	
	private static int two(int x6, int x2, int x3) {
		return x6+x2+x3;
	}
	private static int three(int x7, int x3, int x4) {
		return x7+x3+x4;
	}
	private static int four(int x8, int x4, int x5) {
		return x8+x4+x5;
	}
	
	private static boolean isMagical(List<Integer> numbers) {
		return one(numbers.get(0), numbers.get(1))==two(numbers.get(5), numbers.get(1), numbers.get(2))&&
				two(numbers.get(5), numbers.get(1), numbers.get(2))==three(numbers.get(6), numbers.get(2), numbers.get(3))&&
				three(numbers.get(6), numbers.get(2), numbers.get(3))==four(numbers.get(7), numbers.get(3), numbers.get(4))&&
				four(numbers.get(8), numbers.get(4), numbers.get(0))==one(numbers.get(0), numbers.get(1));

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
	
	private static int go(List<Integer> solution) {
		return solution.subList(5, 9).stream()
				             .min((a, b) -> a - b)
				             .orElse(0);
	}
}