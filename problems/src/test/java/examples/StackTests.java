package examples;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class StackTests {
	private static final Stack<Integer> CORRECT_MAX_VALUES_RESULT = new Stack<Integer>() {
		{
			{
				push(56);
				push(34);
				push(29);
				push(20);
				push(18);
				push(12);
				push(10);
				push(6);
				push(4);
				push(3);
				push(1);
			}
		}
	};

	@Test
	public void testSort() {
		Stack<Integer> values = new Stack<Integer>();
		values.push(3);
		values.push(1);
		values.push(4);
		values.push(6);
		values.push(10);
		values.push(20);
		values.push(12);
		values.push(34);
		values.push(56);
		values.push(18);
		values.push(29);
		Stacks.Sort(values);
		assertEquals(values, CORRECT_MAX_VALUES_RESULT);

	}

}
