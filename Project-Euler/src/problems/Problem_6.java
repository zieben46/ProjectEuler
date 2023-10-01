package problems;

public class Problem_6 {

	public static void main(String[] args) {
		long sumOfSquare=0;
		long squareOfSum=0;
		for (int i=1;i<=100;i++) {
			sumOfSquare=(long) (sumOfSquare+Math.pow(i,2));
			squareOfSum=(long) squareOfSum+i;			
		}
		System.out.println((long) Math.pow(squareOfSum,2)-sumOfSquare);

	}
}

