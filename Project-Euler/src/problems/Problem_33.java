//30/50 = 3/5 by crossing out the 0's.  Find all such fractions following this patternthat contain 2 digits in numerator 
//and denomenator, excluding those that use 0's to cancel.

package problems;

public class Problem_33 {

public static void main(String[] args) {
        double answer = 1;
		for (int numerator = 10; numerator < 99; numerator++) {
			for (int denomenator = numerator+1; denomenator < 99; denomenator++) {
				if (isEasyReduction(numerator, denomenator)) {
					System.out.println("EASY FRACTION:  "+numerator+"/"+denomenator);
					answer = answer * numerator/denomenator;
				}
			}
		}
		System.out.println();
		System.out.println("REDUCED DENOMENATOR : "+ 1/answer);
	}

	private static boolean isEasyReduction(int numerator, int denomenator) {
		int commonValue = commonValue(numerator, denomenator);
		if (commonValue > 0) {
			int[] newNumb = numberWithoutCommonValue(numerator, denomenator, commonValue);
			int newNumbsNumerator = newNumb[0];
			int newNumbsDenomenator = newNumb[1];
			return isSameNumber(numerator, denomenator, newNumbsNumerator, newNumbsDenomenator);
		} else {
			return false;
		}
	}

	private static int commonValue(int numerator, int denomenator) {
		String[] numeratorArray = Integer.toString(numerator).split("");

		for (String digit: numeratorArray) {
			if (Integer.toString(denomenator).contains(digit)) {
				return Integer.parseInt(digit);
			}
		}
		return 0;
	}

	private static int[] numberWithoutCommonValue(int numerator, int denomenator, int commonValue ) {
		String commonValueStr = Integer.toString(commonValue);
		String numeratorWithoutCommonValue = Integer.toString(numerator).replace(commonValueStr, "");
		String denomenatorWithoutCommonValue = Integer.toString(denomenator).replace(commonValueStr, "");
		try {
			return new int[]{Integer.parseInt(numeratorWithoutCommonValue), Integer.parseInt(denomenatorWithoutCommonValue)};
		} catch (NumberFormatException e) {
			return new int[]{1, 1};
		}
	}

	private static boolean isSameNumber(int num1, int denom1, int num2, int denom2) {
		if (denom2 > 0) {
			return (float) denom1*num2/denom2 == num1;
		} else {
			return false;
		}
	}
	
}