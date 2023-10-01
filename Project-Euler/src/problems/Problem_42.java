//
//
//The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
//
//1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
//
//By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.
//
//Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?
//

package problems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem_42 {

	public static void main(String[] args) throws IOException {
		List<String> words = getWords();
		
		System.out.println(
		words.stream()
		     .filter(Problem_42::isTriangleWord)
		     .count());
	}


	private static int wordScore(String word) {
		return word.toLowerCase()
				   .chars()
				   .map(e -> e-96)
				   .sum();
	}

	private static boolean isTriangleWord(String word) {
		int wordScore = wordScore(word);
		return IntStream.range(1, 20)
				.map(e -> (e*(e+1)/2)).anyMatch(e -> e == wordScore);
	}
	

	private static List<String> getWords() {
		try {
			
			Stream<String> stream = Files.lines(Paths.get("C:\\Users\\Administrator\\Desktop\\prject euler\\temp\\project42Text.txt"));
			ArrayList<String> textFile = (ArrayList<String>) stream.collect(Collectors.toList());
			stream.close();
			return Arrays.asList(textFile.get(0).replaceAll("\"","").split(","));
			
			} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}