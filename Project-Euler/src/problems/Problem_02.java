package problems;

public class Problem_02 {

	
	public static void main(String[] args) {
		int answer=0;
		for (int i=1;i<=1000000;i++) {
			if (testEven(returnFib(i))>4000000) {
				System.out.println(answer);
				break;
			}
			answer=answer+testEven(returnFib(i));
		}
	}

	public static int returnFib(int input) {
		int output=1;
		if (input<=2) {
			return output;
		}
		else {
			return output=returnFib(input-1)+returnFib(input-2);
		}
	}

	public static int testEven(int i){
		return  (i%2==0 ? i:0);
	}
	
}
