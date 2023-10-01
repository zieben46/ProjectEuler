//Half way cheated.
//
//The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in any order the result will always be prime. For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.
//
//Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.


package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_60 {

private static HashSet<Integer> allSmallPrimes;
	static HashMap<Integer, ArrayList<Integer>> matched = new HashMap<>();

	public static void main(String[] args) {
		long thenTime = System.nanoTime();
		
		allSmallPrimes = getSmallPrimes();		
		initializeMap();
		mapConcatableSolutions();
		testGroups();

		long nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}

	private static void mapConcatableSolutions() {
		for (Integer key: matched.keySet()) {
			for (int prime : allSmallPrimes) {
				if (isConcatable(key, prime)) {
					ArrayList<Integer> value = matched.get(key);
					value.add(prime);
					matched.put(key, value);
				}
			}
		}
	}
	
	private static boolean isConcatable(int a, int b) {
		try {
			String aString =  Integer.toString(a);
			String bString = Integer.toString(b);
			String abString = aString+bString;
			String baString = bString+aString;
			if (isBigPrime(abString)&&isBigPrime(baString)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private static boolean isBigPrime(String input) {
		Integer bigPrime = Integer.parseInt(input);
		return !IntStream.range(2, (int) Math.sqrt(bigPrime) + 1)
					.anyMatch(e -> bigPrime % e == 0);
	}

	private static boolean isPrime(int input) {
		return !IntStream.range(2, (int) Math.sqrt(input) + 1)
				.anyMatch(e -> input % e == 0);
	}

	private static HashSet<Integer> getSmallPrimes() {
		return IntStream.range(3, 10000)
				.parallel()
				.filter(Problem_60::isPrime)
				.boxed()
				.collect(Collectors.toCollection(HashSet::new));
	}
	
	private static void initializeMap() {
		for (int prime: allSmallPrimes) {
			ArrayList<Integer> values = new ArrayList<Integer>();
			matched.put(prime, values);
		}
	}

	private static void testGroups() {	
		for (int key: matched.keySet()) {
			int size = matched.get(key).size();
			if (size>=4) {
				for (int a=0; a<size-3; a++) {
					for (int b=a+1; b<size-2; b++) {
						for (int c=b+1; c<size-1; c++) {
							for (int d=c+1; d<size; d++) {
								
								int value1 = matched.get(key).get(a);
								int value2 = matched.get(key).get(b);
								int value3 = matched.get(key).get(c);
								int value4 = matched.get(key).get(d);
								int value5 = key;

								if (value4<key&&value3<key&&value2<key&&value1<key) {

									ArrayList<Integer> matchedKeyValues1 = matched.get(value1);
									ArrayList<Integer> matchedKeyValues2 = matched.get(value2);
									ArrayList<Integer> matchedKeyValues3 = matched.get(value3);
									ArrayList<Integer> matchedKeyValues4 = matched.get(value4);

									if (matchedKeyValues1.contains(value2)&&
											matchedKeyValues1.contains(value3)&&
											matchedKeyValues1.contains(value4)&&
											matchedKeyValues1.contains(value5)&&

											matchedKeyValues2.contains(value1)&&
											matchedKeyValues2.contains(value3)&&
											matchedKeyValues2.contains(value4)&&
											matchedKeyValues2.contains(value5)&&

											matchedKeyValues3.contains(value1)&&
											matchedKeyValues3.contains(value2)&&
											matchedKeyValues3.contains(value4)&&
											matchedKeyValues3.contains(value5)&&

											matchedKeyValues4.contains(value1)&&
											matchedKeyValues4.contains(value2)&&
											matchedKeyValues4.contains(value3)&&
											matchedKeyValues4.contains(value5)) {

										System.out.println(value1+" "+value2+" "+value3+" "+value4+" "+value5);
										System.out.println(value1+value2+value3+value4+value5);
									}
								}
							}
						}
					}
				}	
			}
		}
	}
}