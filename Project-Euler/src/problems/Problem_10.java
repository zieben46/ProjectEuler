//sum of primes under 2,000,000

package problems;

//10001th prime number

public class Problem_10 {
	public static void main(String[] args) {
		int i=2;
		int counter=0;
		long answer=0;
		while (true) {
			System.out.println(i);
			answer=answer+testPrime(i);
			if (i==2000000) {
				break;
			}
			i++;
		}
		System.out.println(answer);
	}

	public static int testPrime(int input) {
		for (int i=2;i<input;i++) {
			if (input%i==0) {
				return 0;
			}

		}
		return input;
	}
}