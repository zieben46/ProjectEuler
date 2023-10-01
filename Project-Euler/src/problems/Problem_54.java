

//In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:
//
//    High Card: Highest value card.
//    One Pair: Two cards of the same value.
//    Two Pairs: Two different pairs.
//    Three of a Kind: Three cards of the same value.
//    Straight: All cards are consecutive values.
//    Flush: All cards of the same suit.
//    Full House: Three of a kind and a pair.
//    Four of a Kind: Four cards of the same value.
//    Straight Flush: All cards are consecutive values of same suit.
//    Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
//
//The cards are valued in the order:
//2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
//
//If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next highest cards are compared, and so on.
//
//Consider the following five hands dealt to two players:
//Hand	 	Player 1	 	Player 2	 	Winner
//1	 	5H 5C 6S 7S KD
//Pair of Fives
//	 	2C 3S 8S 8D TD
//Pair of Eights
//	 	Player 2
//2	 	5D 8C 9S JS AC
//Highest card Ace
//	 	2C 5C 7D 8S QH
//Highest card Queen
//	 	Player 1
//3	 	2D 9C AS AH AC
//Three Aces
//	 	3D 6D 7D TD QD
//Flush with Diamonds
//	 	Player 2
//4	 	4D 6S 9H QH QC
//Pair of Queens
//Highest card Nine
//	 	3D 6D 7H QD QS
//Pair of Queens
//Highest card Seven
//	 	Player 1
//5	 	2H 2D 4C 4D 4S
//Full House
//With Three Fours
//	 	3C 3D 3S 9S 9D
//Full House
//with Three Threes
//	 	Player 1
//
//The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.
//
//How many hands does Player 1 win?
//

