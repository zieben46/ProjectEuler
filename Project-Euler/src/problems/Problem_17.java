//Number of chars from one to one thousand exluding spaces and heiphen

package problems;

import java.util.HashMap;

public class Problem_17 {

	String one = "one";
	String two = "two";
	String three = "three";
	String four = "four";
	String five = "five";
	String six = "six";
	String seven = "seven";
	String eight = "eight";
	String nine = "nine";
	String ten = "ten";
	String eleven = "eleven";
	String twelve = "twelve";
	String thirteen = "thirteen";
	String fourteen = "fourteen";
	String fifteen = "fifteen";
	String sixteen = "sixteen";
	String seventeen = "seventeen";
	String eighteen = "eighteen";
	String nineteen = "nineteen";
	String twenty = "twenty";
	String thirty = "thirty";
	String forty = "forty";
	String fifty = "fifty";
	String sixty = "sixty";
	String seventy = "seventy";
	String eighty = "eighty";
	String ninety = "ninety";
	String hundred = "hundrend";
	String and = "and";

	HashMap<Integer, String> numberMap;

	public static void main(String[] args) {
		new Problem_17();
	}

	public Problem_17() {
		int charCount = 0;
		numberMap = createNumberMap();
		
		for (int i = 1; i <=999; i++) {
			String currNumbString = createNumber(i);
			int currNumbCharCount = getCount(currNumbString);
			charCount += currNumbCharCount;
			
		}
		charCount +=11;
		System.out.println("TOTAL: "+ charCount);
	}
	
	private String createNumber(int number) {
		int hundredsInt = number / 100;
		int tensInt = (number / 10) % 10;
		int onesInt = number % 10;
		
		String hundredsString = "";
		String tensString = "";
		String onesString = "";
		
		if (hundredsInt > 0) {
		hundredsString = numberMap.get(hundredsInt)+" hundred";
		if (tensInt > 0 || onesInt > 0) {
			hundredsString+=" and";
		}
		}
	
		
		if (tensInt*10 > 10) {
			tensString = numberMap.get(tensInt*10);
			onesString = numberMap.get(onesInt);
			
		} else {
			tensString = numberMap.get(tensInt*10+onesInt);
		}
		
		return hundredsString+" "+tensString+" "+onesString;
	}
	
	
	private int getCount(String input) {
		return input.replaceAll(" ","").length();
	}

	private HashMap<Integer, String> createNumberMap() {
		HashMap<Integer, String> numberMap = new HashMap();
		numberMap.put(0, "");
		numberMap.put(1, one);
		numberMap.put(2, two);
		numberMap.put(3, three);
		numberMap.put(4, four);
		numberMap.put(5, five);
		numberMap.put(6, six);
		numberMap.put(7, seven);
		numberMap.put(8, eight);
		numberMap.put(9, nine);
		numberMap.put(10, ten);
		numberMap.put(11, eleven);
		numberMap.put(12, twelve);
		numberMap.put(13, thirteen);
		numberMap.put(14, fourteen);
		numberMap.put(15, fifteen);
		numberMap.put(16, sixteen);
		numberMap.put(17, seventeen);
		numberMap.put(18, eighteen);
		numberMap.put(19, nineteen);
		numberMap.put(20, twenty);
		numberMap.put(30, thirty);
		numberMap.put(40, forty);
		numberMap.put(50, fifty);
		numberMap.put(60, sixty);
		numberMap.put(70, seventy);
		numberMap.put(80, eighty);
		numberMap.put(90, ninety);
		numberMap.put(100, hundred);
		return numberMap;
	}
}
