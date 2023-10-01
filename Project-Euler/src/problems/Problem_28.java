//
//21 22 23 24 25
//20  7  8  9 10
//19  6  1  2 11
//18  5  4  3 12
//17 16 15 14 13
//
//Find sum of the 2 diagonals in a 1001 x 1001 square

package problems;

import java.util.stream.Stream;

public class Problem_28 {

	public static void main(String[] args) {
		int maxSpacing = getMaxSpacingFromBoxSize(1001);
		Stream<Integer> beginningStream = Stream.of(new Integer[]{1});
		
		System.out.println(
				joinStreams(beginningStream, maxSpacing)
				            .mapToInt(e -> e).sum());
	}

	private static Stream<Integer> createStreamSegment(int beginning, int spacing) {
		return Stream.iterate(beginning, e -> e + spacing).limit(4);
	}

	private static Stream<Integer> joinStreams(Stream<Integer> input, int spacing) {
		if (spacing == 0) {
			return input;
		} else {
			int streamStartPoint = getStreamStartPointfromSpacing(spacing);
			Stream<Integer> streamSegment = createStreamSegment(streamStartPoint, spacing);
			return joinStreams(Stream.concat(streamSegment, input), spacing-2);
		}
	}
	
	private static int getStreamStartPointfromSpacing(int spacing) {
		return spacing*spacing - spacing+1;
	}
	
	private static int getMaxSpacingFromBoxSize(int input) {
		return input-1;
	}
}