//1,000,000 permutation of 0123456789, in order of small to largest.  I cheated on this one too


package problems;

public class Problem_24 {
	private static int counter = 0;
	public static void main(String[] args) {

		permutations("0123456789");
	}

	private static void permutations(String input) {
		permutations("",input);
	}

	private static void permutations(String prefix, String str) {
		int n = str.length();
		if (n == 0) {
			counter++;
			if (counter == 1000000) {
				System.out.println(prefix);
			}
		} else {
			for (int i = 0; i<n; i++) {
				permutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));	
			}
		}
	}
}
