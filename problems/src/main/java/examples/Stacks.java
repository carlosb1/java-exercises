package examples;

import java.util.Stack;

public class Stacks {
	public static class MyQueue {
		private Stack<Integer> mainStack, auxStack;

		public MyQueue() {
			mainStack = new Stack<Integer>();
			auxStack = new Stack<Integer>();
		}

		public void enqueue(Integer item) {
			mainStack.push(item);
		}

		public Integer dequeue() {
			int size = mainStack.size();
			for (int i = 0; i < size - 1; i++) {
				auxStack.push(mainStack.pop());
			}
			Integer value = mainStack.pop();
			int auxSize = auxStack.size();
			for (int i = 0; i < auxSize; i++) {
				mainStack.push(auxStack.pop());
			}
			return value;
		}
	}

	public static void Sort(Stack<Integer> values) {
		final Stack<Integer> auxStack = new Stack<Integer>();
		final int size = values.size();

		for (int indexValues = 0; indexValues < size; indexValues++) {
			Integer maxValue = -1;
			for (int i = 0; i < size - indexValues; i++) {
				Integer value = values.pop();
				if (maxValue < value) {
					if (maxValue != -1) {
						auxStack.push(maxValue);
					}
					maxValue = value;
				} else {
					auxStack.push(value);
				}
			}
			/* we have the maxim value */
			values.push(maxValue);
			/* refill values */
			int auxSize = auxStack.size();
			for (int i = 0; i < auxSize; i++) {
				values.push(auxStack.pop());
			}

		}

	}

}
