package problems;

public class problem_1 {
	
	public static int answer;
	public static void main(String[] args) {
	
		
		for (int i=1;i<1000;i++) {
			answer=answer+testIt(i);	
		}
		System.out.println(answer);
	}
	
	public static int testIt(int i) {
		return (i%3==0 || i%5==0 ? i:0);
	}

}
