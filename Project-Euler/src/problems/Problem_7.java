package problems;


//10001th prime number

public class Problem_7 {
	public static void main(String[] args) {
		int i=2;
		int counter=0;
		while (true) {
			if (testPrime(i)>0) {
				counter++;
			}
			if (counter==10001) {
				break;
			}
			i++;
		}
		System.out.println(i);
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