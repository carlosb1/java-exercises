package exercises;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class SumAllTest {

	@Test
	public void testSumAllMapReduce() {
		List<String> values = Arrays.asList("hello", "world");
		Assert.assertTrue(WordCount.WordCountsMapReduce(values) == 10);
	}

}
