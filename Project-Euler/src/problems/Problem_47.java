//
//
//The first two consecutive numbers to have two distinct prime factors are:
//
//14 = 2 × 7
//15 = 3 × 5
//
//The first three consecutive numbers to have three distinct prime factors are:
//
//644 = 2² × 7 × 23
//645 = 3 × 5 × 43
//646 = 2 × 17 × 19.
//
//Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?

package problems;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Problem_47 {

public static void main(String[] args) {
		start();
	}
	
	
	private static void start() {
		ArrayList<Integer> firstNumb = primeFactors(1);
		ArrayList<Integer> secondNumb = primeFactors(2);
		ArrayList<Integer> thirdNumb = primeFactors(3);
		ArrayList<Integer> fourthNumb = primeFactors(4);
		ArrayList<ArrayList<Integer>> fourNumbs = new ArrayList<>();
		
		fourNumbs.add(firstNumb);
		fourNumbs.add(secondNumb);
		fourNumbs.add(thirdNumb);
		fourNumbs.add(fourthNumb);
		int i = 5;
		while (true) {
			
			fourNumbs = dedupArrays(fourNumbs);
			
			if (!hasDuplicates(fourNumbs)&&hasNDistinct(fourNumbs, 4)) {
				int answer = 1;
				for (int numb: fourNumbs.get(0)) {
					answer = answer*numb;
				}
				System.out.println(answer);
				break;
			} else {
				fourNumbs.remove(0);
				fourNumbs.add(primeFactors(i));
				i++;
				
			}
		}
	}


	private static ArrayList<ArrayList<Integer>> dedupArrays(ArrayList<ArrayList<Integer>> input) {
		ArrayList<ArrayList<Integer>> output = new ArrayList<>();
		for (ArrayList<Integer> list: input) {
			output.add(dedupArray(list));
			//list = dedupArray(list);
		}
		return output;
	}

	private static ArrayList<Integer> dedupArray(ArrayList<Integer> input) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		input.sort((a, b) -> b - a);
		for (int i = 0; i<input.size(); i++) {
			int currentNumber = input.get(i);
			int lastIndexofCurrentNumber = input.lastIndexOf((Object) currentNumber);
			if (lastIndexofCurrentNumber > i) {
				output.add((int) Math.pow(currentNumber, lastIndexofCurrentNumber-i+1));
				i = lastIndexofCurrentNumber;
			} else {
				output.add(currentNumber);
			}
		}
		return output;
	}
	
	private static boolean hasNDistinct(ArrayList<ArrayList<Integer>> input, int distinct) {
		for (ArrayList<Integer> list: input) {
			if (list.size()!=distinct) {
				return false;
			}
		}	
	return true;	
	}


	private static boolean hasDuplicates(ArrayList<ArrayList<Integer>> input) {
		ArrayList<ArrayList<Integer>> segment= new ArrayList<>();
		boolean duplicates = false;
		for(int i=0; i < input.size()-1;i++) {
			segment = (ArrayList<ArrayList<Integer>>) input.clone();
			segment.remove(input.get(i));
			for (Integer integ: input.get(i)) {
				for(ArrayList<Integer> seg: segment) {
					if (seg.contains(integ)) {
						duplicates= true;
					}
				}
			}
		}
		return duplicates;
	}


	private static ArrayList<Integer> primeFactors(int number) {
		ArrayList<Integer> numberAsArray = new ArrayList<>();
		numberAsArray.add(number);
		return primeFactors(new ArrayList<Integer>(), numberAsArray);
	}

	private static ArrayList<Integer> primeFactors(ArrayList<Integer> primeFactors, ArrayList<Integer> composites) {
		if (composites.size()==0) {
			return primeFactors;
		} else {
			ArrayList<Integer> newComposites = new ArrayList<>();
			for (int number: composites ) {
				if (isPrime(number)) {
					primeFactors.add(number);
				} else {
					ArrayList<Integer> newNumbers = breakDownNumber(number);
					newComposites.addAll(newNumbers);
				}
			}
			return primeFactors(primeFactors, newComposites);
		}
	}

	private static boolean isPrime(int input) {
		return !IntStream.range(2,(int) Math.sqrt(input)+1)
				.anyMatch(e -> input % e ==0);
	}

	private static ArrayList<Integer> breakDownNumber(int input) {
		int firstElement = IntStream.iterate(input/2+1,e -> e-1)
				                    .filter(e -> input % e == 0)
		                    		.findFirst()
			                    	.orElse(1);
		int secondElement = input/firstElement;
		ArrayList<Integer> output = new ArrayList<>();
		output.add(firstElement);
		output.add(secondElement);
		return output;
	}
	
	
}