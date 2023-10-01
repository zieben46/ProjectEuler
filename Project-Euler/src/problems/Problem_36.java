package problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
//
//Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
//
//(Please note that the palindromic number, in either base, may not include leading zeros.)
//
//
//After doing this, I found a method: Integer.valueOf(12, 2) will return 12 in base 2


public class Problem_36 {


public static void main(String[] args) {
		
			System.out.println(
					IntStream.range(1, 1000000)
					         .parallel()
					         .filter(Problem_36::isPalindromicBase2)
					         .filter(Problem_36::isPalindromicBase10)
					         .sum()
					);

		}
		
		private static boolean isPalindromicBase2(int number) {
			BigInteger binaryNumber = toBinary(number);
			BigInteger binaryNumberReversed = reverseBigInt(binaryNumber);
			return binaryNumber.equals(binaryNumberReversed);
		}
		
		private static boolean isPalindromicBase10(int number) {
			double numberReversed = reverseInt(number);
			return numberReversed == number;
		}
		
		private static double reverseInt(int number) {
			StringBuilder binaryNumberString = new StringBuilder(Integer.toString(number));
			binaryNumberString.reverse();
			return Double.parseDouble(binaryNumberString.toString());
		}
		
		private static BigInteger reverseBigInt(BigInteger binaryNumber) {
			StringBuilder binaryNumberString = new StringBuilder(binaryNumber.toString());
			binaryNumberString.reverse();
			return new BigInteger(binaryNumberString.toString());
		}
		
		private static BigInteger toBinary(int number) {
			ArrayList<Integer> baseTwoNumbs = toBaseTwo(number);
			return baseTwoListToBinary(baseTwoNumbs);
		}
		
		private static ArrayList<Integer> toBaseTwo(int number) {
			ArrayList<Integer> baseTwo = new ArrayList<>();
			return toBaseTwoList(baseTwo, number);
		}
		
		private static ArrayList<Integer> toBaseTwoList(ArrayList<Integer> baseTwo, int number) {
			if (number==0) {
				return baseTwo;
			} else {
				int closestTwoBase = closestTwoBase(number);
				int delta = number-closestTwoBase;
				baseTwo.add(closestTwoBase);
				return toBaseTwoList(baseTwo, delta);
			}
		}
		
		private static int closestTwoBase(int number) {
			return Stream.iterate(1, e -> e*2)
					     .filter(e -> e*2 - number > 0)
					     .findFirst()
					     .orElse(1);
		}
		
		private static BigInteger baseTwoListToBinary(ArrayList<Integer> number) {
			BigInteger binary = BigInteger.valueOf(0);
			for (int baseTwoNumb: number) {
				int exponent = baseTwoExponent(baseTwoNumb);
				BigInteger base = BigInteger.valueOf(10);
				base = base.pow(exponent);
				binary = binary.add(base);
			}
			return binary;
		}
		
		private static int baseTwoExponent(int input) {
			return baseTwoExponent(0, input);
		}
		
		private static int baseTwoExponent(int n, int input) {
			if (input == 1) {
				return n;
			} else {
				return baseTwoExponent(n+1, input/2);
			}
		}
		
}