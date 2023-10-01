package problems;

//pythagarian theorom of a+b+c==1000

public class Problem_09 {
	public static void main(String[] args) {
		for (int a=1;a<1000;a++) {
			for (int b=1;b<1000;b++) {
				int c=getC(a,b);
				if (testPathag(a,b,c)==true) {
					System.out.println(a*b*c);
				}
			}
		}
	}

	public static int getC(int a, int b) {
		return (int) (Math.sqrt(a*a+b*b));
	}

	public static boolean testPathag (int a,int b,int c) {
		return (a*a+b*b==c*c)&&a+b+c==1000;
	}
}