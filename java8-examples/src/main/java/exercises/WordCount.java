package exercises;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class WordCount {

	private final static class MapWordCount implements Function<String, Integer> {
		public Integer apply(String values) {
			return values.length();
		}
	}

	private final static class ReduceCount implements BinaryOperator<Integer> {

		public Integer apply(Integer t, Integer u) {
			return t + u;
		}

	}

	public static Integer WordCountsMapReduce(List<String> values) {
		MapWordCount mapCount = new MapWordCount();
		ReduceCount reduceAccum = new ReduceCount();
		return values.stream().map(mapCount).reduce(reduceAccum).get();
	}

}
