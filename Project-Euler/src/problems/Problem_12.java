//triangle number with over 500 divisors

package problems;

public class Problem_12 {

	public static void main(String[] args) {
		long divisorCounter=0;
		long i=12343;
		while (divisorCounter<501) {
			i++;

			divisorCounter=divisorCount(nthTri(i));
			System.out.println("Ith iteration: "+i);
			System.out.println("Ith Triangle " +nthTri(i));
			System.out.println("Ith divisor counter : "+divisorCounter);
		}
		
		System.out.println("Answer is: "+nthTri(i) );
	}
	
	//Triangle Computer
	public static long nthTri(long input) {
		if (input==0) {
			return input;
		} else {
			input=input-1;
			return input+1+nthTri(input);
		}
	}
	
	//Divisor Counter
	public static long divisorCount(long input) {
		int counter=0;
		for (long i=input;i>=1;i--) {
			if (input%i==0) {
				counter++;
			}
		}
		return counter;
	}
}



