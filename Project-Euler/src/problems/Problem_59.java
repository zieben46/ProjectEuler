//
//
//Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code for Information Interchange). For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
//
//A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given value, taken from a secret key. The advantage with the XOR function is that using the same encryption key on the cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.
//
//For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random bytes. The user would keep the encrypted message and the encryption key in different locations, and without both "halves", it is impossible to decrypt the message.
//
//Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key. If the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message. The balance for this method is using a sufficiently long password key for security, but short enough to be memorable.
//
//Your task has been made easy, as the encryption key consists of three lower case characters. Using cipher.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes, and the knowledge that the plain text must contain common English words, decrypt the message and find the sum of the ASCII values in the original text.
//
//

package problems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem_59 {
	private static int counter = 0;
	public static void main(String[] args) {
		List<String> rawFile= getFile();

		for (int i = 96; i<=126; i++) {
			for (int j = 96; j <= 126; j++) {
				for (int k = 96; k <= 126; k++) {
					int[] key = new int[]{i,j,k};
					List<String> processed = (process(rawFile, key));
					float numberOfThe = countThe(processed);
					if (numberOfThe > 5) {
						System.out.println(Character.toString((char)key[0])+Character.toString((char)key[1])+Character.toString((char)key[2]));
						System.out.println(processed);
						System.out.println(countASCII(processed));
						System.out.println();	
					}
					counter = 0;
				}
			}
		}
	}




	private static List<String> getFile() {
		try {
			Stream<String> stream = Files.lines(Paths.get("C:\\Users\\Administrator\\Desktop\\prject euler\\temp\\project59Text.txt"));
			ArrayList<String> textFile = (ArrayList<String>) stream.collect(Collectors.toList());
			return Arrays.asList(textFile.get(0).replaceAll("\"","").split(","));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<String> process(List<String> input, int[] key) {
		return input.stream()
				.map(e -> XORchar(e, key))
				.collect(Collectors.toList());
	}

	private static String XORchar(String input, int[] key) {
		int e = 0;
		switch (counter) {
		case 0:
			e = ((Integer.parseInt(input))^key[0]);
			
			counter++;
			break;
		case 1:

			e = ((Integer.parseInt(input))^key[1]);
			counter++;
			break;
		case 2:

			e = ((Integer.parseInt(input))^key[2]);
			counter = 0;
			break;
		}

		String f = Character.toString((char) e);
		return f;   
	}
	
	private static float countASCII(List<String> input) {
		return input.stream()
	         .mapToInt(e ->e.charAt(0))
	         .sum();
	}

	private static float countThe(List<String> input) {
		int counter = 0;

		for (int i = 0;i < input.size() - 3; i++) {
			String S1 = input.get(i);
			String S2 = input.get(i+1);
			String S3 = input.get(i+2);
			if (input.get(i).equals("t")&&input.get(i+1).equals("h")&&input.get(i+2).equals("e")) {
				counter++;
			}
		}
		return counter;
	}
}