package problems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem_54 {

	public static void main(String[] args) {
		double thenTime = System.nanoTime();
		
		List<String> allCards = getCards();
		List<List<String>> dividedCards = divideCards(allCards);
		List<String> p1 =dividedCards.get(0);
		List<String> p2 = dividedCards.get(1);
		
	
		int counter = 0;
		int totalHands = 0;
			for (int i = 0; i<p1.size(); i++) {
				totalHands++;
				
				List<Card> p1Cards = getHand(p1.get(i));
				List<Card> p2Cards = getHand(p2.get(i));
				
				double[] score = HandScorer.scoreHands(p1Cards, p2Cards);
				double p1Score = score[0];
				double p2Score = score[1];
				
				System.out.println("P1 CARDS: "+p1Cards+"   P2 CARDS:  "+p2Cards+"          P1 Score: "+p1Score+"   P2 Score:  "+p2Score);
				System.out.println("             "+handType(p1Cards)+"                                "+handType(p2Cards));
				System.out.println();
				
				if (p1Score > p2Score) {
					counter++;
				}
		}
			
			System.out.println("P1 WIN RATIO:  "+counter+"/"+totalHands);
			
	
		double nowTime = System.nanoTime();
		System.out.println("completed in "+(nowTime - thenTime)/1000000+"ms");
	}
		
	private static List<String> getCards() {
		try {
			
			Stream<String> stream = Files.lines(Paths.get("C:\\Users\\Administrator\\Desktop\\prject euler\\temp\\project54Text.txt"));
			ArrayList<String> textFile = (ArrayList<String>) stream.collect(Collectors.toList());
			
			stream.close();
			return textFile;
			
			} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static List<List<String>> divideCards(List<String> allCards) {
		List<String> p1 = new ArrayList<>();
		List<String> p2 = new ArrayList<>();
		for (String tenCards: allCards) {
			p1.add(tenCards.substring(0, 14));
			p2.add(tenCards.substring(15,29));
		}
		
		
		
		List<List<String>> dividedCards = new ArrayList<>();
		dividedCards.add(p1);
		dividedCards.add(p2);
		return dividedCards;
	}
	
	private static List<Card> getHand(String cardsString) {

		return Arrays.asList(cardsString.split(" ")).stream()
				                                    .map(e -> new Card(e.substring(0, e.length() - 1),
				                                    		            e.substring(e.length() - 1)))
				                                    .collect(Collectors.toList());		
	}
	
	public static String handType(List<Card> cards) {
		if (HandScorer.isRoyalFlush(cards)) {
			return "ROYAL FLUSH";
		}
		
		if (HandScorer.isStraightFlush(cards)) {
			return "STRAIGHT FLUSH";
		}
		
		if (HandScorer.isFourOfAKind(cards)) {
			return  "FOUR OF A KIND";
		}
		
		
		if (HandScorer.isFullHouse(cards)) {
			return  "FULL HOUSE";
		}
		
		if (HandScorer.isFlush(cards)) {
			return  "FLUSH";
		}
		
		if (HandScorer.isStraight(cards)) {
			return  "STRAIGHT";
		}
		
		if (HandScorer.isThreeOfAKind(cards)) {
			return  "THREE OF A KIND";
		}
		
		if (HandScorer.isTwoPair(cards)) {
	
			return  "TWO PAIR";
		}
		
		if (HandScorer.isOnePair(cards)) {
			return "ONE PAIR";
		}

		return  "NOTHING";
	}
}


class Card {
	private int number;
	private String suit;
	
	
	public Card(String number, String suit) {
		this.number = convertNumber(number);
		this.suit = suit;
		
	}
	
	private int convertNumber(String number) {
		switch (number) {
		case("T"):
			return 10;
		case("K"):
			return 13;
		case("Q"):
			return 12;
		case("J"):
			return 11;
		case("A"):
			return 14;
		default:
			return Integer.parseInt(number);
		}
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getSuit() {
		return suit;
	}
	
	@Override
	public String toString() {
		return convertString(number)+convertSuit(suit);
	}
	
	private String convertString(int number) {
		switch (number) {
		case(10):
			return "10";
		case(13):
			return "KING";
		case(12):
			return "QUEEN";
		case(11):
			return "JACK";
		case(14):
			return "ACE";
		default:
			return Integer.toString(number);
		}
	}
	
//	C: u2663      D: u2666      H: u2665      S: u2660
	
	private String convertSuit(String suit) {
		switch (suit) {
		case("C"):
			return "\u2663";
		case("D"):
			return "\u2666";
		case("H"):
			return "\u2665";
		case("S"):
			return "\u2660";
		}
		return "JOKER";
	}
}

class HandScorer {
	
	public static double[] scoreHands(List<Card> p1Cards, List<Card> p2Cards) {
		double p1Score = scoreHand(p1Cards);
		double p2Score = scoreHand(p2Cards);
		return new double[]{p1Score, p2Score};
		
	}


	public static double scoreHand(List<Card> cards) {
		if (isRoyalFlush(cards)) {
			return 90.0 + scoreRoyalFlush(cards);
		}
		
		if (isStraightFlush(cards)) {
			return 80.0 + scoreStraightFlush(cards);
		}
		
		if (isFourOfAKind(cards)) {
			return 70.0 + scoreFourOfAKind(cards);
		}
		
		
		if (isFullHouse(cards)) {
			return 60.0 + scoreFullHouse(cards);
		}
		
		if (isFlush(cards)) {
			return 50.0 + scoreFlush(cards);
		}
		
		if (isStraight(cards)) {
			return 40.0 + scoreStraight(cards);
		}
		
		if (isThreeOfAKind(cards)) {
			return 30.0 + scoreThreeOfAKind(cards);
		}
		
		if (isTwoPair(cards)) {
			return 20.0 + scoreTwoPairCards(cards);
		}
		
		if (isOnePair(cards)) {
			return 10.0 + scoreOnePairCards(cards);
		}

		return highestValue(cards);
	}


	
	public static boolean isRoyalFlush(List<Card> cards) {
		return allSameSuit(cards)&&isTenThroughAce(cards);		
	}
	
	public static List<Card> RoyalFlushCards(List<Card> cards) {
		return cards;	
	}
	
	public static double scoreRoyalFlush(List<Card> cards) {
		return cards.stream()
				    .mapToDouble(e -> (double) e.getNumber()/10)
				    .max()
				    .orElse(0); 
	}

	
	public static boolean isStraightFlush(List<Card> cards) {
		return allSameSuit(cards)&&isConsecutive(cards);
	}
	
	public static double scoreStraightFlush(List<Card> cards) {
		return cards.stream()
				    .mapToDouble(e -> (double) e.getNumber()/10)
				    .max()
				    .orElse(0); 
	}
	

	public static List<Card> straightFlushCards(List<Card> cards) {
		return cards;
	}

	public static boolean isFourOfAKind (List<Card> cards) {
		return collectByNumber(cards).values().stream()
			                               	  .anyMatch(e -> e.size()==4);	
	}
	
	public static List<Card> fourOfAKindCards (List<Card> cards) {
		return collectByNumber(cards).values().stream()
				.filter(e -> e.size()==4)
				.findFirst()
				.orElse(null);
	}
	
	public static double scoreFourOfAKind(List<Card> cards) {
		double score = 0;
		List<Card> fourOfAKindCards = fourOfAKindCards(cards);
		score =  (double) fourOfAKindCards.get(0).getNumber()/10;
		List<Card> remainingCards = new ArrayList<>(cards);
		remainingCards.removeAll(fourOfAKindCards);
		score = score +  (double) remainingCards.get(0).getNumber()/100;
		return score;
	}
	
	public static boolean isFullHouse(List<Card> cards) {
		Map<Integer, List<Card>> group = collectByNumber(cards);
		List<Integer> counts = group.values()
				.stream()
				.mapToInt(e -> e.size())
				.boxed()
				.collect(Collectors.toList());
		return counts.contains((Object) 2)&& counts.contains((Object) 3);
	}
	
	public static List<Card> fullHouseCards (List<Card> cards) {
		return cards;
	}
	
	public static double scoreFullHouse(List<Card> cards) {
		cards.sort((a, b) -> b.getNumber() - a.getNumber());
		return (double) cards.get(0).getNumber()/10 + (double) cards.get(4).getNumber()/100;	
	}
	
	

	public static boolean isFlush(List<Card> cards) {
		return allSameSuit(cards);
	}
	
	public static List<Card> flushCards (List<Card> cards) {
		return cards;
	}
	
	public static double scoreFlush(List<Card> cards) {
		cards.sort((a, b) -> b.getNumber() - a.getNumber());
		return (double) cards.get(0).getNumber()/10;
	}

	public static boolean isStraight(List<Card> cards) {
		return isConsecutive(cards);
	}
	
	public static List<Card> straightCards(List<Card> cards) {
		return cards;
	}
	
	public static double scoreStraight(List<Card> cards) {
		cards.sort((a, b) -> b.getNumber() - a.getNumber());
		return (double) cards.get(0).getNumber()/10;
	}
	

	public static boolean isThreeOfAKind(List<Card> cards) {
		Map<Integer, List<Card>> group = collectByNumber(cards);
		return group.values().stream()
				.anyMatch(e -> e.size() == 3);
	}
	
	public static List<Card> threeOfAKindCards(List<Card> cards) {
		Map<Integer, List<Card>> group = collectByNumber(cards);
		return group.values().stream()
				.filter(e -> e.size() == 3)
				.findFirst()
				.orElse(null);
	}
	
	public static double scoreThreeOfAKind(List<Card> cards) {
		double score = 0;
		List<Card> threeOfAKindCards = threeOfAKindCards(cards);
		score = (double) threeOfAKindCards.get(0).getNumber()/10;
		
		List<Card> remainingCards = new ArrayList<>(cards);
		remainingCards.removeAll(threeOfAKindCards);
		
		
		
		remainingCards.sort((a, b) -> b.getNumber() - a.getNumber());
		score += (double) remainingCards.get(0).getNumber()/100 +  (double) remainingCards.get(1).getNumber()/1000;
		return score;
	}

	public static boolean isTwoPair(List<Card> cards) {
		Map<Integer, List<Card>> group = collectByNumber(cards);
		return 2 == group.values().stream()
				.filter(e -> e.size() == 2)
				.count();
	}
	
	public static List<Card> twoPairCards(List<Card> cards) {
		Map<Integer, List<Card>> group = collectByNumber(cards);
		return group.values().stream()
				.filter(e -> e.size() == 2)
				.flatMap(e -> e.stream())
				.collect(Collectors.toList());
	}
	
	public static double scoreTwoPairCards(List<Card> cards) {
		double score = 0;
		List<Card> twoPairCards = twoPairCards(cards);
		score = twoPairCards.stream()
				            .mapToDouble(e -> (double) e.getNumber()/10)
				            .max()
				            .orElse(0);
		
		score += twoPairCards.stream()
	            .mapToDouble(e -> (double) e.getNumber()/100)
	            .min()
	            .orElse(0);
		
		List<Card> remainingCards = new ArrayList<>(cards);
		remainingCards.removeAll(twoPairCards);
		
		score +=  (double) remainingCards.get(0).getNumber()/1000;
		return score;
	}
	
	public static boolean isOnePair(List<Card> cards) {
		Map<Integer, List<Card>> group = collectByNumber(cards);
		return 1 == group.values().stream()
				.filter(e -> e.size() == 2)
				.count();
	}
	
	public static List<Card> onePairCards(List<Card> cards) {
		Map<Integer, List<Card>> group = collectByNumber(cards);
		return group.values().stream()
				.filter(e -> e.size() == 2)
				.findFirst()
				.orElse(null);
	}
	
	public static double scoreOnePairCards(List<Card> cards) {
		double score = 0;
		List<Card> onePairCards = onePairCards(cards);
		score = (double) onePairCards.get(0).getNumber()/10;
		
		List<Card> remainingCards = new ArrayList<>(cards);
		remainingCards.removeAll(onePairCards);
		
		remainingCards.sort((a, b) -> b.getNumber() - a.getNumber());
		score += (double) remainingCards.get(0).getNumber()/100 +(double) remainingCards.get(1).getNumber() / 1000 +(double) remainingCards.get(2).getNumber() / 10000;
		return score;
	}


	public static boolean allSameSuit(List<Card> cards) {
		return collectBySuit(cards).size()==1;
	}

	public static boolean isTenThroughAce(List<Card> cards) {
		cards.sort((a, b) -> a.getNumber() - b.getNumber());
		return cards.get(0).getNumber()==10&&
				cards.get(1).getNumber() ==11&&
				cards.get(2).getNumber() ==12&&
				cards.get(3).getNumber() ==13&&
				cards.get(4).getNumber() ==14;
	}

	public static boolean isConsecutive(List<Card> cards) {
		cards.sort((a, b) -> a.getNumber() - b.getNumber());
		Map<Boolean, List<Card>> group = cards.stream()
				.collect(Collectors.partitioningBy(e -> e.getNumber() > 6));
		if (isPartialConsecutive(group.get(false))&&isPartialConsecutive(group.get(true))&&canBeJoined(group)) {
			return true;
		}

		return false;
	}

	public static boolean canBeJoined(Map<Boolean, List<Card>> group ) {
		List<Card> groupSmall = group.get(false);
		List<Card> groupBig = group.get(true);
		if (groupSmall.size() == 0 ||groupBig.size() == 0) {
			return true;
		}
		Card smallestCardGroupSmall = groupSmall.get(0);
		Card BiggestCardGroupBig = groupBig.get(groupBig.size() - 1);
		Card biggestCardGroupSmall = groupSmall.get(groupSmall.size() - 1);
		Card smallestCardGroupBig = groupBig.get(0);
		return smallestCardGroupSmall.getNumber() == 2&& BiggestCardGroupBig.getNumber()==14||
				biggestCardGroupSmall.getNumber()+1 == smallestCardGroupBig.getNumber();
	}

	public static boolean isPartialConsecutive(List<Card> cards) {

		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getNumber() + 1 != cards.get(i +1).getNumber()) {
				return false;
			}

		}
		return true;
	}

	public static Map<String, List<Card>> collectBySuit(List<Card> cards) {
		return cards.stream()
				.collect(Collectors.groupingBy(Card::getSuit));
	}

	public static Map<Integer, List<Card>> collectByNumber(List<Card> cards) {
		return cards.stream()
				.collect(Collectors.groupingBy(Card::getNumber));
	}
	
	public static double highestValue(List<Card> cards) {
		cards.sort((a, b) -> b.getNumber() - a.getNumber());
		return (double) cards.get(0).getNumber()/10 + 
				(double) cards.get(1).getNumber()/100 + 
				(double) cards.get(2).getNumber()/1000 + 
				(double) cards.get(3).getNumber()/10000 + 
				(double) cards.get(4).getNumber()/100000;
	}
}