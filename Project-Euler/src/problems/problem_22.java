package problems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class problem_22 {
	private List<String> names = new ArrayList<>();

	public static void main(String[] args) {
		new problem_22();
	}

	public problem_22() {
		names = getNames();
		names.sort((a, b) -> a.compareTo(b));
					
		int answer = 0;
		for (String name: names) {
			answer = answer + (names.indexOf(name)+1)*
					(name.chars()
					     .map(e -> Character.toLowerCase(e))
					     .map(e -> e - 96)
					     .sum()
					);
		}
		
		System.out.println(answer);
	}
	
	
	
	
	private List<String> getNames() {
		try {
			
			Stream<String> stream = Files.lines(Paths.get("C:\\Users\\Administrator\\Desktop\\prject euler\\temp\\project22Text.txt"));
			ArrayList<String> textFile = (ArrayList<String>) stream.collect(Collectors.toList());
			return Arrays.asList(textFile.get(0).replaceAll("\"","").split(","));
			} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}