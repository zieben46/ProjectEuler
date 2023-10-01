//Longest Collatz sequence under 1000000

package problems;

public class Problem_14 {
	public static int counter=1;
	public static int answer=0;

	public static void main(String[] args) {
		int x;
		int answer = 0;
		int realanswer=0;
	for (int i=1000000;i>=1;i--) {
//			System.out.println(i);
			x=i;
			while (x>1) {
				x=getValue(x);
				counter++;
			}
			//System.out.println(counter);
			if (counter>answer){
				answer=counter;
				realanswer=i;
			}
			counter=0;
		}
		System.out.println(realanswer+"IS THE ANSWER!");
		
	}


	public static int getValue(int input){
		if (input%2==0) {
			return (input/2);

		} else {
			return (3*input+1);

		}
	}
}


