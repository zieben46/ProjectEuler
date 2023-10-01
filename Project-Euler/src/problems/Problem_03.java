package problems;

public class Problem_03 {
	public static void main(String[] args) {
		System.out.println(breakdown(600851475143L));
	}

	public static long breakdown(long input) {
		for (long i=(input-1)/1000000;i>1;i=i-1) {
			System.out.println(i);
			if (input%i==0) {
				input=i;
				breakdown(input);
			}
		}
		return input;
	}
}