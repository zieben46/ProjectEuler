package problems;

//finds the lowest integer evenly divisable by numbers 1-20 

public class Problem_5_test {
	public static void main(String[] args) {
		int test=21;
		int i=2;
		while (true) {
			if (isDiv(test,i)==true) {
				i++;
			} else {
				test=test+1;
				i=2;
			}
			if (i==20) {
				System.out.println(test);
				break;
			}
		}
	}

	public static boolean isDiv(int test, int divisor) {
		return (test%divisor==0);
	}
}